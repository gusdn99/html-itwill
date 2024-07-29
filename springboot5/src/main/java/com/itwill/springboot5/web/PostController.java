package com.itwill.springboot5.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostCreateDto;
import com.itwill.springboot5.dto.PostListItemDto;
import com.itwill.springboot5.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/post")
public class PostController {
	private final PostService postSvc;
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("list()");
		
		// 서비스 계층의 메서드를 호출
		List<PostListItemDto> list = postSvc.read();
		
		// 뷰에 포스트 목록을 전달
		model.addAttribute("posts", list);
		
	}
	
	@GetMapping("/create")
	public void create() {
		log.info("GET: create()");
		
	}
	
	@PostMapping("/create")
	public String create(PostCreateDto dto) {
		log.info("POST: create(dto = {})", dto);
		
		postSvc.create(dto);
		return "redirect:/post/list";
		
	}
	
	@GetMapping({ "/details", "/modify" })
	public void details(@RequestParam(name = "id", defaultValue = "0.0") long id, Model model) {
		log.info("details(id = {})", id);
		
		Post post = postSvc.readById(id);
		
		model.addAttribute("post", post);
		
	}
	
}
