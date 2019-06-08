package com.example.softwareproject;

import com.example.softwareproject.repository.impl.CustomizedRepositoryImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomizedRepositoryImpl.class)
public class SoftwareProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftwareProjectApplication.class, args);
    }

    

}
