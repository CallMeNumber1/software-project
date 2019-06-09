package com.example.softwareproject.repository;

import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.entity.ExamDetail;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamDetailRepository extends CustomizedRepository<ExamDetail, Integer> {

    @Query("select ed.exam from ExamDetail ed where ed.teacher.id = :tid")
    List<Exam> listExamByTeacher(@Param("tid") int tid);

    @Query("select count(ed) from ExamDetail ed where ed.id = :eid")
    int coutByEid(@Param("eid")int eid);

}