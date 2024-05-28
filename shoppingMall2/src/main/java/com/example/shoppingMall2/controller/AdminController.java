package com.example.shoppingMall2.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shoppingMall2.dto.Member;
import com.example.shoppingMall2.dto.Product;
import com.example.shoppingMall2.dto.ProductFile;
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
	
	//관리자 메인 가자(상품목록 리스트)
	@RequestMapping("/main")
	public String main(Model model) {
		System.out.println("관리자 메인");
		List<Product> products = adminService.getAllProducts();
		model.addAttribute("products", products);
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
	
	// 상품 등록폼 이동
	@RequestMapping("/registForm")
	public String registForm() {
		System.out.println("상품 등록 폼 이동");
		return "/admin/regist_form";
	}
	
	// 상품 등록
	@RequestMapping("/regist")
	public String regist(ProductFile productFile) {
		System.out.println("상품 등록중");
		System.out.println(productFile);
		Product product = new Product();
		product.setPname(productFile.getPname());
		product.setPcontent(productFile.getPcontent());
		product.setStockprice(productFile.getStockprice());
		product.setSaleprice(productFile.getSaleprice());
		product.setAmount(productFile.getAmount());
		
		String pimgOriginName = productFile.getFileNamePimg();
		String pcontent_imgOriginName = productFile.getFileNamePcontent_img();

		String pimgNewName = UUID.randomUUID().toString() + "_" + pimgOriginName;
		String pcontent_imgNewName = UUID.randomUUID().toString() + "_" + pcontent_imgOriginName;
		
		System.out.println("pimg : " + pimgOriginName +" : " + pimgNewName);
		System.out.println("pcontent_img : " + pcontent_imgOriginName +" : " + pcontent_imgNewName);

		product.setPimg(pimgNewName);
		product.setPcontent_img(pcontent_imgNewName);
		
		// 파일저장
		File pimgFile = new File(product.getPimg());
		System.out.println("pimgFile : " + pimgFile);
		File pcontent_imgFile = new File(product.getPcontent_img());
		System.out.println("pcontent_imgFile : " + pcontent_imgFile);
		
		try {
			productFile.getPimg().transferTo(pimgFile);
			productFile.getPcontent_img().transferTo(pcontent_imgFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		adminService.registProduct(product);
		
		return "redirect:/admin/main";
	}
	
	
	
	
	
	
	
	
	
}