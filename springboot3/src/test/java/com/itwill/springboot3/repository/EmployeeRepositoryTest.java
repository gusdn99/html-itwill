package com.itwill.springboot3.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository empRepo;
	
//	@Test
	public void testDependencyInjection() {
		// EmployeeRepository 객체가 의존성 주입을 받을 수 있는지 테스트.
		assertThat(empRepo).isNotNull();
		log.info("empRepo={}", empRepo);
	}
	
//	@Test
	public void testFindAll() {
		// employees 테이블 전체 검색 테스트:
		long count = empRepo.count();
		assertThat(count).isEqualTo(107L);
		log.info("count={}", count);
		
		List<Employee> list = empRepo.findAll();
		log.info("list[0]={}", list.get(0));
	}
	
	@Transactional
	@Test
	public void testFindById() {
		// 1. EmployeeRepository.findById() 메서드 테스트
		// 2. Employees 테이블 - Jobs 테이블 간의 관계 테스트(JOB_ID)
		// 3. Employees 테이블 - Employees 테이블 간의 관계 테스트(MANAGER_ID - EMPLOYEE_ID)
		// 4. Employees 테이블 - Departments 테이블 간의 관계 테스트(DEPARTMENT_ID)
		// 5. Employees 테이블 - Departments 테이블 - LOCATIONS 테이블 간의 관계 테스트(LOCATION_ID)
		// 6. Employees 테이블 - Departments 테이블 - LOCATIONS 테이블 - COUNTRIES 테이블 간의 관계 테스트(COUNTRY_ID)
		
		// 테이블에 id(사번)가 존재하는 경우:
		Employee emp = empRepo.findById(100).orElseThrow();
		log.info("emp={}", emp); // 1. EmployeeRepository.findById()
		log.info("emp.job={}", emp.getJob()); // 2. JOB_ID
		log.info("emp.manager={}", emp.getManager()); // 3. MANAGER_ID
		log.info("emp.department={}", emp.getDepartment()); // 4. DEPARTMENT_ID
		log.info("emp.department.location={}", emp.getDepartment().getLocation()); // 5. LOCATION_ID
		log.info("emp.department.location.country={}", emp.getDepartment().getLocation().getCountry()); // 6. COUNTRY_ID
	}
	
}
