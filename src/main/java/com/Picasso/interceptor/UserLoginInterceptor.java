package com.Picasso.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.Picasso.util.IdentifierString;
@Component
public class UserLoginInterceptor implements HandlerInterceptor {
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if(request.getSession().getAttribute("loginState") == null || request.getSession().getAttribute("userType") == null || request.getSession().getAttribute("loginName") == null) {
			response.sendRedirect("redirect:/");
			return false;
		}
		if(request.getSession().getAttribute("loginState").equals(IdentifierString.LOGIN_STATE_LOGIN) && request.getSession().getAttribute("userType").equals(IdentifierString.USER_TYPE_USER)) {
			String userName = request.getSession().getAttribute("loginName").toString();
	        System.out.println("当前用户已登录，登录的用户名为： " + userName);
	        return true;
		}else {
			response.sendRedirect("redirect:/");
			return false;
		}
        
		
        
        
    }

	
}
