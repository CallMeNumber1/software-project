package com.example.softwareproject;

import com.example.softwareproject.component.TimeUtils;
import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.repository.ExamDetailRepository;
import com.example.softwareproject.service.ExamService;
import com.example.softwareproject.service.AddDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    private AddDataService addDataService;
    @Autowired
    ExamDetailRepository examDetailRepository;



    @Test
    public void init() {
        addDataService.init_Exam();
        addDataService.init_Teacher();
        addDataService.init_ExamDetail(1,new int[]{1, 2, 3});
    }

    @Test
    public void set_ExamDetail_1() {
        addDataService.init_ExamDetail(1,new int[]{3});
    }

    @Test
    public void set_ExamDetail_2() {
        addDataService.init_ExamDetail(2,new int[]{3});
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

    @Test
    public void rmExamDetail_test() {
        examService.rmExamDetail(1);
    }


}
