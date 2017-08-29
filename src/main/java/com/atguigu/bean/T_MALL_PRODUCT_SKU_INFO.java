package com.atguigu.bean;

import java.util.Date;

public class T_MALL_PRODUCT_SKU_INFO {
	
	private int sku_id;
	private String shp_msh;
	private String shp_lb; // 商品类别
	private int pp_id;
	private float shpz_zhl;
	private float shp_chc; // 商品尺寸
	private int shp_ys; // 商品颜色
	private int shp_bb; // 商品版本
	private Date chjshj;
	public T_MALL_PRODUCT_SKU_INFO() {
		super();
	}
	public T_MALL_PRODUCT_SKU_INFO(int sku_id, String shp_msh, String shp_lb, int pp_id, float shpz_zhl, float shp_chc,
			int shp_ys, int shp_bb, Date chjshj) {
		super();
		this.sku_id = sku_id;
		this.shp_msh = shp_msh;
		this.shp_lb = shp_lb;
		this.pp_id = pp_id;
		this.shpz_zhl = shpz_zhl;
		this.shp_chc = shp_chc;
		this.shp_ys = shp_ys;
		this.shp_bb = shp_bb;
		this.chjshj = chjshj;
	}
	public int getSku_id() {
		return sku_id;
	}
	public void setSku_id(int sku_id) {
		this.sku_id = sku_id;
	}
	public String getShp_msh() {
		return shp_msh;
	}
	public void setShp_msh(String shp_msh) {
		this.shp_msh = shp_msh;
	}
	public String getShp_lb() {
		return shp_lb;
	}
	public void setShp_lb(String shp_lb) {
		this.shp_lb = shp_lb;
	}
	public int getPp_id() {
		return pp_id;
	}
	public void setPp_id(int pp_id) {
		this.pp_id = pp_id;
	}
	public float getShpz_zhl() {
		return shpz_zhl;
	}
	public void setShpz_zhl(float shpz_zhl) {
		this.shpz_zhl = shpz_zhl;
	}
	public float getShp_chc() {
		return shp_chc;
	}
	public void setShp_chc(float shp_chc) {
		this.shp_chc = shp_chc;
	}
	public int getShp_ys() {
		return shp_ys;
	}
	public void setShp_ys(int shp_ys) {
		this.shp_ys = shp_ys;
	}
	public int getShp_bb() {
		return shp_bb;
	}
	public void setShp_bb(int shp_bb) {
		this.shp_bb = shp_bb;
	}
	public Date getChjshj() {
		return chjshj;
	}
	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}
	@Override
	public String toString() {
		return "T_MALL_PRODUCT_SKU_INFO [sku_id=" + sku_id + ", shp_msh=" + shp_msh + ", shp_lb=" + shp_lb + ", pp_id="
				+ pp_id + ", shpz_zhl=" + shpz_zhl + ", shp_chc=" + shp_chc + ", shp_ys=" + shp_ys + ", shp_bb="
				+ shp_bb + ", chjshj=" + chjshj + "]";
	}
	
	

}
