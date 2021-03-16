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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blog.config.auth.PricipalDetails;
import com.cos.blog.domain.post.Post;
import com.cos.blog.service.PostService;
import com.cos.blog.service.ReplyService;
import com.cos.blog.web.dto.CommonRespDto;
import com.cos.blog.web.post.dto.PostSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReplyController {
	
	private final ReplyService replyService;
	
	
	
	@DeleteMapping("/reply/{id}")
	public @ResponseBody CommonRespDto<?> delete(@PathVariable int id, @AuthenticationPrincipal PricipalDetails pricipalDetails) {
		
		// 모든 컨트롤러에 삭제하기, 수정하기는 무조건 동일인물이 로그인했는지 확인!!
		int result = replyService.삭제하기(id, pricipalDetails.getUser().getId());
		
		return new CommonRespDto<>(result,null);
	}
	

}
