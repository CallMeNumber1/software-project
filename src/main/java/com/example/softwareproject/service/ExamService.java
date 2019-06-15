package com.example.softwareproject.service;


import com.example.softwareproject.component.TimeUtils;
import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.entity.ExamDetail;
import com.example.softwareproject.entity.User;
import com.example.softwareproject.repository.ExamDetailRepository;
import com.example.softwareproject.repository.ExamRepository;
import com.example.softwareproject.repository.UserRepository;
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
        for (int i = 0; i < uids.length; i++) {
            User u = userRepository.findById(uids[i]).get();
            Exam e = examRepository.findById(eid).get();
            u.setInvigilationCnt(u.getAuthority() + 1); //监考次数加1
            ExamDetail examDetail = new ExamDetail();
            examDetail.setExam(e);
            examDetail.setTeacher(u);
            examDetailRepository.save(examDetail);

        }
        return null;
    }

    public Map getUserStatusByEid(int eid) {
        List<User> conflictUserList = new ArrayList<>(); //用来装和将分配考试时间冲突的user
        List<User> userList = userRepository.findAll();  //用来装未冲突的user

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

        return Map.of("conflictUsers", conflictUserList, "availableUser", userList);
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
            message = message + u.getName() + " 监考次数： " + cnt + "\n";
        }
        log.debug(message);


    }

    //生成冲突警告信息，返回String
    private String generateWarningMessage(User u, Exam e1, Exam e2) {
        return u.getName() + "教师监考的"
                + e1.getName() + " : " + e1.getBeginTime().toString() + " - " + e1.getEndTime().toString()
                + " 和 " + e2.getName() + " : "+ e2.getBeginTime() + " - " + e2.getEndTime() + "冲突";
    }

    //删除ExamDetail
    public void rmExamDetail(int edid) {
        examDetailRepository.deleteById(edid);
    }


}
