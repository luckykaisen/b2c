package com.atguigu.controller;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Repeatable;
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
import com.atguigu.util.CartUtil;
import com.atguigu.util.CookieUtil;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;
	
	@RequestMapping("goto_add_cart_success")
	public String goto_add_cart_success() {
		return "mall_add_cart_success";
	}
	
	
	/**
	 * 用户改变购物车中商品的数量
	 * @param check
	 * @param sku_id
	 * @param session
	 * @param request
	 * @param cart_cookie
	 * @return
	 */
	@RequestMapping("delete_cart_by_sku_id")
	public String delete_cart_by_sku_id(Map<String,Object> map , int sku_id , HttpSession session , HttpServletRequest request ,HttpServletResponse response,@CookieValue(value="cart_cookie",required=false)String cart_cookie) {
		// 判断用户是否登录
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		
		// 购物车
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		
		// 数据库传参数
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		if(user == null) {
			if(StringUtils.isBlank(cart_cookie)) {
				// ok
			} else {
				try {
					cart_cookie = URLDecoder.decode(cart_cookie, "UTF-8");
					list_cart = JSON.parseArray(cart_cookie, T_MALL_SHOPPINGCAR.class);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < list_cart.size(); i++) {
					if(list_cart.get(i).getSku_id() == sku_id) {
						
						// 把购物车中的商品移除
						list_cart.remove(i);
						break;
					}
				}
				// 把购物车转换为json字符串
				String list_cart_json_string = JSON.toJSONString(list_cart);
				
				try {
					list_cart_json_string = URLEncoder.encode(list_cart_json_string, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				Cookie cookie = new Cookie("cart_cookie",list_cart_json_string);
				cookie.setMaxAge(60*60);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			
		} else {
			// 用户已登录
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart");
			for (int i = 0; i < list_cart.size(); i++) {
				if(list_cart.get(i).getSku_id() == sku_id) {
					// 把购物车中的商品移除
					list_cart.remove(i);
					break;
				}
			}
			paramMap.put("sku_id", sku_id);
			paramMap.put("yh_id", user.getId());
			// 修改数据库中，购物车某件商品的选中状态
			cartService.delete_cart_by_sku_id(paramMap);
		}
		// 传入一个购物车，返回购物车中选中商品的总价格，总数量
		Map<String,Object> map_attr = new HashMap<String,Object>();
		map_attr = CartUtil.get_select_cart_total_price_and_total_num(list_cart);
		map.put("total_price", map_attr.get("total_price"));
		map.put("total_commodity", map_attr.get("total_commodity"));
		map.put("list_cart" , list_cart);
		return "mall_cart_inner_list";
	}
	
	
	/**
	 * 
	 * 用户改变购物车中商品的数量
	 * @param check
	 * @param sku_id
	 * @param session
	 * @param request
	 * @param cart_cookie
	 * @return
	 */
	@RequestMapping("add_and_subtract")
	public String add_and_subtract(Map<String,Object> map , int tjshl , int sku_id , HttpSession session , HttpServletRequest request ,HttpServletResponse response,@CookieValue(value="cart_cookie",required=false)String cart_cookie) {
		// 判断用户是否登录
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		
		// 购物车
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		
		// 数据库传参数
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		if(user == null) {
			if(StringUtils.isBlank(cart_cookie)) {
				// ok
			} else {
				try {
					cart_cookie = URLDecoder.decode(cart_cookie, "UTF-8");
					list_cart = JSON.parseArray(cart_cookie, T_MALL_SHOPPINGCAR.class);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < list_cart.size(); i++) {
					if(list_cart.get(i).getSku_id() == sku_id) {
						
						double sku_jg = list_cart.get(i).getSku_jg();
						BigDecimal hj = BigDecimalUtil.multiply(tjshl, sku_jg);
						list_cart.get(i).setTjshl(tjshl);
						list_cart.get(i).setHj(hj);
						break;
					}
				}
				// 把购物车转换为json字符串
				String list_cart_json_string = JSON.toJSONString(list_cart);
				
				try {
					list_cart_json_string = URLEncoder.encode(list_cart_json_string, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				Cookie cookie = new Cookie("cart_cookie",list_cart_json_string);
				cookie.setMaxAge(60*60);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			
		} else {
			// 用户已登录
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart");
			for (int i = 0; i < list_cart.size(); i++) {
				if(list_cart.get(i).getSku_id() == sku_id) {
					double sku_jg = list_cart.get(i).getSku_jg();
					BigDecimal hj = BigDecimalUtil.multiply(tjshl, sku_jg);
					list_cart.get(i).setTjshl(tjshl);
					list_cart.get(i).setHj(hj);
					
					paramMap.put("hj", hj);
					break;
				}
			}
			paramMap.put("tjshl", tjshl);
			paramMap.put("sku_id", sku_id);
			paramMap.put("yh_id", user.getId());
			// 修改数据库中，购物车某件商品的选中状态
			cartService.update_cart_total_price_and_total_num(paramMap);
		}
		
		// 传入一个购物车，返回购物车中选中商品的总价格，总数量
		Map<String,Object> map_attr = new HashMap<String,Object>();
		map_attr = CartUtil.get_select_cart_total_price_and_total_num(list_cart);
		map.put("total_price", map_attr.get("total_price"));
		map.put("total_commodity", map_attr.get("total_commodity"));
		map.put("list_cart",list_cart);
		
		return "mall_cart_inner_list";
	}
	
	
	/**
	 * 用户改变购物车中商品的勾选状态
	 * @param check
	 * @param sku_id
	 * @param session
	 * @param request
	 * @param cart_cookie
	 * @return
	 */
	@RequestMapping("commdity_check")
	public String commdity_check(Map<String,Object> map , String check , int sku_id , HttpSession session , HttpServletRequest request ,HttpServletResponse response,@CookieValue(value="cart_cookie",required=false)String cart_cookie) {
		// 判断用户是否登录
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		
		// 购物车
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		
		// 数据库传参数
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		if(user == null) {
			if(StringUtils.isBlank(cart_cookie)) {
				// ok
			} else {
				try {
					cart_cookie = URLDecoder.decode(cart_cookie, "UTF-8");
					list_cart = JSON.parseArray(cart_cookie, T_MALL_SHOPPINGCAR.class);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < list_cart.size(); i++) {
					if(list_cart.get(i).getSku_id() == sku_id) {
						list_cart.get(i).setShfxz(check);
						break;
					}
				}
				// 把购物车转换为json字符串
				String list_cart_json_string = JSON.toJSONString(list_cart);
				
				try {
					list_cart_json_string = URLEncoder.encode(list_cart_json_string, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				Cookie cookie = new Cookie("cart_cookie",list_cart_json_string);
				cookie.setMaxAge(60*60);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			
		} else {
			// 用户已登录
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart");
			for (int i = 0; i < list_cart.size(); i++) {
				if(list_cart.get(i).getSku_id() == sku_id) {
					list_cart.get(i).setShfxz(check);
					break;
				}
			}
			paramMap.put("shfxz", check);
			paramMap.put("sku_id", sku_id);
			paramMap.put("yh_id", user.getId());
			// 修改数据库中，购物车某件商品的选中状态
			cartService.update_cart_commodity_status(paramMap);
		}
		
		// 传入一个购物车，返回购物车中选中商品的总价格，总数量
		Map<String,Object> map_attr = new HashMap<String,Object>();
		map_attr = CartUtil.get_select_cart_total_price_and_total_num(list_cart);
		map.put("total_price", map_attr.get("total_price"));
		map.put("total_commodity", map_attr.get("total_commodity"));
		map.put("list_cart",list_cart);
		
		return "mall_cart_inner_list";
	}
	
	
	/**
	 * 跳转到购物车页面
	 * @param map
	 * @param cart_cookie
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("goto_cart_list")
	public String goto_cart_list(Map<String,Object> map ,@CookieValue(value="cart_cookie",required=false)String cart_cookie ,HttpSession session , HttpServletRequest request) {

		Map<String,Object> paramMap = new HashMap<String,Object>();
		// 判断用户是否登录
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		
		// 没有登录从cookie中获取购物车
		if(user == null) {
			if(StringUtils.isBlank(cart_cookie)) {
				// ok
			} else {
				try {
					cart_cookie = URLDecoder.decode(cart_cookie, "UTF-8");
					list_cart = JSON.parseArray(cart_cookie, T_MALL_SHOPPINGCAR.class);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
			}
			
		} else {
			// 用户已登录
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart");
		}
		
		// 传入一个购物车，返回购物车中选中商品的总价格，总数量
		Map<String,Object> map_attr = new HashMap<String,Object>();
		map_attr = CartUtil.get_select_cart_total_price_and_total_num(list_cart);
		map.put("total_price", map_attr.get("total_price"));
		map.put("total_commodity", map_attr.get("total_commodity"));
		map.put("list_cart" , list_cart);
		
		return "mall_cart_list";
	}
	
	/**
	 * 鼠标移动到迷你购物车按钮显示购物车中商品
	 * @param map
	 * @param cart_cookie
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("show_mini_cart")
	public String show_mini_cart(Map<String,Object> map ,@CookieValue(value="cart_cookie",required=false)String cart_cookie ,HttpSession session , HttpServletRequest request) {
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		// 判断用户是否登录
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		
		// 没有登录从cookie中获取购物车
		if(user == null) {
			if(StringUtils.isBlank(cart_cookie)) {
				// ok
			} else {
				try {
					cart_cookie = URLDecoder.decode(cart_cookie, "UTF-8");
					list_cart = JSON.parseArray(cart_cookie, T_MALL_SHOPPINGCAR.class);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
			}
			
		} else {
			// 用户已登录
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart");
		}
		// 传入一个购物车，返回购物车中选中商品的总价格，总数量
		Map<String,Object> map_attr = new HashMap<String,Object>();
		map_attr = CartUtil.get_select_cart_total_price_and_total_num(list_cart);
		map.put("total_price", map_attr.get("total_price"));
		map.put("total_commodity", map_attr.get("total_commodity"));
		map.put("list_cart" , list_cart);
		return "mall_mini_cart_inner_list";
	}
	
	/**
	 * 添加购物车
	 * @param cart
	 * @param request
	 * @param session
	 * @param response
	 * @param cart_cookie
	 * @return
	 */
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
				int tjshl = cart.getTjshl();
				double sku_jg = cart.getSku_jg();
				BigDecimal total_price = BigDecimalUtil.multiply(tjshl, sku_jg);
				cart.setHj(total_price);
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
					BigDecimal sum_hj = BigDecimalUtil.multiply(cart.getTjshl(), cart.getSku_jg());
					cart.setHj(sum_hj);
					list_cart.add(cart);
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
			session.setAttribute("list_cart", list_cart);
		}
		
		return mav;
	}
	
	
	
}
