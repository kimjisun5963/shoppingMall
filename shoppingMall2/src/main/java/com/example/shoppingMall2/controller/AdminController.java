package com.example.shoppingMall2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingMall2.dto.Member;
import com.example.shoppingMall2.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	//관리자 로그인 가자
	@RequestMapping("/")
	public String loginForm() {
		System.out.println("관리자 로그인");
		return "/admin/login";
	}
	
	//관리자 로그인 중
	@RequestMapping("/login")
	public String login(@RequestParam("username")String username, @RequestParam("pw")String pw, HttpServletRequest request) {
		System.out.println("관리자 로그인 중");
		Member member = adminService.login(username, pw);
		
		if(member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", member);
			return "redirect:/admin/main";
		}else {
			return "redirect:/admin/";
		}
	}
	
	//관리자 메인 가자
	@RequestMapping("/main")
	public String main() {
		System.out.println("관리자 메인");
		return "/admin/main";
	}
	
	
	//관리자 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		System.out.println("관리자 로그아웃");
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/admin/";
	}
}