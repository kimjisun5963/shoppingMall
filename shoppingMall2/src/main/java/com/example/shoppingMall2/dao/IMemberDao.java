package com.example.shoppingMall2.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.shoppingMall2.dto.Member;

@Mapper
public interface IMemberDao {

	public Member login(String username, String pw);
	public void signup(@Param("member")Member member);
}
