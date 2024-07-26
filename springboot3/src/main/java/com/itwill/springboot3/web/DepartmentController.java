package com.itwill.springboot3.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot3.domain.Department;
import com.itwill.springboot3.dto.DepartmentDetailsDto;
import com.itwill.springboot3.dto.EmployeeListItemDto;
import com.itwill.springboot3.service.DepartmentService;
import com.itwill.springboot3.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // -> final 필드를 초기화하는 생성자. 생정자에 의한 의존성 주입.
@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	// 생성자에 의한 의존성 주입
	private final DepartmentService deptSvc;
//	private final EmployeeService empSvc;  // 추가: EmployeeService 의존성 주입
	
	@GetMapping("/list")
	public void list(@RequestParam(name = "p", defaultValue = "0") int pageNo,
			Model model) {
		log.info("list(pageNo={})", pageNo);
		
		// 서비스(비즈니스) 계층의 메서드를 호출해서 (데이터베이스의) 부서 목록을 불러옴.
		Page<Department> list = deptSvc.read(pageNo, Sort.by("id")); // "id"는 엔터티의 필드 이름
		
		// 부서 목록을 뷰 템플릿에게 전달
		model.addAttribute("page", list);
	}
	
	@GetMapping("/details/{id}")
	public String details(@PathVariable Integer id, Model model) {
		log.info("details(id = {})", id);
		
		DepartmentDetailsDto dto = deptSvc.readById(id);
		model.addAttribute("department", dto);
		
		return "department/details";
		
	}
	
	@GetMapping("/details")
	public void details(@RequestParam(name = "dname") String departmentName, Model model) {
		log.info("details(departmentName = {})", departmentName);
		
		DepartmentDetailsDto dto = deptSvc.readByDepartmentName(departmentName);
		model.addAttribute("department", dto);
		
	}
	
//	@GetMapping("/details/{id}")
//	public String details(@PathVariable Integer id, @RequestParam(name = "p", defaultValue = "0") int pageNo, Model model) {
//		log.info("details(id={}, pageNo={})", id, pageNo);
//		
//		Department dept = deptSvc.readById(id);
//		model.addAttribute("department", dept);
//		
//		// 부서의 직원 목록을 페이징하여 모델에 추가
//		Page<EmployeeListItemDto> employeesPage = empSvc.readByDepartment(id, pageNo, 10); // 추가: 부서별 직원 목록 페이징 처리
//		model.addAttribute("page", employeesPage);
//		
//		return "department/details";
//		
//	}
	
}
