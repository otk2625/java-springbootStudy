package com.cos.myjpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.domain.user.UserRepository;
import com.cos.myjpa.web.user.dto.UserJoinReqDto;
import com.cos.myjpa.web.user.dto.UserLoginReqDto;
import com.cos.myjpa.web.user.dto.UserRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public List<UserRespDto> 전체찾기() {
		// Post return 하기 싫을경우!
		// 방법1 - 리스트로 만들어서 for문으로 하나씩 넣기
		List<User> usersEntity = userRepository.findAll();
		
		List<UserRespDto> userRespDtos = new ArrayList<>();
		for(int i=0; i<usersEntity.size(); i++) {
			userRespDtos.add(new UserRespDto(usersEntity.get(i)));
		}
		
		// 방법2 - map방법 (자바 stream)
//		List<UserRespDto> userRespDtos = usersEntity.stream().map((u)->{
//			return new UserRespDto(u);
//		}).collect(Collectors.toList());
		
		return userRespDtos;
	}
	@Transactional(readOnly = true)
	public UserRespDto 한건찾기(Long id) {
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		UserRespDto userRespDto = new UserRespDto(userEntity);
		
		return userRespDto;
	}
	@Transactional(readOnly = true)
	public User 프로파일(Long id) {
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		return userEntity;
	}
	@Transactional(readOnly = true)
	public User 로그인(UserLoginReqDto userLoginReqDto) {
		User userEntity = userRepository
				.findByUsernameAndPassword(userLoginReqDto.getUsername(), userLoginReqDto.getPassword());
		return userEntity;
	}

	//여기는 read가 아닌 write이기 때문에 트랜잭션을 발동 시켜야함
	//다른곳에서 동시 접근 못하게 막는다
	@Transactional
	public User 회원가입(UserJoinReqDto userJoinReqDto) {
		User userEntity = userRepository.save(userJoinReqDto.toEntity());
		
		return userEntity;
	}
}
