package com.itwill.spring1.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j // -> private static final Logger log = LoggerFactory.getLogger(ExampleController.class); 코드를 삽입.
@Controller
public class ExampleController {
	
	@GetMapping("/") // get 방식 호출
	public String home(Model model) {
		log.debug("home()");
		
		LocalDateTime now = LocalDateTime.now();
		model.addAttribute("now", now);
		
		return "home"; // jsp 파일 이름
	}

}
