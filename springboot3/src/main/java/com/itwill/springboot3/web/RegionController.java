package com.itwill.springboot3.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot3.domain.Region;
import com.itwill.springboot3.service.RegionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // -> final 필드를 초기화하는 생성자. 생정자에 의한 의존성 주입.
@Controller
@RequestMapping("/region")
public class RegionController {
	
	// 생성자에 의한 의존성 주입
	private final RegionService regSvc;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list()");
		
		// 서비스(비즈니스) 계층의 메서드를 호출해서 (데이터베이스의) 직원 목록을 불러옴.
		List<Region> list = regSvc.read();
		
		// 지역 목록을 뷰 템플릿에게 전달
		model.addAttribute("regions", list);
	}
	
	@GetMapping("/details/{id}")
	public String details(@PathVariable(name = "id") Integer id, Model model) {
		log.info("details(id={})", id);
		
		Region ctry = regSvc.readById(id);
		model.addAttribute("region", ctry);
		
		return "region/details";
		
	}
	
}
