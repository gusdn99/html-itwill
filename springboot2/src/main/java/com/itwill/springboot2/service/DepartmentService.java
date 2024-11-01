package com.itwill.springboot2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DepartmentService {

	// 생성자에 의한 의존성 주입: (1) RequiredArgsConstructor + (2) final field
	private final DepartmentRepository deptRepo;

	public List<Department> read() {
		log.info("read()");

		// 영속성(저장소) 계층의 메서드를 호출해서 DB 쿼리를 실행.
		return deptRepo.findAll();
	}

	public Department readById(Integer id) {
		log.info("readById(id={})", id);

		return deptRepo.findById(id).orElseThrow();
//		return deptRepo.findById(id).orElse(null);
	}

}
