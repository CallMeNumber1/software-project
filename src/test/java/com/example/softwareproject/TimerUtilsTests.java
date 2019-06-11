package com.example.softwareproject;

import com.example.softwareproject.component.TimeUtils;
import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.service.ExamService;
import com.example.softwareproject.service.AddDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @program: software-project
 * @description: 时间测试
 * @author: zhanyeye
 * @create: 2019-06-10 08:20
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TimerUtilsTests {

    @Autowired
    private ExamService examService;
    @Autowired
    private TimeUtils timeUtils;
    @Autowired
    private AddDataService addDataService;

    @Test
    public void isTimeConflictTest() {
        Exam e1 = new Exam();
        e1.setName("Web程序设计");
        e1.setLocation("925");
        e1.setBeginTime(LocalDateTime.of(2019, 6, 6, 13, 0, 0));
        e1.setEndTime(LocalDateTime.of(2019, 6, 6, 15, 0, 0));


        Exam e2 = new Exam();
        e2.setName("系统程序设计");
        e2.setLocation("925");
        e2.setBeginTime(LocalDateTime.of(2019, 6, 6, 15, 0, 0));
        e2.setEndTime(LocalDateTime.of(2019, 6, 6, 17, 0, 0));


        Exam e3 = new Exam();
        e3.setName("Android");
        e3.setLocation("922");
        e3.setBeginTime(LocalDateTime.of(2019, 6, 6, 10, 0, 0));
        e3.setEndTime(LocalDateTime.of(2019, 6, 6, 11, 0, 0));

        log.debug(timeUtils.isTimeConflict(e1, e3)+ " ");

    }



}
