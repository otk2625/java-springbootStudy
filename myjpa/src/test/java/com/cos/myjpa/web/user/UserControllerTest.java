package com.cos.myjpa.web.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.domain.user.UserRepository;
import com.cos.myjpa.web.user.dto.UserJoinReqDto;
import com.cos.myjpa.web.user.dto.UserLoginReqDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EntityManager entityManager;

	@Test
	public void join() throws Exception {
		// given (테스트를 하기 위한 준비)
		UserJoinReqDto userEntity = new UserJoinReqDto("test", "1234", "test@naver.com");
		String content = new ObjectMapper().writeValueAsString(userEntity);

		// when(테스트 실행)
		ResultActions resultAction = mockMvc.perform(post("/join").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content).accept(MediaType.APPLICATION_JSON_UTF8));

		// then (검증)
		resultAction.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void login() throws Exception {
		User user = new User(1L, "test","1234","text@nate.com",null,null);
		userRepository.save(user);
		
		// given (테스트를 하기 위한 준비)
		UserLoginReqDto userEntity = new UserLoginReqDto("test", "1234");
		String content = new ObjectMapper().writeValueAsString(userEntity);

		// when(테스트 실행)
		ResultActions resultAction = mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content).accept(MediaType.APPLICATION_JSON_UTF8));

		// then (검증)
		resultAction.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}

}
