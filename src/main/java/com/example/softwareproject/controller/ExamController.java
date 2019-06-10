package com.example.softwareproject.controller;

import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.entity.ExamDetail;
import com.example.softwareproject.repository.ExamDetailRepository;
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

    @PutMapping("/exams")
    public Map putExam(@RequestBody Exam exam) {
        Exam newExam = examService.modifyExam(exam);
        return Map.of("exam", newExam);
    }

    @DeleteMapping("/exams")
    public void DeleteExam(@RequestAttribute int eid) {
        examService.rmExam(eid);
    }

    @GetMapping("/exam_details/{eid}/{uid}")
    public Map getExamDetail(@PathVariable int eid, @PathVariable int uid) {
        return examService.setExamDetail(eid, uid);
    }

    @DeleteMapping("/exam_details/{edid}")
    public void DeleteExamDetail(@PathVariable int edid) {
        examService.rmExamDetail(edid);
    }


}
