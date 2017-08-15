package com.atguigu.dao;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;

public interface SkuDao {

	List<T_MALL_PRODUCT> select_spu_sp(T_MALL_PRODUCT product);

	void save_sku(T_MALL_SKU sku);

	void save_sku_attr_value(Map<String, Object> paramMap);

}
