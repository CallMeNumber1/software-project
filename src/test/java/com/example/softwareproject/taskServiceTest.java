package com.example.softwareproject;

import com.example.softwareproject.service.AddDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class taskServiceTest {
    @Autowired
    private AddDataService addDataService;

    @Test
    public void test() {
        addDataService.init_Task();
        addDataService.init_TaskDetail();
    }

}
