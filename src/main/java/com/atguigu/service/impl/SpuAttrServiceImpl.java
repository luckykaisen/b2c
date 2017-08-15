package com.atguigu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_VALUE;
import com.atguigu.dao.SpuAttrDao;
import com.atguigu.service.SpuAttrService;

@Service
public class SpuAttrServiceImpl implements SpuAttrService{

	@Autowired
	private SpuAttrDao supAttrDao;
	
	/**
	 * 根据二级分类名查询属性
	 */
	@Override
	public List<OBJECT_T_MALL_ATTR> select_attr_by_class_2_id(Integer id) {
		return supAttrDao.select_attr_by_class_2_id(id);
	}

	@Override
	public void save_attr_and_value(List<OBJECT_T_MALL_ATTR> list_attr2) {
		
		for (int i = 0; i < list_attr2.size(); i++) {
			OBJECT_T_MALL_ATTR object_T_MALL_ATTR = list_attr2.get(i);
			supAttrDao.save_attr(object_T_MALL_ATTR);
			
			int id = object_T_MALL_ATTR.getId();
			List<T_MALL_VALUE> list_value = object_T_MALL_ATTR.getList_value();
			
			for (int j = 0; j < list_value.size(); j++) {
				T_MALL_VALUE t_MALL_VALUE = list_value.get(j);
				t_MALL_VALUE.setShxm_id(id);
				supAttrDao.save_attr_value(t_MALL_VALUE);
			}
		}

		
		
		//supAttrDao.save_attr_value(list_attr2);
		
		
	}

	
}
