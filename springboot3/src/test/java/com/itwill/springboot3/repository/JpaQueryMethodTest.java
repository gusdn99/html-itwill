package com.itwill.springboot3.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot3.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class JpaQueryMethodTest {
	@Autowired
	private EmployeeRepository empRepo;
	
	@Test
	public void test() {
		List<Employee> list;
//		list = empRepo.findByDepartmentId(30); // 1번		
//		list = empRepo.findByFirstName("Steven"); // 2번		
//		list = empRepo.findByFirstNameContaining("te"); // 3-1번 ("%" 안 적어도 됨.)
//		list = empRepo.findByFirstNameLike("%te%"); // 3-2번 ("%" 적어야 함.)		
//		list = empRepo.findByFirstNameContainingIgnoreCase("Te"); // 4번		
//		list = empRepo.findByFirstNameContainingIgnoreCaseOrderByFirstNameDesc("Te"); // 5번		
//		list = empRepo.findBySalaryGreaterThan(16_000.); // 6번
//		list = empRepo.findBySalaryLessThan(2_500.); // 7번
//		list = empRepo.findBySalaryBetween(9_000., 10_000.); // 8번		
//		list = empRepo.findByHireDateLessThan(LocalDate.of(2002, 12, 31)); // 9번
//		list = empRepo.findByHireDateGreaterThan(LocalDate.of(2007, 12, 31)); // 10번
//		list = empRepo.findByHireDateBetween(LocalDate.of(2004, 01, 01), LocalDate.of(2004, 12, 31)); // 11번
//		list = empRepo.findByDepartmentDepartmentName("IT"); // 12번
//		list = empRepo.findByDepartmentLocationCity("Seattle"); // 13번
//		list = empRepo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase("te", "te"); // 14번
//		list = empRepo.findByName("Te", "Te"); // 15-1번
//		list = empRepo.findByName2("tE"); // 15-2번
//		list = empRepo.findByDeptName("IT"); // 16번
//		list = empRepo.findByLocCity("Seattle"); // 17번
		list = empRepo.findByCtryName("Canada"); // 18번
		
		list.forEach(System.out::println);
//		log.info("list={}", list);
	}
	
}
