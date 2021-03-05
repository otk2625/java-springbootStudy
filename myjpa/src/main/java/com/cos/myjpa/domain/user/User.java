package com.cos.myjpa.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.myjpa.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User { // User 1 <-> Post N
	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence
	private Long id;
	private String username;
	private String password;
	private String email;
	
	@CreationTimestamp //값이 들어올때 자동으로 현재 시간이 들어감
	private LocalDateTime createData;
	
	//mappedBy에 변수명을 넣어야함, User의 FK인 user
	//가본 전략은 LAZY 가져와야할 가져올 수가 N개라면 기본전략이 LAZY
	//역방향 매핑
	@JsonIgnoreProperties({"user"}) //Post안에 있는 user를 getter하지 마!
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY) //난 FK의 주인이 아니다. FK는 user 변수명이다
	private List<Post> posts;
	
//	@Transient
//	private int value; 
}
