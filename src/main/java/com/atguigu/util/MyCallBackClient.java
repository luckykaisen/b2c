package com.atguigu.util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class MyCallBackClient implements CallbackHandler{

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback ws = (WSPasswordCallback) callbacks[0];
		
		ws.setIdentifier("wss4j");
		
		String pw = MD5Util.md5("123456"+MyDateUtil.getDate2MD5Password());
		
		
		ws.setPassword(pw);
	}

}
