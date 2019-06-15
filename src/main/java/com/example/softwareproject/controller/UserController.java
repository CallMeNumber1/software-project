package com.example.softwareproject.controller;

import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @program: software-project
 * @description: 用户请求处理
 * @author: zhanyeye
 * @create: 2019-06-10 14:34
 */
import com.example.softwareproject.entity.Task;
import com.example.softwareproject.entity.TaskDetail;
import com.example.softwareproject.entity.User;
import com.example.softwareproject.service.TaskService;
import com.example.softwareproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private ExamService examService;
    @GetMapping("/exams")
    public Map getExams() {
        List<Exam> exams = examService.listExams();
        return Map.of("exams", exams);
    }

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    /**
     * 用户获取分配给他的所有任务
     */
    @GetMapping("/users/{uid}")
    public Map getTaskDetail(@PathVariable int uid) {
        return Map.of("taskDetails", userService.getTaskDetails(uid));
    }

    /**
    * 用户回复分配的任务
     * json传来用户完成的任务内容
     */
    @PatchMapping("/users/{uid}/tasks/{tid}")
    public Map response(@PathVariable int uid, @PathVariable int tid, @RequestBody TaskDetail taskDetail) {
       System.out.println(taskDetail.getResponseContent());
        TaskDetail db_taskDetail = userService.getTaskDetail(uid, tid);
        db_taskDetail.setResponseContent(taskDetail.getResponseContent());
        Task task = taskService.getTask(tid);
        System.out.println("end");
        //还未完成
        if (db_taskDetail.getFinishStatus() == 3) {
            db_taskDetail.setFinishedTime(LocalDateTime.now());
            if (LocalDateTime.now().isAfter(task.getDeadLine())) {
                db_taskDetail.setFinishStatus(2);
                System.out.println("enter1");
                return Map.of("taskDetail", taskService.modifyTaskDetail(db_taskDetail),"info",userService.generatePromptMessage(task));

            } else {
                db_taskDetail.setFinishStatus(1);
                System.out.println("enter2");
                return Map.of("taskDetail", taskService.modifyTaskDetail(db_taskDetail));

            }
        } else {
            System.out.println("enter3");
                return Map.of("taskDetail", taskService.modifyTaskDetail(db_taskDetail));

        }

    }

    @GetMapping("/tasks/{tid}")
    public Map ListUsersRes(@PathVariable int tid) {
        return Map.of("taskDetails",userService.getOthersRes(tid));
    }

}
