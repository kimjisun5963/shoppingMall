package com.example.shoppingMall2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppingMall2.dao.IMemberDao;
import com.example.shoppingMall2.dto.Member;

@Service
public class MemberService {
	
	@Autowired
	IMemberDao memberDao;
	public Member login(String username, String pw) {
		Member member = memberDao.login(username, pw);
		return member;
	}
	

	
}
