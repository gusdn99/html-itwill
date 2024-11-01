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

import com.itwill.springboot3.domain.Country;
import com.itwill.springboot3.service.CountryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor // -> final 필드를 초기화하는 생성자. 생정자에 의한 의존성 주입.
@Controller
@RequestMapping("/country")
public class CountryController {
	
	// 생성자에 의한 의존성 주입
	private final CountryService ctrySvc;
	
	@GetMapping("/list")
	public void list(@RequestParam(name = "p", defaultValue = "0") int pageNo,
			Model model) {
		log.info("list(pageNo={})", pageNo);
		
		// 서비스(비즈니스) 계층의 메서드를 호출해서 (데이터베이스의) 직원 목록을 불러옴.
		Page<Country> list = ctrySvc.read(pageNo, Sort.by("id"));
		
		// 나라 목록을 뷰 템플릿에게 전달
		model.addAttribute("page", list);
	}
	
	@GetMapping("/details/{id}")
	public String details(@PathVariable(name = "id") String id, Model model) {
		log.info("details(id={})", id);
		
		Country ctry = ctrySvc.readById(id);
		model.addAttribute("country", ctry);
		
		return "country/details";
		
	}
	
}
