package com.Picasso.dao;

import com.Picasso.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao {
    @Autowired
    private static JdbcTemplate jdbcTemplate;

    public static ArrayList<User> findAllUser() {
        String sql = "select * from User";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        ArrayList<User> users = new ArrayList<>();
        for (Map<String, Object> map : list) {
            String username = (String) map.get("username");
            String passwd = (String) map.get("passwd");
            if (!username.equals("")) {
                users.add(new User(username, passwd));
            }
        }
        return users;
    }

    public static User findUser(String name, String pwd) {
        String sql = "select * from User where username = ? and passwd = ?";
        Object[] args = {name, pwd};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        if (list != null) {
            return new User(name, pwd);
        }
        return null;
    }

    public static boolean checkPwd(String name, String pwd) {
        if (findUser(name, pwd) == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean updateUser(String old_id, String old_pwd, String new_id, String new_pwd) {
        if (!checkPwd(old_id, old_pwd)) {
            return false;
        } else {
            String sql = "update User set username = ?,passwd = ? where username = ?";
            Object args[] = {new_id, new_pwd, old_id};
            jdbcTemplate.update(sql, args);
            return true;
        }
    }

    public static boolean insertUser(String id, String pwd) {
        String sql = "select * from User where username = ?";
        Object[] args = {id};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);


        if (list != null) {
            return false;
        } else {
            sql = "insert into User (username , passwd) values (? ,?)";
            Object[] args1 = {id, pwd};
            jdbcTemplate.update(sql, args1);
            return true;
        }
    }

    public static boolean deleteUser(String id) {
        String sql = "select * from User where username = ?";
        Object[] args = {id};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        if (list == null) {
            return false;
        } else {
            sql = "delete from User where username = ?";
            jdbcTemplate.update(sql, args);
            return true;
        }
    }
}
