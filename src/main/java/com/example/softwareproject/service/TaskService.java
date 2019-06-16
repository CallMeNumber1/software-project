package com.example.softwareproject.service;

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


@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskDetailRepository taskDetailRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Task> addTask(Task task) {
        Task t = taskRepository.save(task);
        taskRepository.refresh(t);
        return taskRepository.findAll();
    }

    public Task modify(Task task) {
        return taskRepository.save(task);
    }

    public Task getTask(int tid) {
        return taskRepository.findById(tid).get();
    }

    public void rmTask(int tid) {taskRepository.deleteById(tid);}

    public TaskDetail allocate(int uid, int tid){
        User user = userRepository.findById(uid).get();
        Task task = taskRepository.findById(tid).get();
        TaskDetail taskDetail = new TaskDetail();
        taskDetail.setTask(task);
        taskDetail.setUser(user);
        taskDetail.setFinishStatus(3);
        TaskDetail td = taskDetailRepository.save(taskDetail);
        return taskDetailRepository.refresh(td);
    }

    /**
     * 当用户修改了回复内容
     * @param taskDetail
     * @param uid
     * @return 返回分配给该用户的所有任务
     */
    public List<TaskDetail> modifyTaskDetail(TaskDetail taskDetail,int uid) {
        taskDetailRepository.save(taskDetail);
        return taskDetailRepository.listTaskDetailByUid(uid);
    }

    public void rmTaskDetail(int tdId) {taskDetailRepository.deleteById(tdId);}


}
