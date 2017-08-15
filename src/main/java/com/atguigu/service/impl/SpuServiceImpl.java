package com.atguigu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.dao.SpuMapper;
import com.atguigu.service.SpuService;
@Service
public class SpuServiceImpl implements SpuService{

	@Autowired
	private SpuMapper spuDao;
	
	public void save_spu(T_MALL_PRODUCT product) {
		spuDao.save_spu(product);
	}

	public void save_spu_image(List<String> saveNames, int id) {
		spuDao.save_spu_image(saveNames,id);
	}

}
