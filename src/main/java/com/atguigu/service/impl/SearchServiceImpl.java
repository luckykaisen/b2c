package com.atguigu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MODEL_MALL_OBJECT_T_MALL_SPU_SKU;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.dao.SearchMapper;
import com.atguigu.service.SerchService;

@Service
public class SearchServiceImpl implements SerchService{
	
	@Autowired
	private SearchMapper serchMapper;

	@Override
	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2(Map<String,Object> paramMap) {
		return serchMapper.select_sku_by_class_2(paramMap);
	}

	@Override
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(Integer flbh2) {
		return serchMapper.select_attr_by_class_2_id(flbh2);
	}

	
	/**
	 * 点击商品信息，进入商品的详细信息购买网页,通过这两个值查询出对应的某一个商品
	 */
	@Override
	public MODEL_MALL_OBJECT_T_MALL_SPU_SKU query_commodity_info(Map<String, Object> paramMap) {
		return serchMapper.select_commodity_info(paramMap);
	}

	@Override
	public List<T_MALL_SKU> query_commodity_info_by_sibling(Map<String, Object> paramMap) {
		return serchMapper.select_commodity_info_by_sibling(paramMap);
	}
}
