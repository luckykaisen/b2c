package com.atguigu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_USER;
import com.atguigu.dao.LoginMapper;
import com.atguigu.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginMapper loginMapper;

	@Override
	public T_MALL_USER mall_login(T_MALL_USER user) {
		return loginMapper.mall_login(user);
	}

	@Override
	public int mall_regist(T_MALL_USER user) {
		return loginMapper.mall_regist(user);
	}
	
}
