package com.atguigu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.dao.CartMapper;
import com.atguigu.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;

	@Override
	public List<T_MALL_SHOPPINGCAR> query_cart(Map<String, Object> paramMap) {
		return cartMapper.select_cart(paramMap);
	}

	@Override
	public void add_cart(T_MALL_SHOPPINGCAR cart) {
		cartMapper.add_cart(cart);
	}

	@Override
	public void update_cart(T_MALL_SHOPPINGCAR old_cart , int sku_id) {
		cartMapper.update_cart(old_cart , sku_id);
	}
}
