package com.itwill.springboot2.repository;

// import static 구문: static 메서드, 필드 이름을 임포트.
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot2.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class EmployeeRepositoryTest {

	@Autowired // 의존성 주입(DI: dependency injection), 제어의 역전(IoC: Inversion of Control)
	private EmployeeRepository empRepo;
	
//	@Test
	public void test() {
		// Assertions.assertNotNull(empRepo);
		assertThat(empRepo).isNotNull(); // empRepo 객체가 null이 아니면 테스트 성공.
		log.info("***** empRepo: {}", empRepo);
	}
	
	// select * from emp
//	@Test
	public void findAllTest() {
		List<Employee> list = empRepo.findAll();
		assertThat(list.size()).isEqualTo(14);
		
		for (Employee e : list) {
			System.out.println(e);
		}
	}
	
	// select * from emp where empno = 7499
	@Test
	public void findByTest() {
		// 사번으로 검색하는 메서드를 찾아서 단위 테스트 코드 작성.
		Optional<Employee> employee = empRepo.findById(7499);
//		assertThat(employee).isPresent();
//		log.info("employee: {}", employee.get());
		assertThat(employee).isNotNull();
		log.info("employee: {}", employee);
	}
	
}
