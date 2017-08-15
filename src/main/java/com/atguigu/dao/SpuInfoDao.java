package com.atguigu.dao;

import java.util.List;

import com.atguigu.bean.T_MALL_PRODUCT;

public interface SpuInfoDao {

	List<T_MALL_PRODUCT> query_spu_all_info();

	T_MALL_PRODUCT query_spu_info_by_id(Integer id);

}
