package com.cos.myjpa.config;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.handler.ex.MyAuthenticationException;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{ //view컨트롤, model컨트롤, Controller

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptor() {
			
			//컨트롤러 실행 직전에 동작
			//반환값이 true일 경우 정상적 진행
			//반환값이 false면 컨트롤러 진입 안함
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
			
				HttpSession session = request.getSession();
				
				User principal = (User)session.getAttribute("principal");
				if(principal == null) { //로그인이 안되었다는 뜻
					throw new MyAuthenticationException();
					//return false;
				}else {
					return true;
				}
				
			}
		}).addPathPatterns("/user").addPathPatterns("/post");
	}
}
