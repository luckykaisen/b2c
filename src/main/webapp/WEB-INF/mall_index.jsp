<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/css.css">
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function(){
		$.getJSON("js/json/class_1.js",function(data){
			$.each(data , function(i , n){
				$("#mall_spu_class_1").append("<li value="+n.id+" onmouseover =show_class2(this.value)><a>"+n.flmch1+"</a></li>");
			});
		});
	});
	function show_class2(class_1_id){
		$("#mall_spu_class_2").empty();
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$.each(data , function(i , n){
				$("#mall_spu_class_2").append("<a href=\"get_sku_by_class_2.do?flbh2="+n.id+"\">"+n.flmch2+"</a>");
			});
		});
		$("#mall_spu_class_2").show();
	}
	
</script>
<title>硅谷商城</title>
</head>
<body>
	
	<jsp:include page="mall_head_info.jsp"></jsp:include>
	<div class="top_img">
		<img src="images/top_img.jpg" alt="">
	</div>
	
	<div class="search">
		<div class="logo"><img src="./images/logo.jpg" alt=""></div>
		<div class="search_on">
			<div class="se">
				<input type="text" name="search" class="lf">
				<input type="submit" class="clik" value="搜索">
			</div>
			<div class="se">
				<a href="">取暖神奇</a>
				<a href="">1元秒杀</a>
				<a href="">吹风机</a>
				<a href="">玉兰油</a>
			</div>
			
		</div>
		
		<jsp:include page="mall_mini_cart_inner.jsp"></jsp:include>
	</div>
	
	<div class="menu">
		<div class="nav">
			<div class="navs">
				<div class="left_nav">
					全部商品分类
					<div class="nav_mini">
						<ul >
							<li id="mall_spu_class_1">
								<div id="mall_spu_class_2" class="two_nav">
								</div>
							</li>
						</ul>
					</div>
				</div>
				<ul>
					<li><a href="">服装城</a></li>
					<li><a href="">美妆馆</a></li>
					<li><a href="">超市</a></li>
					<li><a href="">全球购</a></li>
					<li><a href="">闪购</a></li>
					<li><a href="">团购</a></li>
					<li><a href="">拍卖</a></li>
					<li><a href="">金融</a></li>
					<li><a href="">智能</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="banner">
		<div class="ban">
			<img src="./images/banner.jpg" width="980" height="380" alt="">
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	<!-- 显示已选择过滤属性 -->
	<!-- 
	<div id="select_attr">
	</div>
	 -->
	
<!-- 	<div id="show_commodity">
		
	</div> -->
</body>
</html>