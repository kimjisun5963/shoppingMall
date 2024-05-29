package com.example.shoppingMall2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

	private Long rno;
	private Long pno;
	private String username;
	private String rcontent;
	private int scope;
	private String regdate;
	private int check;
}
