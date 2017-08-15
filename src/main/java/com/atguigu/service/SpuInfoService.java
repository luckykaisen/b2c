package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.T_MALL_PRODUCT;

public interface SpuInfoService {

	List<T_MALL_PRODUCT> query_spu_all_info();

	T_MALL_PRODUCT query_spu_info_by_id(Integer id);
	
}
