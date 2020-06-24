<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addallo.jsp' starting page</title>
    
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
  		$("#allo-add-title").textbox({
  			width:240,
  			height:32,
  			required:true,
  			missingMessage:"请输入工作计划",
  		});
  		
  		$("#allo-add-type").combobox({
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
  		
  		$("#allo-add-staff").textbox({
  			width:240,
  			height:32,
  			required:true,
  			missingMessage:"请点击放大镜选择分配的员工",
  			icons : [{
  				iconCls : "icon-zoom",
  				handler : function(){
  					$("#addAlloChooseStaff-dialog").dialog({
  						title : "选择分配员工",
						width : 550,
						height : 380,
						iconCls : "icon-zoom",
						modal : true,
						href : "/CRM/jsp/allo/addAlloChooseStaff.jsp"
  					});
  				}
  			}]
  		});
  		
  		
  		
  		$("#allo-add-state_date").datebox({
  			width:180,
  			height:32,
  			required:true,
  			editable:false,
  			missingMessage:"请选择开始时间",
  		});
  		
  		
  		$("#allo-add-end_date").datebox({
  			width:180,
  			height:32,
  			required:true,
  			editable:false,
  			missingMessage:"请选择结束时间",
  		});
  		
  	</script>
  
  	
  
  
  	<form id="allo-add" method="post">
  	<!-- 就是work里面的staff_id，只不过这里添加就不再是取session的user对应的staff_id了，而是自己添加 -->
  	<input type="hidden" name="staff_id" id="staff_id">
	<table class="form-table">
		<tbody>
			<tr>
				<td class="label">
					<label for="allo-add-title" class="form-label" >工作名称：</label>
				</td>
				<td class="input">
					<input type="text" id="allo-add-title" name="title"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="allo-add-type" class="form-label" >工作类型：</label>
				</td>
				<td class="input">
					<input type="text" id="allo-add-type" name="type"/>
				</td>
			</tr>
			<tr>
			<tr>
				<td class="label">
					<label for="allo-add-staff" class="form-label" >分配给：</label>
				</td>
				<td class="input">
					<input type="text" id="allo-add-staff" name="staff_name"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="allo-add-state_date" class="form-label" >开始时间：</label>
				</td>
				<td class="input">
					<input type="text" id="allo-add-state_date" name="state_date_string"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="allo-add-end_date" class="form-label" >结束时间：</label>
				</td>
				<td class="input">
					<input type="text" id="allo-add-end_date" name="end_date_string"/>
				</td>
			</tr>
		</tbody>
	</table>
	
	<div id="addAlloChooseStaff-dialog"></div>
  </form>
  </body>
</html>
