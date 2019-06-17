package com.example.softwareproject.component;

import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.entity.User;
import com.example.softwareproject.repository.ExamDetailRepository;
import com.example.softwareproject.repository.ExamRepository;
import com.example.softwareproject.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: software-project
 * @description: 定时器
 * @author: zhanyeye
 * @create: 2019-06-16 14:25
 */
@Slf4j
@Component
@Transactional
public class Timer {

    @Autowired
    private ExamService examService;
    @Autowired
    private ExamDetailRepository examDetailRepository;
    @Autowired
    private ExamRepository examRepository;


    //发送监考提醒
    @Scheduled(cron = "0 * * * * ?")    //目前设置为每分钟发送1次，便于演示
    public void sendMessage() {
        LocalDate time = LocalDate.now(); //获取今天日期
        LocalDate nextTime = time.plusDays(1);    //加一
        List<Exam> examList = examService.listExams();

        for (Exam e : examList) {
            if (e.getBeginTime().toLocalDate().isEqual(nextTime)) {
                sendMessage(e, nextTime);
            }
        }
    }


    @Scheduled(cron = "0 * * * * ?")    //目前设置为每天8点,更新考试状态
    public void updateExamStatus() {
        LocalDate nowTime = LocalDate.now(); //获取今天日期
        List<Exam> examList = examService.listExams();
        for (Exam e : examList) {
            if (e.getBeginTime().toLocalDate().isBefore(nowTime)) {
                examRepository.updateExamStatus(e.getId(),2);  //跟新考试已经结束
            }
        }
    }

    //在后端打印log,模拟发送提醒信息
    private void sendMessage(Exam exam, LocalDate nextTime) {
        List<User> userlist = examDetailRepository.listUserByEid(exam.getId());
        log.debug("明日：" + nextTime.toString() + " 的考试：");
        String message = "考试名称：" + exam.getName() + " \n";
        message = message + "考试时间：" + exam.getBeginTime() + " - " + exam.getEndTime();
        message = message + "\n考试地点: " + exam.getLocation() + "\n监考人员信息：";
        if (userlist.size() == 0) {
            message += "该考试尚未分配！！！\n";
        } else {
            for (User u : userlist) {
                message = message + u.getName()  + ", ";
            }
        }
        message += "\n";

        log.debug(message);
    }

}
