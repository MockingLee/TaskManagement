package com.Picasso.dao;

import com.Picasso.entity.UserTask;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserTaskDao {
    @Select("SELECT * FROM user_task WHERE uid = #{uid}")
    List<UserTask> findTaskById(@Param("uid") int uid);

    @Select("SELECT * FROM user_task")
    List<UserTask> findAllTask();

    @Insert("INSERT INTO user_task(uid, tid) VALUES(#{uid}, #{tid})")
    void insertUserTask(@Param("uid") int uid, @Param("tid") int tid);

    @Delete("DELETE from user_task WHERE tid = #{tid}")
    void deleteUserTask(@Param("tid") int tid);
}
