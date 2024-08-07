package com.itwill.springboot5.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
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
	
	// 괄호 안에 호출하려고 하는 메서드 이름을 적어야 함.
	// @PreAuthorize("isAuthenticated()") // => role에 상관없이 아이디/비밀번호로만 인증.
	@PreAuthorize("hasRole('USER')") // => role이 일치하는 아이디/비밀번호 인증.
	@GetMapping("/create")
	public void create() {
		log.info("GET: create()");
		
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/create")
	public String create(PostCreateDto dto) {
		log.info("POST: create(dto = {})", dto);
		
		// 서비스 계층의 메서드를 호출해서 작성한 포스트를 DB에 저장.
		postSvc.create(dto);
		
		return "redirect:/post/list";
		
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping({ "/details", "/modify" })
	public void details(@RequestParam(name = "id", defaultValue = "0.0") long id, Model model) {
		log.info("details(id = {})", id);
		
		Post post = postSvc.readById(id);
		
		model.addAttribute("post", post);
		
		// 요청 주소가 "details"인 경우에는 view 이름은 details.html
		// 요청 주소가 "modify"인 경우에는 view 이름은 modify.html
		
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id", defaultValue = "0") long id) {
		log.debug("delete(id = {})", id);
		
		// 서비스 컴포넌트의 메서드를 호출해서 데이터베이스의 테이블에서 해당 아이디의 글을 삭제.
		postSvc.delete(id);
		
		// 포스트 목록 페이지로 리다이렉트.
		return "redirect:/post/list";
	}
	
	@PreAuthorize("hasRole('USER')")
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
	    
	    // pagination fragment에서 사용할 현재 요청 주소 정보
        model.addAttribute("baseUrl", "/post/search");
        
	    return "post/list"; // 검색 결과를 표시할 뷰 이름
	}
	
}
