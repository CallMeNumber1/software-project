package com.example.softwareproject.controller;

import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.entity.User;
import com.example.softwareproject.repository.ExamDetailRepository;
import com.example.softwareproject.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: software-project
 * @description: 监考相关请求处理
 * @author: zhanyeye
 * @create: 2019-06-10 14:41
 */

@Slf4j
@RestController
@RequestMapping("/api/admin/invigilation")
public class ExamController {

    @Autowired
    private ExamService examService;

    /**
     * 添加考试
     * @param exam
     * @return MAP
     */
    @PostMapping("/exams")
    public Map postExam(@RequestBody Exam exam) {
        examService.addExam(exam);
        return Map.of("exams", examService.listExams());
    }

    /**
     * 修改考试
     * @param exam
     * @return Map
     */
    @PatchMapping("/exams/{eid}")
    public Map putExam(@RequestBody Exam exam) {
        Exam newExam = examService.modifyExam(exam);
        return Map.of("exam", newExam);
    }

    /**
     * 删除考试
     * @param eid
     */
    @DeleteMapping("/exams/{eid}")
    public void DeleteExam(@PathVariable int eid) {
        examService.rmExam(eid);
    }


    /**
     * 根据即将分配的eid,返回冲突的用户和未冲突的用户，将2个集合封装在map中
     * @param eid
     * @return MAP
     */
    @GetMapping("/exam_details/{eid}")
    public Map getUserStatus(@PathVariable int eid) {
        return examService.getUserStatusByEid(eid);
    }


    /**
     * 分配考试监考
     * @param
     * @param
     * @return Map
     */
    @PostMapping("/exam_details/{eid}")
    public void setExamDetail(@PathVariable int eid, @RequestBody int[] uids) {
        examService.setExamDetail(eid,uids);
        //不确定是否需要返回值
    }

    /**
     * 删除考试监考
     * @param edid
     */
    @DeleteMapping("/exam_details/{edid}")
    public void DeleteExamDetail(@PathVariable int edid) {
        examService.rmExamDetail(edid);
    }

}
