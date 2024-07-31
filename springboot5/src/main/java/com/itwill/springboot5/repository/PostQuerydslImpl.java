package com.itwill.springboot5.repository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.domain.QPost;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PostQuerydslImpl extends QuerydslRepositorySupport implements PostQuerydsl{
	
	// QuerydslRepositorySupport 이거는 기본 생성자가 없어서, 아규먼트 갖는 생성자를 만들어야 함.
	// 엔터티 클래스를 아규먼트에 추가해야 함.
	public PostQuerydslImpl() {
		super(Post.class);
	}

	@Override
	public Post searchById(Long id) {
		log.info("searchById(id = {}", id);
		
		QPost post = QPost.post;
		JPQLQuery<Post> query  = from(post); // select p from Post p
		query.where(post.id.eq(id)); // query + where id = ?
		Post entity = query.fetchOne();
		
		return entity;
	}

}
