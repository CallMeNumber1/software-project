package com.example.softwareproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "exam", cascade = CascadeType.REMOVE)
    private List<ExamDetail> examDetails;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;  //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;    //结束时间
    private String location;          //考试教室
    private int numbersOfTeacher = 3;      //人数

    public static final int STATUS_UNASSIGNIED = 0;
    public static final int STATUS_ASSIGNIED = 1;
    public static final int STATUS_FULFILL = 2;

    private int status = STATUS_UNASSIGNIED; //考试的分配转态，默认未分配



    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    private LocalDateTime insertTime;

    public Exam(String name, LocalDateTime beginTime, LocalDateTime endTime, String location, int numbersOfTeacher) {
        this.name = name;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.location = location;
        this.numbersOfTeacher = numbersOfTeacher;
    }

}
