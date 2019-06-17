package com.example.softwareproject.controller;

import com.example.softwareproject.entity.User;
import com.example.softwareproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/users")
    public Map addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Map.of("users", userService.listUsers());
    }

    @GetMapping("/UserList")
    public Map getUserList() {
        return Map.of("userList", userService.listUsers());
    }

    @DeleteMapping("users/{uid}")
    public void rmUser(@PathVariable int uid) {
        userService.rmUser(uid);
    }

    @PatchMapping("/users/{uid}")
    public Map modifyUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User u = userService.modifyUser(user);
        return Map.of("user", u);
    }
    // 6-16 add by chong
    @GetMapping("/users/{uid}")
    public Map getUser(@PathVariable int uid) {
        User u = userService.getUserById(uid);
        return Map.of("user", u);
    }

}
