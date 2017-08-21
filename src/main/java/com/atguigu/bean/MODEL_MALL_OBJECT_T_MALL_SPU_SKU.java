package com.atguigu.bean;

import java.util.List;

public class MODEL_MALL_OBJECT_T_MALL_SPU_SKU extends T_MALL_SKU {
	// 商品信息表
	private T_MALL_PRODUCT t_mall_product;
	
	//商品属性 ，与属性值
	/*private List<OBJECT_T_MALL_ATTR> object_t_mall_attr;*/
	private List<T_MALL_ATTR_VALUE_NAME> t_mall_attr_value_name;
	
	// sku信息表
	private T_MALL_SKU t_mall_sku;
	
	// 商品中的图片描述信息表
	private List<T_MALL_PRODUCT_IMAGE> list_image;

	public T_MALL_PRODUCT getT_mall_product() {
		return t_mall_product;
	}

	public void setT_mall_product(T_MALL_PRODUCT t_mall_product) {
		this.t_mall_product = t_mall_product;
	}


	public List<T_MALL_ATTR_VALUE_NAME> getT_mall_attr_value_name() {
		return t_mall_attr_value_name;
	}

	public void setT_mall_attr_value_name(List<T_MALL_ATTR_VALUE_NAME> t_mall_attr_value_name) {
		this.t_mall_attr_value_name = t_mall_attr_value_name;
	}

	public T_MALL_SKU getT_mall_sku() {
		return t_mall_sku;
	}

	public void setT_mall_sku(T_MALL_SKU t_mall_sku) {
		this.t_mall_sku = t_mall_sku;
	}

	public List<T_MALL_PRODUCT_IMAGE> getList_image() {
		return list_image;
	} 

	public void setList_image(List<T_MALL_PRODUCT_IMAGE> list_image) {
		this.list_image = list_image;
	}
}
