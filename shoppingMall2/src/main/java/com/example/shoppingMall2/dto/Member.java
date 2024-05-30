package com.example.shoppingMall2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	private Long mno;
	private String username;
	private String password;
	private String name;
	private String tel;
	private String addr;
	private String role;
	
}
