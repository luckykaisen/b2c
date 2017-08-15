package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;

public interface SpuAttrService {

	List<OBJECT_T_MALL_ATTR> select_attr_by_class_2_id(Integer id);

	void save_attr_and_value(List<OBJECT_T_MALL_ATTR> list_attr2);


}
