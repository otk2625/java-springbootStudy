package com.cos.blog.web.reply.dto;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReplySaveDto {
	private String content;
	private User user;
	private Post post;
	
	public Reply toEntity() {
		return Reply.builder()
				.content(content)
				.post(post)
				.user(user).build();
		
	}
	
}
