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
<link rel="stylesheet" href="css/style.css">
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function all_checked(check){
		if (check){
			$(".commdity_check").attr("checked" , true);
		}else{
			$(".commdity_check").attr("checked" , false);
		}
	}
	
	// 复选框选择 ，修改勾选状态
	function commdity_check(check , sku_id){
		var checked_length = $(".commdity_check:checked").length;
		var sum_checked_length = $(".commdity_check").length;
		if(checked_length == sum_checked_length){
			$("#all_checked").attr("checked" , true);
		}else{
			$("#all_checked").attr("checked" , false);
		}
		
		if(check){
			check = 1;
		}else{
			check = 0;
		}
		$.post("commdity_check.do",{check:check,sku_id:sku_id},function(data){
			$("#list_cart").html(data);
		});
	}
	
	// 商品数量添加或减少
	function add_and_subtract(tjshl , sku_id){
		// 判断添加数量是否是数字
		var re=/^\+?[1-9][0-9]*$/;
		var b = re.test(tjshl);
		if(b){
			if(tjshl <= 10000){
				$.post("add_and_subtract.do",{tjshl:tjshl,sku_id:sku_id},function(data){
					$("#list_cart").html(data);
				});
			}else{
				alert("请输入正确数字");
			}
		}else{
			alert("请输入正确数字");
		}
	}
	
	
	// 删除商品
	function delete_cart_by_sku_id(sku_id){
		$.post("delete_cart_by_sku_id.do",{sku_id:sku_id},function(data){
			$("#list_cart").html(data);
		});
	}

</script>
<title>硅谷商城</title>
</head>
<style type="text/css">
	td{vertical-align: middle !important;}
	.form-group{padding: 5px 0;}
</style>
<body>

	<c:if test="${empty list_cart }">
		购物车中没有商品，管快选购吧！
	</c:if>
 
	<c:if test="${not empty list_cart}">
		<div class="Cbox">
			<table class="table table-striped table-bordered table-hover">
			   <thead>
			     <tr>
			       <th>商品图片</th>
			       <th>商品名称</th>
			       <th>商品属性</th>
			       <th>商品价格</th>
			       <th>商品数量</th>
			       <th>操作</th>
			       <th>操作</th>
			        <th>操作</th>
			     </tr>
			   </thead>
			   <tbody>
					<c:forEach items="${list_cart}" var="cart"> 
					<tr>
						<td><input class="commdity_check" type="checkbox" onclick="commdity_check(this.checked,${cart.sku_id})" ${cart.shfxz=="0"?"":"checked"}/></td>
						<td>
							<img alt="${cart.sku_mch }" src="upload/image/${cart.shp_tp}" width="70px"/>
						</td>
						<td>${cart.sku_mch}</td>
						<td>
				       		颜色：<span style='color:#ccc'>白色</span><br>
				       		尺码：<span style='color:#ccc'>L</span>
			       		</td>
						<td>
							${cart.sku_jg}
						</td>
						<td>
							<c:if test="${cart.tjshl != 1}">
								<a href="javascript:add_and_subtract(${cart.tjshl-1},${cart.sku_id});"> - </a>
							</c:if>
							<input type="text" value="${cart.tjshl}" style="width: 40px;" onchange="add_and_subtract(this.value , ${cart.sku_id})" style="width:50px;text-align:center"/>
							<a href="javascript:add_and_subtract(${cart.tjshl+1},${cart.sku_id});"> + </a>
						</td>
						<td>
							${cart.hj }
						</td>
						<td>
							<a href="javascript:delete_cart_by_sku_id(${cart.sku_id});">删除</a>
						</td>
					</tr>
				</c:forEach>
			   </tbody>
		 	</table>
		</div>
		<div class="Cprice">
			<div class="price">总价：${total_price }</div>
			<div class="price">总数量：${total_commodity }</div>
			<div class="jiesuan">结算</div>
		</div>
	</c:if>  
	
</body>
</html>