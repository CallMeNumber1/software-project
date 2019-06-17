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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    public void rmTask(int tid) {taskRepository.deleteById(tid);}

    public void allocate(int tid, int[] uids){
        Task task = taskRepository.findById(tid).get();
        for (int i = 0; i < uids.length; i++) {
            User user = userRepository.findById(uids[i]).get();
            TaskDetail taskDetail = new TaskDetail();
            taskDetail.setTask(task);
            taskDetail.setUser(user);
            taskDetail.setFinishStatus(3);
            TaskDetail n_td = taskDetailRepository.save(taskDetail);
            taskDetailRepository.refresh(n_td);
        }
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

    /**
     * 获取某一任务没有被分配到的用户集合
     * @param tid
     * @return
     */
    public List<User> getUsableUsers(int tid) {
        List<TaskDetail> taskDetails = taskDetailRepository.listTaskDetailByTid(tid);
        List<User> usedUsers = taskDetails.stream().
                map(TaskDetail::getUser).collect(Collectors.toList());
        List<User> allUsers = userRepository.findAll();
        //取两集合的差集
        List<User> usableUsers = allUsers.stream().filter(item -> !usedUsers.contains(item)).collect(Collectors.toList());
        return usableUsers;
    }

    public void rmTaskDetail(int tdId) {taskDetailRepository.deleteById(tdId);}

}
