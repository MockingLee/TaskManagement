package com.lee.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	private String rightUserName = "admin";
    private String rightPassword = "admin";
    
    @RequestMapping("/")
    public String getIndex() {
    	//System.out.println("login");
    	return "login";
    }
    @RequestMapping("welcome")
    public String getWelcome() {
    	return "welcome";
    }
	
	@RequestMapping("/login")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + " " + password);
        if (null == username || null == password) {
            return "redirect:/";
        }
        
    
        if (password.equals(rightPassword) && username.equals(rightUserName)) {
        	request.getSession().setAttribute("loginName", "admin");
            return "redirect:/welcome";
        }else {
        	return "redirect:/";
        }
 
        


	}
	
	@RequestMapping("/loginout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
	
}
