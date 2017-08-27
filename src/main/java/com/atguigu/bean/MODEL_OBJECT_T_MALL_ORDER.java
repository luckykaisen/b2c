package com.atguigu.bean;

import java.util.List;

public class MODEL_OBJECT_T_MALL_ORDER extends T_MALL_ORDER{

	List<OBJECT_T_MALL_PARCEL> list_parcel;

	public List<OBJECT_T_MALL_PARCEL> getList_parcel() {
		return list_parcel;
	}

	public void setList_parcel(List<OBJECT_T_MALL_PARCEL> list_parcel) {
		this.list_parcel = list_parcel;
	}
	
	
}
