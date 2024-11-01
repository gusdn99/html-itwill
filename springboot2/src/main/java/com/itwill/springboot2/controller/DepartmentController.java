package com.itwill.springboot2.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // -> final 필드를 초기화하는 생성자. 생정자에 의한 의존성 주입.
@Controller
@RequestMapping("/department")
public class DepartmentController {
	
	// 생성자에 의한 의존성 주입
	private final DepartmentService deptSvc;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list()");
		
		// 서비스(비즈니스) 계층의 메서드를 호출해서 (데이터베이스의) 부서 목록을 불러옴.
		List<Department> list = deptSvc.read();
		
		// 부서 목록을 뷰 템플릿에게 전달
		model.addAttribute("departments", list);
		
	}
	
	@GetMapping("/details/{id}")
	public String details(@PathVariable(name = "id") int id, Model model) {
		log.info("details(id={})", id);
		
		Department dept = deptSvc.readById(id);
		model.addAttribute("department", dept);
		
		return "department/details";
		
	}
	
}
