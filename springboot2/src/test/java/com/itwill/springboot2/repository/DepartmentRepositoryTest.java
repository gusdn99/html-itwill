package com.itwill.springboot2.repository;

// import static 구문: static 메서드, 필드 이름을 임포트.
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.domain.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 클래스 안에 @Test 메서드가 여러 개 있을 때, 메서드의 실행 순서 지정.
@SpringBootTest
public class DepartmentRepositoryTest {

	@Autowired // 의존성 주입(DI: dependency injection), 제어의 역전(IoC: Inversion of Control)
	private DepartmentRepository deptRepo;
	
//	@Test
//	public void test() {
//		// Assertions.assertNotNull(deptRepo);
//		assertThat(deptRepo).isNotNull(); // deptRepo 객체가 null이 아니면 테스트 성공.
//		log.info("***** deptRepo: {}", deptRepo);
//	}
	
	@Test
    @Order(1)
    public void test() {
        // DepartmentRepository 객체를 의존성 주입받았음(객체가 생성됨)을 테스트 
        assertThat(deptRepo).isNotNull();
        log.info("deptRepo: {}", deptRepo);
    }
	
	// select * from dept
//	@Test
//	public void findAllTest() {
//		List<Department> list = deptRepo.findAll();
//		assertThat(list.size()).isEqualTo(4);
//		
//		for (Department d : list) {
//			System.out.println(d);
//		}
//	}
	
	@Transactional
	@Test
    @Order(2)
    public void findAllTest() {
        // dept 테이블 전체 검색 테스트: 행의 개수가 4개이면 성공.
        List<Department> list = deptRepo.findAll();
        assertThat(list.size()).isEqualTo(4);
        
        list.forEach(System.out::println);
    }
	
	// select * from dept where deptno = 10
//		@Test
//		public void findByTest() {
//			// 부서번호로 검색하는 메서드를 찾아서 단위 테스트 코드 작성.
//			Optional<Department> department = deptRepo.findById(20);
////			assertThat(department).isPresent();
////			log.info("department: {}", department.get());
//			assertThat(department).isNotNull();
//			log.info("department: {}", department);
//		}
		
	@Transactional
	@Test
	@Order(3)
	public void findByTest() {
		// 부서번호(deptno 컬럼, id 필드)로 검색 테스트
		// 부서번호가 테이블에 있는 경우:
		Department dept1 = deptRepo.findById(10).orElseThrow();
		// orElseThrow: 데이터가 존재하면 데이터를 리턴, 데이터가 없으면 NoSuchElementException 발생.
		
		assertThat(dept1.getId()).isEqualTo(10);
		log.info("dept1: {}", dept1);
		
		// OneToMany 관계: 10번 부서의 모든 직원 정보 출력
		List<Employee> employees = dept1.getEmployees();
		employees.forEach(System.out::println);

		// 부서번호가 테이블에 없는 경우:
		boolean isEmpty = deptRepo.findById(100).isEmpty();
		// isEmpty: Optional 객체가 데이터를 갖고 있으면 true를, 갖고 있지 않으면 false를 리턴.
		
		assertThat(isEmpty);
	}
	
	
}
