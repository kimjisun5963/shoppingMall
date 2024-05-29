package com.example.shoppingMall2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingBasketDto {
	private Long pno;
	private String pname;
	private String pimg;
	private int saleprice;
	private Long sbno;
	private String username;
	private Long samount;
}
