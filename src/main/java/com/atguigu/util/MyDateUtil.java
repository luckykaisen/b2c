package com.atguigu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {

	/**
	 * 返回系统当前时间用来加密和解密webservice客户端密码
	 * @return
	 */
	public static String getDate2MD5Password() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		
		String format = simpleDateFormat.format(new Date());
		
		return format;
	}
	
	/**
	 * 时间的相加相减
	 * @return
	 */
	public static Date get_flow_time(int time) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 3);
		return calendar.getTime();
	}
}
