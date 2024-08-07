package com.itwill.springboot5.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MemberTest {
	
	@Test
	public void testEqualsAndHashCode() {
		Member member1 = Member.builder()
				.id(1L)
				.username("admin")
				.password("1111")
				.email("admin@itwill.com")
				.build();
		log.info("member1 = {}", member1);
		
		Member member2 = Member.builder()
				.id(2L)
				.username("admin")
				.password("2222")
				.email("admin2222@itwill.com")
				.build();
		log.info("member2 = {}", member2);
		
		assertThat(member1).isEqualTo(member2);
		// member1.equals(member2) 리턴 값이 true인지 테스트.
		// => username 필드에만 @EqualsAndHashCode.Include 애너테이션이 있어서 성공함.
		// => @EqualsAndHashCode.Include 애너테이션이 붙은 필드의 아규먼트가 같으면, isEqualTo() 메서드를,
		// => 아규먼트가 다르면, isNotEqualTo() 메서드를 사용해서 비교함.
	}
}
