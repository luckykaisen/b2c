package com.atguigu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class OrderUtil {
	/**
	 * 根据用户的id获取订单编号
	 * @param userId:传入用户的id
	 * @return
	 */
	public static String getOrderId(int userId) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmSS");
		String format = simpleDateFormat.format(new Date());
		String orderId = format + userId; 
		System.err.println(orderId);
		return orderId;
	}
	
	/**
	 * 获取物流id
	 * @param userId
	 * @return
	 */
	public static String getFlowId(int userId) {
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int nextInt = r.nextInt(9);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmSS");
		
		String format = simpleDateFormat.format(new Date());
		
		sb.append(format).append(nextInt).append(userId);
		return sb.toString();
	}
	
	
}
