package com.Picasso.service;

import com.Picasso.dao.AccountDao;
import com.Picasso.dao.TaskDao;
import com.Picasso.dao.UserTaskDao;
import com.Picasso.entity.Task;
import com.Picasso.entity.UserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private UserTaskDao userTaskDao;

    /**
     * 查询
     */
    @Transactional
    public List<Task> selectAllTaskByUid(int uid) {
        List<Task> tasks = new ArrayList<>();
        for (UserTask userTask : userTaskDao.findTaskById(uid)) {
            Task task = selectTaskById(userTask.getTid());
            if (task != null)
                tasks.add(task);
        }
        return tasks;
    }

    public List<Task> selectAllTask() {
        return taskDao.findAllTask();
    }

    public Task selectTaskById(int tid) {
        return taskDao.findTaskById(tid);
    }

    public UserTask selectUserTask(int uid, int tid) {
        return userTaskDao.findUserTask(uid, tid);
    }

    /**
     * 插入更改
     */
    @Transactional
    public Task addTask(int uid, String title, String content, Date init_time, Date update_time, int process) {
        if (accountDao.findAccountById(uid) == null) {
            return null;
        } else {
            taskDao.insertTask(title, content, init_time, update_time, process);
            int tid = taskDao.findMaxId();
            userTaskDao.insertUserTask(uid, tid);
            return this.selectTaskById(tid);
        }
    }

    @Transactional
    public Task changeTask(String title, String content, Date init_time, Date update_time, int process, int tid) {
        if (this.selectTaskById(tid) != null) {
            taskDao.updateTask(title, content, init_time, update_time, process, tid);
            return this.selectTaskById(tid);
        } else {
            return null;
        }
    }

    /**
     * 删除
     */
    public boolean delTask(int tid) {
        if (selectTaskById(tid) == null) {
            return false;
        } else {
            userTaskDao.deleteUserTask(tid);
            taskDao.deleteTask(tid);
            return true;
        }
    }

}
