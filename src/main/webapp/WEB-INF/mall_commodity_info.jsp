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
	
</script>
<title>硅谷商城</title>
</head>
<body>
	<img alt="" src="upload/image/${spu_sku_attr.t_mall_product.shp_tp}" width="200px"><img/>
	<hr>
	商品名称 : ${spu_sku_attr.t_mall_product.shp_mch}<br>
	商品属性 : 
	<c:forEach items="${spu_sku_attr.t_mall_attr_value_name}" var="attr">
		${attr.attr_val}&nbsp;&nbsp;
	</c:forEach>
	<br>
	商品描述 : ${spu_sku_attr.t_mall_product.shp_msh}<br>
	<hr>
	选择商品 : 
	<c:forEach items="${sibling_sku}" var="sku">
		<a href="query_commodity_info.do?spu_id=${sku.shp_id}&sku_id=${sku.id}">${sku.sku_mch }</a>&nbsp;&nbsp;
	</c:forEach>
	<hr>
	
	<form action="add_cart.do" method="post">
		<input type="hidden" name="sku_mch" value="${spu_sku_attr.sku_mch}"/>
		<input type="hidden" name="sku_jg" value="${spu_sku_attr.jg}"/>
		<input type="hidden" name="tjshl" value="1"/>
<%-- 		<input type="hidden" name="yh_id" value="${user.id }"/> --%>
		<input type="hidden" name="shp_id" value="${spu_sku_attr.t_mall_product.id}"/>
		<input type="hidden" name="sku_id" value="${spu_sku_attr.id}"/>
		<input type="hidden" name="shp_tp" value="${spu_sku_attr.t_mall_product.shp_tp}"/>
		<input type="hidden" name="shfxz" value="0"/>
		<input type=hidden name="kcdz" value="${spu_sku_attr.kcdz}"/>
		<input type="submit" value="加入购物车"/>
	</form>

	<hr>
	<c:forEach items="${spu_sku_attr.list_image}" var="image">
		<img alt="" src="upload/image/${image.url}" width="500px"><br>
	</c:forEach>
</body>
</html>