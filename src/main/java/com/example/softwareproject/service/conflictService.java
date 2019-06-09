package com.example.softwareproject.service;

import com.example.softwareproject.entity.Exam;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class conflictService {


    public boolean isConflict() {
        Exam n_exam = new Exam();
        Exam o_exam = new Exam();
        n_exam.setBeginTime(LocalDateTime.of(2019, 6, 23,15,30,0));
        n_exam.setEndTime(LocalDateTime.of(2019,6,23,17,0,0));
        o_exam.setBeginTime(LocalDateTime.of(2019,6,23,9,0,0));
        o_exam.setEndTime(LocalDateTime.of(2019,6,23,10,0,0));
        LocalDateTime st1 = n_exam.getBeginTime(), end1 = n_exam.getEndTime();
        LocalDateTime st2 = o_exam.getBeginTime(), end2 = o_exam.getEndTime();
        //找到较晚的结束时间
        if (st1.isEqual(st2) || end1.isEqual(end2)) return true;
        if (st1.isAfter(st2)) {
            if (st1.isBefore(end2)) return true;
        } else {
            if (st2.isBefore(end1)) return true;
        }

        System.out.println(st1);
        System.out.println(st2);
        return false;
    }
}
