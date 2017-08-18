package com.atguigu.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MallIndexController {

	@RequestMapping("mall_index")
	public String mall_index(HttpServletRequest request , Map<String,Object> map) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null ) {
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("yh_mch")) {
					String value = cookies[i].getValue();
					map.put("yh_mch", value);
				}
			}
		}
		
		return "mall_index";
	}
}
