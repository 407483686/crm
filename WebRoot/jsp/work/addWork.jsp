<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addwork.jsp' starting page</title>
    
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
  		$("#work-add-title").textbox({
  			width:240,
  			height:32,
  			required:true,
  			missingMessage:"请输入工作计划",
  		});
  		
  		$("#work-add-type").combobox({
  			width:100,
  			height:32,
  			required:true,
  			editable:false,
  			missingMessage:"请选择工作类型",
  				data : [{
				text : "业务",
				value : "业务"
			},{
				text : "内勤",
				value : "内勤"
			}],
			// 基础数据值对应的名称
			valueField : "value",
			// 基础数据字段对应的名称
			textField : "text",
			// 下拉面板的高度
			panelHeight : "auto",
  		});
  		
  		$("#work-add-state_date").datebox({
  			width:180,
  			height:32,
  			required:true,
  			editable:false,
  			missingMessage:"请选择开始时间",
  		});
  		
  		
  		$("#work-add-end_date").datebox({
  			width:180,
  			height:32,
  			required:true,
  			editable:false,
  			missingMessage:"请选择结束时间",
  		});
  	</script>
  
  	
  
  
  	<form id="work-add" method="post">
	<table class="form-table">
		<tbody>
			<tr>
				<td class="label">
					<label for="work-add-title" class="form-label" >工作名称：</label>
				</td>
				<td class="input">
					<input type="text" id="work-add-title" name="title"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="work-add-type" class="form-label" >工作类型：</label>
				</td>
				<td class="input">
					<input type="text" id="work-add-type" name="type"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="work-add-state_date" class="form-label" >开始时间：</label>
				</td>
				<td class="input">
					<input type="text" id="work-add-state_date" name="state_date_string"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="work-add-end_date" class="form-label" >结束时间：</label>
				</td>
				<td class="input">
					<input type="text" id="work-add-end_date" name="end_date_string"/>
				</td>
			</tr>
		</tbody>
	</table>
  </form>
  </body>
</html>
