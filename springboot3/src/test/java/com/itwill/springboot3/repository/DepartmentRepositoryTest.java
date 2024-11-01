package com.itwill.springboot3.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Department;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class DepartmentRepositoryTest {
	
	@Autowired
	private DepartmentRepository deptRepo;
	
//	@Test
	public void testDependencyInjection() {
		// DepartmentRepository 객체가 의존성 주입을 받을 수 있는지 테스트.
		assertThat(deptRepo).isNotNull();
		log.info("deptRepo={}", deptRepo);
	}
	
//	@Test
	public void testFindAll() {
		// departments 테이블 전체 검색 테스트:
		long count = deptRepo.count();
		assertThat(count).isEqualTo(27);
		log.info("count={}", count);
		
		List<Department> departments  = deptRepo.findAll();
		log.info("list[0]={}", departments .get(0));
		departments.forEach(System.out::println); // (x) -> System.out.prnitln(x)
	}
	
	@Transactional
	@Test
	public void testFindById() {
		// 1. DepartmentRepository.findById() 메서드 테스트
		// 2. Department 테이블 - Employees 테이블 간의 관계 테스트(MANAGER_ID - EMPLOYEE_ID)
		// 3. Department 테이블 - Locations 테이블 간의 관계 테스트(LOCATION_ID)
		
		// 테이블에 id(사번)가 존재하는 경우:
		Department dept = deptRepo.findById(10).orElseThrow();
		assertThat(dept.getId()).isEqualTo(10);
		log.info("dept={}", dept); // 1. DepartmentRepository.findById()
		log.info("dept.manager={}", dept.getManager()); // 2. MANAGER_ID
		log.info("dept.department={}", dept.getLocation()); // 3. LOCATION_ID
		
	}
}
