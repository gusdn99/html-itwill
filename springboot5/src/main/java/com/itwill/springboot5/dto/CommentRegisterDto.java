package com.itwill.springboot5.dto;

import lombok.Data;

@Data
public class CommentRegisterDto {
	private Long postId;
	private String ctext;
	private String writer;
	
}
