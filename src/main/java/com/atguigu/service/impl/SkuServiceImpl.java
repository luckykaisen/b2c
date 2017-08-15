package com.atguigu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.dao.SkuDao;
import com.atguigu.service.SkuService;

@Service
public class SkuServiceImpl implements SkuService{

	@Autowired
	private SkuDao skuDao;

	@Override
	public List<T_MALL_PRODUCT> get_spu_sp(T_MALL_PRODUCT product) {
		return skuDao.select_spu_sp(product);
	}

	@Override
	public void save_sku(T_MALL_SKU sku) {
		skuDao.save_sku(sku);
	}

	@Override
	public void save_sku_attr_value(OBJECT_T_MALL_SKU_ATTR_VALUE list_sku, int sku_id,Integer shp_id) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		
		
		paramMap.put("list_sku", list_sku.getList_sku());
		paramMap.put("sku_id", sku_id);
		paramMap.put("shp_id", shp_id);
		skuDao.save_sku_attr_value(paramMap);
	}


	
	
}
