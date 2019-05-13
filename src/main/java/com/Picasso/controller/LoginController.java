package com.Picasso.controller;

import javax.servlet.http.HttpServletRequest;

import com.Picasso.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Picasso.dao.UserDao;
import com.Picasso.util.HashProtection;
import com.Picasso.util.IdentifierString;

@Controller
public class LoginController {
    @Autowired
    ApplicationContext ctx;
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
    
    @RequestMapping("userIndex")
    public String getUserIndex() {
    	return "userIndex";
    }
    @RequestMapping("adIndex")
    public String getAdIndex() {
    	return "adIndex";
    }
	
	@RequestMapping(value = "/login" , method=RequestMethod.POST)
    public String login(HttpServletRequest request) {
		if(checkIfLogin(request))
			return "ActionFailed";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + " " + password);
        if (null == username || null == password) {
            return "redirect:/";
        }

        UserDao userDao = ctx.getBean(UserDao.class);

        if (userDao.checkPwd(username,HashProtection.sha1(password))) {
        	request.getSession().setAttribute("loginState", IdentifierString.LOGIN_STATE_LOGIN);
        	request.getSession().setAttribute("userType", IdentifierString.USER_TYPE_USER);
        	request.getSession().setAttribute("loginName", username);
            return "redirect:/userIndex";
        }else {
        	return "redirect:/";
        }
	}
	
	@RequestMapping("/logout")
    public String loginOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
	
	
	
	@RequestMapping(value="loginAd" , method=RequestMethod.POST)
	public String loginAd(HttpServletRequest request) {
		if(checkIfLogin(request))
			return "ActionFailed";
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("ad " + username + " " + password);
        if (null == username || null == password) {
            return "redirect:/";
        }
        UserDao userDao = ctx.getBean(UserDao.class);
    
        if (userDao.checkPwd(username,HashProtection.sha1(password))) {
        	request.getSession().setAttribute("loginState", IdentifierString.LOGIN_STATE_LOGIN);
        	request.getSession().setAttribute("userType", IdentifierString.USER_TYPE_AD);
        	request.getSession().setAttribute("loginName", username);           
        	return "redirect:/adIndex";
        }else {
        	return "redirect:/";
        }
	}
	
	private boolean checkIfLogin(HttpServletRequest request) {
		if(request.getSession().getAttribute("loginState") == null)
			return false;
		String login_state = request.getSession().getAttribute("loginState").toString();
		return login_state.equals(IdentifierString.LOGIN_STATE_LOGIN);
	}
	
}
