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
		function show_commodity(flbh2){
			$.post("get_sku_by_class_2.do",{"flbh2":flbh2},function(data){
				$("#show_commodity").html(data);
			});
		}
		
		
		function search_order_change(neworder){
			var oldorder = $("#search_order_by_attr").val();
			 if(neworder == oldorder){
				 oldorder +=" desc ";
			 } else {
				 oldorder = neworder;
			 }
			 $("#search_order_by_attr").val(oldorder);
			 search_get_sku_by_attr("${flbh2}");
		}

		// 选择属性查询时调用
		function search_get_sku_by_attr(flbh2){
			// 获取文本框的数组
			var attr_json_array = $("#select_attr input[name='search_attr_id_arry']");
			var list_av_string = "";
			$.each(attr_json_array , function(i , json){
				var attr_json = $.parseJSON(json.value);
				list_av_string += "list_sku["+i+"].shxm_id=" + attr_json.shxm_id + "&list_sku["+i+"].shxzh_id=" + attr_json.shxzh_id + "&";
			});
			list_av_string += "flbh2="+flbh2 ;
			
			var order = $("#search_order_by_attr").val();
			if($.trim(order) != ""){
				list_av_string += "&order="+order;
			}
			$.ajax({
				url : "search_commodity_by_attr.do",
				data : list_av_string,
				success : function(data){
					$("#show_commodity").html(data);
				}
			});
		}


			function select_attr(shxzh_mch,shxzh,shxzh_id,shxm_id,flbh2){
				//alert(shxzh_mch+"===" + shxzh+"===" + shxm_id + "===" + shxzh_id);
				var info = shxzh+""+shxzh_mch;
				$("#select_attr").append("<div id=\"select_attr_"+shxm_id+"\"><a href=\"javascript:over_attr("+shxm_id+","+flbh2+");\">"+info+"</a><input type='hidden' name='search_attr_id_arry' value='{\"shxm_id\":"+shxm_id+",\"shxzh_id\":"+shxzh_id+"}'/></div>");
				
				$("#attr_"+shxm_id).hide();
				
				search_get_sku_by_attr(flbh2);
			}


			function over_attr(shxm_id , flbh2 ){
				$("#attr_"+shxm_id).show();
				$("#select_attr_"+shxm_id).remove();
				search_get_sku_by_attr(flbh2);
			}
</script>
<title>硅谷商城</title>
</head>
<body>
	<input id="search_order_by_attr" type="hidden" name="search_order_by_attr" value=""/>
	<div id="select_attr">
	</div>
		<c:forEach items="${mall_attr}" var="attr">
			<div id="attr_${attr.id}">
				${attr.shxm_mch} :
				<c:forEach items="${attr.list_value}" var="attr_value">
					<a href="javascript:;select_attr('${attr_value.shxzh_mch}','${attr_value.shxzh }',${attr_value.id },${attr.id },${flbh2 })">${attr_value.shxzh }${attr_value.shxzh_mch }</a>
				</c:forEach>
			</div>
		</c:forEach>
		<br>
		<hr>
		<a href="javascript:search_order_change(' order by jg ');">商品价格</a> 
		<a href="javascript:search_order_change(' order by sku_xl ');">商品销量</a>  
		<a href="javascript:search_order_change(' order by sku.chjshj ');">上架时间</a>  
		<a href="javascript:search_order_change(' order by plsh ');">评论数</a>      
		     
		  
		<hr>
		<div id="show_commodity">
			<jsp:include page="mall_commodity_inner.jsp"></jsp:include>
		</div>
</body>
</html>