<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addClient.jsp' starting page</title>
    
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
  		//移动电话为11位数字
  		$.extend($.fn.validatebox.defaults.rules, {
		    tel: {
		        validator: function(value, param){
		            return /^[0-9]{11}$/.test(value);
		        }
		    }
		});
  	
  	
  		$("#client-add-company").textbox({
  			width:240,
  			height:32,
  			required:true,
  			missingMessage:"请输入公司名称",
  		});
  		
  		$("#client-add-name").textbox({
  			width:240,
  			height:32,
  			required:true,
  			missingMessage:"请输入联系人",
  		});
  		
  		$("#client-add-tel").textbox({
  			width:240,
  			height:32,
  			required:true,
  			missingMessage:"移动电话",
  			validType : "tel",
  			invalidMessage:"移动电话不合法,必须为11位数字"
  		});
  		
  		
  		$("#client-add-source").combobox({
  			width : 100,
  			height:32,
			editable : false,
			data : [{
				text : "广告营销",
				value : "广告营销"
			},{
				text : "电话营销",
				value : "电话营销"
			},{
				text : "主动联系",
				value : "主动联系"
			}],
			// 基础数据值对应的名称
			valueField : "value",
			// 基础数据字段对应的名称
			textField : "text",
			// 下拉面板的高度
			panelHeight : "auto",
  		});
  	</script>
  
  	
  
  
  	<form id="client-add" method="post">
	<table class="form-table">
		<tbody>
			<tr>
				<td class="label">
					<label for="client-add-company" class="form-label" >公司名称：</label>
				</td>
				<td class="input">
					<input type="text" id="client-add-company" name="company"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="client-add-name" class="form-label" >联系人：</label>
				</td>
				<td class="input">
					<input type="text" id="client-add-name" name="name"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="client-add-tel" class="form-label" >移动电话：</label>
				</td>
				<td class="input">
					<input type="text" id="client-add-tel" name="tel"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="client-add-source" class="form-label" >客户来源：</label>
				</td>
				<td class="input">
					<input type="text" id="client-add-source" name="source"/>
				</td>
			</tr>
		</tbody>
	</table>
  </form>
  </body>
</html>
