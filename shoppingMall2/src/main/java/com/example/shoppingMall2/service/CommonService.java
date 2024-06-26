package com.example.shoppingMall2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppingMall2.dao.ICommonDao;
import com.example.shoppingMall2.dto.Product;
import com.example.shoppingMall2.dto.Review;

@Service
public class CommonService {

	@Autowired
	ICommonDao commonDao;
	
	public List<Product> getProductAllList(){
		List<Product> list = commonDao.getProductAllList();
		return list;
	}
	
	public Product getProductDetail(Long pno) {
		Product product = commonDao.getProductDetail(pno);
		return product;
	}
	
	public List<Product> hotProductList(){
		List<Product> list = commonDao.hotProductList();
		return list;
	}
	
	public List<Product> newProductList(){
		List<Product> list = commonDao.newProductList();
		return list;
	}
	
	public Review getReview(Long rno) {
		Review review = commonDao.getReview(rno);
		return review;
	}
}
