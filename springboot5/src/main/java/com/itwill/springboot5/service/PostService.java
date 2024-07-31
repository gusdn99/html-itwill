package com.itwill.springboot5.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostCreateDto;
import com.itwill.springboot5.dto.PostListItemDto;
import com.itwill.springboot5.dto.PostSearchRequestDto;
import com.itwill.springboot5.dto.PostUpdateDto;
import com.itwill.springboot5.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
	
	private final PostRepository postRepo;
	
	@Transactional(readOnly = true)
	public Page<PostListItemDto> read(int pageNo, Sort sort) {
		log.info("read(pageNo = {}), sort = {}", pageNo, sort);
		
		// Pageable 객체 생성: PageRequest.of(페이지번호, 한 페이지의 아이템 개수, 정렬기준)
		Pageable pageable = PageRequest.of(pageNo, 5, sort);
		
		// findAll(Pageable pageable) 메서드를 호출.
		Page<Post> page = postRepo.findAll(pageable);
		log.info("page.getTotalPages = {}", page.getTotalPages()); // 전체 페이지 개수.
		log.info("page.number = {}", page.getNumber()); // 현재 페이지 번호
		log.info("page.hasPrevious = {}", page.hasPrevious()); // 이전 페이지가 있는지 여부(있으면 true, 없으면 false)
		log.info("page.hasNext = {}", page.hasNext()); // 다음 페이지가 있는지 여부(있으면 true, 없으면 false)
		
		// Page<Post> 타입을 Page<PostListItemDto> 타입으로 변환해서 리턴.
		// (x) -> PostListItemDto.fromEntity(x)
		Page<PostListItemDto> posts = page.map(PostListItemDto::fromEntity);
		
		return posts;
		
	}
	
//	@Transactional(readOnly = true)
//	public List<PostListItemDto> read() {
//		log.info("read()");
//		
//		// 영속성(persistence/repository) 계층의 메서드를 호출해서 엔터티들의 리스트를 가져옴.
//		List<Post> list = postRepo.findAll();
//		log.info("list size = {}", list.size());
//		
//		// List<Post> 객체를 List<PostListItemDto> 타입으로 변환.
//		List<PostListItemDto> posts = list.stream()
//				.map(PostListItemDto::fromEntity) // (x) -> PostListItemDto.fromEntity(x)
//				.toList();
//		return posts;
//		
//	}
	
	@Transactional
	public Long create(PostCreateDto dto) {
		log.info("create(dto = {})", dto);
		
		// 영속성 계층의 메서드를 호출해서 DB insert 쿼리를 실행.
		Post entity = postRepo.save(dto.toEntity());
		log.info("entity = {}", entity);
		
		// DB에 insert된 레코드의 PK(id)를 리턴.
		return entity.getId();
	}
	
//	public Post create(PostCreateDto dto) {
//		log.info("create(dto = {})", dto);
//		
//		Post post = postRepo.save(dto.toEntity());
//		
//		return post;
//		
//	}
	
	@Transactional(readOnly = true)
	public Post readById(Long id) {
		log.info("readById(id = {})", id);
		
		Post post = postRepo.findById(id).orElseThrow();
		log.info("post = {}", post);
		
		return post;
	}
	
	@Transactional
	public void delete(Long id) {
		log.debug("delete(id = {})", id);
		
		postRepo.deleteById(id);
		
	}
	
	@Transactional
	public Post update(PostUpdateDto dto) {
		log.info("update(dto = {}", dto);
		
		// id로 Post 엔터티 객체를 찾음.(DB select 쿼리)
		Post entity = postRepo.findById(dto.getId()).orElseThrow();
		
		// DB에서 검색한 엔터티 객체의 필드들을 업데이트(수정)
		entity.update(dto.getTitle(), dto.getContent());
		
		// @Transactional 애너테이션을 사용한 경우, DB에서 검색한 entity 객체가 변경되면 update 쿼리가 자동으로 실행.
		// @Transactional 애너테이션을 사용하지 않은 경우, postRepo.save(entity) 메서드를 직접 호출해야 함.
		
		// postRepo.save(entity); 이거는 @Transactional 애너테이션이 없을 때 사용. (애너테이션 있으면 필요없음.)
		
		return entity;
	}
	
	@Transactional(readOnly = true)
	public Page<PostListItemDto> search(PostSearchRequestDto dto, Sort sort) {
		log.info("search(dto = {}, sort = {})", dto, sort);
		
		Pageable pageable = PageRequest.of(dto.getP(), 5, sort);
		
//		Page<Post> page = postRepo.findByTitleOrContent(dto.getKeyword(), pageable);
		Page<Post> result = null;
		
		switch (dto.getCategory()) {
		case "t":
			result = postRepo.findByTitleContainingIgnoreCase(dto.getKeyword(), pageable);
			break;
		case "c":
			result = postRepo.findByContentContainingIgnoreCase(dto.getKeyword(), pageable);
			break;
		case "tc":
			result = postRepo.findByTitleOrContent(dto.getKeyword(), pageable);
			break;
		case "a":
			result = postRepo.findByAuthorContainingIgnoreCase(dto.getKeyword(), pageable);
			break;
		}
		
		log.info("page.getTotalPages = {}", result.getTotalPages());
	    log.info("page.number = {}", result.getNumber());
	    log.info("page.hasPrevious = {}", result.hasPrevious());
	    log.info("page.hasNext = {}", result.hasNext());
		
		return result.map(PostListItemDto::fromEntity);
	}
	
//	@Transactional(readOnly = true)
//	public Page<PostListItemDto> search(PostSearchRequestDto dto, int pageNo, Sort sort) {
//	    log.info("search(dto = {}, pageNo = {}, sort = {})", dto, pageNo, sort);
//
//	    // Pageable 객체 생성
//	    Pageable pageable = PageRequest.of(pageNo, 5, sort);
//
//	    // searchPosts 메서드 호출
//	    Page<Post> page = postRepo.search(dto.getCategory(), dto.getKeyword(), pageable);
//
//	    log.info("page.getTotalPages = {}", page.getTotalPages());
//	    log.info("page.number = {}", page.getNumber());
//	    log.info("page.hasPrevious = {}", page.hasPrevious());
//	    log.info("page.hasNext = {}", page.hasNext());
//
//	    // Page<Post>를 Page<PostListItemDto>로 변환
//	    return page.map(PostListItemDto::fromEntity);
//	}
	
}
