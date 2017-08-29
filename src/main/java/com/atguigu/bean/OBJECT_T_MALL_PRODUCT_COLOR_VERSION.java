package com.atguigu.bean;

public class OBJECT_T_MALL_PRODUCT_COLOR_VERSION {
	private T_MALL_PRODUCT_COLOR t_mall_product_color;
	
	private T_MALL_PRODUCT_VERSION t_mall_product_version;

	public OBJECT_T_MALL_PRODUCT_COLOR_VERSION() {
		super();
	}

	public OBJECT_T_MALL_PRODUCT_COLOR_VERSION(T_MALL_PRODUCT_COLOR t_mall_product_color,
			T_MALL_PRODUCT_VERSION t_mall_product_version) {
		super();
		this.t_mall_product_color = t_mall_product_color;
		this.t_mall_product_version = t_mall_product_version;
	}

	public T_MALL_PRODUCT_COLOR getT_mall_product_color() {
		return t_mall_product_color;
	}

	public void setT_mall_product_color(T_MALL_PRODUCT_COLOR t_mall_product_color) {
		this.t_mall_product_color = t_mall_product_color;
	}

	public T_MALL_PRODUCT_VERSION getT_mall_product_version() {
		return t_mall_product_version;
	}

	public void setT_mall_product_version(T_MALL_PRODUCT_VERSION t_mall_product_version) {
		this.t_mall_product_version = t_mall_product_version;
	}

	@Override
	public String toString() {
		return "OBJECT_T_MALL_PRODUCT_COLOR_VERSION [t_mall_product_color=" + t_mall_product_color
				+ ", t_mall_product_version=" + t_mall_product_version + "]";
	}
	
	
	
}
