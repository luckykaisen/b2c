package com.atguigu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;

public interface CartMapper {

	List<T_MALL_SHOPPINGCAR> select_cart(Map<String, Object> paramMap);

	void add_cart(T_MALL_SHOPPINGCAR cart);

	void update_cart(@Param("old_cart")T_MALL_SHOPPINGCAR old_cart ,@Param("sku_id") int sku_id);

	void add_cart_batch(Map<String, Object> paramMap);

	void update_same_cart_batch(@Param("list")List<T_MALL_SHOPPINGCAR> list);

	void update_cart_commodity_status(Map<String, Object> paramMap);

	void update_cart_total_price_and_total_num(Map<String, Object> paramMap);

	void delete_cart_by_sku_id(Map<String, Object> paramMap);

	void delete_cart_commdity_by_sku_id_and_user_id(@Param("list_order_info")List<T_MALL_ORDER_INFO> list_order_info,@Param("yh_id")int userId);

}
