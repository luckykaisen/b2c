package com.atguigu.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;

public class CookieUtil {


	
	
	/**
	 * 判断新添加购物车中的商品是否是新的商品
	 * @param list_cart
	 * @param cart
	 * @return ： 新商品返回true，购物车中重复的商品返回false
	 */
	public static Map<String,Object> isNewCart(List<T_MALL_SHOPPINGCAR> list_cart, T_MALL_SHOPPINGCAR cart) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		// 商品中，sku_id 是唯一的，所以用sku_id判断是否是重复的商品
		
		String flag = "true";
		
		int new_sku_id = cart.getSku_id();
		
		for (int i = 0; i < list_cart.size(); i++) {
			T_MALL_SHOPPINGCAR old_cart = list_cart.get(i);
			// 如果sku相等，是重复商品
			if(old_cart.getSku_id() == new_sku_id) {
				flag = "false";
				paramMap.put("old_cart", old_cart);
				break;
			}
		}
		paramMap.put("is_new_cart", flag);
		
		return paramMap;
	}
}
