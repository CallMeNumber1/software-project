package com.example.softwareproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    //普通长度可能不够
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "task")
    private List<TeacherTask> teacherTasks;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadLine;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
    insertable = false, updatable = false)
    private LocalDateTime insertTime;
}
