package com.Picasso.controller;

import javax.servlet.http.HttpServletRequest;

import com.Picasso.entity.Account;
import com.Picasso.entity.Task;
import com.Picasso.service.TaskService;
import com.Picasso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestAPI {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Account doLogin(@RequestBody Map<String, Object> info) {
        //{"username":"dzb","passwd":"123"}
        String username = (String) info.get("username");
        String passwd = (String) info.get("passwd");
        return userService.checkAccount(username, passwd);
    }


    @RequestMapping(value = "/addAcount", method = RequestMethod.POST)
    public boolean addAcount(@RequestBody Map<String, Object> info) {
        //{"username":"dzb","passwd":"123"}
        String username = (String) info.get("username");
        String passwd = (String) info.get("passwd");
        int weight = 0;
        if (info.get("weight") != null) {
            weight = (Integer) info.get("weight");
        }
        System.out.println(weight);
        return userService.createAccount(username, passwd, weight);
    }




}