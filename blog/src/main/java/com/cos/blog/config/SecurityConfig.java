package com.cos.blog.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.cos.blog.config.oauth.Oauth2DetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration //IoC등록
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final Oauth2DetailsService oauth2DetailsService;
	
	//IoC 등록만 하면 AuthenticationManaget가 Bcrypt가 자동 검증해줌
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
		.antMatchers("/user/**","/post/**","/reply/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")//ROLE_는 강제성이있음. 롤 검증시
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/loginForm") //데이터를 X-WWW-form-urlencoded json으로 던지면 받지 못함!
		.loginProcessingUrl("/login") // 여기서 /login주소가 들어오면 스프링 security가 낚아챈다 
		.defaultSuccessUrl("/") //로그인이 성공하면 어디로 갈 것 인가?
		.and()
		.oauth2Login()
		.userInfoEndpoint()
		.userService(oauth2DetailsService);
		
	}
}
