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
  		$("#documentary-edit-title").textbox({
  			width:240,
  			height:32,
			editable:false
  		});
  		
  		$("#documentary-edit-company").textbox({
  			width:240,
  			height:32,
  			editable:false
  		});
  		
  		$("#documentary-edit-staff_name").textbox({
  			width:240,
  			height:32,
  			editable:false
  		});
  		
  		
  		$("#documentary-edit-way").combobox({
  			width : 100,
  			height:32,
			editable : false,
			data : [{
				text : "上门拜访",
				value : "上门拜访"
			},{
				text : "电话沟通",
				value : "电话沟通"
			},{
				text : "网络咨询",
				value : "网络咨询"
			}],
			// 基础数据值对应的名称
			valueField : "value",
			// 基础数据字段对应的名称
			textField : "text",
			// 下拉面板的高度
			panelHeight : "auto",
  		});
  		
  		$("#documentary-edit-evolve").combobox({
  			width : 100,
  			height:32,
			editable : false,
			data : [{
				text : "未成交",
				value : "未成交"
			},{
				text : "谈判中",
				value : "谈判中"
			},{
				text : "已成交",
				value : "已成交"
			}],
			// 基础数据值对应的名称
			valueField : "value",
			// 基础数据字段对应的名称
			textField : "text",
			// 下拉面板的高度
			panelHeight : "auto",
  		});
  		
  		$("#documentary-edit-remark").textbox({
  			width:240,
  			height:32,
  			prompt:"20字以内备注说明"
  		});
  	</script>
  
  	
  
  
  	<form id="documentary-edit" method="post">
  	<input name="id" type="hidden"/>
	<table class="form-table">
		<tbody>
			<tr>
				<td class="label">
					<label for="documentary-edit-title" class="form-label" >跟单标题：</label>
				</td>
				<td class="input">
					<input type="text" id="documentary-edit-title" name="title"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="documentary-edit-company" class="form-label" >关联公司：</label>
				</td>
				<td class="input">
					<input type="text" id="documentary-edit-company" name="company"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="documentary-edit-staff_name" class="form-label" >跟单员：</label>
				</td>
				<td class="input">
					<input type="text" id="documentary-edit-staff_name" name="staff_name"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="documentary-edit-way" class="form-label" >跟单方式：</label>
				</td>
				<td class="input">
					<input type="text" id="documentary-edit-way" name="way"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="documentary-edit-evolve" class="form-label" >进展状况：</label>
				</td>
				<td class="input">
					<input type="text" id="documentary-edit-evolve" name="evolve"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="documentary-edit-remark" class="form-label" >详情备注：</label>
				</td>
				<td class="input">
					<input type="text" id="documentary-edit-remark" name="remark"/>
				</td>
			</tr>
		</tbody>
	</table>
  </form>
  </body>
</html>
