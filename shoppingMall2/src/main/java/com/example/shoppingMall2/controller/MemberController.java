package com.example.shoppingMall2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingMall2.dto.Member;
import com.example.shoppingMall2.dto.ShoppingBasketDto;
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
	
	
	@RequestMapping("/signup")
	public String signup(Member member) {
		System.out.println("회원가입 중");
		memberService.signup(member);
		return "redirect:/";
	}
	

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		System.out.println("회원 로그아웃");
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/admin/";
	}
	
	@RequestMapping("/shoppingBasketList")
	public String shoppingBasketList(HttpServletRequest request, Model model) {
		System.out.println("장바구니 가기");
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		List<ShoppingBasketDto> shoppingBasketList = memberService.shoppingBasketList(member.getUsername());
		System.out.println(shoppingBasketList);
		model.addAttribute("list", shoppingBasketList);
		return "/members/shopping_basket";
	}
	
	@RequestMapping("modifyShoppingBasket")
	public String modifyShoppingBasket(@RequestParam("samount")int samount, @RequestParam("sbno")Long sbno) {
		//System.out.println("장바구니 수정" + samount + sbno);
		memberService.modifyShoppingBasket(samount, sbno);
		return "redirect:/members/shoppingBasketList";
	}
	
	@RequestMapping("registShoppingBasket")
	public String registShoppingBasket(HttpServletRequest request, @RequestParam("samount")int samount, @RequestParam("pno")Long pno) {
		System.out.println("장바구니 담기" + samount + pno);
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		memberService.registShoppingBasket(pno, member.getUsername(), samount);
		return "redirect:/members/shoppingBasketList";
	}
	
	
	
}
