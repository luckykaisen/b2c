package com.atguigu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.MODEL_MALL_OBJECT_T_MALL_SPU_SKU;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.dao.SearchMapper;
import com.atguigu.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchMapper serchMapper;

	@Override
	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2(String order,int flbh2 , List<T_MALL_SKU_ATTR_VALUE> list_sku) {
		StringBuffer sb = new StringBuffer();
		sb.append(" and spu.flbh2 = "+ flbh2 + " ");
		
		if(!StringUtils.isBlank(order)) {
			sb.append(" "+order+" ");
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
		return serchMapper.select_sku_by_class_2(paramMap);
	}

	@Override
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(Integer flbh2) {
		return serchMapper.select_attr_by_class_2_id(flbh2);
	}

	
	/**
	 * 点击商品信息，进入商品的详细信息购买网页,通过这两个值查询出对应的某一个商品
	 */
	@Override
	public MODEL_MALL_OBJECT_T_MALL_SPU_SKU query_commodity_info(Map<String, Object> paramMap) {
		return serchMapper.select_commodity_info(paramMap);
	}

	@Override
	public List<T_MALL_SKU> query_commodity_info_by_sibling(Map<String, Object> paramMap) {
		return serchMapper.select_commodity_info_by_sibling(paramMap);
	}
}
