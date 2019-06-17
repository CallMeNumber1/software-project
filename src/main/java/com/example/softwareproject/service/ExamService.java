package com.example.softwareproject.service;


import com.example.softwareproject.component.TimeUtils;
import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.entity.ExamDetail;
import com.example.softwareproject.entity.User;
import com.example.softwareproject.repository.ExamDetailRepository;
import com.example.softwareproject.repository.ExamRepository;
import com.example.softwareproject.repository.UserRepository;
import com.example.softwareproject.util.Invigilation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @program: software-project
 * @description: 考试业务逻辑处理
 * @author: chong, zhanyeye
 * @create: 2019-06-09 10:38
 */

@Service
@Slf4j
@Transactional
public class ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExamDetailRepository examDetailRepository;
    @Autowired
    private TimeUtils timeUtils;

    public List<Exam> listExams() {
        return examRepository.findAll();
    }

    public Exam addExam(Exam newExam) {
        List<Exam> exams = examRepository.listByLoc(newExam.getLocation());
        for (Exam e : exams) {
            if (timeUtils.isTimeConflict(e, newExam)) {
                log.debug("-------考试安排冲突--------");
                // 此处应该抛出异常，让客户端知道发生了冲突
                throw new ResponseStatusException(HttpStatus.CONFLICT, "考试安排冲突");
            }
        }
        log.debug("-----正常安排--------");
        examRepository.save(newExam);
        // 获取数据库时间戳
        newExam = examRepository.refresh(newExam);
        return newExam;
    }

    public void rmExam(int eid) {
        examRepository.deleteById(eid);
    }

    public Exam findExam(int eid) {
        return examRepository.findById(eid).get();
    }

    public Exam modifyExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Map setExamDetail(int eid, int[] uids) {
        Exam exam = examRepository.findById(eid).get();
        if (uids.length != 0) {
            exam.setStatus(Exam.STATUS_ASSIGNIED); //设置监考已经分配
        } else {
            exam.setStatus(Exam.STATUS_UNASSIGNIED); //设置监考未分配
        }

        examDetailRepository.rmExamDetailByEid(eid); // 先根据eid 将examdetail 删除，以确保数据被更新

        for (int i = 0; i < uids.length; i++) {
            User user = userRepository.findById(uids[i]).get();
            user.setInvigilationCnt(user.getInvigilationCnt() + 1); //监考次数加1

            ExamDetail examDetail = new ExamDetail();
            examDetail.setExam(exam);
            examDetail.setTeacher(user);
            examDetailRepository.save(examDetail);
        }
        sendMessage(exam);
        return null;
    }

    public Map listExamDetails() {
        List<Invigilation> invigilationList = new ArrayList<>();
        List<Exam> examList = examRepository.findAll();
        for (Exam e : examList) {
            Invigilation invig = new Invigilation();
            invig.setExam(e);
            invig.setUserlist(examDetailRepository.listUserByEid(e.getId()));
            invigilationList.add(invig);
        }
        return Map.of("examDetailList", invigilationList);
    }

    public void rmExamDetail(int eid) {
        //删除前修改用户监考次数
        List<User> userList = examDetailRepository.listUserByEid(eid);
        for (User u : userList) {
            u.setInvigilationCnt(u.getInvigilationCnt() - 1);
        }
        //删除前修改考试监考状态
        List<Exam> examList = examDetailRepository.listExamByTid(eid);
        for (Exam e : examList) {
            e.setStatus(Exam.STATUS_UNASSIGNIED);
        }
        examDetailRepository.rmExamDetailByEid(eid);
    }

    //返回和eid考试 已分配、冲突、不冲突的教师
    public Map getUserStatusByEid(int eid) {
        List<User> conflictUserList = new ArrayList<>(); //用来装和将分配考试时间冲突的user
        List<User> userList = userRepository.findAll();  //用来装未冲突的user
        List<User> assignedUserList = examDetailRepository.listUserByEid(eid); //已分配的老师

        Exam exam = examRepository.findById(eid).get(); //获取即将分配的考试
        List<ExamDetail> examDetailList = examDetailRepository.findAll();
        for (ExamDetail ed : examDetailList) {
            if (timeUtils.isTimeConflict(ed.getExam(), exam)) {
                conflictUserList.add(ed.getTeacher());
            }
        }
        //如果出现在冲突集合中则删除
        Iterator<User> iter = userList.iterator();
        while (iter.hasNext()) {
            User u = iter.next(); //移动游标，同时返回游标指向的元素
            if (conflictUserList.contains(u)) {
                iter.remove();
            }
        }
        return Map.of("assignedUsers", assignedUserList, "availableUser", userList, "conflictUsers", conflictUserList);
    }

    //在后端打印log,模拟发送信息
    private void sendMessage(Exam exam) {
        String message = "";
        message = message + "考试时间：" + exam.getBeginTime() + " - " + exam.getEndTime();
        message = message + "\n考试地点: " + exam.getLocation() + "\n监考人员信息：\n";

        List<User> userlist = examDetailRepository.listUserByEid(exam.getId());
        for (User u : userlist) {
            List<Exam> examList = examDetailRepository.listExamByTid(u.getId());
            int cnt = 0;
            for (Exam e : examList) {
                if (timeUtils.isTimeConflict(e, exam)) {
                    cnt++;
                }
            }
            message = message + u.getName() + " 同时段监考次数： " + cnt + "\n";
        }
        log.debug(message);


    }

}
