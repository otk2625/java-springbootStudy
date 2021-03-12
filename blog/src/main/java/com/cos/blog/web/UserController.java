package com.cos.blog.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PricipalDetails;

@Controller
public class UserController {
	
	// 로그인, 로그아웃, 회원가입(AuthController), 회원정보 변경
	
	// 403 뜸 : 인증 안 됨.
	@GetMapping("/user") //세션 접근하는 방법!
	public @ResponseBody String findAll(@AuthenticationPrincipal PricipalDetails pricipalDetails) { // @Controller + @ResponseBody = @RestController
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		PricipalDetails pricipalDetails = (PricipalDetails) authentication.getDetails();
		System.out.println("getUsername : " + pricipalDetails.getUsername());
		return "User";
	}
	
	
}
