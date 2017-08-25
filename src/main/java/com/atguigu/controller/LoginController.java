package com.atguigu.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER;
import com.atguigu.service.CartService;
import com.atguigu.service.LoginService;
import com.atguigu.service.UserService_ws;
import com.atguigu.util.BigDecimalUtil;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService_ws userService_ws;
	
	@RequestMapping("goto_mall_login")
	public String goto_mall_login() {
		return "mall_login";
	}
	
	@RequestMapping("goto_mall_regist")
	public String goto_mall_regist() {
		return "mall_regist";
	}
	
	@RequestMapping("mall_login")
	public String mall_login(@CookieValue(value="cart_cookie",required=false)String cart_cookie ,HttpSession session,HttpServletResponse response, T_MALL_USER user) {
		//T_MALL_USER login_user = loginService.mall_login(user);
		T_MALL_USER login_user = userService_ws.login(user);
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		// 登陆成功
		if(login_user != null) {
			// 登录成功把数据库中的购物车放入session中
			paramMap.put("yh_id", login_user.getId());
			// 数据库中的购物车
			list_cart = cartService.query_cart(paramMap);
			
			// cookie中没有商品
			if(cart_cookie == null || StringUtils.isBlank(cart_cookie)) {
				// ok
			}else {
				// cookie中有商品
				try {
					cart_cookie = URLDecoder.decode(cart_cookie, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				List<T_MALL_SHOPPINGCAR> list_cart_cookie = JSON.parseArray(cart_cookie, T_MALL_SHOPPINGCAR.class);
				
				if(list_cart == null || list_cart.size() == 0) {
					// 数据库中没有商品
					// 把cookie中的购物车批量添加到数据库
					list_cart = list_cart_cookie;
					paramMap.put("list_cart", list_cart_cookie);
					cartService.add_cart_batch(paramMap);
				}else {
					// 存放商品相同的购物车
					List<T_MALL_SHOPPINGCAR> same_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
					
					// 存放商品不同的购物车
					List<T_MALL_SHOPPINGCAR> different_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
					
					// 数据库中有商品
					Map<Integer,T_MALL_SHOPPINGCAR> cart_map = new HashMap<Integer,T_MALL_SHOPPINGCAR>();
					for (int i = 0; i < list_cart.size(); i++) {
						cart_map.put(list_cart.get(i).getSku_id(), list_cart.get(i));
					}
					
					for (int i = 0; i < list_cart_cookie.size(); i++) {
						
						T_MALL_SHOPPINGCAR new_cart = list_cart_cookie.get(i);
						
						if(cart_map.get(new_cart.getSku_id()) == null) {
//							// 
//							BigDecimal sum_hj = BigDecimalUtil.multiply(new_cart.getTjshl(), new_cart.getSku_jg());
//							new_cart.setHj(sum_hj);
							// 不相同
							different_cart.add(new_cart);
							list_cart.add(new_cart);
						}else {
							// 相同
							// 把相同的商品放入集合中，集体批量更新
							T_MALL_SHOPPINGCAR odl_cart = cart_map.get(new_cart.getSku_id());
							int tjshl = new_cart.getTjshl();
							int tjshl2 = odl_cart.getTjshl();
							int sum_tjshl = tjshl + tjshl2;
							BigDecimal sum_hj = BigDecimalUtil.multiply(sum_tjshl, new_cart.getSku_jg());
							odl_cart.setHj(sum_hj);
							odl_cart.setTjshl(sum_tjshl);
							same_cart.add(odl_cart);
						}
					}
					
					if(same_cart != null && same_cart.size() != 0) {
						//paramMap.put("list_cart_batch", same_cart);
						// 相同商品更新
						cartService.update_same_cart_batch(same_cart);
					}
					
					if(different_cart != null && different_cart.size() != 0) {
						// 不同商品添加
						paramMap.put("list_cart", different_cart);
						cartService.add_cart_batch(paramMap);
					}
					
					
				}
			
				// 清空cookie中的数据
				Cookie cookie = new Cookie("cart_cookie" ,"");
				cookie.setMaxAge(-1);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			
			session.setAttribute("list_cart", list_cart);
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
	public String mall_regist(T_MALL_USER user ) {
		int result = userService_ws.mall_regist(user);
		userService_ws.sendSMS(user.getYh_shjh());
		if(result == 0) {
			return "mall_regist";
		}else {
			// 注册成功
			return "redirect:/mall_index.do";
		}
		
	} 
	
	@RequestMapping("mall_logout")
	public String mall_logout(HttpSession session) {
		
		session.invalidate();
		return "mall_index";
	} 
}
