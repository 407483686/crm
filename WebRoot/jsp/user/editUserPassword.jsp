<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adduser.jsp' starting page</title>
    
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
  		//把文本框设置为easyUI自带的文本框
  		$("#user-editPassword-account").textbox({
  			width:220,
  			height:32,
  			//不可以修改用户名，只能修改密码
  			editable:false
  		});
  		
  		$("#user-editPassword-password").textbox({
  			width:220,
  			height:32,
  			required:true,
  			//规定长度在6到30之间
  			validType:"length[6,30]",
  			missingMessage:"请输入	新密码",
  			invalidMessage:"密码长度应在6位到30位之间"
  		});
  		
  		$("#user-editPassword-nopassword").textbox({
  			width:220,
  			height:32,
  			required:true,
  			validType:"equals['#user-editPassword-password']",
  			missingMessage:"请输入确认密码",
  			invalidMessage:"确认密码和密码不一致"
  		});
  		
  		$.extend($.fn.validatebox.defaults.rules, {
	   		 equals: {
		        validator: function(value,param){
		            return value == $(param[0]).val();
		        },
		        message: 'Field do not match.'
	   		 }
   		 });
  	</script>
  
  	<form id="form-editPassword" method="post">
  	<!-- 存储用户id -->
  	<input type="hidden" name="id" value="${user.id}">
  	
	<table class="form-table">
		<tbody>
			<tr>
				<td class="label">
					<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 -->
					<label for="user-editPassword-account" class="form-label" >用户名：</label>
				</td>
				<td class="input">
					<input type="text" id="user-editPassword-account" name="accounts" value="${user.accounts}"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 -->
					<label for="user-editPassword-password" class="form-label" >新密码：</label>
				</td>
				<td class="input">
					<input type="password" id="user-editPassword-password" name="password"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 -->
					<label for="user-editPassword-nopassword" class="form-label" >确认密码：</label>
				</td>
				<td class="input">
					<input type="password" id="user-editPassword-nopassword"/>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
  </body>
</html>
