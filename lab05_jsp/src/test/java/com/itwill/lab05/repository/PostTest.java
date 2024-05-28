package com.itwill.lab05.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostTest {
	private static final Logger log = LoggerFactory.getLogger(PostTest.class);
	
	private PostDao dao = PostDao.INSTANCE; // singleton 객체
	
//	@Test
	public void test() {
		// Post 타입 객체 생성 - Builder 디자인 패턴.
		Post p = Post.builder()
				.title("테스트")
				.author("관리자")
				.content("builder design pattern")
				.id(1)
				.build();
		
		// assertNotNull(arg): arg가 null이 아니면 JUnit 테스트 성공, null이면 테스트 실패.
		// assertNull(arg): arg가 null이면 단위 테스트 성공, null이 아니면 테스트 실패.
		Assertions.assertNotNull(p);
		log.debug("p = {}", p);
	}

	@Test
	public void testPostDao() {
		Assertions.assertNotNull(dao); // PostDao 타입 객체가 null이 아니면 단위 테스트 성공.
		log.debug("dao = {}", dao); // 17:26:40.074 DEBUG [com.itwill.lab05.repository.PostTest    ] dao = INSTANCE
		
		List<Post> result = dao.select();
		Assertions.assertEquals(3, result.size());
		// result.size(): 데이터베이스의 행의 개수. expected 값과 actual 값이 같으면 단위 테스트 성공, 다르면 실패.
		for (Post p : result) {
			log.debug(p.toString());
		}
	}
}
