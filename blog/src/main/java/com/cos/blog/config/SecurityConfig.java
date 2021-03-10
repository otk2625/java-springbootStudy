package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration //IoC등록
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	//IoC 등록만 하면 AuthenticationManaget가 Bcrypt가 자동 검증해줌
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
		.antMatchers("/user","/post").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/loginForm") //데이터를 X-WWW-form-urlencoded json으로 던지면 받지 못함!
		.loginProcessingUrl("/login"); // 여기서 /login주소가 들어오면 스프링 security가 낚아챈다 
	}
}
