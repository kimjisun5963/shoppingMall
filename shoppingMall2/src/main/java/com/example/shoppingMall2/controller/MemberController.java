package com.example.shoppingMall2.controller;

import java.time.LocalTime;
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
import com.example.shoppingMall2.dto.Sales;
import com.example.shoppingMall2.dto.SalesDetail;
import com.example.shoppingMall2.dto.ShoppingBasket;
import com.example.shoppingMall2.dto.ShoppingBasketDto;
import com.example.shoppingMall2.service.CommonService;
import com.example.shoppingMall2.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/members")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	CommonService commonService;
	
	
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		System.out.println("회원 로그인폼");
		return "/members/login";
	}
	
	
	
	//@RequestMapping("/login")
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
	

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		System.out.println("회원 로그아웃");
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/shoppingBasketList")
	public String shoppingBasketList(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
		System.out.println("장바구니 가기");
		//HttpSession session = request.getSession();
		//Member member = (Member) session.getAttribute("member");
		List<ShoppingBasketDto> shoppingBasketList = memberService.shoppingBasketList(customUserDetails.getUsername());
		System.out.println(shoppingBasketList);
		model.addAttribute("list", shoppingBasketList);
		return "/members/shopping_basket";
	}
	
	@RequestMapping("modifyShoppingBasket")
	public String modifyShoppingBasket(@RequestParam("samount")Long samount, @RequestParam("sbno")Long sbno) {
		//System.out.println("장바구니 수정" + samount + sbno);
		memberService.modifyShoppingBasket(samount, sbno);
		return "redirect:/members/shoppingBasketList";
	}
	
	@RequestMapping("registShoppingBasket")
	public String registShoppingBasket(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam("samount")Long samount, @RequestParam("pno")Long pno) {
		System.out.println("장바구니 담기" + samount + pno);
		//HttpSession session = request.getSession();
		//Member member = (Member) session.getAttribute("member");
		ShoppingBasket shoppingBasket = memberService.checkShoppingBasket(pno, customUserDetails.getUsername());
		System.out.println("장바구니에 있는 같은 Pno, username으로 값 " + shoppingBasket);
		if(shoppingBasket == null) {
			System.out.println("없어서 등록");
			memberService.registShoppingBasket(pno, customUserDetails.getUsername(), samount);
			
		}else {
			Long result = (Long) ((shoppingBasket.getSamount()) + (samount));
			System.out.println("있아서 수정" + result);
			memberService.modifyShoppingBasket(result, shoppingBasket.getSbno());
		}
		
		return "redirect:/members/shoppingBasketList";
	}
	
	@RequestMapping("deleteShoppingBasket")
	public String deleteShoppingBasket(@RequestParam("sbno")Long sbno) {
		System.out.println("장바구니 삭제" + sbno);
		memberService.deleteShoppingBasket(sbno);
		return "redirect:/members/shoppingBasketList";
	}
	
	@RequestMapping("salesOneProduct")
	public String salesOneProduct(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam("samount")Long samount, @RequestParam("pno")Long pno) {
		//HttpSession session = request.getSession();
		//Member member = (Member) session.getAttribute("member");
		//String username = member.getUsername();
		LocalTime now = LocalTime.now();
		String dateTime = now.toString();
		String scode = dateTime.replaceAll("[:,.]","");
		System.out.println("주문하기" + scode);
		Sales sales = new Sales(null, pno, customUserDetails.getUsername(), samount, null ,scode);
		memberService.salesOneProduct(sales);
		
		Product product = commonService.getProductDetail(pno);
		Long amount = product.getAmount();
		amount = (amount-samount);
		memberService.updateProductAmount(amount, pno);
		
		return "redirect:/members/mypage";
	}
	
	@RequestMapping("salesAll")
	public String salesAll(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		String[] chkes = request.getParameterValues("chk");
		System.out.println("주문 all " + chkes);
		//HttpSession session = request.getSession();
		//Member member = (Member) session.getAttribute("member");
		//String username = member.getUsername();
		LocalTime now = LocalTime.now();
		String dateTime = now.toString();
		String scode = dateTime.replaceAll("[:,.]","");
		System.out.println("주문하기" + scode);
		for(String s : chkes) {
			System.out.println(s);
			Long sbno = (long) Integer.parseInt(s);
			ShoppingBasket shoppingBasket = memberService.getShoppingBasket(sbno);
			
			Sales sales = new Sales(null, shoppingBasket.getPno(), customUserDetails.getUsername(), shoppingBasket.getSamount(), null ,scode);
			memberService.salesOneProduct(sales);
			memberService.deleteShoppingBasket(sbno);
			
			Product product = commonService.getProductDetail(shoppingBasket.getPno());
			Long amount = product.getAmount();
			amount = (amount-shoppingBasket.getSamount());
			memberService.updateProductAmount(amount, shoppingBasket.getPno());
			
		}
		return "redirect:/members/mypage";
	}
	
	
	@RequestMapping("/mypage")
	public String mypage(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
		System.out.println("마이페이지");
		//HttpSession session = request.getSession();
		//Member member = (Member) session.getAttribute("member");
		//String username = member.getUsername();
		List<SalesDetail> list = memberService.getSalesDetailList(customUserDetails.getUsername());
		model.addAttribute("list", list);
		return "/members/mypage";
	}
	

	@RequestMapping("/deleteSales")
	public String deleteSales(@RequestParam("sno")Long sno) {
		System.out.println("주문 취소");
		Sales sales = memberService.getSales(sno);
		Product product = commonService.getProductDetail(sales.getPno());
		Long amount = product.getAmount();
		amount = (amount+sales.getSamount());
		memberService.updateProductAmount(amount, sales.getPno());
		
		memberService.deleteSales(sno);
		
		return "redirect:/members/mypage";
	}
	
	@RequestMapping("/registReview")
	public String registReview(Review review, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		System.out.println("상품 후기 등록");
		//HttpSession session = request.getSession();
		//Member member = (Member) session.getAttribute("member");
		String username = customUserDetails.getUsername();
		review.setUsername(username);
		memberService.registReview(review);
		
		return "redirect:/productDtail?pno=" + review.getPno();
	}
	
	@RequestMapping("/reviewModify")
	public String reviewModify(@RequestParam("rcontent")String rcontent, @RequestParam("rno")Long rno, @RequestParam("pno")Long pno) {
		System.out.println("상품 후기 수정");
		memberService.updateReview(rcontent, rno);
		return "redirect:/productDtail?pno=" + pno;
	}
	
}
