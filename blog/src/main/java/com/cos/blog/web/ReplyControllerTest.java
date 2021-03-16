package com.cos.blog.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.post.PostRepository;
import com.cos.blog.domain.reply.ReplyRepository;
import com.cos.blog.web.dto.CommonRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyControllerTest {
	private final ReplyRepository replyRepository;
	private final PostRepository postRepository;
	
	
	// 게시글 상세보기시 (user, post, reply들)
	@GetMapping("/test/post/{id}")
	public CommonRespDto<?> test(@PathVariable int id) {
		postRepository.findAll();
		
		Post postEntity = postRepository.findById(id).get();
		return new CommonRespDto<>(1, postEntity);
	}
	
	
}
