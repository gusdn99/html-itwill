package com.itwill.spring2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.dto.UserSigninDto;
import com.itwill.spring2.dto.UserSignupDto;
import com.itwill.spring2.repository.User;
import com.itwill.spring2.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@GetMapping("/signup")
	public void signup() {
		log.debug("GET: signup()");
	}

	@PostMapping("/signup")
	public String signup(UserSignupDto dto) {
		log.debug("POST: signup(dto = {})", dto);

		userService.signup(dto);
		return "redirect:/";
	}
	
	@GetMapping("/signin")
	public void signin() {
		log.debug("GET: signin()");
	}
	
	@PostMapping("/signin")
	public String signin(UserSigninDto dto) {
		log.debug("POST: signin(dto = {})", dto);
		
		userService.signin(dto);
		return "redirect:/";
	}
	
//	// 로그인 화면에서 사용자가 입력(전송)한 userid, password 값을 읽음. signin post방식
//	String userid = req.getParameter("userid");
//	String password = req.getParameter("password");
//
//	// 서비스 계층의 메서드를 호출해서 로그인 성공 여부를 판단.
//	User user = userService.signIn(userid, password);
//
//	// 로그인 성공이면 타겟(target) 페이지, 그렇지 않으면 다시 로그인 페이지로 이동:
//	String target = req.getParameter("target");
//	log.debug("target = {}", target);
//
//	if (user != null) { // 데이터베이스 users 테이블에서 일치하는 사용자 정보가 있는 경우
//		// 세션에 로그인 정보를 저장.
//		HttpSession session = req.getSession(); // 메모리에 있는 세션 객체를 찾음. 세션은 heap 메모리에 있음.
//		session.setAttribute("signedInUser", user.getUserid());
//
//		// 타겟 목적지(URL)로 이동.
//		if (target == null || target.equals("")) {
//			String url = req.getContextPath() + "/"; // 홈페이지로 이동.
//			log.debug("redirect: {}" + url);
//			resp.sendRedirect(url);
//		} else {
//			resp.sendRedirect(target);
//		}
//
//	} else { // 데이터베이스 users 테이블에서 일치하는 사용자 정보가 있지 X
//		// 다시 로그인 페이지로 이동
//		String url = req.getContextPath() + "/user/signin?result=f&target=" + URLEncoder.encode(target, "UTF-8");
//		log.debug("redirect: {}" + url);
//		resp.sendRedirect(url);
//	}
	
	@GetMapping("/profile")
	public void profile(@RequestParam(name = "userid") String userid, Model model) {
		log.debug("profile(userid = {})", userid);
		
		User user = userService.read(userid);
		model.addAttribute("user", user);
	}
	
//	@Override signout
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		log.debug("doGet()");
//		
//		// 로그아웃:
//		// (1) 세션에 저장된 signedInUser(로그인 정보)를 삭제
//		// (2) 세션 객체를 무효화(invalidate) - 세션 삭제
//		// (2)만 실행하면 (1)은 자동으로 실행됨.
//		
//		HttpSession session = req.getSession();
//		session.removeAttribute("signedInUser"); // removeAttribute의 아규먼트: setAttribute에서 사용한 속성 이름.
//		session.invalidate(); // (2)
//		
//		// 로그아웃 이후에 로그인 페이지로 이동
//		String url = req.getContextPath() + "/user/signin";
//		resp.sendRedirect(url);
//	}
	

}

