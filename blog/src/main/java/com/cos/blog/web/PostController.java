package com.cos.blog.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PricipalDetails;
import com.cos.blog.domain.post.Post;
import com.cos.blog.service.PostService;
import com.cos.blog.web.dto.CommonRespDto;
import com.cos.blog.web.post.dto.PostSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostController {
	
	private final PostService postService;
	
	
	@GetMapping("/")
	public String findAll(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5) Pageable pageable,
			@AuthenticationPrincipal PricipalDetails principalDetails) {
		
//		System.out.println("누구로 로그인? : " + principalDetails.getAttributes());
//		System.out.println("누구로 로그인? : " + principalDetails.getUser().getUsername());
		Page<Post> posts = postService.전체찾기(pageable);
		
		// 모델에 담는 것은리퀘스트 디스패쳐에 담고 포워딩한 거랑 똑같음
		model.addAttribute("posts", posts);
		return "post/list";
	}
	
	@GetMapping("/post/{id}")
	public String detail(@PathVariable int id, Model model) {
		Post postEntity = postService.상세보기(id);
		model.addAttribute("post",postEntity);
		
		return "/post/detail"; //ViewResolver가 .jsp파일을 찾아줌
	}
	
	
	@GetMapping("/post/search")
	public String searchByTitle(Model model ,@RequestParam(value = "searchname") String title, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5) Pageable pageable) {
		System.out.println("제목 넘어와짐? : " + title);
		Page<Post> posts = postService.검색(title, pageable);
		
		
		model.addAttribute("posts",posts);
		model.addAttribute("mode", "search");
		model.addAttribute("searchname", title);
		
		return "post/list"; //ViewResolver가 .jsp파일을 찾아줌
	}
	
	
	@GetMapping("/post/saveForm")
	public String saveForm() {
		return "/post/saveForm";
	}
	
	@GetMapping("/post/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		Post postEntity = postService.상세보기(id);
		model.addAttribute("post",postEntity);
		return "/post/updateForm"; //ViewResolver가 .jsp파일을 찾아줌
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
	
	
	
	@DeleteMapping("/post/{id}")
	public @ResponseBody CommonRespDto<?> delete(@PathVariable int id) {
		postService.삭제하기(id);
		
		return new CommonRespDto<>(1,null);
	}
	
	@PutMapping("/post/{id}")
	public @ResponseBody CommonRespDto<?> update(@PathVariable int id, @RequestBody PostSaveReqDto postSaveReqDto ) {
		postService.수정하기(id, postSaveReqDto);
		System.out.println("수정");
		return new CommonRespDto<>(1,null);
	}
	
	
}
