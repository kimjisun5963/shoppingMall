package com.example.shoppingMall2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesDetail {
	private Long sno;
	private String salesDate;
	private int samount;
	private String scode;
	private Long pno;
	private String pname;
	private String pimg;
	private int saleprice;
}
