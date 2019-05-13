package com.Picasso.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.Picasso.interceptor.AdLoginInterceptor;
import com.Picasso.interceptor.UserLoginInterceptor;
@Configuration
public class LoginConfiguration implements WebMvcConfigurer {
	
	@Autowired
    private UserLoginInterceptor userLogInterceptor;
	@Autowired
	private AdLoginInterceptor adLoginInterceptor;

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
		
        registry.addInterceptor(userLogInterceptor).addPathPatterns("/userIndex").excludePathPatterns("/" , "/login");
        registry.addInterceptor(adLoginInterceptor).addPathPatterns("/adIndex").excludePathPatterns("/" , "loginAd");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

	
}
		

