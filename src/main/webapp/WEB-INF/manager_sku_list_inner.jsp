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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function spu_attr_add(){
		
	}
	
	// 隐藏显示属性值
	function spu_atrr_click(checked , id){
		if(checked){
			$("#attr_value_" +id).show();
		}else{
			$("#attr_value_" +id).hide();
		}
	
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	属性名 ： ${flmch_2}<br>
	<c:forEach items="${attr}" var="attr_1"  varStatus="index">
		<input id="attr_${attr_1.id}" type="checkbox" name="list_sku[${index.index }].shxm_id" value="${attr_1.id}" onclick="spu_atrr_click(this.checked ,${attr_1.id})"/>${attr_1.shxm_mch}
	</c:forEach>
	<br><hr>
	
	<c:forEach items="${attr}" var="attr_1" varStatus="index">
		<div id="attr_value_${attr_1.id}" style="display:none;">
			<c:forEach items="${attr_1.list_value}" var="attr_2">
				<input type="radio" name="list_sku[${index.index }].shxzh_id" value="${attr_2.id}"/>${attr_2.shxzh}&nbsp;${attr_2.shxzh_mch}
			</c:forEach><br>
		</div>
	</c:forEach>
	<br><hr>
	<input type="hidden" name="shp_id" value="${sp_id}"/>
	商品价格：<input type="text" name="jg"/><br>
	库存名称：<input type="text" name="sku_mch"/><br>
	库存数量：<input type="text" name="kc"/><br>
	库存地址：<input type="text" name="kcdz"/><br>
	
	<br> 
	<input type="submit" value="提交"/>
	
<%-- 	<a href="spu_attr_add.do?flmch_2=${flmch_2}&flbh2=${flbh2}" >添加属性</a> --%>
</body>
</html>