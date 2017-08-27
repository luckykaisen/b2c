package com.atguigu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MODEL_OBJECT_T_MALL_ORDER;
import com.atguigu.bean.OBJECT_T_MALL_PARCEL;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_USER;
import com.atguigu.dao.OrderMapper;
import com.atguigu.service.CartService;
import com.atguigu.service.OrderService;
import com.atguigu.util.OrderUtil;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public void saveOrderInfo(MODEL_OBJECT_T_MALL_ORDER order, T_MALL_USER user, T_MALL_ADDRESS address) {
		
		// 生成订单号
		String orderId = OrderUtil.getOrderId(user.getId());
		
		order.setDzh_id(address.getId());
		order.setDzh_mch(address.getDz_mch());
		order.setJdh(2);
		order.setShhr(address.getShjr());
		order.setYjsdshj(new Date());
		order.setId(orderId);
		
		// 保存订单
		int result = orderMapper.saveOrder(order);
		
		// 接收生成的物流id
		List<OBJECT_T_MALL_PARCEL> list_parcel = order.getList_parcel();
		
		// 存放所有的订单商品集合，统一批量添加数据库
		List<T_MALL_ORDER_INFO> list_order_info = new ArrayList<T_MALL_ORDER_INFO>();
		if(result != 0) {
			for (int i = 0; i < order.getList_parcel().size(); i++) {
				// 生成物流id
				String flowId = OrderUtil.getFlowId(user.getId());
				OBJECT_T_MALL_PARCEL object_T_MALL_PARCEL = list_parcel.get(i);
				object_T_MALL_PARCEL.setId(flowId);
				
				List<T_MALL_ORDER_INFO> list_flow = order.getList_parcel().get(i).getList_flow();
				for (int j = 0; j < list_flow.size(); j++) {
					T_MALL_ORDER_INFO t_MALL_ORDER_INFO = list_flow.get(j);
					t_MALL_ORDER_INFO.setWl_id(flowId);
					t_MALL_ORDER_INFO.setDd_id(orderId);
					// 把所有包裹中的商品订单放入集合中，统一添加到数据库
					list_order_info.add(t_MALL_ORDER_INFO);
				}
			}
			// 保存物流id
			orderMapper.saveFlowId(list_parcel);
			orderMapper.saveOrderInfo(list_order_info);
			
			// 清空购物车中的商品
			cartService.delete_cart_commdity_by_sku_id_and_user_id(list_order_info,user.getId());
		}
	}

}
