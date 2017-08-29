package com.atguigu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.MODEL_OBJECT_T_MALL_ORDER;
import com.atguigu.bean.OBJECT_T_MALL_PARCEL;
import com.atguigu.bean.T_MALL_FLOW;
import com.atguigu.bean.T_MALL_ORDER_INFO;

public interface OrderMapper {

	int saveOrder(MODEL_OBJECT_T_MALL_ORDER order);

	void saveFlowId(@Param("list_parcel")List<OBJECT_T_MALL_PARCEL> list_parcel);

	void saveOrderInfo(@Param("list_order_info")List<T_MALL_ORDER_INFO> list_order_info);

	void save_flow_info(T_MALL_FLOW flow);

	int if_can_buy(T_MALL_ORDER_INFO order_info);

	void update_order_status(MODEL_OBJECT_T_MALL_ORDER order);

	void update_sku_kc_xl(T_MALL_ORDER_INFO order_info);

	void update_flow_info(@Param("list_parcel")List<OBJECT_T_MALL_PARCEL> list_parcel);

	
}
