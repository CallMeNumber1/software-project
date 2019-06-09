package com.example.softwareproject.repository;

import com.example.softwareproject.entity.Exam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends CustomizedRepository<Exam, Integer> {
    @Query("SELECT e FROM Exam e WHERE e.location=:location")
    List<Exam> listByLoc(@Param("location")String location);
}