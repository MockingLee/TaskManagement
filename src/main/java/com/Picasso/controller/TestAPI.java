package com.Picasso.controller;

import javax.servlet.http.HttpServletRequest;

import com.Picasso.entity.Account;
import com.Picasso.entity.Task;
import com.Picasso.service.TaskService;
import com.Picasso.service.UserService;
import com.Picasso.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestAPI {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> doLogin(@RequestBody Map<String, Object> info) {
        // {"username":"dzb","passwd":"123"}
        String username = (String) info.get("username");
        String passwd = (String) info.get("password");
        System.out.println(username + " " + passwd);
        Account account = userService.checkAccount(username, passwd);
        Map<String, Object> map = new HashMap<>();
        if (account != null) {
            map.put("success", true);
            map.put("info", account);
        } else {
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping(value = "/addAccount", method = RequestMethod.POST)
    public Map<String, Object> addAccount(@RequestBody Map<String, Object> info) {
        // {"username":"dzb","passwd":"123"}
        Map<String, Object> account = (Map<String, Object>) info.get("info");
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> msg = (Map<String, Object>) info.get("msg");
        Account acc = null;
        if ((acc = userService.checkAccount((String) account.get("username"),
                (String) account.get("passwd"))) != null) {
            if (acc.getWeight() > 0) {
                int weight = 0;
                if (info.get("weight") != null) {
                    weight = Integer.valueOf((String)msg.get("weight"));
                }
                System.out.println(weight);
                String init_pass = RandomString.getRandomString(10);
                boolean flag = userService.createAccount((String) msg.get("username"), init_pass, weight);
                if (!flag) {
                    res.put("success", false);
//                    res.put("info", acc);
                    res.put("res", "double");
                } else {
                    res.put("success", true);
                    res.put("msg", msg);
//                    res.put("info", acc);
                }
            }
        } else {
            res.put("success", false);
            res.put("info", account);
            res.put("res", "weight");
        }
        return res;
    }

    @RequestMapping(value = "/delAccount", method = RequestMethod.POST)
    public Map<String, Object> delAccount(@RequestBody Map<String, Object> request){
        Map<String,Object> response = new HashMap<>();
        return response;
    }

    @RequestMapping(value = "/changePass", method = RequestMethod.POST)
    public Map<String, Object> changePass(@RequestBody Map<String, Object> request){
        Map<String,Object> response = new HashMap<>();
        return response;
    }

    @RequestMapping(value = "/changeInfo", method = RequestMethod.POST)
    public Map<String, Object> changeInfo(@RequestBody Map<String, Object> request){
        Map<String,Object> response = new HashMap<>();
        return response;
    }

    @RequestMapping(value = "/addTask", method = RequestMethod.POST)
    public Map<String, Object> addTask(@RequestBody Map<String, Object> request){
        Map<String,Object> response = new HashMap<>();
        return response;
    }

    @RequestMapping(value = "/updateTask", method = RequestMethod.POST)
    public Map<String, Object> updateTask(@RequestBody Map<String, Object> request){
        Map<String,Object> response = new HashMap<>();
        return response;
    }

    @RequestMapping(value = "/changeTask", method = RequestMethod.POST)
    public Map<String, Object> changeTask(@RequestBody Map<String, Object> request){
        Map<String,Object> response = new HashMap<>();
        return response;
    }

    @RequestMapping(value = "/delTask", method = RequestMethod.POST)
    public Map<String, Object> delTask(@RequestBody Map<String, Object> request){
        Map<String,Object> response = new HashMap<>();
        return response;
    }

    @RequestMapping(value = "/getTask", method = RequestMethod.POST)
    public Map<String, Object> getTask(@RequestBody Map<String, Object> request){
        Map<String,Object> response = new HashMap<>();
        return response;
    }

    @RequestMapping(value = "/getUserAllTask", method = RequestMethod.POST)
    public Map<String, Object> getUserAllTask(@RequestBody Map<String, Object> request){
        Map<String,Object> response = new HashMap<>();
        return response;
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.POST)
    public Map<String, Object> getUsers(@RequestBody Map<String, Object> request){
        Map<String,Object> response = new HashMap<>();
        return response;
    }

}