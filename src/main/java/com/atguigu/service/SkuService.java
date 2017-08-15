package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;

public interface SkuService {

	List<T_MALL_PRODUCT> get_spu_sp(T_MALL_PRODUCT product);

	void save_sku(T_MALL_SKU sku);

	void save_sku_attr_value(OBJECT_T_MALL_SKU_ATTR_VALUE list_sku, int sku_id ,Integer shp_id);

}
