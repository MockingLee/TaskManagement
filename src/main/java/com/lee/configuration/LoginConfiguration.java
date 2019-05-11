package com.lee.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lee.interceptor.LoginInterceptor;
@Configuration
public class LoginConfiguration implements WebMvcConfigurer {
	
	@Autowired
    private LoginInterceptor logInterceptor;

    /**
     * 实例化WebMvcConfigurer接口
     *
     * @return
     */
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
       
		/** 
		 * 拦截器配置 addPathPatterns 接 要拦截的url    excludePathPatterns 接 不需要拦截的  例如登录  不需要拦截
		 */
		
        registry.addInterceptor(logInterceptor).addPathPatterns("/**").excludePathPatterns("/" , "/login" );
        WebMvcConfigurer.super.addInterceptors(registry);
    }

	
}
		

