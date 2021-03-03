package com.cos.myjpa.web.post.dto;

import com.cos.myjpa.domain.post.Post;

import lombok.Data;


@Data
public class PostSaveReqDto {
	// 여기서는 @Valid해야함
	private String title;
	private String content;
	
	public Post toEntity() {
		return Post.builder()
				.title(title)
				.content(content).build();
	}
}
