package com.example.demo;

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
public class TaskManagementApplicationTests {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void contextLoads() {
		
		String id = "dzb";
        String pwd = "123";
        String sql = "select * from Account where username = " + id;
        System.out.println(jdbcTemplate);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        sql = "insert into Account (username , passwd) values (" + id + ", " + pwd + ")";
        jdbcTemplate.update(sql);

		
	}

}
