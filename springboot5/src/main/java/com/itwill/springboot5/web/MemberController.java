package com.itwill.springboot5.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
	
	@GetMapping("/signin")
	// SecurityConfig.securityFilterChain() 메서드 안의 formLogin() 메서드 안의 아규먼트로 설정한 요청 주소.
	// 로그인 페이지 화면만 html로 만듦.
	// 로그인 성공하면 자동으로 성공 후의 타겟 화면으로 리다이렉트가 되므로
	// @PostMapping은 만들 필요 없음. 기본적으로 securityFilterChain() 메서드 안에 있음.
	public void signIn() {
		log.info("GET signIn");
	}
	
}
