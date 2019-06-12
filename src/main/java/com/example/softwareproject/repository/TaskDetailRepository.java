package com.example.softwareproject.repository;

import com.example.softwareproject.entity.TaskDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDetailRepository extends CustomizedRepository<TaskDetail,Integer> {

    @Query("select td from TaskDetail td where td.user.id=:uid and td.task.id=:tid")
    TaskDetail getTaskDetail(@Param("uid") int uid, @Param("tid") int tid);

    @Query("select td from TaskDetail td where td.user.id=:uid")
    List<TaskDetail> listTaskDetailByUid(@Param("uid") int uid);

    @Query("select td from TaskDetail td where td.task.id=:tid")
    List<TaskDetail> listTaskDetailByTid(@Param("tid") int tid);
}
