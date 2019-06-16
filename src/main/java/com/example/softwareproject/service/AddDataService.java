package com.example.softwareproject.service;

import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.entity.Task;
import com.example.softwareproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private TaskService taskService;
    @Autowired
    private PasswordEncoder passwordEncoder;



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
        User u1 = new User("black","3001",passwordEncoder.encode("123456"),User.USER_AUTHORITY);
        User u2 = new User("while","3002",passwordEncoder.encode("123456"),User.USER_AUTHORITY);
        User u3 = new User("belly","3003",passwordEncoder.encode("123456"),User.USER_AUTHORITY);
        User u4 = new User("monk","3004",passwordEncoder.encode("123456"),User.USER_AUTHORITY);
        userService.addUser(u1);
        userService.addUser(u2);
        userService.addUser(u3);
        userService.addUser(u4);
    }


    public void init_ExamDetail(int eid, int[] array) {
        examService.setExamDetail(eid, array);
    }

    public void init_Task() {
        Task task = new Task();
        task.setTitle("SOA实验报告");
        task.setDescription("根据数字东林有关建设文档完成验实报告一和实验报告二。");
        task.setDeadLine(LocalDateTime.of(2019, 6, 23, 0, 0, 0));

        Task task1 = new Task();
        task1.setTitle("软件测试实验报告");
        task1.setDescription("根据实验指导书完成实验一至四，其中等价类的设计要着重注意。");
        task1.setDeadLine(LocalDateTime.of(2019, 6, 10, 0, 0, 0));

        taskService.addTask(task);
        taskService.addTask(task1);
    }

    public void init_TaskDetail() {
        taskService.allocate(1,new int[]{2,3,4});
        taskService.allocate(2,new int[]{1,2,3,4});
    }


}
