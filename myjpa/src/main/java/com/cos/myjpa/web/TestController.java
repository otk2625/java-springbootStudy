package com.cos.myjpa.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.myjpa.domain.post.Post;
import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.post.dto.PostSaveReqDto;
import com.cos.myjpa.web.post.dto.PostUpdateReqDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class TestController {
	
	private final PostRepository postRepository;

	@PostMapping("/test/post")
	public CommonRespDto<?> save(@RequestBody PostSaveReqDto postSaveDto) { //title, content 
		
		//원래는 세션값을 넣어야함
		//User user = new User(1L, "ssar", "1234","ssar@nate.com",LocalDateTime.now());
		
		Post postEntity = postRepository.save(postSaveDto.toEntity());
		//내부적으로 실패시 => Exception한다! 1이 리턴이 되지 않음!
		//postEntity.setUser(user);
		
		return new CommonRespDto<>(1,"성공",postEntity);
	}
	
	@GetMapping("/test/post")
	public CommonRespDto<?> findAll(){
		List<Post> postEntity = postRepository.findAll();
		return new CommonRespDto<>(1,"성공",postEntity);
	}
	
	@GetMapping("/test/post/{id}")
	public CommonRespDto<?> findByID(@PathVariable Long id){
		
		Post postEntity = postRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		}); //화살표 함수로 처리 가능
		
		return new CommonRespDto<>(1, "성공", postEntity);
	}
	
	@PutMapping("/test/post/{id}")
	public CommonRespDto<?> update(@PathVariable Long id, @RequestBody PostUpdateReqDto postUpdateReqDto){
		//1. id로 찾기
		Post postEntity = postRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		}); 
		
		//2. 기존에 있던걸 가져와서 update처리
		postEntity.setTitle(postUpdateReqDto.getTitle());
		postEntity.setContent(postUpdateReqDto.getContent());
		
		//3. save
		Post postUpdateEntity = postRepository.save(postEntity); //더티 체킹을 사용해야 하는데 그러려면 @Service 만들어야 가능!!
		
		return new CommonRespDto<>(1,"성공", postUpdateEntity);
	}
	
	@DeleteMapping("/test/post/{id}")
	public CommonRespDto<?> deleteById(@PathVariable Long id){
		
		postRepository.deleteById(id);
		
		return new CommonRespDto<>(1, "성공", null);
	}
	
	
}
