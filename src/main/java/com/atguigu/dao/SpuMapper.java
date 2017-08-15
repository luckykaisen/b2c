package com.atguigu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.T_MALL_PRODUCT;

public interface SpuMapper {

	void save_spu(T_MALL_PRODUCT product);

	void save_spu_image(@Param("imageName") List<String> saveNames, @Param("shp_id") int shp_id);

	


}
