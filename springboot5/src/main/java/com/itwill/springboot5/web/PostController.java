package com.itwill.springboot5.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot5.domain.Post;
import com.itwill.springboot5.dto.PostCreateDto;
import com.itwill.springboot5.dto.PostListItemDto;
import com.itwill.springboot5.dto.PostSearchRequestDto;
import com.itwill.springboot5.dto.PostUpdateDto;
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
	public void list(@RequestParam(name = "p", defaultValue = "0") int pageNo,
			Model model) {
		log.info("list(pageNo={})", pageNo);
		
		// 서비스 계층의 메서드를 호출
		Page<PostListItemDto> list = postSvc.read(pageNo, Sort.by("id").descending()); // descending(): 내림차순
		
		// 뷰에 포스트 목록을 전달
		model.addAttribute("page", list);
		
	}
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		log.info("list()");
//		
//		// 서비스 계층의 메서드를 호출
//		List<PostListItemDto> list = postSvc.read();
//		
//		// 뷰에 포스트 목록을 전달
//		model.addAttribute("posts", list);
//		
//	}
	
	@GetMapping("/create")
	public void create() {
		log.info("GET: create()");
		
	}
	
	@PostMapping("/create")
	public String create(PostCreateDto dto) {
		log.info("POST: create(dto = {})", dto);
		
		// 서비스 계층의 메서드를 호출해서 작성한 포스트를 DB에 저장.
		postSvc.create(dto);
		
		return "redirect:/post/list";
		
	}
	
	@GetMapping({ "/details", "/modify" })
	public void details(@RequestParam(name = "id", defaultValue = "0.0") long id, Model model) {
		log.info("details(id = {})", id);
		
		Post post = postSvc.readById(id);
		
		model.addAttribute("post", post);
		
		// 요청 주소가 "details"인 경우에는 view 이름은 details.html
		// 요청 주소가 "modify"인 경우에는 view 이름은 modify.html
		
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id", defaultValue = "0") long id) {
		log.debug("delete(id = {})", id);
		
		// 서비스 컴포넌트의 메서드를 호출해서 데이터베이스의 테이블에서 해당 아이디의 글을 삭제.
		postSvc.delete(id);
		
		// 포스트 목록 페이지로 리다이렉트.
		return "redirect:/post/list";
	}
	
	@PostMapping("/update")
	public String update(PostUpdateDto dto) {
		log.info("update(dto = {}", dto);
		
		postSvc.update(dto);
		
		return "redirect:/post/details?id=" + dto.getId();
		
	}
	
	@GetMapping("/search")
	public String search(PostSearchRequestDto dto,
	                     Model model) {
	    log.info("search(dto = {})", dto);
	    
	    Page<PostListItemDto> page = postSvc.search(dto, Sort.by("id").descending());
	    
	    model.addAttribute("page", page);

	    return "post/list"; // 검색 결과를 표시할 뷰 이름
	}
	
//	@GetMapping("/search")
//	public String search(@RequestParam(name = "p", defaultValue = "0") int pageNo, PostSearchRequestDto dto,
//	                     Model model) {
//	    log.info("search(pageNo = {}, dto = {})", pageNo, dto);
//
//	    // 서비스 계층의 메서드를 호출
//	    Page<PostListItemDto> list = postSvc.search(dto, pageNo, Sort.by("id").descending());
//
//	    // 뷰에 포스트 목록을 전달
//	    model.addAttribute("page", list);
//	    model.addAttribute("search", dto);
//
//	    return "post/list"; // 검색 결과를 표시할 뷰 이름
//	}
	
}
