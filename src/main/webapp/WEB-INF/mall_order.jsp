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
	<form action="submitOrder.do" method="post">
		<c:forEach items="${list_address}" var="address">
			<input type="radio" name="order_address_id" value="${address.id}"/>${address.dz_mch}&nbsp;${address.shjr}&nbsp;${address.lxfsh}&nbsp;
		</c:forEach>
		<br><br><hr>
		<c:forEach items="${order.list_parcel}" var="parcel">
			库存地址：${parcel.mqdd} <hr>
			<c:forEach items="${parcel.list_flow}" var="order_info">
				订单名称 ：  ${order_info.sku_mch}<br>
				图片 ： <img alt="" src="upload/image/${order_info.shp_tp}" width="100px"><br>
				价格 ：  ${order_info.sku_jg}<br>
				数量 ：  ${order_info.sku_shl}<br>
			</c:forEach>
			<br>
		</c:forEach>
		总价格：￥ ${order.zje}
		<br><br>
		<input type="submit" value="提交订单"/>
	</form>
</body>
</html>