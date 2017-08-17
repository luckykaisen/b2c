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

	<%-- <table align="center"  border="1">
		<c:forEach items="${spu_info}" var="spu">
			<tr><td><img src="upload/image/${spu.shp_tp}" width="50px"></td><td>${spu.shp_mch} 
			 </td><td>${spu.shp_msh }</td><td><a href="update.do?id=${spu.id}">更新</a></td><td>删除</td></tr>
			<c:forEach items="${spu.list_value}" var="attr">
					${attr.shxm_mch } ${attr.shxzh_mch }${attr.shxzh}
			</c:forEach>
			
		</c:forEach>	
			
			
	</table> --%>
	
	<c:forEach items="${spu_info}" var="spu">
			${spu.shp_mch}
			<c:forEach items="${spu.list_value}" var="attr">
					${attr.shxm_mch } ${attr.shxzh_mch }${attr.shxzh}
			</c:forEach>
			<br><br>
		</c:forEach>	
</body>
</html>


