package com.example.shoppingMall2.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.shoppingMall2.dto.Member;

@Mapper
public interface IAdminDao {
	
	// 관리자 로그인 중
	public Member login(String username, String pw);
	
}
