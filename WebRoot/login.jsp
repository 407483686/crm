<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>CRM客户关系管理系统</title>
<link rel="stylesheet" type="text/css"
	href="js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/login.css">

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>

<body>
	<!-- 登录面板 -->
	<form id="form-login" method="post">
		<table class="form-table">
			<tbody>
				<tr>
					<td class="label">
						<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 --> <label
						for="login-account" class="form-label">用户名：</label></td>
					<td class="input"><input type="text" id="login-account"
						name="accounts" class="easyui-textbox" /></td>
				</tr>
				<tr>
					<td class="label">
						<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 --> <label
						for="login-password" class="form-label">密码：</label></td>
					<td class="input"><input type="password" id="login-password"
						name="password" class="easyui-textbox" /></td>
				</tr>
				<!-- <tr>
					<td colspan="2" id="register" class="register">没有业务帐号？<a
						href="javascript:void(0)" class="btn-register">「快速申请」</a></td>
				</tr> -->
			</tbody>
		</table>
	</form>
	<!-- 这个是快速申请账号对话框 
    <div id="user_dialog"></div> -->
</body>
</html>
