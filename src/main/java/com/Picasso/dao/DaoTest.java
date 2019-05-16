package com.Picasso.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.Picasso.service.TaskService;
import com.Picasso.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.Picasso.dao")
public class DaoTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void contextLoads() {
        System.out.println(taskService.selectAllTask().size());
    }

}