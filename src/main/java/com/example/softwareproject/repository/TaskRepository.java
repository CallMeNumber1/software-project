package com.example.softwareproject.repository;

import com.example.softwareproject.entity.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CustomizedRepository<Task, Integer> {

}
