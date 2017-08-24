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

	// 鼠标放到我的购物车按钮上发送请求
	function show_mini_cart(){
		
		$.post("show_mini_cart.do" ,function(data){
			$("#mini_cart_list").html(data);
		});
		
		$("#mini_cart_list").show();
	}
	
	function show_mini_cart_list(){
		$("#mini_cart_list").show();
	}
	
	
	function hide_mini_cart(){
		$("#mini_cart_list").hide();
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	
	
	
	<div class="card">
		<a href="goto_cart_list.do" onmousemove="show_mini_cart()" >购物车<div class="num">0</div></a>
			
		<div id="mini_cart_list" onmousemove="show_mini_cart_list()" onmouseout="hide_mini_cart()">
		</div>
			
		</div>
</body>
</html>