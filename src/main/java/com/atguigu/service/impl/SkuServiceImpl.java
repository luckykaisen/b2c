package com.atguigu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_VALUE;
import com.atguigu.dao.SkuDao;
import com.atguigu.dao.SpuAttrDao;
import com.atguigu.service.SkuService;
import com.atguigu.service.SpuAttrService;

@Service
public class SkuServiceImpl implements SkuService{

	@Autowired
	private SkuDao skuDao;
	
	
}
