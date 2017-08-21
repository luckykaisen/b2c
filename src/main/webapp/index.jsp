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
	<script type="text/javascript" src="js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.3.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.3.5/themes/icon.css">



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

	function add_tabs(url , title){
		var flag = $('#tt').tabs('exists',title);
		if(flag){
			$('#tt').tabs('select',title);
		}else{
			$.post(url , function(data){
				$('#tt').tabs('add',{    
				    title:title,    
				    content:data,    
				    closable:true,
				});  
			});
		}
	}
	
	function add_tabs2n(url , title){
		var flag = $('#tt').tabs('exists',title);
		if(flag){
			$('#tt').tabs('select',title);
		}else{
			$.post(url , function(data){
				$('#tt').tabs('add',{    
				    title:title,    
				    content:data,    
				    closable:true,
				});  
			});
		}
	}
</script>
<title>硅谷商城</title>
</head>
<body>
	
</body>
</html>