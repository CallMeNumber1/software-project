package com.example.softwareproject.service;

import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @program: software-project
 * @description: 初始化数据
 * @author: zhanyeye
 * @create: 2019-06-09 10:32
 */
@Service
@Transactional  //要加事务，不然 refresh() 时会 Entity not managed
public class AddDataService {

    @Autowired
    private ExamService examService;
    @Autowired
    private UserService userService;


    public void init_Admin() {
        User admin = new User("black","1020","123456",User.ADMIN_AUTHORITY);
        userService.addUser(admin);
    }

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
        User u1 = new User("black","3001","123456",User.USER_AUTHORITY);
        User u2 = new User("while","3002","123456",User.USER_AUTHORITY);
        User u3 = new User("belly","3003","123456",User.USER_AUTHORITY);
        User u4 = new User("monk","3004","123456",User.USER_AUTHORITY);
        userService.addUser(u1);
        userService.addUser(u2);
        userService.addUser(u3);
        userService.addUser(u4);
    }

    public void init_ExamDetail() {
//        examService.setExamDetail(1, 1);
//        examService.setExamDetail(2, 2);
//        examService.setExamDetail(3, 3);
    }

}
