package com.example.softwareproject.service;

import com.example.softwareproject.entity.Exam;
import com.example.softwareproject.entity.Task;
import com.example.softwareproject.entity.TaskDetail;
import com.example.softwareproject.entity.User;
import com.example.softwareproject.repository.TaskDetailRepository;
import com.example.softwareproject.repository.TaskRepository;
import com.example.softwareproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: software-project
 * @description: 用户逻辑管理
 * @author: zhanyeye
 * @create: 2019-06-10 12:55
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskDetailRepository taskDetailRepository;

    public User addUser(User user) {
        User u = userRepository.save(user);
        return userRepository.refresh(u );

    }

    public User getUser(String account) {
        return userRepository.findUserByAccount(account);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void rmUser(int uid) {
        userRepository.deleteById(uid);
    }

    public User modifyUser(User user) {
        return userRepository.save(user);
    }

    public List<TaskDetail> getTaskDetails(int uid) {
        return taskDetailRepository.listTaskDetailByUid(uid);
    }

    public TaskDetail getTaskDetail(int uid, int tid) {
        return taskDetailRepository.getTaskDetail(uid, tid);
    }

    //生成提示信息，返回String
    public String generatePromptMessage(Task t) {
        return "该任务的截止时间为：" + t.getDeadLine().toString() + "! 您未按时完成任务！";
    }

    public List<TaskDetail> getOthersRes(int tid) {
        return taskDetailRepository.listTaskDetailByTid(tid);
    }
}
