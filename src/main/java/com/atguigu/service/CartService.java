package com.atguigu.service;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;

public interface CartService {

	List<T_MALL_SHOPPINGCAR> query_cart(Map<String, Object> paramMap);

	void add_cart(T_MALL_SHOPPINGCAR cart);

	void update_cart(T_MALL_SHOPPINGCAR old_cart , int sku_id);

}
