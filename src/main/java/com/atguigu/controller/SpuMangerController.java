package com.atguigu.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.MODEL_OBJECT_T_MALL_VALUE;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.service.SpuInfoService;
import com.atguigu.util.MultipartFileUtil;

@Controller
public class SpuMangerController {
	
	@Autowired
	private SpuInfoService spuInfoService;
	
	@RequestMapping("query_spu")
	public String spu_info(Map<String,Object> paramMap) {
		
		InputStream resourceAsStream = MultipartFileUtil.class.getClassLoader().getResourceAsStream("upLoadPath.properties");
		
		Properties properties = new Properties();
		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String path = (String) properties.get("windows_path");
		
		List<MODEL_OBJECT_T_MALL_VALUE> spu_info = spuInfoService.query_spu_all_info();
		
		paramMap.put("spu_info", spu_info);
		return "html/list";
	}
	
	/**
	 * 更新
	 * @param map
	 * @return
	 */
	@RequestMapping("update")
	public String update(Map<String,Object> map , Integer id) {
		T_MALL_PRODUCT result = spuInfoService.query_spu_info_by_id(id);
		
		map.put("result", result);
		
		return "html/update";
	}
	
}
