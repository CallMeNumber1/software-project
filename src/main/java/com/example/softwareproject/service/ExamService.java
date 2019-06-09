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

import java.util.List;


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


    public ExamDetail setExamDetail(int uid, int eid) {
        User user = userRepository.findById(uid).get();
        Exam exam = examRepository.findById(eid).get();

        if (exam.getNumbersOfTeacher() == examDetailRepository.coutByEid(eid)) { //考试需要监考人数 和 已分配人数 比较
            throw new ResponseStatusException(HttpStatus.CONFLICT, "该考试监考人员已经分配完成,无需再分配！");
        }

        List<Exam> examList = examDetailRepository.listExamByTeacher(uid); //uid 老师的所有监考考试
        int conflictCount = 0; //记录exam 和 uid 老师已分派考试的时间的冲突次数：冲突达到 2 则不能再分配了
        String conflictExam; //记录和哪门考试冲突
        
        for(Exam e : examList) {
            if (timeUtils.isTimeConflict(e, exam)) {
                log.debug(e.getName()+ "????");
                conflictCount++;
                conflictExam = e.getName();
                if (conflictCount == 2) {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "同一时间同一教师只能执行 2 次不同监考！");
                }
            }
        }

        ExamDetail examDetail = new ExamDetail();
        examDetail.setTeacher(user);
        examDetail.setExam(exam);
        examDetail = examDetailRepository.save(examDetail);

//        throw new ResponseStatusException(HttpStatus.CONFLICT, user.getName() + "教师监考的"+ conflictExam + "和" + exam.setName() + "冲突");

        return examDetailRepository.refresh(examDetail);
    }


    public void rmExamDetail(int edid) {
        examDetailRepository.deleteById(edid);
    }


}
