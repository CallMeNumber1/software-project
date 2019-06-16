package com.example.softwareproject.controller;

import com.example.softwareproject.entity.Task;
import com.example.softwareproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class TaskController {

    @Autowired
    private TaskService taskService;
    // add by chong 管理员获取所有任务
    @GetMapping("/tasks")
    public Map getTasks() {
        return Map.of("tasks", taskService.listTasks());
    }
    @GetMapping("/tasks/{tid}")
    public Map getTask(@PathVariable int tid) {
        return Map.of("task", taskService.getTask(tid));
    }
    /**
     * 管理员新增任务
     * @param task
     * @return 新增任务之后的所有的任务列表
     */
    @PostMapping("/tasks")
    public Map addTask(@RequestBody Task task) {
        return Map.of("tasks",taskService.addTask(task));
    }

    /**
     * 管理员修改任务信息
     * @param task
     * @return
     */
    @PatchMapping("/tasks/{tid}")
    public Map modifyTask(@RequestBody Task task) {
        return Map.of("task", taskService.modify(task));
    }

    /**
     * 管理员将任务分配给指定用户
     * @param tid
     * @return
     */
    @PostMapping("/tasks/{tid}")
    public void allocateTask(@PathVariable int tid, @RequestBody int[] uids) {
        taskService.allocate(tid, uids);
        //return Map.of("taskDetail", taskService.allocate(uid, tid));
    }

    /**
     * 管理员删除任务
     * @param tid
     */
    @DeleteMapping("/tasks/{tid}")
    public void DeleteTask(@PathVariable int tid) {
        taskService.rmTask(tid);
    }

    /**
     * 管理员删除某一分配任务
     * @param tdId
     */
    @DeleteMapping("/taskDetails/{tdId}")
    public void DeleteTaskDetail(@PathVariable int tdId) {
        taskService.rmTaskDetail(tdId);
    }

}
