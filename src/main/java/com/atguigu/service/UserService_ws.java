package com.atguigu.service;

import javax.jws.WebService;

import com.atguigu.bean.T_MALL_USER;
@WebService
public interface UserService_ws {

	T_MALL_USER login(T_MALL_USER user);
	
	int mall_regist(T_MALL_USER user);
	
	void sendSMS(String yh_shjh);
}
