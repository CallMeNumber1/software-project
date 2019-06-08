package com.example.softwareproject.repository;

import com.example.softwareproject.entity.Teacher;

import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CustomizedRepository<Teacher, Integer> {
    
}