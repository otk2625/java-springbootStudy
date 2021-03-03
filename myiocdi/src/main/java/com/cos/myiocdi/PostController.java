package com.cos.myiocdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Component(용도없음), Configuration(설정파일), Service(서비스), Repository(저장소), Bean

// RestController, Controller -> IoC(싱글톤)등록 new PostController(@Bean의 객체를 주입);
@RestController
public class PostController {
	
	private final Robot robot; // DI

	public PostController(Robot robot) {
		super();
		this.robot = robot;
	}



	@GetMapping("/")
	public String home() {
	
		return "home"+robot.getName();
	}
}
