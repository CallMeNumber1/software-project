package com.example.softwareproject.service;


import com.example.softwareproject.entity.User;
import com.example.softwareproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: software-project
 * @description: 初始化管理员
 * @author: zhanyeye
 * @create: 2019-06-11 09:11
 */
@Slf4j
@Component
@Transactional
public class InitService implements InitializingBean {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setAuthority(User.ADMIN_AUTHORITY);
            user.setAccount("1001");
            user.setPassword(passwordEncoder.encode(user.getAccount()));
            user.setName("Admin");
            userRepository.save(user);
        }
    }
}

