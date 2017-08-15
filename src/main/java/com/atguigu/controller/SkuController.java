package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SkuController {

	/**
	 * 跳转添加仓库页面
	 * @return
	 */
	@RequestMapping("goto_sku_add")
	public String sku_add() {
		return "manager_sku";
	}
}
