package com.itwill.spring2.dto;

import com.itwill.spring2.repository.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSearchDto {
	private String category;
	private String keyword;
	
//	public static PostSearchDto fromEntity(Post post) {
//        return PostSearchDto.builder()
//                .category("")
//                .keyword(post.getTitle() + " " + post.getContent() + " " + post.getAuthor())
//                .build();
//    }

//	private String title;
//    private String content;
//    private String author;
//    
//    public static PostSearchDto fromEntity(Post post) {
//        return PostSearchDto.builder()
//                .title(post.getTitle())
//                .content(post.getContent())
//                .author(post.getAuthor())
//                .build();
//    }

}

