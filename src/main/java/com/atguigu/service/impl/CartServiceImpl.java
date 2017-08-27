package com.atguigu.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_ORDER_INFO;
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

	@Override
	public void add_cart_batch(Map<String,Object> paramMap) {
		cartMapper.add_cart_batch(paramMap);
	}

	@Override
	public void update_same_cart_batch(List<T_MALL_SHOPPINGCAR> list) {
		cartMapper.update_same_cart_batch(list);
	}

	@Override
	public void update_cart_commodity_status(Map<String, Object> paramMap) {
		cartMapper.update_cart_commodity_status(paramMap);
	}

	/**
	 * 更新购物车中商品的总数量以及总价格
	 */
	@Override
	public void update_cart_total_price_and_total_num(Map<String, Object> paramMap) {
		cartMapper.update_cart_total_price_and_total_num(paramMap);
	}

	@Override
	public void delete_cart_by_sku_id(Map<String, Object> paramMap) {
		cartMapper.delete_cart_by_sku_id(paramMap);
	}

	@Override
	public void delete_cart_commdity_by_sku_id_and_user_id(List<T_MALL_ORDER_INFO> list_order_info,int userId) {
		cartMapper.delete_cart_commdity_by_sku_id_and_user_id(list_order_info,userId);
	}
}
