package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot3.domain.Job;
import com.itwill.springboot3.repository.JobRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class JobService {
	
	// 생성자에 의한 의존성 주입: (1) RequiredArgsConstructor + (2) final field
	private final JobRepository jobRepo;
	
	public List<Job> read() {
		log.info("read()");
		
		// 영속성(저장소) 계층의 메서드를 호출해서 DB 쿼리를 실행.
		return jobRepo.findAll();
	}
	
	public Job readById(String id) {
		log.info("readById(id={})", id);
		
		return jobRepo.findById(id).orElseThrow();
		
	}
	
}
