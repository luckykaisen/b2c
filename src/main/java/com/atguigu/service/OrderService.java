package com.atguigu.service;

import com.atguigu.bean.MODEL_OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER;

public interface OrderService {
	
	void saveOrderInfo(MODEL_OBJECT_T_MALL_ORDER order , T_MALL_USER user , T_MALL_ADDRESS address);
}
