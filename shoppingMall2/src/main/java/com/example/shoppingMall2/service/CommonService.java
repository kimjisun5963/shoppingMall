package com.example.shoppingMall2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppingMall2.dao.ICommonDao;
import com.example.shoppingMall2.dto.Product;

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
}
