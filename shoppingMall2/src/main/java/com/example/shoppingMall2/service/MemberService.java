package com.example.shoppingMall2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppingMall2.dao.IMemberDao;
import com.example.shoppingMall2.dto.Member;
import com.example.shoppingMall2.dto.ShoppingBasketDto;

@Service
public class MemberService {
	
	@Autowired
	IMemberDao memberDao;
	
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
	
	public void modifyShoppingBasket(int samount, Long sbno) {
		//System.out.println("장바구니 수정" + samount + sbno);
		memberDao.modifyShoppingBasket(samount, sbno);
	}
	
	public void registShoppingBasket(Long pno, String username, int samount) {
		memberDao.registShoppingBasket(pno, username, samount);
	}
	
}
