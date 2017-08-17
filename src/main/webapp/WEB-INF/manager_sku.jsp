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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
$(function() {
	// 得到文件的json格式的数据
	$.getJSON("js/json/class_1.js", function(data) {
		// 遍历到下拉菜单
		$.each(data, function(i, n) {
			$("#sku_class_1").append("<option value="+n.id+">" + n.flmch1 + "</option>");
		});

	});
});
	function get_class2_by_class1_id(class_1_id) {
		$("#sku_pp").empty();
		$("#sku_class_2").empty();
		$.getJSON("js/json/class_2_" + class_1_id + ".js", function(data) {
			// 遍历到下拉菜单
			$.each(data, function(i, n) {
				$("#sku_class_2").append("<option value="+n.id+">" + n.flmch2+"</option>");
			});
		});
		get_tm_by_class2_id(class_1_id);
	}

	function get_tm_by_class2_id(class_1_id) {
		$.getJSON("js/json/tm_class_1_" + class_1_id + ".js", function(data) {
			$("#sku_pp").empty();
			// 遍历到下拉菜单
			$.each(data, function(i, n) {
				$("#sku_pp").append("<option value="+n.id+">" + n.ppmch + "</option>");
			});

		});
	}
	function spu_sp_change(pp_id){
		var flbh1 = $("#sku_class_1").val();
		var flbh2 = $("#sku_class_2").val();
		
		$.post("get_spu_sp.do" ,{"flbh1" : flbh1 , "flbh2" :flbh2 ,"pp_id" : pp_id} , function (data){
			$("#sku_sp").empty();
			$.each(data , function(i , n ){
				$("#sku_sp").append("<option value="+n.id+">" + n.shp_mch + "</option>");
			});
			 
		});
	}
	
	// 加载局部页面仓库属性
	function get_spu_attr(sp_id){
		var flbh2 = $("#sku_class_2").val();
		$.post("get_sku_attr.do",{"flbh2":flbh2 , "sp_id" : sp_id},function(data){
			$("#sku_attr").html(data);
		});
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	<form action="save_sku_attr_sp.do" method="post">
		<select name="flbh1" id="sku_class_1" onchange="get_class2_by_class1_id(this.value)"></select> 
		<select name="flbh2" id="sku_class_2"></select> 
		<select name="pp_id" id="sku_pp" onchange="spu_sp_change(this.value)"></select>
		<select name="sp_id" id="sku_sp" onchange="get_spu_attr(this.value)"></select>
		<br>
		<idv id="sku_attr"></div>
	</form>
</body>
</html>