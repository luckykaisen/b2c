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
	
		<c:if test="${not empty list_cart}">
			<c:forEach items="${list_cart}" var="cart">
				<img alt="" src="upload/image/${cart.shp_tp}" width="70px">
				￥${cart.sku_jg}x${cart.tjshl} <a href="javascript:;">删除</a>
				<br>
			</c:forEach>
			共 ${total_commodity}件商品 ，共计￥${total_price}元   <a href="javascript:;">去购物车</a>
		</c:if>
		 
		<c:if test="${empty list_cart}">
			购物车中还没有商品，赶紧选购吧！
		</c:if>
	
</body>
</html>