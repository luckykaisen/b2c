package com.atguigu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.service.SpuService;
import com.atguigu.util.MultipartFileUtil;


@Controller
public class SpuController {
	
	@Autowired
	private SpuService spuService;
	
	@RequestMapping("sale_spu")
	public String get_class1(Map<String , Object> map) {
		return "manager_spu";
	}
	
	
	/**
	 * 保存spu信息
	 * @param product
	 * @param files
	 * @return
	 */
	@RequestMapping("save_spu")
	public String save_spu(T_MALL_PRODUCT product,@RequestParam("files") MultipartFile[] files) {
		
		List<String> saveName = MultipartFileUtil.saveImage(files);
		// 取出第一个图片当作展示图片
		String first_image_name = saveName.get(0);
		product.setShp_tp(first_image_name);
		
		spuService.save_spu(product);
		
		spuService.save_spu_image(saveName , product.getId());
		
		return "redirect:/sale_spu.do";
	}
}
