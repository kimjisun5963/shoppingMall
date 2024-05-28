package com.example.shoppingMall2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.shoppingMall2.dto.Member;
import com.example.shoppingMall2.dto.Product;

@Mapper
public interface IAdminDao {
	
	// 관리자 로그인 중
	public Member login(String username, String pw);
	// 등록된 상품 가져오기
	public List<Product> getAllProducts();
	// 상품 등록하기
	public void registProduct(@Param("product")Product product);
	
}
