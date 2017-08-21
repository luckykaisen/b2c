package com.atguigu.service;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.MODEL_MALL_OBJECT_T_MALL_SPU_SKU;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;

public interface SerchService {

	List<OBJECT_T_MALL_SKU> get_sku_by_class_2(Map<String, Object> paramMap);

	
	List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(Integer flbh2);


	MODEL_MALL_OBJECT_T_MALL_SPU_SKU query_commodity_info(Map<String, Object> paramMap);


	List<T_MALL_SKU> query_commodity_info_by_sibling(Map<String, Object> paramMap);



}
