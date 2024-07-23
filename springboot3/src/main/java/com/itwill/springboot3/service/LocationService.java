package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.stereotype.Service;

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
	
	public List<Location> read() {
		log.info("read()");
		
		// 영속성(저장소) 계층의 메서드를 호출해서 DB 쿼리를 실행.
		return locRepo.findAll();
	}
	
	public Location readById(Integer id) {
		log.info("readById(id={})", id);
		
		return locRepo.findById(id).orElseThrow();
		
	}
	
}
