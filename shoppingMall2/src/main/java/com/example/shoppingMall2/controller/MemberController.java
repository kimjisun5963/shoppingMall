package com.example.shoppingMall2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingMall2.dto.Member;
import com.example.shoppingMall2.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/members")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		System.out.println("회원 로그인폼");
		return "/members/login";
	}
	
	@RequestMapping("/signupForm")
	public String signupForm() {
		System.out.println("회원가입 폼");
		return "/members/signup";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam("username")String username, @RequestParam("pw")String pw, HttpServletRequest request) {
		System.out.println("로그인 중");
		
		Member member = memberService.login(username, pw);
		
		if(member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			return "redirect:/";
		}else {
			return "redirect:/members/loginForm";
		}
	}
	
}
