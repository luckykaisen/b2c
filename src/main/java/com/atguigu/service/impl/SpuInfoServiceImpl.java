package com.atguigu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MODEL_OBJECT_T_MALL_VALUE;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.dao.SpuInfoDao;
import com.atguigu.service.SpuInfoService;

@Service
public class SpuInfoServiceImpl implements SpuInfoService {
	
	@Autowired
	private SpuInfoDao spuInfoDao;
	
	@Override
	public List<MODEL_OBJECT_T_MALL_VALUE> query_spu_all_info() {
		
		return spuInfoDao.query_spu_all_info();
		
	}

	@Override
	public T_MALL_PRODUCT query_spu_info_by_id(Integer id) {
		return spuInfoDao.query_spu_info_by_id(id);
		 
	}

}
