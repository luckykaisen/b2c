package com.atguigu.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.MODEL_OBJECT_T_MALL_ORDER;
import com.atguigu.bean.OBJECT_T_MALL_PARCEL;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER;
import com.atguigu.service.AddressService;
import com.atguigu.service.CartService;
import com.atguigu.service.OrderService;

@SessionAttributes("order")
@Controller
public class OrderController {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;
	
	@RequestMapping("pay_success")
	public String pay_success() {
		return "pay_success";
	}
	
	@RequestMapping("goto_mall_pay")
	public String goto_mall_pay() {
		return "mall_pay";
	}
	
	@RequestMapping("pay_fail")
	public String pay_fail() {
		return "pay_fail";
	}
	
	/**
	 * 确认订单
	 * @return
	 */
	@RequestMapping("ConfirmOrder")
	public String ConfirmOrder(HttpSession session , Map<String,Object> map) {
		
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		// 因为用户已经登录所以在session中获取购物车信息
		List<T_MALL_SHOPPINGCAR> list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart");
		
		// 订单
		MODEL_OBJECT_T_MALL_ORDER order = new MODEL_OBJECT_T_MALL_ORDER();
		
		// 创建包裹
		List<OBJECT_T_MALL_PARCEL> list_parcel = new ArrayList<OBJECT_T_MALL_PARCEL>();
					
		// 进行分包
		Set<String> set_parcel = new HashSet<String>();
		
		// 利用set不可重复计算出有几个包裹
		for (int i = 0; i < list_cart.size(); i++) {
			set_parcel.add(list_cart.get(i).getKcdz());
		}
		// 订单的总价格
		BigDecimal order_total_price = new BigDecimal("0");
		
		// 把商品按照仓库地址放入包裹中
		Iterator<String> iterator = set_parcel.iterator();
		while(iterator.hasNext()) {
			OBJECT_T_MALL_PARCEL parcel = new OBJECT_T_MALL_PARCEL();
			// 每个包裹中的商品
			List<T_MALL_ORDER_INFO> flow_order_info = new ArrayList<T_MALL_ORDER_INFO>();
			String kcdzh = iterator.next();
			BigDecimal parcel_total_price = new BigDecimal("0");
			// 包裹的库存地址
			//parcel.setSku_kcdz(kcdzh);
			parcel.setMqdd(kcdzh);
			for(int i = 0; i < list_cart.size(); i++) {
				if(kcdzh.equals(list_cart.get(i).getKcdz()) && list_cart.get(i).getShfxz().equals("1")) {
					T_MALL_ORDER_INFO order_info = new T_MALL_ORDER_INFO();
					
					order_info.setGwch_id(list_cart.get(i).getId());
					order_info.setShp_tp(list_cart.get(i).getShp_tp());
					order_info.setSku_id(list_cart.get(i).getSku_id());
					order_info.setSku_jg(new BigDecimal(list_cart.get(i).getSku_jg()));
					order_info.setSku_kcdz(list_cart.get(i).getKcdz());
					order_info.setSku_mch(list_cart.get(i).getSku_mch());
					order_info.setSku_shl(list_cart.get(i).getTjshl());
					flow_order_info.add(order_info);
					// 计算每个包裹的总价格
					parcel_total_price = parcel_total_price.add(new BigDecimal(list_cart.get(i).getSku_jg()));
					
					// 订单的总价格
					order_total_price = order_total_price.add(new BigDecimal(list_cart.get(i).getSku_jg()));
				}
			}
			parcel.setList_flow(flow_order_info);
			// 设置包裹的总价格
//			//parcel.setSku_jg(parcel_total_price);
//			parcel.set
			list_parcel.add(parcel);
		}
		// 把包裹最后放入订单中
		order.setList_parcel(list_parcel);
		order.setZje(order_total_price);
		order.setYh_id(user.getId());
		order.setJdh(1);
		map.put("order", order);
		
		// 获取用户的收货地址
		List<T_MALL_ADDRESS> addressByUserId = addressService.getAddressByUserId(user.getId()+"");
		map.put("list_address", addressByUserId);
		return "mall_order";
	}
	
	
	/**
	 * 用户提交订单
	 * @param order ：session中已经差好的单子
	 * @param order_address :用户的收货地址
	 * @return
	 */
	@RequestMapping("submitOrder")
	public ModelAndView submitOrder(@ModelAttribute("order")MODEL_OBJECT_T_MALL_ORDER order,Integer order_address_id,HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/goto_mall_pay.do");
	
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		
		// 根据用户收货地址id查询地址详细信息
		T_MALL_ADDRESS address = addressService.getAddressById(order_address_id+"");
		
		// 把订单信息写入数据库
		orderService.saveOrderInfo(order, user, address);
		
		// 清空session订单记录
		//session.setAttribute("order", "");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("yh_id", user.getId());
		List<T_MALL_SHOPPINGCAR> list_cart = cartService.query_cart(paramMap);
		session.setAttribute("list_cart", list_cart);
		return mav;
	}
	
	/**
	 * 付款成功，支付该方法，减库存，更新物流信息，订单信息
	 * @param order
	 * @param session
	 * @return
	 */
	@RequestMapping("do_pay")
	public String do_pay(@ModelAttribute("order")MODEL_OBJECT_T_MALL_ORDER order,HttpSession session) {
		
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		try {
			orderService.update_order_flow_status(order, user);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/pay_fail.do";
		}
		
		
		// 清空session订单记录
		session.setAttribute("order", "");
		return "redirect:/pay_success.do";
	}
	
}
