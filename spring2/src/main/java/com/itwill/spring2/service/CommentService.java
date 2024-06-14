package com.itwill.spring2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring2.dto.CommentCreateDto;
import com.itwill.spring2.dto.CommentItemDto;
import com.itwill.spring2.dto.CommentUpdateDto;
import com.itwill.spring2.repository.Comment;
import com.itwill.spring2.repository.CommentDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor

@Service
public class CommentService {

	private final CommentDao commentDao; // 생성자에 의한 의존성 주입
	
	public List<CommentItemDto> readByPostId(Integer postId) {
		log.debug("readByPostId(postId={})", postId);
		
		// 리포지토리(영속성) 계층의 메서드를 호출해서 comments 테이블의 데이터를 검색.
		List<Comment> list = commentDao.selectByPostId(postId);
		
		// List<Comment>를 List<CommentItemDto>로 변환해서 리턴.
//		List<CommentItemDto> result = new ArrayList<>();
//		for (Comment c : list) {
//			CommentItemDto dto = CommentItemDto.fromEntity(c);
//			result.add(dto);
//		}
//		return result;
		
		return list.stream()
              .map(CommentItemDto::fromEntity) // map((x) -> CommentItemDto.fromEntity(x))
              .toList();
	}
	
	public int create(CommentCreateDto dto) {
		log.debug("create({})", dto);
		
		int result = commentDao.insert(dto.toEntity());
		log.debug("insert 결과 = {}", result);
		
		return result;
	}
	
	public int update(CommentUpdateDto dto) {
		log.debug("update({})", dto);
		
		int result = commentDao.update(dto.toEntity());
		log.debug("update 결과 = {}", result);
		
		return result;
	}
	
	public int deleteById(Integer id) {
		log.debug("deleteById(id = {})", id);
		
		int result = commentDao.deleteById(id);
		log.debug("deleteById 결과 = {}", result);
		
		return result;
		
	}
	
	public int deleteByPostId(Integer postId) {
		log.debug("deleteByPostId(postId = {})", postId);
		
		int result = commentDao.deleteByPostId(postId);
		log.debug("deleteByPostId 결과 = {}", result);
		
		return result;
		
	}
	
	public Comment readById(Integer id) {
		log.debug("readById(id = {})", id);
		return commentDao.selectById(id);
		
	}
	
//	public Post readById(Integer id) {
//		log.debug("readById(id = {})", id);
//		return postDao.selectById(id);
//	}
//
//	
//    public List<PostListDto> search(PostSearchDto dto) {
//    	log.debug("search({})", dto);
//    	
//        List<Post> list = postDao.search(dto);
//        return list.stream()
//                .map(PostListDto::fromEntity)
//                .toList();
//    }
	
//    public CommentListDto readById(Integer id) {
//        log.debug("readById(id={})", id);
//        
//        Comment comment = commentDao.selectById(id);
//        log.debug(comment.toString());
//        
//        return CommentListDto.fromEntity(comment);
//    }

}

