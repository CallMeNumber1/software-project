package com.example.softwareproject.service;

import com.example.softwareproject.entity.User;
import com.example.softwareproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: software-project
 * @description: 用户逻辑管理
 * @author: zhanyeye
 * @create: 2019-06-10 12:55
 */
@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User addUser(User user) {
        User u = userRepository.save(user);
        return userRepository.refresh(u );

    }

    public User getUser(String account) {
        return userRepository.findUserByAccount(account);
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public void rmUser(int uid) {
        userRepository.deleteById(uid);
    }

    public User modifyUser(User user) {
        return userRepository.save(user);
    }

}
