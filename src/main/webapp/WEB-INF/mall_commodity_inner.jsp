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
	
	<hr>
	<c:forEach items="${mall_sku}" var="sku">
		<a href="query_commodity_info.do?spu_id=${sku.spu.id}&sku_id=${sku.id}">
			<div>
				<img alt="" src="upload/image/${sku.spu.shp_tp }" width="70px"><br>
				商品名称 ： ${sku.spu.shp_mch}<br>
				商品价格 ： ${sku.jg }<br>
				库        存 ： ${sku.kc }<br>
				品        牌 ： ${sku.tm.ppmch }<br>
				<hr>
			</div>	
		</a>
	</c:forEach>

</body>
</html>