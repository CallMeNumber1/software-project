package com.example.softwareproject.controller;

import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.entity.ExamDetail;
import com.example.softwareproject.entity.User;
import com.example.softwareproject.repository.ExamDetailRepository;
import com.example.softwareproject.service.ExamService;
import com.example.softwareproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public Map addUser(@RequestBody User user) {
        User u =  userService.addUser(user);
        return Map.of("user", u);
    }

    @GetMapping("/UserList")
    public Map getUserList() {
        return Map.of("userList", userService.findAllUser());
    }

    @DeleteMapping("users/{uid}")
    public void rmUser(@PathVariable int uid) {
        userService.rmUser(uid);
    }

    @PutMapping("/users")
    public Map modifyUser(@RequestBody User user) {
        User u = userService.modifyUser(user);
        return Map.of("user", u);
    }


}
