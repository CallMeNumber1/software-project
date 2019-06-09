package com.example.softwareproject;


import com.example.softwareproject.service.ExamService;
import com.example.softwareproject.component.TimeUtils;
import com.example.softwareproject.entity.Exam;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SoftwareProjectApplicationTests {
    @Autowired
    private ExamService examService;
    @Autowired
    TimeUtils timeUtils;

    @Test
    public void datebaseinit_test() {

    }

    @Test
    public void min_max_Time_test() {
        LocalDateTime t1 = LocalDateTime.of(2019, 6, 15, 9, 10);
        LocalDateTime t2 = LocalDateTime.of(2019, 6, 16, 10, 10);
        log.debug(timeUtils.maxTime(t1, t2).toString() );
        log.debug(timeUtils.minTime(t1, t2).toString() );
        log.debug(t1.compareTo(t2) + "\n" +  t2.compareTo(t1) + "\n" +  t1.compareTo(t1));
    }
    @Test
    public void isTimeConflict_test() {
        Exam e1 = new Exam();
        Exam e2 = new Exam();
        e1.setBeginTime(LocalDateTime.of(2019,6, 12, 5, 0));
        e1.setEndTime(LocalDateTime.of(2019,6, 12, 7, 0));
        e2.setBeginTime(LocalDateTime.of(2019,6, 12, 7, 0));
        e2.setEndTime(LocalDateTime.of(2019,6, 12, 8, 0));
        log.debug(timeUtils.isTimeConflict(e1, e2) + "");
    }
    
    @Test
    public void init() {
        Exam e1 = new Exam();
        e1.setName("Web程序设计");
        e1.setLocation("925");
        e1.setBeginTime(LocalDateTime.of(2019, 6, 6, 13, 0, 0));
        e1.setEndTime(LocalDateTime.of(2019, 6, 6, 15, 0, 0));
        examService.addExam(e1);
        Exam e2 = new Exam();
        e2.setName("系统程序设计");
        e2.setLocation("925");
        e2.setBeginTime(LocalDateTime.of(2019, 6, 6, 15, 0, 0));
        e2.setEndTime(LocalDateTime.of(2019, 6, 6, 17, 0, 0));
        examService.addExam(e2);
//        Exam e3 = new Exam();
//        e3.setName("SOA");
//        e3.setLocation("925");
//        e3.setBeginTime(LocalDateTime.of(2019, 6, 6, 14, 0, 0));
//        e3.setEndTime(LocalDateTime.of(2019, 6, 6, 16, 0, 0));
//        examService.addExam(e3);
    }
}
