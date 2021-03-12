package com.cos.blog.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PricipalDetails;
import com.cos.blog.domain.post.Post;
import com.cos.blog.service.PostService;
import com.cos.blog.web.post.dto.PostSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {
	
	private final PostService postService;
	
	
	@GetMapping("/")
	public String findAll(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5) Pageable pageable,
			@AuthenticationPrincipal PricipalDetails principalDetails) {
		
		System.out.println("누구로 로그인? : " + principalDetails.getAttributes());
		System.out.println("누구로 로그인? : " + principalDetails.getUser().getUsername());
		Page<Post> posts = postService.전체찾기(pageable);
		
		// 모델에 담는 것은리퀘스트 디스패쳐에 담고 포워딩한 거랑 똑같음
		model.addAttribute("posts", posts);
		return "post/list";
	}
	
	
	@GetMapping("/post/saveForm")
	public String saveForm() {
		return "/post/saveForm";
	}
	
	@PostMapping("/post")
	public String save(PostSaveReqDto postSaveReqDto, @AuthenticationPrincipal PricipalDetails principalDetails) {
		Post post = postSaveReqDto.toEntity();
		// User 객체를 받아옴
		post.setUser(principalDetails.getUser());
		
		// 영속화된 엔티티
		Post postEntity = postService.글쓰기(post);
		
		if(postEntity == null) {
			return "post/saveForm";
		}else {
			return "redirect:/";
		}
	}
}
