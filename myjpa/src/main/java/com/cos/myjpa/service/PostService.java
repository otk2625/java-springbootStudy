package com.cos.myjpa.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.cos.myjpa.domain.post.Post;
import com.cos.myjpa.domain.post.PostRepository;
import com.cos.myjpa.domain.user.User;
import com.cos.myjpa.web.dto.CommonRespDto;
import com.cos.myjpa.web.post.dto.PostRespDto;
import com.cos.myjpa.web.post.dto.PostSaveReqDto;
import com.cos.myjpa.web.post.dto.PostUpdateReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {
	private final PostRepository postRepository;
	private final HttpSession session;

	public Post 한건저장(PostSaveReqDto postSaveDto, User principal) {
		// Post안에 유저정보 집어 넣기
		Post post = postSaveDto.toEntity();
		post.setUser(principal);

		Post postEntity = postRepository.save(post);
		// 내부적으로 실패시 => Exception한다! 1이 리턴이 되지 않음!
		postEntity.setUser(principal);

		return postEntity;
	}

	public PostRespDto 한건찾기(Long id) {
		// Optional get(), orElseGet(), orElseThrow()
		// 웬만하면 orElseThrow()사용!
		Post postEntity = postRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		}); // 화살표 함수로 처리 가능

		// Dto사용해서 User정보 안보이게
		PostRespDto postRespDto = new PostRespDto(postEntity);

		return postRespDto;
	}

	public List<Post> 모두찾기() {
		List<Post> postEntity = postRepository.findAll();
		return postEntity;
	}

	public Post 한건수정(PostUpdateReqDto postUpdateReqDto, Long id) {
		// 1. id로 찾기
		// 영속화! => 영속성 컨텍스트에서 가져오는것
		Post postEntity = postRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});

		// 2. 기존에 있던걸 가져와서 update처리
		postEntity.setTitle(postUpdateReqDto.getTitle());
		postEntity.setContent(postUpdateReqDto.getContent());

		// 3. save
		Post postUpdateEntity = postRepository.save(postEntity); // 더티 체킹을 사용해야 하는데 그러려면 @Service 만들어야 가능!!
		
		return postUpdateEntity;
	}

	public void 한건삭제(Long id) {
		postRepository.deleteById(id);
	}
}
