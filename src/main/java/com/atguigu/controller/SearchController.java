package com.atguigu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.MODEL_MALL_OBJECT_T_MALL_SPU_SKU;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.OBJECT_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.service.SerchService;

@Controller
public class SearchController {
	
	@Autowired
	private SerchService serchService;
	
	@RequestMapping("get_sku_by_class_2")
	public String get_sku_by_class_2(Integer flbh2 ,OBJECT_T_MALL_SKU_ATTR_VALUE list_attr, Map<String,Object> map) {
		
		List<T_MALL_SKU_ATTR_VALUE> list_sku = list_attr.getList_sku();
		
		StringBuffer sb = new StringBuffer();
		sb.append(" and spu.flbh2 = "+ flbh2 + " ");
		
		if ( list_sku != null && list_sku.size() > 0) {
			sb.append(" and sku.id in ( select sku_0.sku_id from ");
			
			for (int i = 0; i < list_sku.size(); i++) {
				sb.append(" ( select sku_id from t_mall_sku_attr_value where shxm_id = "+list_sku.get(i).getShxm_id()+" and  shxzh_id = "+list_sku.get(i).getShxzh_id()+" )sku_"+i+" ");
				
				if ( i != list_sku.size() - 1 ) {
					sb.append(" , ");
				}
			}
			
			if( list_sku.size() > 1 ) {
				sb.append(" where ");
			}
			
			//sku_0.sku_id = sku_1.sku_id  and sku_1.sku_id = sku_2.sku_id
			for (int i = 0; i < list_sku.size() - 1; i++) {
				if( list_sku.size() > 1 ) {
					sb.append("sku_"+i+".sku_id = sku_"+(i+1)+".sku_id");
					if ( i < list_sku.size() - 2 ) {
						sb.append(" and ");
					}
				}
			}
			sb.append(" ) ");
		}
		
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
			
		paramMap.put("sql", sb.toString());
		paramMap.put("flbh2", flbh2);
		// 获取分类编号2的属性
		List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id = serchService.get_attr_by_class_2_id(flbh2);
		
		
		
		List<OBJECT_T_MALL_SKU> mall_sku = serchService.get_sku_by_class_2(paramMap);
		
		map.put("mall_attr", get_attr_by_class_2_id);
		map.put("flbh2",flbh2);
		map.put("mall_sku",mall_sku);
	
		
		
		return "mall_search";
	}
	
	
	@RequestMapping("search_commodity_by_attr")
	public String search_commodity_by_attr(Integer flbh2 ,OBJECT_T_MALL_SKU_ATTR_VALUE list_attr, Map<String,Object> map) {
		
		List<T_MALL_SKU_ATTR_VALUE> list_sku = list_attr.getList_sku();
		
		StringBuffer sb = new StringBuffer();
		sb.append(" and spu.flbh2 = "+ flbh2 + " ");
		if(!StringUtils.isBlank(list_attr.getOrder())) {
			sb.append(" "+list_attr.getOrder()+" ");
		}
		
		if ( list_sku != null && list_sku.size() > 0) {
			sb.append(" and sku.id in ( select sku_0.sku_id from ");
			
			for (int i = 0; i < list_sku.size(); i++) {
				sb.append(" ( select sku_id from t_mall_sku_attr_value where shxm_id = "+list_sku.get(i).getShxm_id()+" and  shxzh_id = "+list_sku.get(i).getShxzh_id()+" )sku_"+i+" ");
				
				if ( i != list_sku.size() - 1 ) {
					sb.append(" , ");
				}
			}
			
			if( list_sku.size() > 1 ) {
				sb.append(" where ");
			}
			
			//sku_0.sku_id = sku_1.sku_id  and sku_1.sku_id = sku_2.sku_id
			for (int i = 0; i < list_sku.size() - 1; i++) {
				if( list_sku.size() > 1 ) {
					sb.append("sku_"+i+".sku_id = sku_"+(i+1)+".sku_id");
					if ( i < list_sku.size() - 2 ) {
						sb.append(" and ");
					}
				}
			}
			sb.append(" ) ");
			
			
		}
		
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
			
		paramMap.put("sql", sb.toString());
		paramMap.put("flbh2", flbh2);
		// 获取分类编号2的属性
		
		
		
		List<OBJECT_T_MALL_SKU> mall_sku = serchService.get_sku_by_class_2(paramMap);
		
		map.put("mall_sku",mall_sku);
	
		
		
		return "mall_commodity_inner";
	}
	
	/**
	 * 点击商品信息，进入商品的详细信息购买网页
	 * @param spu_id ： 传入的商品id
	 * @param sku_id ： 传入的sku id
	 * @return
	 */
	@RequestMapping("query_commodity_info")
	public String query_commodity_info(Integer spu_id , Integer sku_id , Map<String,Object> map) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("spu_id", spu_id);
		paramMap.put("sku_id", sku_id);
		
		// 查询商品的详细的信息，包括图片（不包括其他兄弟商品）
		MODEL_MALL_OBJECT_T_MALL_SPU_SKU spu_sku_attr= serchService.query_commodity_info(paramMap);
		
		// 查询商品的兄弟商品
		List<T_MALL_SKU> sibling_sku = serchService.query_commodity_info_by_sibling(paramMap);
		
		map.put("sibling_sku", sibling_sku);
		map.put("spu_sku_attr", spu_sku_attr);
		return "mall_commodity_info";
	}
}
