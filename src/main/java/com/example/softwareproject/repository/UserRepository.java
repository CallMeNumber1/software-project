package com.example.softwareproject.repository;

import com.example.softwareproject.entity.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CustomizedRepository<User, Integer> {
    
}