package com.itwill.springboot2.repository;

// import static 구문: static 메서드, 필드 이름을 임포트.
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
		log.info("empRepo: {}", empRepo);
	}
	
	// select * from emp
//	@Test
	public void findAllTest() {
		List<Employee> list = empRepo.findAll();
		// ListCrudRepository는 CrudRepository를 상속받음
		assertThat(list.size()).isEqualTo(14);
		
		for (Employee e : list) {
			System.out.println(e);
		}
	}
	
	// select * from emp where empno = 7499
//	@Test
//	public void findByTest() {
//		// 사번으로 검색하는 메서드를 찾아서 단위 테스트 코드 작성.
//		Optional<Employee> employee = empRepo.findById(7499);
////		assertThat(employee).isPresent();
////		log.info("employee: {}", employee.get());
//		assertThat(employee).isNotNull();
//		log.info("employee: {}", employee);
//	}
	
	@Transactional
//	@Test
    public void findByTest() {
        // 사번이 테이블에 있는 경우:
        Optional<Employee> emp1 = empRepo.findById(7788);
        // Optional: 선택사항(객체에 값이 존재할 수도(not null), 존재하지 않을 수도(null) 있기 때문)
        // where 조건문이 있을 때 사용함.
        
//        Employee scott = emp1.get();
        // 데이터가 존재하면, get() 메서드 실행 시 exception 발생하지 X. 반대로 데이터가 null이면 NoSuchElementException 발생.
        
        Employee scott = emp1.orElseGet(() -> null);
        // orElseGet: 데이터가 있으면 데이터를 리턴, 없으면 제공하는 표현식의 결과값을(여기서는 람다식의 null이 결과값)을 리턴.
        
        assertThat(scott).isNotNull();
        assertThat(scott.getEname()).isEqualTo("SCOTT");
        log.info("scott= {}", scott);
        
        log.info("dept={}", scott.getDepartment());
        
//        assertThat(scott.getId()).isEqualTo(7788);
//        log.info("7788: {}", scott);
        
        // 사번이 테이블에 없는 경우:
        Optional<Employee> emp2 = empRepo.findById(1000);
        Employee none = emp2.orElseGet(() -> null);
        // orElseGet: 데이터가 있으면 데이터를 리턴, 없으면 제공하는 표현식의 결과값을(여기서는 람다식의 null이 결과값)을 리턴.
        
        assertThat(none).isNull();
    }
	
	@Transactional
	@Test
	public void findManagerTest() {
		// 사번이 7369인 직원 정보 검색.
//		Optional<Employee> emp = empRepo.findById(7369);
		Employee emp = empRepo.findById(7369).orElseThrow();
		// orElseThrow: 값이 존재하면 값을, 존재하지 않으면 NoSuchElementException 발생.
		assertThat(emp.getId()).isEqualTo(7369);
		log.info("emp={}", emp);
		
		Employee mgr = emp.getManager();
		assertThat(mgr.getId()).isEqualTo(7902); // 7369 직원의 매니저는 7902
		log.info("mgr={}", mgr);
		
	}
	
}
