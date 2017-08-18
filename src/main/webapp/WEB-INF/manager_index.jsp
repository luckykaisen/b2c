<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath %>">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
 
	$(function(){
		var url = "${url}";
		var title = "${title}";
		if(url != '' && title != ''){
			add_tabs(url , title);
		}
		
	}); 
	function add_tabs(url , title){
		var flag = $('#tt').tabs('exists',title);
		if(flag){
			$('#tt').tabs('select',title);
		}else{
			$.post(url ,{"url" :url,"title":title}, function(data){
				$('#tt').tabs('add',{    
				    title:title,    
				    content:data,    
				    closable:true,
				});  
			});
		}
	}
	 
</script>
<title>硅谷商城</title>
</head>
<body  class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">
		<ul class="easyui-tree">
			<li>
				<span>商品管理</span>
				<ul>
					<li><a href="javascript:;add_tabs('sale_spu.do','发布spu')">发布spu</a></li>
					<li><a href="javascript:;add_tabs('spu_attr.do' ,'添加属性')">添加属性</a></li>
					<li><a href="javascript:;add_tabs('goto_sku_add.do','添加库存')">添加库存</a></li>
				</ul>
			</li>
			<li>
				<span>商品查询</span>
				<ul>
					<li><a href="query_spu.do">查询商品</a></li>
				</ul>
			</li>
		</ul>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
		<div id="tt" class="easyui-tabs"> 
		 	
		</div>  
		
	</div>
</body>
</html>