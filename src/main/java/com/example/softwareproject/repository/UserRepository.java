package com.example.softwareproject.repository;

import com.example.softwareproject.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CustomizedRepository<User, Integer>, JpaRepository<User, Integer> {
    @Query("select u from User u where u.account = :account")
    User findUserByAccount(@Param("account")String account);
}