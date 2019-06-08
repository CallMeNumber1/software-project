package com.example.softwareproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class TeacherTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;

    //任务完成的状态：0表示未完成，1表示延时完成，2表示按时完成
    private int finishStatus;
    //回复内容
    @Column(columnDefinition = "TEXT")
    private String responseContent;

    //任务的完成时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime finishedTime;

}
