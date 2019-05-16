package com.Picasso.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestAPI {
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public String doLogin(@RequestBody String payload){
        System.out.println(payload);
        String res = "{\"info\" : 1, \"success\" : 1}";
        return res;
    }

    @RequestMapping("/**")
    public String getAll(HttpServletRequest request){
        System.out.println(request.getRequestURI());
        return "okAll";
    }
}