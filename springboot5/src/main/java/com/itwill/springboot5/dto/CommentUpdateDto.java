package com.itwill.springboot5.dto;

import com.itwill.springboot5.domain.Comment;

import lombok.Data;

@Data
public class CommentUpdateDto {
	private Long id;
	private String ctext;
	
	// CommentUpdateDto 타입을 Comment 타입으로 변환해서 리턴.
	public Comment toEntity() {
		return Comment.builder()
				.id(id)
				.ctext(ctext)
				.build();
	}
	
}
