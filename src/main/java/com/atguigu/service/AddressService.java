package com.atguigu.service;

import java.util.List;

import javax.jws.WebService;

import com.atguigu.bean.T_MALL_ADDRESS;

@WebService
public interface AddressService {
	
	int saveUserAddress(T_MALL_ADDRESS address);
	
	List<T_MALL_ADDRESS> getAddressByUserId(String userId);
	
	T_MALL_ADDRESS getAddressById(String id);
}
