package com.itwill.springboot5.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot5.domain.Comment;
import com.itwill.springboot5.domain.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CommentRepositoryTest {
	// CommentRepository의 CRUD 기능을 단위 테스트.
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Test
	public void testDependencyInjection() {
		assertThat(commentRepo).isNotNull();
		log.info("commentRepo = {}", commentRepo);
	}
	
//	@Test
	public void testFindAll() {
		List<Comment> list = commentRepo.findAll();
		assertThat(list.size()).isEqualTo(0);
		list.forEach(System.out::println);
	}
	
//	@Test
	public void testSave() {
		
		Comment entity = Comment.builder()
				.post(Post.builder().id(5L).build())
				.ctext("댓글 저장 테스트")
				.writer("user")
				.build();
		
		log.info("save 전: {}", entity);
		commentRepo.save(entity);
		log.info("save 후: {}", entity);
	}
	
//	@Test
	public void testUpdate() {
		Comment entity = commentRepo.findById(1L).orElseThrow();
		log.info("findById 결과 = {}", entity);
		
		entity.update("댓글 수정 테스트");
		log.info("update 호출 = {}", entity);
		
		commentRepo.save(entity);
		log.info("save 호출 = {}", entity);
		
	}
	
//	@Test
	public void testDeleteById() {
		commentRepo.deleteById(1L);
	}
	
//	@Test
	public void testDeleteByPostId() {
		
	}
	
//	@Test
	public void makeDummyData() {
		List<Comment> data = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			Comment comment = Comment.builder()
					.post(Post.builder().id(7L).build())
					.ctext("더미 테스트 #" + i)
					.writer("test")
					.build();
			data.add(comment);
		}
		
		commentRepo.saveAll(data);
	}
	
}
