package com.cos.blog.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;
import com.cos.blog.web.user.dto.UserUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public User 회원수정(int id, UserUpdateReqDto userUpdateReqDto) {
		// 영속성 컨텍스트
		User userEntity = userRepository.findById(id).get(); //1차 캐시
		String rawPassword = bCryptPasswordEncoder.encode(userUpdateReqDto.getPassword());
		
		userEntity.setPassword(rawPassword);
		userEntity.setEmail(userUpdateReqDto.getEmail());
		
		return userEntity;
	}// update는 항상 더티체킹
}
