package com.example.softwareproject.repository;

import com.example.softwareproject.entity.Exam;

import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends CustomizedRepository<Exam, Integer> {

}