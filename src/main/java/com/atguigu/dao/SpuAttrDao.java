package com.atguigu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_VALUE;

public interface SpuAttrDao {

	List<OBJECT_T_MALL_ATTR> select_attr_by_class_2_id(Integer id);

	void save_attr( OBJECT_T_MALL_ATTR object_T_MALL_ATTR);

	void save_attr_value(T_MALL_VALUE t_MALL_VALUE);

	//void save_attr(@Param("flbh2") int flbh2, @Param("shxm_mch")String shxm_mch);
	
	
}
