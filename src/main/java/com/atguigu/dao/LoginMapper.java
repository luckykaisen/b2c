package com.atguigu.dao;

import com.atguigu.bean.T_MALL_USER;

public interface LoginMapper {

	T_MALL_USER mall_login(T_MALL_USER user);

	int mall_regist(T_MALL_USER user);

}
