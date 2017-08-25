package com.atguigu.util;

import java.util.Random;

public class SMSCode {

	/**
	 * 获取短信发送验证码
	 * @param time
	 * @return
	 */
	public static String getSMSCode(int time) {
		StringBuffer sb = new StringBuffer();
		
		Random r = new Random();
		for (int i = 0; i < time; i++) {
			sb.append(r.nextInt(9));
		}
		return sb.toString();
	}
}
