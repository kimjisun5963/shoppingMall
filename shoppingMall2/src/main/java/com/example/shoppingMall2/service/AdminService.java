package com.example.shoppingMall2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppingMall2.dao.IAdminDao;
import com.example.shoppingMall2.dto.Member;

@Service
public class AdminService {

	@Autowired
	IAdminDao adminDao;
	
	// 관리자 로그인 중
	public Member login(String username, String pw) {
		Member member = adminDao.login(username, pw);
		return member;
	}
	
}