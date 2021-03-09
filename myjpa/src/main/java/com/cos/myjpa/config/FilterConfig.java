package com.cos.myjpa.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cos.myjpa.filter.MyAuthenticationFilter;


@Configuration
public class FilterConfig {
	
	@Bean //리턴되는 어떤값을 IoC에 등록할 것
	public FilterRegistrationBean<MyAuthenticationFilter> authenticationFilterRegister() {
		FilterRegistrationBean<MyAuthenticationFilter> bean =
				new FilterRegistrationBean<>(new MyAuthenticationFilter());
		
		//세팅
		bean.addUrlPatterns("/test"); //'/*' : 모든 주소에 다 요청!
		bean.setOrder(0); //실행순서
		
		return bean;
	}
}
