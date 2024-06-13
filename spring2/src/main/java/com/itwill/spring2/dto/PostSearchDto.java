package com.itwill.spring2.dto;

import lombok.Data;

@Data
//검색 요청에서의 요청 파라미터 검색 카테고리와 검색어를 저장하기 위한 DTO
public class PostSearchDto {
	private String category;
	private String keyword;
<<<<<<< HEAD
	private Integer id;
	private String title;
	private String author;
	private LocalDateTime modifiedTime;
	
	public static PostSearchDto fromEntity(Post post) {
        return PostSearchDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.author(post.getAuthor())
				.modifiedTime(post.getModifiedTime())
                .build();
    }

=======
>>>>>>> branch 'main' of https://github.com/gusdn99/html-itwill.git

}
