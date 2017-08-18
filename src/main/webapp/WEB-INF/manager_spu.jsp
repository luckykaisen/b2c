<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function() {
		// 得到文件的json格式的数据
	/* 	$.getJSON("js/json/class_1.js", function(data) {
			// 遍历到下拉菜单
			$.each(data, function(i, n) {
				$("#spu_class_1").append("<option value="+n.id+">" + n.flmch1 + "</option>");
			});

		}); */
	});
		/* function get_class2_by_class1_id(class_1_id) {
			$("#spu_pp").empty();
			$("#spu_class_2").empty();
			$.getJSON("js/json/class_2_" + class_1_id + ".js", function(data) {
				// 遍历到下拉菜单
				$.each(data, function(i, n) {
					$("#spu_class_2").append("<option value="+n.id+">" + n.flmch2+"</option>");
				});
			});
			get_tm_by_class2_id(class_1_id);
		} */

	

	function upload_file(index){
		$("#spu_file_" + index).click();
	}
	
	function spu_change_image(index){
		var file = $("#spu_file_"+index)[0].files[0];
		var url = window.URL.createObjectURL(file);
		$("#spu_image_" + index).attr("src",url);
		
		if(index >= 3) {
			var image = '<img id="spu_image_'+(index+1)+'" alt="" width="70px" src="small_image/upload_hover.png" style="cursor:pointer" onclick="upload_file('+(index+1)+')">';
			var file = '<input id="spu_file_'+(index+1)+'" onchange="spu_change_image('+(index+1)+')" style="display:none;" type="file" name="files"><br>';
			$("#spu_images").append(image);
			$("#spu_files").append(file);
		}
	}
	$("#spu_class_1").combobox({
		url:'js/json/class_1.js',    
	    valueField:'id',    
	    textField:'flmch1',
	    onSelect : function get_class2_by_class1_id() {
	    	var class_1_id = $(this).combobox("getValue");
			$("#spu_class_2").combobox({
				url:'js/json/class_2_' + class_1_id + '.js',    
			    valueField:'id',    
			    textField:'flmch2',
			    onSelect :	function get_tm_by_class2_id() {
					$("#spu_pp").combobox({
						url:'js/json/tm_class_1_' + class_1_id + '.js',    
					    valueField:'id',    
					    textField:'ppmch'
					});
				}
			});
		}
	});
	
	
	
</script>
<title>硅谷商城</title>
</head>
<body>
	<form action="save_spu.do" enctype="multipart/form-data" method="post">
		 	<div class="easyui-layout" data-options="fit:true" style="height:200px">
				<div data-options="region:'north',split:true,border:false" style="height:50px">
					商品名称：<input type="text" name="shp_mch"><br>
					商品描述：<input type="text" name="shp_msh"><br> 
				</div>
				<div data-options="region:'west',split:true,border:false" style="width:200px">
					<select name="flbh1" id="spu_class_1" class="easyui-combobox" onchange="get_class2_by_class1_id(this.value)" style="width:150px;">
						<option>请选择</option>
					</select><br><br>
					<select name="flbh2" id="spu_class_2" class="easyui-combobox" style="width:150px;">
						<option>请选择</option>
					</select><br><br>
					<select name="pp_id" id="spu_pp" class="easyui-combobox" style="width:150px;">
						<option>请选择</option>
					</select><br><br>
				</div>
				<div data-options="region:'center',border:false">
					
					<div id="spu_images">
					<img id="spu_image_1" alt="" width="70px" src="small_image/upload_hover.png" style="cursor:pointer" onclick="upload_file(1)">
					<img id="spu_image_2" alt="" width="70px" src="small_image/upload_hover.png" style="cursor:pointer" onclick="upload_file(2)">
					<img id="spu_image_3" alt="" width="70px" src="small_image/upload_hover.png" style="cursor:pointer" onclick="upload_file(3)">
					</div>
				</div>
			</div>
	
		
				
				
		<input type="submit" value="提交" />
		<div id="spu_files">
		<input id="spu_file_1" onchange="spu_change_image(1)" style="display:none;" type="file" name="files"><br>
		<input id="spu_file_2" onchange="spu_change_image(2)" style="display:none;" type="file" name="files"><br>
		<input id="spu_file_3" onchange="spu_change_image(3)" style="display:none;" type="file" name="files"><br>
		
		</div>
			</div>
	</form>

</body>
</html>