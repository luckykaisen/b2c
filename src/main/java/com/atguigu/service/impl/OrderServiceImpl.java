package com.atguigu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MODEL_OBJECT_T_MALL_ORDER;
import com.atguigu.bean.OBJECT_T_MALL_PARCEL;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_FLOW;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_USER;
import com.atguigu.dao.OrderMapper;
import com.atguigu.service.CartService;
import com.atguigu.service.OrderService;
import com.atguigu.util.MyDateUtil;
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
		order.setId(orderId);
		
		// 保存订单
		int result = orderMapper.saveOrder(order);
		
		// 接收生成的物流id
		List<OBJECT_T_MALL_PARCEL> list_parcel = order.getList_parcel();
		
		// 存放所有的订单商品集合，统一批量添加数据库
		List<T_MALL_ORDER_INFO> list_order_info = new ArrayList<T_MALL_ORDER_INFO>();
		if(result != 0) {
			for (int i = 0; i < order.getList_parcel().size(); i++) {
				//T_MALL_FLOW flow = new T_MALL_FLOW();
				OBJECT_T_MALL_PARCEL flow = order.getList_parcel().get(i);
				flow.setDd_id(orderId);
				flow.setMdd(address.getDz_mch());
				flow.setYh_id(user.getId());
				// 保存物流信息
				orderMapper.save_flow_info(flow);
				
				List<T_MALL_ORDER_INFO> list_flow = order.getList_parcel().get(i).getList_flow();
				for (int j = 0; j < list_flow.size(); j++) {
					T_MALL_ORDER_INFO t_MALL_ORDER_INFO = list_flow.get(j);
					t_MALL_ORDER_INFO.setWl_id(flow.getId()+"");
					t_MALL_ORDER_INFO.setDd_id(orderId);
					// 把所有包裹中的商品订单放入集合中，统一添加到数据库
					list_order_info.add(t_MALL_ORDER_INFO);
				}
			}
			orderMapper.saveOrderInfo(list_order_info);
			
			// 清空购物车中的商品
			cartService.delete_cart_commdity_by_sku_id_and_user_id(list_order_info,user.getId());
		}
	}

	@Override
	public void update_order_flow_status(MODEL_OBJECT_T_MALL_ORDER order, T_MALL_USER user) {
		
		// 更新订单的状态jdh=3,预计送达时间
		order.setYjsdshj(MyDateUtil.get_flow_time(3));
		order.setJdh(3);
		orderMapper.update_order_status(order);
		
		// 更新物流的信息
		List<OBJECT_T_MALL_PARCEL> list_parcel = order.getList_parcel();
		for (int i = 0; i < list_parcel.size(); i++) {
			OBJECT_T_MALL_PARCEL parcel = list_parcel.get(i);
			
			parcel.setLxfsh("123456789");
			parcel.setPsfsh("宅急送");
			parcel.setPsmsh("着急");
			parcel.setYwy("007");
			parcel.setPsshj(MyDateUtil.get_flow_time(3));
			
			for (int j = 0; j < parcel.getList_flow().size(); j++) {
				T_MALL_ORDER_INFO order_info = parcel.getList_flow().get(j);
				
				boolean b = if_can_buy(order_info);
				if(b) {
					//修改库存数量
					orderMapper.update_sku_kc_xl(order_info);
				}else {
					throw new RuntimeException();
				}
			}
		}
		// 修改物流的信息
		orderMapper.update_flow_info(list_parcel);
		
		
		
	}
	
	/**
	 * 判断商品数量是否可买
	 * @param order_info
	 * @return
	 */
	public boolean if_can_buy(T_MALL_ORDER_INFO order_info) {
		boolean b = false;
		int result = orderMapper.if_can_buy(order_info);
		if(result >= 0) {
			b = true;
		}
		return b;
	}

}
