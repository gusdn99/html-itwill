package com.itwill.spring2.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@GetMapping("/signup") // GET 방식의 /user/signup 요청을 처리하는 컨트롤러 메서드
	public void signup() {
		log.debug("GET signup()");
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
	public String signin(UserSigninDto dto, HttpSession session, @RequestParam(name = "target") String target) throws UnsupportedEncodingException {
		log.debug("POST: signin(dto = {})", dto);
		
		User user = userService.signin(dto);
		if (user != null) {
			session.setAttribute("signedInUser", user.getUserid());
			
			if (target == null || target.equals("")) {
				return "redirect:/";
			} else {
				return "redirect" + target;
			}
		} else {
			return "/user/signin?result=f&target=" + URLEncoder.encode(target, "UTF-8");
						
		}
			
	}
	
    // TODO: 사용자 아이디 중복체크 REST 컨트롤러
	

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
	
	@GetMapping("/signout")
	public String signout(HttpSession session) {
		log.debug("GET signout()");
		
		session.removeAttribute("signedInUser");
		session.invalidate();
		return "redirect:/user/signin";
	}
	
	

}

