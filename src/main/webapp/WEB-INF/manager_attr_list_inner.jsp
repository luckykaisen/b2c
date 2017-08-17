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
	function spu_attr_add(){
		
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	属性名 ： ${flmch_2}<br>
	<c:forEach items="${attr}" var="attr_1">
		${attr_1.shxm_mch } :
		<c:forEach items="${attr_1.list_value}" var="attr_2">
		${attr_2.shxzh}	&nbsp;${attr_2.shxzh_mch}&nbsp;&nbsp;
		</c:forEach>
		<br>
	</c:forEach>
	
	<a href="spu_attr_add.do?flmch_2=${flmch_2}&flbh2=${flbh2}" >添加属性</a>
	
</body>
</html>