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
	<c:if test="${empty user }">
		${yh_mch }<a id="a_mall_login" href="goto_mall_login.do">登录</a>&nbsp;&nbsp;<a href="goto_mall_regist.do">注册</a>
	</c:if>
	<c:if test="${not empty user }">
		<a>欢迎${user.yh_nch }</a>我的订单<a href="mall_logout.do">注销</a>
	</c:if>
</body>
</html>