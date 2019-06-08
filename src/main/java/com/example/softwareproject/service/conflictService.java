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
        o_exam.setBeginTime(LocalDateTime.of(2019,6,23,16,0,0));
        o_exam.setEndTime(LocalDateTime.of(2019,6,23,18,0,0));
        boolean ret = true;
        LocalDateTime begin, terminal;
        //找到较晚的结束时间
        if (n_exam.getBeginTime().isAfter(o_exam.getBeginTime())) {
            begin = n_exam.getBeginTime();
        } else {
            begin = o_exam.getBeginTime();
        }

        if (n_exam.getEndTime().isBefore(o_exam.getEndTime())) {
            terminal = n_exam.getEndTime();
        } else {
            terminal = o_exam.getEndTime();
        }

        if (begin.isAfter(terminal)) {
            ret = false;
        }
        System.out.println(String.valueOf(begin));
        System.out.println(String.valueOf(terminal));
        return ret;
    }
}
