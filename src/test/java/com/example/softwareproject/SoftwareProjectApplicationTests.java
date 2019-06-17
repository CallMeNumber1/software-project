package com.example.softwareproject;


import com.example.softwareproject.repository.UserRepository;
import com.example.softwareproject.service.ExamService;
import com.example.softwareproject.component.TimeUtils;
import com.example.softwareproject.service.AddDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SoftwareProjectApplicationTests {
    @Autowired
    private ExamService examService;
    @Autowired
    private TimeUtils timeUtils;
    @Autowired
    private AddDataService addDataService;
    @Autowired
    private UserRepository userRepository;





    

}
