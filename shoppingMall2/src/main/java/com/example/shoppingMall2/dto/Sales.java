package com.example.shoppingMall2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sales {
	private Long sno;
	private Long pno;
	private String username;
	private Long samount;
}
