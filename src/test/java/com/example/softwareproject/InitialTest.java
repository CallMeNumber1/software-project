package com.example.softwareproject;

import com.example.softwareproject.service.AddDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: software-project
 * @description: 初始化数据
 * @author: zhanyeye
 * @create: 2019-06-17 13:11
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class InitialTest {
    @Autowired
    private AddDataService addDataService;
    @Test
    public void init() {
        addDataService.init_Exam();
        addDataService.init_Teacher();
        addDataService.init_ExamDetail();
        addDataService.init_Task();
        addDataService.init_TaskDetail();
    }
}
