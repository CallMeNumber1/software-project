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

    @PostMapping("/tasks")
    public Map addTask(@RequestBody Task task) {
        return Map.of("task",taskService.addTask(task));
    }

    @PatchMapping("/tasks")
    public Map modifyTask(@RequestBody Task task) {
        return Map.of("task", taskService.modify(task));
    }

    /**
     * 将任务分配给指定用户
     * @param tid
     * @param uid
     * @return
     */
    @PutMapping("/tasks/{tid}/{uid}")
    public Map allocateTask(@PathVariable int tid, @PathVariable int uid) {
        return Map.of("taskDetail", taskService.allocate(uid, tid));
    }

    @DeleteMapping("/tasks/{tid}")
    public void DeleteTask(@PathVariable int tid) {
        taskService.rmTask(tid);
    }

    @DeleteMapping("/taskDetails/{tdId}")
    public void DeleteTaskDetail(@PathVariable int tdId) {
        taskService.rmTaskDetail(tdId);
    }

}
