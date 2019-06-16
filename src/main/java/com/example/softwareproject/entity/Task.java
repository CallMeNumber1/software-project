package com.example.softwareproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     *关闭任务，不可再修改
     */
    private int isLocked;

    private String title;
    //普通长度可能不够
    @Column(columnDefinition = "TEXT")
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    private List<TaskDetail> taskDetails;

    //任务的截止时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadLine;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
    insertable = false, updatable = false)
    private LocalDateTime insertTime;
}
