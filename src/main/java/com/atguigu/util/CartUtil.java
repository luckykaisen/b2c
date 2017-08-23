package com.atguigu.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;

public class CartUtil {

	/**
	 * 传入一个购物车，返回购物车中选中商品的总价格，总数量
	 * @param list_cart
	 * @return
	 */
	public static Map<String,Object> get_select_cart_total_price_and_total_num(List<T_MALL_SHOPPINGCAR> list_cart){
		Map<String,Object> map = new HashMap<String,Object>();
		// 总价格
		BigDecimal total_price = new BigDecimal("0");
		
		// 商品总数量
		int total_commodity = 0;
		if(list_cart != null && list_cart.size() > 0) {
			for (int i = 0; i < list_cart.size(); i++) {
				T_MALL_SHOPPINGCAR cart = list_cart.get(i);
				if(cart.getShfxz().equals("1")) {
					total_commodity += cart.getTjshl();
					total_price = total_price.add(cart.getHj());
				}
			}
		}
		
		map.put("total_price" , total_price);
		map.put("total_commodity" , total_commodity);
		return map;
	}
}
