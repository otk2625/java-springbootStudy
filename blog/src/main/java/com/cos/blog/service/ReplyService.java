package com.cos.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.post.PostRepository;
import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.reply.ReplyRepository;
import com.cos.blog.web.post.dto.PostSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {
	private final ReplyRepository replyRepository;
	
	

	@Transactional
	public int 삭제하기(int id, int userId) {
		Reply replyEntity = replyRepository.findById(id).get();
		if(replyEntity.getUser().getId() == userId) {
			replyRepository.deleteById(id);
			return 1;
		}else {
			return -1;
		}
		
		
	}


	@Transactional
	public Reply 댓글쓰기(Reply entity) {
		return replyRepository.save(entity);
		
	}
	
	
}
