package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.domain.Employee;
import com.itwill.springboot3.dto.DepartmentDetailsDto;
import com.itwill.springboot3.dto.EmployeeListItemDto;
import com.itwill.springboot3.repository.DepartmentRepository;
import com.itwill.springboot3.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DepartmentService {

	// 생성자에 의한 의존성 주입: (1) RequiredArgsConstructor + (2) final field
	private final DepartmentRepository deptRepo;
	private final EmployeeRepository empRepo;
	
	@Transactional(readOnly = true) // 엔터티 객체들을 읽기 전용으로 검색.
									// 엔터티의 변화가 없고 읽기 전용으로만 보이고 싶을 때 사용. (기본값은 false)
	public Page<Department> read(int pageNo, Sort sort) {
		log.info("read(pageNo = {}), sort = {}", pageNo, sort);
		
		// Pageable 객체 생성: PageRequest.of(페이지번호, 한 페이지의 아이템 개수, 정렬기준)
		Pageable pageable = PageRequest.of(pageNo, 5, sort);
		
		// findAll(Pageable pageable) 메서드를 호출.
		Page<Department> page = deptRepo.findAll(pageable);
		log.info("hasPrevious = {}", page.hasPrevious()); // 이전 페이지가 있는지 여부(있으면 true, 없으면 false)
		log.info("hasNext = {}", page.hasNext()); // 다음 페이지가 있는지 여부(있으면 true, 없으면 false)
		log.info("getTotalPages = {}", page.getTotalPages()); // 전체 페이지 개수.
		
		// 영속성(저장소) 계층의 메서드를 호출해서 DB 쿼리를 실행.
		return page;
	}
	
	@Transactional(readOnly = true)
	public DepartmentDetailsDto readById(Integer id) {
		log.info("readById(id={})", id);
		
		Department department = deptRepo.findById(id).orElseThrow();
		List<Employee> employees = empRepo.findByDepartmentId(id);
		log.info("# of employees = {}", employees.size());

		return DepartmentDetailsDto.fromEntity(department, employees);
		
	}
	
	@Transactional(readOnly = true)
	public DepartmentDetailsDto readByDepartmentName(String departmentName) {
		log.info("readByDepartmentName(departmentName = {})", departmentName);
		
		// Department 엔터티 객체 생성:
		Department department = Department.builder().departmentName(departmentName).build();
		// departmentName만 값이 설정된 Department 객체를 생성. 나머지 필드는 값이 설정되지 않아서 기본값인 null이 나옴.
		
		// Example 객체 생성:
		Example<Department> example = Example.of(department);
		// 주어진 객체의 non-null 필드(여기서는 departmentName)를 기준으로 쿼리 조건을 생성.
		// Example: non-null 필드만을 쿼리 조건으로 사용함.
		// Example.of(): 주어진 객체를 기반으로 Example 인스턴스를 생성.
		
		// Example 객체를 findOne() 또는 findAll() 메서드의 아규먼트로 전달:
		// => findOne() 메서드는 엔터티  객체를 리턴.
		// => findAll() 메서드는 엔터티의 리스트를 리턴.
		Department resultDept = deptRepo.findOne(example).orElseThrow();
		log.info("resultDept id = {}", resultDept.getId());
		
		List<Employee> employees = empRepo.findByDepartmentId(resultDept.getId());
		
		return DepartmentDetailsDto.fromEntity(resultDept, employees);
		
	}
	
//	@Transactional(readOnly = true)
//	public Department readById(Integer id) {
//		log.info("readById(id={})", id);
//
//		return deptRepo.findById(id).orElseThrow();
//		
//	}

}
