package com.example.softwareproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Teacher {
    //定义静态常量表示教师权限
    public static final int USER_AUTHORITY = 0;
    public static final int ADMIN_AUTHORITY = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int authority = USER_AUTHORITY;  //用户权限
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String phone;
    private int title = 1;  //职称
    private String description;  //描述

    @JsonIgnore
    @OneToMany(mappedBy = "teacher",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<TeacherExam> teacherExams;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    private List<TeacherTask> teacherTasks;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    private LocalDateTime insertTime;

}
