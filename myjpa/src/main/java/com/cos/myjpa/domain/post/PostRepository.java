package com.cos.myjpa.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

//﻿@Repository가 생략가능하다!! 내부적으로 IoC에 등록된다!
public interface PostRepository extends JpaRepository<Post, Long> {

}
