package com.example.shoppingMall2.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFile {

	private Long pno;
	private String pname;
	private MultipartFile pimg;
	private String pcontent;
	private MultipartFile pcontent_img;
	private int stockprice;
	private int saleprice;
	private String regdate;
	private Long amount;
	
	public String getFileNamePimg() {
		return pimg.getOriginalFilename();
	}
	public String getFileNamePcontent_img() {
		return pcontent_img.getOriginalFilename();
	}
}
