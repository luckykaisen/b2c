package com.atguigu.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileUtil {

	public static List<String> saveImage(MultipartFile[] files) {
		List<String> list = new ArrayList<String>();
		
		try {
		InputStream resourceAsStream = MultipartFileUtil.class.getClassLoader().getResourceAsStream("upLoadPath.properties");
	
		Properties properties = new Properties();
		properties.load(resourceAsStream);
		String path = (String) properties.get("windows_path");
		
		
		for (int i = 0; i < files.length; i++) {
			// 获取文件后缀名
			String originalFilename = files[i].getOriginalFilename();
			if(originalFilename != null && originalFilename.length() > 0) {
				originalFilename = originalFilename.substring(originalFilename.lastIndexOf("."));
				
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
				long currentTimeMillis = System.currentTimeMillis();
				String fileName = currentTimeMillis + uuid + originalFilename;
				list.add(fileName);
				files[i].transferTo(new File(path+"/"+fileName));
			}
				
		}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
		return list;
	}
	
}
