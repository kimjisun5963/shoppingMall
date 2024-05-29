package com.example.shoppingMall2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping("/productDtail")
	public String getProductDtail(@RequestParam("pno")Long pno, Model model) {
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
		
		model.addAttribute("reviewList", reviewList);
		return "product_detail";
	}
	
}
