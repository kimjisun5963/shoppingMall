package com.example.shoppingMall2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingMall2.dto.Product;
import com.example.shoppingMall2.service.CommonService;

@Controller
public class MyController {
	
	@Autowired
	CommonService commonService;
	
	@RequestMapping("/")
	public String root(Model model) {
		model.addAttribute("test", "test");
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
		return "product_detail";
	}
	
	
}
