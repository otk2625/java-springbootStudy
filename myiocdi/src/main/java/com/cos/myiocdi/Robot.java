package com.cos.myiocdi;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Getter
//@Component  // 런타임때 ioc 컨테이너 등록
//@Configuration
//@Service
//@Repository
public class Robot {
	private String name = "건담";
	
}
