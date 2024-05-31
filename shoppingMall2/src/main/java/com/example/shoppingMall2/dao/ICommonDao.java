package com.example.shoppingMall2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.shoppingMall2.dto.Product;
import com.example.shoppingMall2.dto.Review;

@Mapper
public interface ICommonDao {
	
	public List<Product> getProductAllList();
	public Product getProductDetail(Long pno);
	public List<Product> hotProductList();
	public List<Product> newProductList();
	public Review getReview(Long rno);
}
