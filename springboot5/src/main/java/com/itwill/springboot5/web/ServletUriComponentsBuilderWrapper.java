package com.itwill.springboot5.web;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// thymeleaf 3.x 버전부터 ServletUriComponentsBuilder 타입을 사용할 수 없음.
public class ServletUriComponentsBuilderWrapper {
	
	public static ServletUriComponentsBuilder fromCurrentRequest() {
		return ServletUriComponentsBuilder.fromCurrentRequest();
	}

}
