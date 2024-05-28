package com.example.shoppingMall2.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppingMall2.dao.IAdminDao;
import com.example.shoppingMall2.dto.Member;
import com.example.shoppingMall2.dto.Product;

@Service
public class AdminService {

	@Autowired
	IAdminDao adminDao;
	
	// 관리자 로그인 중
	public Member login(String username, String pw) {
		Member member = adminDao.login(username, pw);
		return member;
	}
	
	// 등록된 상품 가져오기
	public List<Product> getAllProducts(){
		List<Product> products = adminDao.getAllProducts();
		return products;
	}
	
	
	// 상품 등록하기
	public void registProduct(Product product) {
		adminDao.registProduct(product);
	}
	
	// 상품 정보 가져오기
	public Product getProduct(Long pno) {
		Product product = adminDao.getProduct(pno);
		return product;
	}
	
	// 상품 삭제하기
	public void deleteProduct(Long pno) {
		adminDao.deleteProduct(pno);
	}
	
	// 상품 수정하기
	public void modifyProduct(Product product) {
		adminDao.modifyProduct(product);
	}
	
	
}