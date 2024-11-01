package com.itwill.springboot5.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.itwill.springboot5.domain.Member;
import com.itwill.springboot5.dto.MemberSignUpDto;
import com.itwill.springboot5.service.MemberService;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberSvc;
	
	@GetMapping("/signin")
	// SecurityConfig.securityFilterChain() 메서드 안의 formLogin() 메서드 안의 아규먼트로 설정한 요청 주소.
	// 로그인 페이지 화면만 html로 만듦.
	// 로그인 성공하면 자동으로 성공 후의 타겟 화면으로 리다이렉트가 되므로
	// @PostMapping은 만들 필요 없음. 기본적으로 securityFilterChain() 메서드 안에 있음.
	public void signIn() {
		log.info("GET signIn");
	}
	
	@GetMapping("/signup")
	public void signUp() {
		log.info("GET signUp()");
	}
	
	@PostMapping("/signup")
	public String signUp(MemberSignUpDto dto) {
		log.info("POST signUp(dto = {})", dto);
		
		// 서비스 계층의 메서드를 호출해서 회원가입 정보들을 DB에 저장(save).
		Member member = memberSvc.create(dto);
		log.info("회원가입: {}", member);
		
		// 회원가입 성공하면 로그인 페이지로 이동.
		return "redirect:/member/signin";
	}
	
}
