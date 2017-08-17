package com.atguigu.bean;

import java.util.List;

public class MODEL_OBJECT_T_MALL_VALUE extends T_MALL_PRODUCT{
	
	List<OBJECT_T_MALL_VALUE> list_value;

	public MODEL_OBJECT_T_MALL_VALUE() {
		super();
	}

	public MODEL_OBJECT_T_MALL_VALUE(List<OBJECT_T_MALL_VALUE> list_value) {
		super();
		this.list_value = list_value;
	}

	public List<OBJECT_T_MALL_VALUE> getList_value() {
		return list_value;
	}

	public void setList_value(List<OBJECT_T_MALL_VALUE> list_value) {
		this.list_value = list_value;
	}

	@Override
	public String toString() {
		return "MODEL_OBJECT_T_MALL_VALUE [list_value=" + list_value + "]";
	}
	
	
}
