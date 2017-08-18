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
function add_tabs(url , title , flmch_2 , flbh2){
	var flag = $('#tt').tabs('exists',title);
	//if(flag){
		//$('#tt').tabs('select',title);
	//}else{
		//$('#tt').tabs('select',title);
		$.post(url ,{"flmch_2" :flmch_2 , "flbh2" :flbh2}, function(data){
			$('#tt').tabs('update',{    
			    title:title,    
			    content:data,    
			    closable:true,
			});  
		});
	//}
}



$(function() {
	// 得到文件的json格式的数据
	$("#attr_class_1").combobox({
		 url:'js/json/class_1.js',    
		 valueField:'id',    
		 textField:'flmch1',
		 value:'请选择',
		 onSelect : function get_class2_by_class1_id(class_1_id) {
			 var class_1_id = $(this).combobox("getValue");
				$("#attr_class_2").combobox({
					url:'js/json/class_2_' + class_1_id + '.js',    
					valueField:'id',    
					textField:'flmch2',
					value:'请选择',
					onSelect : 	
						function spu_class_2_change(flbh2){
						var flbh2 = $("#attr_class_2").combobox('getValue');
						var flmch_2 = $("#attr_class_2").combobox('getText');
						$('#spu_attr_info').datagrid({    
						    url:'spu_attr_by_class_2_json.do',   
						    queryParams : {flbh2 : flbh2 ,flmch_2 : flmch_2},
						    columns:[[    
						        {field:'shxm_mch',title:'属性名称',width:100},    
						        {field:'chjshj',title:'创建时间',width:100,
						        	  formatter: function(value,row,index){
											var myDate = new Date();
											var dateTime = myDate.setTime(value);
											return myDate.toLocaleDateString();
										}	
						        },    
						        {field:'list_value',title:'属性值',width:200 ,
						        	 formatter: function(value,row,index){
						        		 var temp = "";
						        		 $.each(value , function( i , json ){
						        			 temp += json.shxzh_mch;
						        			 temp += json.shxzh;
						        		 });
						        		 return temp;
									 }	
						        },
						      
						    ]]    
						});
						var href = 'javascript:add_tabs(\'spu_attr_add.do\',"添加属性",\''+flmch_2+'\','+flbh2+')';
						
						var url = '<a id="attr_add" href="spu_attr_add.do?flmch_2=${flmch_2}&flbh2=${flbh2}" >添加属性</a>';
						
						$("#add_a_href").html(url);
						$("#attr_add").attr("href" , href);
					}
				});
			}
	});

	
});

	
	
</script>
<title>硅谷商城</title>
</head>
<body>
	<form action="spu_attr_by_class_2">
		<select name="flbh1" id="attr_class_1" onchange="get_class2_by_class1_id(this.value)" class="easyui-combobox" style="width:200px;"></select>
	    <select name="flbh2" id="attr_class_2" onchange="spu_class_2_change(this.value)" class="easyui-combobox" style="width:200px;"></select>
	</form>
	<div id="spu_attr_info">
		
	</div>
	<div id="add_a_href">
		
	</div>
</body>
</html>