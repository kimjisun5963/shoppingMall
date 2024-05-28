package com.example.shoppingMall2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	private Long pno;
	private String pname;
	private String pimg;
	private String pcontent;
	private String pcontent_img;
	private int stockprice;
	private int saleprice;
	private String regdate;
	private Long amount;
}
