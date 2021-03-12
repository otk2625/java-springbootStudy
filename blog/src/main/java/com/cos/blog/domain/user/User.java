package com.cos.blog.domain.user;



import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Data
@AllArgsConstructor
@Entity
public class User {

	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence
	private Integer id;
	
	@Column(length = 100, nullable = false) 
	private String username;
	@Column(length = 100, nullable = true) 
	private String password;
	@Column(length = 50, nullable = false)  
	private String email;
	
	@Enumerated(EnumType.STRING)
	private RoleType role; // ADMIN, USER
	
	@CreationTimestamp //값이 들어올때 자동으로 현재 시간이 들어감
	private Timestamp createData;

}
