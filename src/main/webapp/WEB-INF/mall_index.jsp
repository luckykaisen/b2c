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
	$(function(){
		$.getJSON("js/json/class_1.js",function(data){
			$.each(data , function(i , n){
				$("#mall_spu_class_1").append("<li value="+n.id+" style=width:70px onmouseover =show_class2(this.value)>"+n.flmch1+"</li>");
			});
		});
	});
	function show_class2(class_1_id){
		$("#mall_spu_class_2").empty();
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$.each(data , function(i , n){
				$("#mall_spu_class_2").append("<li style=width:70px ><a href=\"get_sku_by_class_2.do?flbh2="+n.id+"\">"+n.flmch2+"</a></li>");
			});
		});
	}
	//${attr.shxm_mch},${attr_value.shxzh },${attr_value.shxm_id }
	
</script>
<title>硅谷商城</title>
</head>
<body>
	<jsp:include page="mall_head_info.jsp"></jsp:include>
	<a href="get_sku_by_class_2.do?flbh2=25">获取商品信息</a>
	<br>
	<div>
		<ul id="mall_spu_class_1"></ul>
	</div>
	
	<!-- 显示已选择过滤属性 -->
	<div id="select_attr">
		<%-- <c:forEach items="${mall_attr}" var="attr">
			<div id="attr_${attr.id}">
				${attr.shxm_mch} :
				<c:forEach items="${attr.list_value}" var="attr_value">
					<a href="javascript:;select_attr('${attr_value.shxzh_mch}','${attr_value.shxzh }',${attr_value.id },${attr.id },${flbh2 })">${attr_value.shxzh }${attr_value.shxzh_mch }</a>
				</c:forEach>
			</div>
		</c:forEach> --%>
	</div>
	
	<div>
		<ul id="mall_spu_class_2"></ul>
	</div>
	
	<div id="show_commodity">
		
	</div>
</body>
</html>