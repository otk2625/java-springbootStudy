package com.cos.blog.domain.reply;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.user.RoleType;
import com.cos.blog.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@Entity
public class Reply {
	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence
	private Integer id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	//유저
	@ManyToOne()
	@JoinColumn(name = "userId")
	private User user;
	
	//게시물
	@ManyToOne()
	@JoinColumn(name = "postId")
	private Post post;
	
	@CreationTimestamp //값이 들어올때 자동으로 현재 시간이 들어감
	private Timestamp createData;
}
