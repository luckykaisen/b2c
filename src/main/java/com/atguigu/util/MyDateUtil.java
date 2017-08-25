package com.atguigu.util;

import java.text.SimpleDateFormat;
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
}
