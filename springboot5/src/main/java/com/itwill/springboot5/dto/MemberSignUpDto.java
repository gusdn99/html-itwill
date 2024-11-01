package com.itwill.springboot5.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.itwill.springboot5.domain.Member;

import lombok.Data;

@Data
public class MemberSignUpDto {
	private String username;
	private String password;
	private String email;
	
	public Member toEntity(PasswordEncoder encoder) {
		return Member.builder()
		.username(username)
		.password(encoder.encode(password)) // 비밀번호 암호화하기 위해 사용.
		.email(email)
		.build();
	}
}
