package com.example.shoppingMall2.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.shoppingMall2.dto.Member;
import com.example.shoppingMall2.dto.Sales;
import com.example.shoppingMall2.dto.ShoppingBasket;
import com.example.shoppingMall2.dto.ShoppingBasketDto;

@Mapper
public interface IMemberDao {

	public Member login(String username, String pw);
	public void signup(@Param("member")Member member);
	public List<ShoppingBasketDto> shoppingBasketList(String username);
	public void modifyShoppingBasket(Long samount, Long sbno);
	public void registShoppingBasket(Long pno, String username, Long samount);
	public void deleteShoppingBasket(Long bsno);
	public void salesOneProduct(@Param("sales")Sales sales);
	public ShoppingBasket getShoppingBasket(Long sbno);
}
