package com.atguigu.util;

import java.util.HashMap;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.FactoryBean;

public class InterfaceFactoryBean<T> implements FactoryBean<T>{

	private String url;
	
	private Class<T> t;
	
	@Override
	public T getObject() throws Exception {
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		
		factoryBean.setAddress(url);
		
		factoryBean.setServiceClass(t);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		map.put(WSHandlerConstants.USER, "user");
		map.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
		map.put(WSHandlerConstants.PW_CALLBACK_CLASS, MyCallBackClient.class.getName());
		
		WSS4JOutInterceptor wss4jOutInterceptor = new WSS4JOutInterceptor(map);
		
		factoryBean.getOutInterceptors().add(wss4jOutInterceptor);
		
		T create = (T) factoryBean.create();
		
		return create;
	}

	@Override
	public Class<?> getObjectType() {
		return t;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Class<T> getT() {
		return t;
	}

	public void setT(Class<T> t) {
		this.t = t;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
