package com.Picasso.dao;

import com.Picasso.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TaskDao {
    @Autowired
    private static JdbcTemplate jdbcTemplate;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static ArrayList<Task> findAllTasks(String id) {
        String sql = "select * from User INNER JOIN user_task INNER JOIN Task where User.user_id = user_task.user_id and user_task.task_id = Task.task_id and User.user_id = ?";
        Object[] args = {id};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        ArrayList<Task> tasks = new ArrayList<>();
        for (Map<String, Object> map : list) {
            String user_id = (String) map.get("user_id");
            int task_id = (Integer) map.get("task_id");
            String title = (String) map.get("title");
            String content = (String) map.get("content");
            Date init_time = (Date) map.get("init_time");
            Date update_time = (Date) map.get("update_time");
            int process = (Integer) map.get("process");
            if (!user_id.equals("")) {
                tasks.add(new Task(task_id, title, content, init_time, update_time, process));
            }
        }
        return tasks;
    }

    public static Task findTask(int id) {
        String sql = "select * from Task where task_id = ?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        if (list != null && list.size() == 1) {
            Map<String, Object> map = list.get(0);
            return new Task(id, (String) map.get("title"), (String) map.get("content"), (Date) map.get("init_time"), (Date) map.get("update_time"), (Integer) map.get("process"));
        }
        return null;
    }

    public static boolean updateTask(int task_id, String title, String content, Date init_time, Date update_time, int process) {
        if (findTask(task_id) == null) {
            return false;
        } else {
            String sql = "update Task set title = ?,content = ?,init_time = ?,update_time = ?,process = ? where task_id = ?";
            Object[] args = {title, content, init_time, update_time, process, task_id};
            jdbcTemplate.update(sql, args);
            return true;
        }
    }

    public static boolean insertTask(int user_id, String title, String content, Date init_time, Date update_time, int process) {
        String sql = "select * from User where user_id = ?";
        Object[] args = {user_id};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        if (list != null && list.size() >= 1) {
            return false;
        } else {
            //插入任务
            sql = "insert into Task (title,content,init_time,update_time,process) values (?, ?, ?, ?, ?)";
            Object[] args1 = {title, content, init_time, update_time, process};
            jdbcTemplate.update(sql, args1);
            //获取当前最大的task_id
            sql = "select max(task_id) as max_id from Task";
            list = jdbcTemplate.queryForList(sql);
            int max_id = (Integer) list.get(0).get("max_id");
            //插入user_task
            sql = "insert into user_task (user_id,task_id) values (?, ?)";
            Object[] args2 = {user_id, max_id};
            jdbcTemplate.update(sql, args2);

            return true;
        }
    }

    public static boolean deleteTask(int task_id) {
        String sql = "select * from Task where task_id = ?";
        Object[] args = {task_id};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        if (list == null) {
            return false;
        } else {
            sql = "delete from Task where task_id = ?";
            jdbcTemplate.update(sql, args);
            return true;
        }
    }
}
