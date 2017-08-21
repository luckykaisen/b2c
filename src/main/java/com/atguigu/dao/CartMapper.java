package com.atguigu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;

public interface CartMapper {

	List<T_MALL_SHOPPINGCAR> select_cart(Map<String, Object> paramMap);

	void add_cart(T_MALL_SHOPPINGCAR cart);

	void update_cart(@Param("old_cart")T_MALL_SHOPPINGCAR old_cart ,@Param("sku_id") int sku_id);

}
