package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.service.SkuService;
import com.atguigu.service.SpuAttrService;

@Controller
public class SkuController {

	@Autowired
	private SkuService skuService;
	
	@Autowired
	private SpuAttrService spuAttrService;
	/**
	 * 跳转添加仓库页面
	 * @return
	 */
	@RequestMapping("goto_sku_add")
	public String sku_add() {
		return "manager_sku";
	}
	
	/**
	 * 
	 * @param flbh1 :分类编号1
	 * @param flbh2 :分类编号2
	 * @param pp_id ：品牌id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get_spu_sp")
	public List<T_MALL_PRODUCT> get_spu_sp(T_MALL_PRODUCT product){
		List<T_MALL_PRODUCT> list_product = new ArrayList<T_MALL_PRODUCT>();
		
		list_product = skuService.get_spu_sp(product);
		
		return list_product;
	}
	
	@RequestMapping("get_sku_attr")
	public String get_sku_attr(Integer flbh2,Integer sp_id,Map<String,Object> map) {
		
		List<OBJECT_T_MALL_ATTR> select_attr_by_class_2_id = spuAttrService.select_attr_by_class_2_id(flbh2);
		
		map.put("attr", select_attr_by_class_2_id);
		map.put("sp_id", sp_id);
		return "manager_sku_list_inner";
	}
	
	/**
	 * 添加库存信息
	 * @param list_sku
	 * @return
	 */
	@RequestMapping("save_sku_attr_sp")
	public String save_sku_attr_sp(OBJECT_T_MALL_SKU_ATTR_VALUE list_sku , T_MALL_SKU sku ,Integer shp_id) {
		
		skuService.save_sku(sku);
		
		int sku_id = sku.getId();
		
		skuService.save_sku_attr_value(list_sku , sku_id , shp_id);
		
		return "redirect:/goto_sku_add.do";
	}
}
