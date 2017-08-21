package com.atguigu.bean;

import java.util.ArrayList;
import java.util.List;

public class OBJECT_T_MALL_SKU_ATTR_VALUE {
	List<T_MALL_SKU_ATTR_VALUE> list_sku = new ArrayList<T_MALL_SKU_ATTR_VALUE>();
	
	private String order;

	public List<T_MALL_SKU_ATTR_VALUE> getList_sku() {
		return list_sku;
	}

	public void setList_sku(List<T_MALL_SKU_ATTR_VALUE> list_sku) {
		this.list_sku = list_sku;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
