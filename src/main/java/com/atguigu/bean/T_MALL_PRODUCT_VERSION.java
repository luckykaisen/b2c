package com.atguigu.bean;

import java.util.Date;

public class T_MALL_PRODUCT_VERSION {
	private int id;
	private int shp_id;
	private String shp_bb;
	private int shfqy;
	private Date chjshj;
	public T_MALL_PRODUCT_VERSION(int id, int shp_id, String shp_bb, int shfqy, Date chjshj) {
		super();
		this.id = id;
		this.shp_id = shp_id;
		this.shp_bb = shp_bb;
		this.shfqy = shfqy;
		this.chjshj = chjshj;
	}
	public T_MALL_PRODUCT_VERSION() {
		super();
	}
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
	public String getShp_bb() {
		return shp_bb;
	}
	public void setShp_bb(String shp_bb) {
		this.shp_bb = shp_bb;
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
	@Override
	public String toString() {
		return "T_MALL_PRODUCT_VERSION [id=" + id + ", shp_id=" + shp_id + ", shp_bb=" + shp_bb + ", shfqy=" + shfqy
				+ ", chjshj=" + chjshj + "]";
	}
}
