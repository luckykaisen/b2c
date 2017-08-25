package com.atguigu.test;

import com.atguigu.util.SMSCode;

import redis.clients.jedis.Jedis;

public class TestJedis {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.233.128",6379);
		
		String str = jedis.get("18210003887_time");
		
		
		if(str.equals("3")) {
			System.out.println("单日发送达到上线");
			return;
		}else {
			String smsCode = SMSCode.getSMSCode(4);
			jedis.set("18210003887", smsCode);
			jedis.expire("18210003887", 60);
			jedis.set("18210003887_time",(Integer.parseInt(str)+1)+"");
		}
	}

}
