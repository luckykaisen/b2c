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
$(function() {
	// 得到文件的json格式的数据
	$.getJSON("js/json/class_1.js", function(data) {
		// 遍历到下拉菜单
		$.each(data, function(i, n) {
			$("#class_1").append("<option value="+n.id+">" + n.flmch1 + "</option>");
		});

	});
});
	function get_class2_by_class1_id(class_1_id) {
		$("#pp").empty();
		$("#class_2").empty();
		$.getJSON("js/json/class_2_" + class_1_id + ".js", function(data) {
			// 遍历到下拉菜单
			$.each(data, function(i, n) {
				$("#class_2").append("<option value="+n.id+">" + n.flmch2+"</option>");
			});
		});
		get_tm_by_class2_id(class_1_id);
	}

	function get_tm_by_class2_id(class_1_id) {
		$.getJSON("js/json/tm_class_1_" + class_1_id + ".js", function(data) {
			$("#pp").empty();
			// 遍历到下拉菜单
			$.each(data, function(i, n) {
				$("#pp").append(
						"<option value="+n.id+">" + n.ppmch + "</option>");
			});

		});
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	<select name="flbh1" id="class_1" onchange="get_class2_by_class1_id(this.value)"></select> 
	<select name="flbh2" id="class_2"></select> 
	<select name="pp_id" id="pp"></select>
	<select name="pp_id" id="sp"></select>
</body>
</html>