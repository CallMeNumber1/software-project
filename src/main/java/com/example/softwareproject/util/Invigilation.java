package com.example.softwareproject.util;

import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: software-project
 * @description: 封装考试和所分配的老师的包装类
 * @author: zhanyeye
 * @create: 2019-06-16 16:08
 */
@Getter
@Setter
public class Invigilation {
    private Exam exam;
    private List<User> userlist;
}
