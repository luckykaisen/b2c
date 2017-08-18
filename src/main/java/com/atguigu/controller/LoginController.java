package com.atguigu.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.T_MALL_USER;
import com.atguigu.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping("goto_mall_login")
	public String goto_mall_login() {
		return "mall_login";
	}
	
	@RequestMapping("goto_mall_regist")
	public String goto_mall_regist() {
		return "mall_regist";
	}
	
	@RequestMapping("mall_login")
	public String mall_login(HttpSession session,HttpServletResponse response, T_MALL_USER user) {
		T_MALL_USER login_user = loginService.mall_login(user);
		// 登陆成功
		if(login_user != null) {
			session.setAttribute("user",login_user);
			Cookie cookie = new Cookie("yh_mch",user.getYh_mch());
			cookie.setMaxAge(60*60);
			cookie.setPath("/");
			response.addCookie(cookie);
			return "redirect:/mall_index.do";
		}else {
			// 登录失败
			return "mall_login";
		}
		
	}   
	
	
	@RequestMapping("mall_regist")
	public String mall_regist(T_MALL_USER user) {
		
		int result = loginService.mall_regist(user);
		
		if(result == 0) {
			return "mall_regist";
		}else {
			// 注册成功
			return "mall_index";
		}
		
	} 
	
	@RequestMapping("mall_logout")
	public String mall_logout(HttpSession session) {
		
		session.invalidate();
		
		return "mall_index";
	} 
}
