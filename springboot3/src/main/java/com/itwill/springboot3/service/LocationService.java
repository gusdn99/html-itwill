package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Location;
import com.itwill.springboot3.repository.LocationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class LocationService {
	
	// 생성자에 의한 의존성 주입: (1) RequiredArgsConstructor + (2) final field
	private final LocationRepository locRepo;
	
	@Transactional(readOnly = true) // 엔터티 객체들을 읽기 전용으로 검색.
									// 엔터티의 변화가 없고 읽기 전용으로만 보이고 싶을 때 사용. (기본값은 false)
	public Page<Location> read(int pageNo, Sort sort) {
		log.info("read(pageNo = {}), sort = {}", pageNo, sort);
		
		// Pageable 객체 생성: PageRequest.of(페이지번호, 한 페이지의 아이템 개수, 정렬기준)
		Pageable pageable = PageRequest.of(pageNo, 4, sort);
		
		// findAll(Pageable pageable) 메서드를 호출.
		Page<Location> page = locRepo.findAll(pageable);
		log.info("hasPrevious = {}", page.hasPrevious()); // 이전 페이지가 있는지 여부(있으면 true, 없으면 false)
		log.info("hasNext = {}", page.hasNext()); // 다음 페이지가 있는지 여부(있으면 true, 없으면 false)
		log.info("getTotalPages = {}", page.getTotalPages()); // 전체 페이지 개수.
		
		// 영속성(저장소) 계층의 메서드를 호출해서 DB 쿼리를 실행.
		return page;
	}
	
	@Transactional(readOnly = true)
	public Location readById(Integer id) {
		log.info("readById(id={})", id);
		
		return locRepo.findById(id).orElseThrow();
		
	}
	
}
