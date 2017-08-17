package com.atguigu.bean;

public class OBJECT_T_MALL_VALUE{
	private String shxm_mch;
	private String shxzh_mch;
	private String shxzh;
	public OBJECT_T_MALL_VALUE(String shxm_mch, String shxzh_mch, String shxzh) {
		super();
		this.shxm_mch = shxm_mch;
		this.shxzh_mch = shxzh_mch;
		this.shxzh = shxzh;
	}
	public OBJECT_T_MALL_VALUE() {
		super();
	}
	public String getShxm_mch() {
		return shxm_mch;
	}
	public void setShxm_mch(String shxm_mch) {
		this.shxm_mch = shxm_mch;
	}
	public String getShxzh_mch() {
		return shxzh_mch;
	}
	public void setShxzh_mch(String shxzh_mch) {
		this.shxzh_mch = shxzh_mch;
	}
	public String getShxzh() {
		return shxzh;
	}
	public void setShxzh(String shxzh) {
		this.shxzh = shxzh;
	}
	@Override
	public String toString() {
		return "OBJECT_T_MALL_VALUE [shxm_mch=" + shxm_mch + ", shxzh_mch=" + shxzh_mch + ", shxzh=" + shxzh + "]";
	}
	
	
}
