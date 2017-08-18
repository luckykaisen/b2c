package com.atguigu.service;

import com.atguigu.bean.T_MALL_USER;

public interface LoginService {

	T_MALL_USER mall_login(T_MALL_USER user);

	int mall_regist(T_MALL_USER user);

}
