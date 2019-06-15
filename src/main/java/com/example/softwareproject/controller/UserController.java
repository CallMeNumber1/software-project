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
}
