package com.atguigu.bean;

import java.util.Date;

public class T_MALL_PRODUCT_COLOR {
	private int id;
	private int shp_id;
	private String shp_ys;
	private int shfqy;
	private Date chjshj;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShp_id() {
		return shp_id;
	}
	public void setShp_id(int shp_id) {
		this.shp_id = shp_id;
	}
	public String getShp_ys() {
		return shp_ys;
	}
	public void setShp_ys(String shp_ys) {
		this.shp_ys = shp_ys;
	}
	public int getShfqy() {
		return shfqy;
	}
	public void setShfqy(int shfqy) {
		this.shfqy = shfqy;
	}
	public Date getChjshj() {
		return chjshj;
	}
	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}
	public T_MALL_PRODUCT_COLOR(int id, int shp_id, String shp_ys, int shfqy, Date chjshj) {
		super();
		this.id = id;
		this.shp_id = shp_id;
		this.shp_ys = shp_ys;
		this.shfqy = shfqy;
		this.chjshj = chjshj;
	}
	public T_MALL_PRODUCT_COLOR() {
		super();
	}
	
	

}
