package com.example.softwareproject.controller;

import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class ExamController {
    @Autowired
    private ExamService examService;

    @PostMapping("/exams")
    public Map postExam(@RequestBody Exam exam) {
        examService.addExam(exam);
        return Map.of("exam", exam);
    }
}
