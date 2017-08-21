package com.atguigu.util;

import java.math.BigDecimal;

public class BigDecimalUtil {
	
	/**
	 * 两个bigdecimal 相乘
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static BigDecimal multiply(Object num1 , Object num2) {
		
		BigDecimal bigDecimal1 = new BigDecimal(num1 + "");
		BigDecimal bigDecimal2 = new BigDecimal(num2 + "");
		return bigDecimal1.multiply(bigDecimal2);
	}
}
