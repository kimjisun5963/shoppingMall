package com.example.shoppingMall2.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppingMall2.dao.IMemberDao;
import com.example.shoppingMall2.dto.Member;
import com.example.shoppingMall2.dto.Product;
import com.example.shoppingMall2.dto.Review;
import com.example.shoppingMall2.dto.Sales;
import com.example.shoppingMall2.dto.SalesDetail;
import com.example.shoppingMall2.dto.ShoppingBasket;
import com.example.shoppingMall2.dto.ShoppingBasketDto;

@Service
public class MemberService {
	
	@Autowired
	IMemberDao memberDao;
	
	public Member findUsername(String username) {
		Member member = memberDao.findUsername(username);
		return member;
	}
	
	public Member login(String username, String pw) {
		Member member = memberDao.login(username, pw);
		return member;
	}
	
	public void signup(Member member) {
		memberDao.signup(member);
	}
	
	public List<ShoppingBasketDto> shoppingBasketList(String username){
		List<ShoppingBasketDto> list = memberDao.shoppingBasketList(username);
		return list;
	}
	
	public ShoppingBasket checkShoppingBasket(Long pno, String username) {
		ShoppingBasket shoppingBasket = memberDao.checkShoppingBasket(pno, username);
		return shoppingBasket;
	}
	public void modifyShoppingBasket(Long samount, Long sbno) {
		//System.out.println("장바구니 수정" + samount + sbno);
		memberDao.modifyShoppingBasket(samount, sbno);
	}
	
	public void registShoppingBasket(Long pno, String username, Long samount) {
		memberDao.registShoppingBasket(pno, username, samount);
	}
	
	public void deleteShoppingBasket(Long bsno) {
		memberDao.deleteShoppingBasket(bsno);
	}
	
	public void salesOneProduct(Sales sales) {
		memberDao.salesOneProduct(sales);
	}
	
	public ShoppingBasket getShoppingBasket(Long sbno) {
		ShoppingBasket shoppingBasket = memberDao.getShoppingBasket(sbno);
		return shoppingBasket;
	}
	
	public List<SalesDetail> getSalesDetailList(String username){
		List<SalesDetail> list = memberDao.getSalesDetailList(username);
		return list;
	}
	
	public void deleteSales(Long sno) {
		memberDao.deleteSales(sno);
	}
	
	public void updateProductAmount(Long amount, Long pno) {
		memberDao.updateProductAmount(amount, pno);
	}
	
	public Sales getSales(Long sno) {
		Sales sales = memberDao.getSales(sno);
		return sales;
	}
	
	public void registReview(@Param("review")Review review) {
		memberDao.registReview(review);
	}
	
	public List<Review> getReviewList(Long pno){
		List<Review> list = memberDao.getReviewList(pno);
		return list;
	}
	public int reviewRealCheck(String username, Long pno) {
		int result = memberDao.reviewRealCheck(username, pno);
		return result;
	}
	
	public void updateReview(String rcontent, Long rno) {
		memberDao.updateReview(rcontent, rno);
	}
	
}
