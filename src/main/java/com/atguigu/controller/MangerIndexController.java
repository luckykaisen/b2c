package com.atguigu.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MangerIndexController {
	
	@RequestMapping("index")
	public String manager_index(Map<String,Object> map , @RequestParam(value="url",required=false)String url,@RequestParam(value="title",required=false)String title) {
		map.put("url", url);
		map.put("title", title);
		return "manager_index";
	}
}
