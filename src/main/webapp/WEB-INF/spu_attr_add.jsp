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
	var index = 1;
	function spu_attr_add_value(){
		var a = '<table id="table_'+index+'" border="1">';
		var b = '<input type="hidden" name="list_attr['+index+'].flbh2" value="${flbh2}"/>';
		var c = '<tr><td>属性名 : <input type="text" name="list_attr['+index+'].shxm_mch"/></td><td></td><td><a href="javascript:;" onclick="spu_attr_add_tr('+index+')">添加</a></td><td></td></tr>';
		var d = '<tr><td>属性值 : <input type="text" name="list_attr['+index+'].list_value[0].shxzh"/></td><td>单位 : <input type="text" name="list_attr['+index+'].list_value[0].shxzh_mch"/></td><td></td><td>删除</td></tr>';
		var e = '<tr><td>属性值 : <input type="text" name="list_attr['+index+'].list_value[1].shxzh"/></td><td>单位 : <input type="text" name="list_attr['+index+'].list_value[1].shxzh_mch"/></td><td></td><td>删除</td></tr>';
		var f = '</table><br>';
		$("#attr_values").append(a+b+c+d+e+f);
		index++;
	}
	
	function spu_attr_add_tr(table_index){
		var tr_length = $("#table_"+table_index+" tr").length;
		var tr = '<tr><td>属性值 : <input type="text" name="list_attr['+table_index+'].list_value['+(tr_length - 1)+'].shxzh"/></td><td>单位 : <input type="text" name="list_attr['+table_index+'].list_value['+(tr_length - 1)+'].shxzh_mch"/></td><td></td><td>删除</td></tr>';
		$("#table_" + table_index).append(tr);
	}
	
</script>
<title>硅谷商城</title>
</head>
<body>
	<form action="save_attr.do" method="post">
		${flmch_2}<br>
		<a href="javascrip:;" onclick="spu_attr_add_value()">添加属性</a>
		<div id="attr_values">
			<table id="table_0" border="1">
				<input type="hidden" name="list_attr[0].flbh2" value="${flbh2}"/>
				<tr><td>属性名 : <input type="text" name="list_attr[0].shxm_mch"/></td><td></td><td><a href="javascript:;" onclick="spu_attr_add_tr(0)">添加</a></td><td></td></tr>
				<tr><td>属性值 : <input type="text" name="list_attr[0].list_value[0].shxzh"/></td><td>单位 : <input type="text" name="list_attr[0].list_value[0].shxzh_mch"/></td><td></td><td>删除</td></tr>
				<tr><td>属性值 : <input type="text" name="list_attr[0].list_value[1].shxzh"/></td><td>单位 : <input type="text" name="list_attr[0].list_value[1].shxzh_mch"/></td><td></td><td>删除</td></tr>
			</table><br>
		</div>
		<input type="submit" value="提交"/>
	</form>
</body>
</html>