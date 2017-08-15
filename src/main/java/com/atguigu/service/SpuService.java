package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.T_MALL_PRODUCT;

public interface SpuService {

	void save_spu(T_MALL_PRODUCT product);
	
	void save_spu_image(List<String> saveNames, int id);
	
}
