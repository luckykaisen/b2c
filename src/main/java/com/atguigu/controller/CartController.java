package com.atguigu.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER;
import com.atguigu.service.CartService;
import com.atguigu.util.BigDecimalUtil;
import com.atguigu.util.CookieUtil;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping("goto_add_cart_success")
	public String goto_add_cart_success() {
		return "mall_add_cart_success";
	}
	
	@RequestMapping("add_cart")
	public ModelAndView add_cart(T_MALL_SHOPPINGCAR cart,HttpServletRequest request,HttpSession session,HttpServletResponse response,
			@CookieValue(value="cart_cookie",required=false)String cart_cookie) {
		ModelAndView mav = new ModelAndView("redirect:/goto_add_cart_success.do");
		
		// 用来向mybatis中传参
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		// 购物车集合
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		
		// 判断用户是否登录
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		// 用户没有登录
		if(user == null) {
			// cookie中没有商品
			if(StringUtils.isBlank(cart_cookie)) {
				list_cart.add(cart);
			} else {
				// cookie中有商品
				// 把cookie字符串转换为数组对象
				try {
					cart_cookie = URLDecoder.decode(cart_cookie, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				list_cart = JSON.parseArray(cart_cookie, T_MALL_SHOPPINGCAR.class);
				Map<String,Object> cookieMap = CookieUtil.isNewCart(list_cart, cart);
				
				//不重复，新的商品
				if(cookieMap.get("is_new_cart").equals("true")) {
					// ok
				} else {
					// 重复商品
					int new_sku_id = cart.getSku_id();
					
					// 在购物车找到重复的商品
					T_MALL_SHOPPINGCAR old_cart = (T_MALL_SHOPPINGCAR)cookieMap.get("old_cart");
					old_cart.setTjshl(old_cart.getTjshl() + cart.getTjshl());
					// 添加的商品数量
					int tjshl = old_cart.getTjshl();
					// 商品的单价
					double sku_jg = old_cart.getSku_jg();
					
					// 返回合计价格
					BigDecimal hj = BigDecimalUtil.multiply(tjshl, sku_jg);
					old_cart.setHj(hj);
				}
				
			}
			
			// 把购物车转换为json字符串
			String list_cart_json_string = JSON.toJSONString(list_cart);
			
			try {
				list_cart_json_string = URLEncoder.encode(list_cart_json_string, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// 把购物车放入cookie中
			Cookie cookie = new Cookie("cart_cookie",list_cart_json_string);
			cookie.setMaxAge(60*60);
			cookie.setPath("/");
			response.addCookie(cookie);
		} else {
			// 用户已登录
			// 查询数据库中是否有购物车数据
			paramMap.put("yh_id", user.getId());
			// 取出当前用户的购物车
			list_cart = cartService.query_cart(paramMap);
			
			// 购物车没有商品
			if(list_cart.size() == 0) {
				cart.setYh_id(user.getId());
				double sku_jg = cart.getSku_jg();
				BigDecimal bigDecimal = new BigDecimal(sku_jg + "");
				cart.setHj(bigDecimal);
				cartService.add_cart(cart);
			} else {
				// 购物车中有商品
				// 查询添加商品在数据库中是否存在
				Map<String,Object> cookieMap = CookieUtil.isNewCart(list_cart, cart);
				
				// 添加的商品是新的
				if(cookieMap.get("is_new_cart").equals("true")) {
					cart.setYh_id(user.getId());
					double sku_jg = cart.getSku_jg();
					BigDecimal bigDecimal = new BigDecimal(sku_jg + "");
					cart.setHj(bigDecimal);
					cartService.add_cart(cart);
				} else {
					// 添加的是重复商品
					T_MALL_SHOPPINGCAR old_cart = (T_MALL_SHOPPINGCAR)cookieMap.get("old_cart");
					// 新添加的商品数量
					int tjshl = cart.getTjshl();
					
					// 已有的商品数量
					int old_tjshl = old_cart.getTjshl();
					
					// 商品单价
					double sku_jg = cart.getSku_jg();
					// 总价格
					BigDecimal hj = BigDecimalUtil.multiply(tjshl + old_tjshl, sku_jg);
					cart.setYh_id(user.getId());
					cart.setHj(hj);
					cart.setTjshl(tjshl + old_tjshl);
					
					// 更新数据库中的购物车商品信息
					cartService.update_cart(cart ,cart.getSku_id());
				}
			}
			list_cart.add(cart);
			session.setAttribute("cart_session", list_cart);
		}
		return mav;
	}
	
	
	
}
