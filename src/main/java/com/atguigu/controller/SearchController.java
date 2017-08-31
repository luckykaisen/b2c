package com.atguigu.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.atguigu.bean.MODEL_MALL_OBJECT_T_MALL_SPU_SKU;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.OBJECT_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.service.SearchService;
import com.atguigu.util.JedisPoolUtils;

import redis.clients.jedis.Jedis;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService serchService;
	
	@RequestMapping("get_sku_by_class_2")
	public String get_sku_by_class_2(Integer flbh2 ,OBJECT_T_MALL_SKU_ATTR_VALUE list_attr, Map<String,Object> map){
		
		Jedis jedis = JedisPoolUtils.getJedis();
		
		List<T_MALL_SKU_ATTR_VALUE> list_sku = list_attr.getList_sku();
		  
		// 获取分类编号2的属性
		List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id = serchService.get_attr_by_class_2_id(flbh2);
		
		List<OBJECT_T_MALL_SKU> mall_sku = new ArrayList<OBJECT_T_MALL_SKU>();
		if(jedis.exists("class_2_" + flbh2)) {
			// 查询redis
			Set<String> zrange = jedis.zrange("class_2_" + flbh2, 0, -1);
			if(zrange != null && zrange.size() > 0) {
				Iterator<String> iterator = zrange.iterator();
				while(iterator.hasNext()) {
					String next = iterator.next();
					// 解码
					try {
						String decode = URLDecoder.decode(next, "utf-8");
						OBJECT_T_MALL_SKU parseObject = JSON.parseObject(decode, OBJECT_T_MALL_SKU.class);
						mall_sku.add(parseObject);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}else {
			// 查询数据库
			// 根据二级分类编号获取sku商品
			mall_sku = serchService.get_sku_by_class_2("",flbh2,list_sku);
		}		
		map.put("mall_attr", get_attr_by_class_2_id);
		map.put("flbh2",flbh2);
		map.put("mall_sku",mall_sku);
	
		return "mall_search";
	}
	
	/**
	 * 根据属性过滤商品
	 * @param flbh2
	 * @param list_attr
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("search_commodity_by_attr")
	public String search_commodity_by_attr(Integer flbh2 ,OBJECT_T_MALL_SKU_ATTR_VALUE list_attr, Map<String,Object> map) {
		
		Jedis jedis = JedisPoolUtils.getJedis();
		
		List<T_MALL_SKU_ATTR_VALUE> list_sku = list_attr.getList_sku();
		
		List<OBJECT_T_MALL_SKU> mall_sku = new ArrayList<OBJECT_T_MALL_SKU>();
		
		String[] keys = new String[list_sku.size()];
		for (int i = 0; i < list_sku.size(); i++) {
			
			T_MALL_SKU_ATTR_VALUE t_MALL_SKU_ATTR_VALUE = list_sku.get(i);
			keys[i] = "av_" + flbh2 + "_" + t_MALL_SKU_ATTR_VALUE.getShxm_id() + "_" + t_MALL_SKU_ATTR_VALUE.getShxzh_id();
		}
		String out = "out";
		for (int i = 0; i < keys.length; i++) {
			out = out + "_" + keys[i];
		}
		if(!jedis.exists(out)) {
			jedis.zinterstore(out, keys);
		}
		
		Set<String> zrange = jedis.zrange(out, 0 , -1);
		if(zrange != null && zrange.size() > 0) {
			
			Iterator<String> iterator = zrange.iterator();
			while(iterator.hasNext()) {
				String next = iterator.next();
				// 解码
				try {
					String decode = URLDecoder.decode(next, "utf-8");
					OBJECT_T_MALL_SKU parseObject = JSON.parseObject(decode, OBJECT_T_MALL_SKU.class);
					mall_sku.add(parseObject);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}else {
			// mysql数据库查询
			mall_sku = serchService.get_sku_by_class_2(list_attr.getOrder(),flbh2,list_sku);
		}
		
		
		
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
