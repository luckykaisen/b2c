package com.atguigu.service;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;

public interface CartService {

	/**
	 * 传入用户id,返回购物车中的商品集合
	 * @param paramMap
	 * @return
	 */
	List<T_MALL_SHOPPINGCAR> query_cart(Map<String, Object> paramMap);

	void add_cart(T_MALL_SHOPPINGCAR cart);

	void update_cart(T_MALL_SHOPPINGCAR old_cart , int sku_id);

	void add_cart_batch(Map<String, Object> paramMap);

	void update_same_cart_batch(List<T_MALL_SHOPPINGCAR> same_cart);

	void update_cart_commodity_status(Map<String, Object> paramMap);

	void update_cart_total_price_and_total_num(Map<String, Object> paramMap);

	void delete_cart_by_sku_id(Map<String, Object> paramMap);

	void delete_cart_commdity_by_sku_id_and_user_id(List<T_MALL_ORDER_INFO> list_order_info,int userId);

}
