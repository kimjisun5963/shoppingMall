package com.example.shoppingMall2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
	@RequestMapping("/")
	public String root(Model model) {
		model.addAttribute("test", "test");
		return "index";
	}
}
