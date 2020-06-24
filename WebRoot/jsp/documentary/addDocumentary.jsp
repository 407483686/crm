<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adddocumentary.jsp' starting page</title>
    
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
  		$("#documentary-add-title").textbox({
  			width:240,
  			height:32,
  			required:true,
  			missingMessage:"请输入跟单标题",
  		});
  		
  		$("#documentary-add-company").textbox({
  			width:240,
  			height:32,
  			editable:false,
  			required:true,
  			missingMessage:"请点击右侧放大镜选择关联公司",
  			icons : [{
  				iconCls : "icon-zoom",
  				handler : function(){
					$("#documentary-add-chooseCompany-dialog").dialog({
						title : "选择关联公司",
						width : 550,
						height : 380,
						iconCls : "icon-zoom",
						modal : true,
						href : "/CRM/jsp/documentary/addDocumentaryChooseCompany.jsp"
					});  					
  				}
  			}]
  		});
  		
  		$("#documentary-add-staff_name").textbox({
  			width:240,
  			height:32,
  			required:true,
  			editable:false,
  			missingMessage:"请点击右侧放大镜选择跟单员",
  			icons : [{
  				iconCls : "icon-zoom",
  				handler : function(){
					$("#documentary-add-chooseStaff-dialog").dialog({
						title : "选择跟单员",
						width : 550,
						height : 380,
						iconCls : "icon-zoom",
						modal : true,
						href : "/CRM/jsp/documentary/addDocumentaryChooseStaff.jsp"
					});  					
  				}
  			}]
  		});
  		
  		
  		$("#documentary-add-way").combobox({
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
  		
  		$("#documentary-add-evolve").combobox({
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
  		
  		$("#documentary-add-remark").textbox({
  			width:240,
  			height:32,
  			prompt:"20字以内备注说明"
  		});
  	</script>
  
  	
  
  
  	<form id="documentary-add" method="post">
	<table class="form-table">
		<tbody>
			<tr>
				<td class="label">
					<label for="documentary-add-title" class="form-label" >跟单标题：</label>
				</td>
				<td class="input">
					<input type="text" id="documentary-add-title" name="title"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="documentary-add-company" class="form-label" >关联公司：</label>
				</td>
				<td class="input">
					<input type="text" id="documentary-add-company" name="company"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="documentary-add-staff_name" class="form-label" >跟单员：</label>
				</td>
				<td class="input">
					<input type="text" id="documentary-add-staff_name" name="staff_name"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="documentary-add-way" class="form-label" >跟单方式：</label>
				</td>
				<td class="input">
					<input type="text" id="documentary-add-way" name="way"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="documentary-add-evolve" class="form-label" >进展状况：</label>
				</td>
				<td class="input">
					<input type="text" id="documentary-add-evolve" name="evolve"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="documentary-add-remark" class="form-label" >详情备注：</label>
				</td>
				<td class="input">
					<input type="text" id="documentary-add-remark" name="remark"/>
				</td>
			</tr>
		</tbody>
	</table>
  </form>
  <!-- 放大镜选择跟单的关联公司 -->
  <div id="documentary-add-chooseCompany-dialog"></div>
  
  <!-- 放大镜选择跟单的跟单员 -->
  <div id="documentary-add-chooseStaff-dialog"></div>
  </body>
</html>
