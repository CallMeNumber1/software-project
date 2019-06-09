package com.example.softwareproject.service;


import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.repository.ExamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @program: software-project
 * @description: 考试业务逻辑处理
 * @author: zhanyeye
 * @create: 2019-06-09 10:38
 */
@Service
@Slf4j
@Transactional
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    public Exam addExam(Exam newExam) {
        List<Exam> exams = examRepository.listByLoc(newExam.getLocation());
        for (Exam e : exams) {
            if (isConflict(e, newExam)) {
                log.debug("-------考试安排冲突--------");
                // 此处应该抛出异常，让客户端知道发生了冲突
                return null;
            }
        }
        log.debug("-----正常安排--------");
        examRepository.save(newExam);
        // 获取数据库时间戳
        newExam = examRepository.refresh(newExam);
        return newExam;
    }
    public boolean isConflict(Exam e1, Exam e2) {
        LocalDateTime st1 = e1.getBeginTime(), et1 = e1.getEndTime();
        LocalDateTime st2 = e2.getBeginTime(), et2 = e2.getEndTime();
        // 排除开始/结束时间相同或同一时间的情况
        if (st1.isEqual(st2) || et1.isEqual(et2)) return true;
        if (st1.isBefore(st2)) {
            if (et1.isAfter(st2)) return true;
        } else {
            if (st1.isBefore(et2)) return true;
        }
        return false;
    }
    public void isConf() {
        Exam e1 = new Exam();
        Exam e2 = new Exam();
        e1.setBeginTime(LocalDateTime.of(2019, 6, 8, 14, 0, 0));
        e1.setEndTime(LocalDateTime.of(2019, 6, 8, 16, 0, 0));
        e2.setBeginTime(LocalDateTime.of(2019, 6, 8, 15, 0, 0));
        e2.setEndTime(LocalDateTime.of(2019, 6, 8, 17, 0, 0));

        Duration dur = Duration.between(e1.getEndTime(), e2.getBeginTime());
        long dif = dur.toHours();
        if (dif < 0) {
            System.out.println("时间冲突");
        } else {
            System.out.println("正常安排");
        }
        System.out.println(dif);
    }
}
