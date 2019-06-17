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
//创建联合约束
@Table(name = "task_detail", uniqueConstraints = {@UniqueConstraint(columnNames={"task_id", "user_id"})})
public class TaskDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //任务完成的状态：1表示按时完成，2表示延时完成，3表示未完成
    private int finishStatus;
    //回复内容
    @Column(columnDefinition = "TEXT")
    private String responseContent;

    //任务的完成时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime finishedTime;

}
