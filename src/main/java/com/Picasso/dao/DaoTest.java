package com.Picasso.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads() {

//		String id = "dzb";
//        String pwd = "123";
//        String sql = "select * from User where username = '"+id+"'";
//        System.out.println(sql);
//        System.out.println(jdbcTemplate);
//        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
//        System.out.println(list.get(0).get("passwd"));
//        sql = "insert into User (username , passwd) value (?,?)";
//        Object args[] = {id,pwd};
////        jdbcTemplate.update(sql,args);

        int task_id = 300000;
        String sql = "select * from Task where task_id = ?";
        Object args[] = {task_id};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,args);
        Date time = (Date)list.get(0).get("init_time");
        System.out.println(time.toString());

//        //插入任务
//        int user_id = 100001;
//        String title = "first title";
//        String content = "first content";
//        Date init_time = new Date();
//        Date update_time = new Date();
//        int process = 10;
//        String sql = "insert into Task (title,content,init_time,update_time,process) values (?, ?, ?, ?, ?)";
//        Object[] args1 = {title, content, init_time, update_time, process};
//        jdbcTemplate.update(sql, args1);
//        //获取当前最大的task_id
//        sql = "select max(task_id) as max_id from Task";
//        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
//        int max_id = (Integer) list.get(0).get("max_id");
//        //插入user_task
//        sql = "insert into user_task (user_id,task_id) values (?, ?)";
//        Object[] args2 = {user_id, max_id};
//        jdbcTemplate.update(sql, args2);

    }

}
