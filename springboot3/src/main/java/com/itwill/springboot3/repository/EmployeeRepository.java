package com.itwill.springboot3.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.springboot3.domain.Employee;

/*
 * Repository<T, ID>
 * |__ CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>
 *     |__ JpaRepository<T, ID>
 *     		|__ EmployeeRepository<T, ID>
 *     			|__ SimpleJpaRepository<T, ID>
 * 
 * T: Entity 클래스, ID: Entity 클래스의 @Id 필드 타입
 */

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
//	Page<Employee> findByDepartmentId(int departmentId, Pageable pageable);
	
	// JPA query method 작성 방법:
	// JPA에서 약속된 키워드들과 엔티티의 필드 이름들을 사용해서
	// 메서드 이름을 (camel 표기법으로) 작성.
	
	// 1. 부서 번호로 검색:
	// select * from employees where department_id = ?
	List<Employee> findByDepartmentId(Integer id);
	
	// 2. 이름(firstName)으로 검색:
	// select * from employees where first_name = ?
	List<Employee> findByFirstName(String firstName);
	
	// 3. 이름에 포함된 단어로 검색: (15번과 비교)
	// select * from employees where first_name like '%?%'
	List<Employee> findByFirstNameContaining(String keyword);
	// -> 3-1. Containing: 아규먼트에 '%'를 적을 필요 X.
	
	List<Employee> findByFirstNameLike(String keyword);
	// -> 3-2. Like: 아규먼트에 '%' 또는 '_'를 적어야 함.
	
	// 4. 이름에 포함된 단어로 검색, 단어의 대/소문자 구문 없이 검색.
	// select * from employees where upper(first_name) like upper(?)
	List<Employee> findByFirstNameContainingIgnoreCase(String keyword);
	
	// 5. 이름에 포함된 단어로 검색, 단어 대/소문자 구분 없이 검색, 정렬 순서는 이름 내림차순.
	// select * from employees where upper(first_name) like upper(?) order by first_name desc
	List<Employee> findByFirstNameContainingIgnoreCaseOrderByFirstNameDesc(String keyword);
	
	// 6. 급여가 어떤 값을 초과하는 직원들의 정보
	// select * from employees where salary > ?
	List<Employee> findBySalaryGreaterThan(Double salary);
	
	// 7. 급여가 어떤 값 미만인 직원들의 정보
	// select * from employees where salary < ?
	List<Employee> findBySalaryLessThan(Double salary);
	
	// 8. 급여가 어떤 범위 안에 있는 직원들의 정보
	// select * from employees where salary between ?1 and ?2
	List<Employee> findBySalaryBetween(Double minSalary, Double maxSalary);
	
	// 9. 입사날짜가 특정 날짜 이전인 직원들의 정보
	// select * from employees where hire_date < ?
	List<Employee> findByHireDateLessThan(LocalDate date);
	
	// 10. 입사날짜가 특정 날짜 이후인 직원들의 정보
	// select * from employees where hire_date > ?
	List<Employee> findByHireDateGreaterThan(LocalDate date);
	
	// 11. 입사날짜가 날짜 범위 안에 있는 직원들의 정보
	// select * from employees where hire_date between ?1 and ?2)
	List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);
	
	// 12. 부서 이름으로 직원들의 정보 검색 (16번과 비교)
	// select * from employees e
	// left join departments d on e.department_id = d.department_id
	// where d.department_name = ?
	List<Employee> findByDepartmentDepartmentName(String DepartmentName);
	
	// 13. 근무 도시 이름으로 직원들의 정보 검색 (17번과 비교)
	// select * from employees e
	// left join departments d on e.department_id = d.department_id
	// left join locations l on d.location_id = l.location_id
	// where l.city = ?
	List<Employee> findByDepartmentLocationCity(String City);
	
	// 14. 대소문자 구분없이 성(lastName) 또는 이름(firstName)에 문자열이 포함된 직원 찾기:
	// select * from employees where upper(first_name) like upper(?) or upper(last_name) like upper(?)
	List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
	
	// JPQL(Java Persistence Query Language)
	// JPA에서 사용하는 "객체지향(Object-oriented)" 쿼리 문법.
	// 테이블 이름과 테이블의 컬럼 이름으로 쿼리 문장을 작성하는 것이 아니라,
	// 엔터티 객체 이름과 엔터티 필드 이름으로 쿼리를 작성하는 문법.
	// alias(별명)을 반드시 사용해야 함.
	// 엔터티 이름과 필드 이름은 대소문자를 구분.
	
	// 15-1. "?" 사용해서 이름 또는 성에 포함된 단어로 직원들 검색 (3번과 비교)
	@Query("select e from Employee e " 
			+ "where upper(e.firstName) like upper('%' || ?1 || '%') "
			+ "or upper(e.lastName) like upper('%' || ?2 || '%')")
	List<Employee> findByName(String firstName, String lastName);
	
	// 15-2. ":" 사용해서 이름 또는 성에 포함된 단어로 직원들 검색 (@Param 애너테이션 사용함) (3번과 비교)
	@Query("select e from Employee e "
			+ "where upper(e.firstName) like upper('%' || :keyword || '%') "
			+ "or upper(e.lastName) like upper('%' || :keyword || '%')")
	List<Employee> findByName2(@Param("keyword") String name);
	
	// 16. 부서 이름으로 직원들 검색 (12번과 비교)
	@Query("select e from Employee e where e.department.departmentName = :dname")
	List<Employee> findByDeptName(@Param("dname") String deptName);
	
	// 17. 특정 도시(예: Seattle)에 근무하는 직원들 검색 (13번과 비교)
	@Query("select e from Employee e "
			+ "where e.department.location.city = :city")
	List<Employee> findByLocCity(@Param("city") String city);
	
	// 18. 특정 국가(예: Canada)에 근무하는 직원들 검색
	@Query("select e from Employee e "
			+ "where e.department.location.country.countryName = :cname")
	List<Employee> findByCtryName(@Param("cname") String ctryName);
	
}
