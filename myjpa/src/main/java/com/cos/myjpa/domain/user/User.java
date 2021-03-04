package com.cos.myjpa.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.myjpa.domain.post.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence
	private Long id;
	private String username;
	private String password;
	private String email;
	
	@CreationTimestamp //값이 들어올때 자동으로 현재 시간이 들어감
	private LocalDateTime createData;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY) //난 연관관계의 주인이 아님! 그러니까 PK 만들지마
	private List<Post> posts;
}
