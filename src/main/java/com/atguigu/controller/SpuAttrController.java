package com.atguigu.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.atguigu.bean.MODEL_OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_ATTR;
import com.atguigu.bean.T_MALL_VALUE;
import com.atguigu.service.SpuAttrService;

@Controller
public class SpuAttrController {
	
	@Autowired
	private SpuAttrService spuAttrService;
	
	@RequestMapping("spu_attr")
	public String spu_attr() {
		
		return "manager_attr";
	}
	
	@RequestMapping("spu_attr_by_class_2")
	public String spu_attr_by_class_2(@RequestParam("flbh2") Integer flbh2,@RequestParam("flmch_2") String flmch_2 , Map<String,Object> map) {
		
		List<OBJECT_T_MALL_ATTR> attr = spuAttrService.select_attr_by_class_2_id(flbh2);
		
		map.put("attr", attr);
		map.put("flmch_2", flmch_2);
		map.put("flbh2", flbh2);
		return "manager_attr_list_inner";
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping("spu_attr_add")
	public String spu_attr_add(@RequestParam("flbh2") Integer flbh2,@RequestParam("flmch_2") String flmch_2 , Map<String,Object> map) {
		map.put("flmch_2", flmch_2);
		map.put("flbh2", flbh2);
		return "spu_attr_add";
	}
	
	@RequestMapping("save_attr")
	public ModelAndView save_attr(MODEL_OBJECT_T_MALL_ATTR list_attr, T_MALL_ATTR attr) {
		
		ModelAndView modelAndView = new ModelAndView("redirect:/spu_attr_add.do");
		List<OBJECT_T_MALL_ATTR> list_attr2 = list_attr.getList_attr();
		
	
		
		spuAttrService.save_attr_and_value(list_attr2);
		// 获取具体每个属性
		//List<T_MALL_VALUE> list_value = object_T_MALL_ATTR.getList_value();
		
		
		
		return modelAndView;
	}
}
