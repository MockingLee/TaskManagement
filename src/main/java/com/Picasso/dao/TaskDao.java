package com.Picasso.dao;

import com.Picasso.entity.Task;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface TaskDao {
    @Select("SELECT * FROM task WHERE tid = #{tid}")
    Task findTaskById(@Param("tid") int tid);

    @Select("SELECT * FROM task")
    List<Task> findAllTask();

    @Select("select Max(tid) as id from task")
    int findMaxId();

    @Insert("INSERT INTO task(title, content, init_time, update_time,  process) VALUES(#{title}, #{content}, #{init_time}, #{update_time}, #{process})")
    void insertTask(@Param("title") String title, @Param("content") String content, @Param("init_time") Date init_time, @Param("update_time") Date update_time, @Param("process") int process);

    @Update("UPDATE  task SET title = #{title}, content = #{content}, init_time = #{init_time}, update_time = #{update_time}, process = #{process} WHERE tid = #{tid}")
    void updateTask(@Param("title") String title, @Param("content") String content, @Param("init_time") Date init_time, @Param("update_time") Date update_time, @Param("process") int process, @Param("tid") int tid);

    @Delete("DELETE from task WHERE tid = #{tid}")
    void deleteTask(@Param("tid") int tid);
}
