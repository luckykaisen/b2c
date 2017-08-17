package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MangerIndexController {
	
	@RequestMapping("index")
	public String manager_index() {
		return "manager_index";
	}
}
