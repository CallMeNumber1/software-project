package com.example.softwareproject;

import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.service.ExamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SoftwareProjectApplicationTests {
    @Autowired
    private ExamService examService;
    @Test
    public void contextLoads() {
    }
    @Test
    public void testExamService() {
        examService.isConf();
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
