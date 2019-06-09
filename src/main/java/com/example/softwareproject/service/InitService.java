package com.example.softwareproject.service;

import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.entity.User;
import com.example.softwareproject.repository.ExamDetailRepository;
import com.example.softwareproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @program: software-project
 * @description: 初始化数据
 * @author: zhanyeye
 * @create: 2019-06-09 10:32
 */
@Service
public class InitService {

    @Autowired
    private ExamService examService;
    @Autowired
    private UserRepository userRepository;

    public void init_Exam() {
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

        Exam e3 = new Exam();
        e3.setName("Android");
        e3.setLocation("922");
        e3.setBeginTime(LocalDateTime.of(2019, 6, 6, 10, 0, 0));
        e3.setEndTime(LocalDateTime.of(2019, 6, 6, 11, 0, 0));
        examService.addExam(e3);

    }

    public void init_Teacher() {
        User u1 = new User("black",User.USER_AUTHORITY);
        User u2 = new User("while",User.USER_AUTHORITY);
        User u3 = new User("belly",User.USER_AUTHORITY);
        User u4 = new User("monk",User.USER_AUTHORITY);
        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
        userRepository.save(u4);
    }

    public void init_ExamDetail() {
        examService.setExamDetail(1, 1);
        examService.setExamDetail(2, 2);
        examService.setExamDetail(3, 3);
    }

}
