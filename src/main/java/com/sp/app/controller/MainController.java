package com.sp.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String first() {
		return "home/first";
	}
	
	@GetMapping("/second")
	public String second() {
		return "home/second";
	}
	
	@GetMapping("/main")
	public String main() {
		
		return "home/main";
	}
	
}
