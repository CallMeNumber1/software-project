package com.example.softwareproject;

import com.example.softwareproject.component.TimeUtils;
import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.service.ExamService;
import com.example.softwareproject.service.InitService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @program: software-project
 * @description: ExamService 测试类
 * @author: zhanyeye
 * @create: 2019-06-09 16:56
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamServiceTests {
    @Autowired
    private ExamService examService;
    @Autowired
    private TimeUtils timeUtils;
    @Autowired
    private InitService initService;

    @Test
    public void init() {
        initService.init_Exam();
        initService.init_Teacher();
        initService.init_ExamDetail();
    }

    @Test
    public void rmExam_test() {
        examService.rmExam(1);
    }

    @Test
    public void modExam_test() {
        Exam exam = examService.findExam(2);
        exam.setName("9999999");
        examService.modifyExam(exam);
    }


}
