package com.Picasso.dao;

import com.Picasso.entity.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminDao {
    @Autowired
    private static JdbcTemplate jdbcTemplate;

    public static ArrayList<Administrator> findAllAdministrator() {
        String sql = "select * from Administrator";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        ArrayList<Administrator> Administrators = new ArrayList<>();
        for (Map<String, Object> map : list) {
            String username = (String) map.get("username");
            String passwd = (String) map.get("passwd");
            if (!username.equals("")) {
                Administrators.add(new Administrator(username, passwd));
            }
        }
        return Administrators;
    }

    public static Administrator findAdministrator(String name, String pwd) {
        String sql = "select * from Administrator where username = ? and passwd = ?";
        Object[] args = {name, pwd};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        if (list != null) {
            return new Administrator(name, pwd);
        }
        return null;
    }

    public static boolean checkPwd(String id, String pwd) {
        if (findAdministrator(id, pwd) == null) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean updateAdministrator(String old_id, String old_pwd, String new_id, String new_pwd) {
        if (!checkPwd(old_id, old_pwd)) {
            return false;
        } else {
            String sql = "update Administrator set username = ?,passwd = ? where username = ?";
            Object args[] = {new_id, new_pwd, old_id};
            jdbcTemplate.update(sql, args);
            return true;
        }
    }

    public static boolean insertAdministrator(String id, String pwd) {
        String sql = "select * from Administrator where username = ?";
        Object[] args = {id};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);


        if (list != null) {
            return false;
        } else {
            sql = "insert into Administrator (username , passwd) values (? ,?)";
            Object[] args1 = {id, pwd};
            jdbcTemplate.update(sql, args1);
            return true;
        }
    }

    public static boolean deleteAdministrator(String id) {
        String sql = "select * from Administrator where username = ?";
        Object[] args = {id};
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, args);
        if (list == null) {
            return false;
        } else {
            sql = "delete from Administrator where username = ?";
            jdbcTemplate.update(sql, args);
            return true;
        }
    }
}
