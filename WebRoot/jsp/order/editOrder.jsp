<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'editClient.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<script type="text/javascript">
  		//订单标题
  		$("#order-edit-title").textbox({
  			width:240,
  			height:32,
			editable:false
  		});
  		
  		//关联跟单
  		$("#order-edit-documentary").textbox({
  			width:240,
  			height:32,
  			editable:false
  		});
  		
  		//订单金额
  		$("#order-edit-cost").textbox({
  			width:240,
  			height:32,
  			editable:false
  		});
  		
  	</script>
  
  	
  
  
  	<form id="order-edit" method="post">
  	<input name="id" type="hidden"/>
	<table class="form-table">
		<tbody>
			<tr>
				<td class="label">
					<label for="order-edit-title" class="form-label" >订单标题：</label>
				</td>
				<td class="input">
					<input type="text" id="order-edit-title" name="title"/>
				</td>
				<td class="label">
					<label for="order-edit-documentary" class="form-label" >关联跟单：</label>
				</td>
				<td class="input">
					<input type="text" id="order-edit-documentary" name="documentary_title"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="order-edit-cost" class="form-label" >订单金额：</label>
				</td>
				<td class="input">
					<input type="text" id="order-edit-cost" name="cost"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="order-edit-details" class="form-label" >订单备注：</label>
				</td>
				<td class="input" colspan="3">
					<textarea class="textarea" name="details"></textarea>
				</td>
			</tr>
		</tbody>
	</table>
  </form>
  </body>
</html>
