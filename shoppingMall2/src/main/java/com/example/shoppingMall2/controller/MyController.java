package com.example.shoppingMall2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingMall2.config.CustomUserDetails;
import com.example.shoppingMall2.dto.Member;
import com.example.shoppingMall2.dto.Product;
import com.example.shoppingMall2.dto.Review;
import com.example.shoppingMall2.service.CommonService;
import com.example.shoppingMall2.service.MemberService;

@Controller
public class MyController {
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder BCryptPasswordEncoder;
	
	@RequestMapping("/")
	public String root(Model model) {
		List<Product> hotProductList = commonService.hotProductList();
		List<Product> newProductList = commonService.newProductList();
		model.addAttribute("hotProductList", hotProductList);
		model.addAttribute("newProductList", newProductList);
		return "index";
	}
	
	@RequestMapping("/productList")
	public String getProductList(Model model) {
		System.out.println("전체 상품 리스트");
		List<Product> productList = commonService.getProductAllList();
		model.addAttribute("productList", productList);
		
		return "product_list";
	}
	
	@RequestMapping("/signup")
	public String signup(Member member) {
		System.out.println("회원가입 중");
		
		String newPw;

			newPw = BCryptPasswordEncoder.encode(member.getPassword());
			member.setPassword(newPw);
		
		
		
		
		memberService.signup(member);
		return "redirect:/";
	}
	
	@RequestMapping("/signupForm")
	public String signupForm() {
		System.out.println("회원가입 폼");
		return "signup";
	}
	
	@RequestMapping("/productDtail")
	public String getProductDtail(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam("pno")Long pno, Model model) {
		System.out.println("상품 상세보기");
		Product product = commonService.getProductDetail(pno);
		System.out.println(product);
		model.addAttribute("product", product);
		
		List<Review> reviewList = memberService.getReviewList(pno);
		
		for(int i = 0; i < reviewList.size() ; i++) {
			Review review = reviewList.get(i);
			int result = memberService.reviewRealCheck(review.getUsername(), pno);
			review.setCheck(result);
		}
		if(customUserDetails != null) {
			model.addAttribute("member", customUserDetails.getUsername());
		}
		model.addAttribute("reviewList", reviewList);
		return "product_detail";
	}
	
//	@RequestMapping("/getReview")
//	public @ResponseBody String getReview(Long rno) {
//		System.out.println("modifyReview.....");
//		Review review = commonService.getReview(rno);
//		
//		Gson gson = new Gson();
//		String result = gson.toJson(review);
//		return result;
//	}
	
	@RequestMapping("/custom403")
	public void custom403() {
		System.out.println("잘못된 접근");
	}
	
}
