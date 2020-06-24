<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'details.jsp' starting page</title>

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
	<table class="details-table" style="max-width: 780px;">
		<tbody>
			<tr>
				<td class="label">通知标题：</td>
				<td class="content" colspan="3">${sessionScope.inform.title}</td>
			</tr>
			<tr>
				<td class="label">发布者：</td> 
				<td class="content" colspan="3">${sessionScope.inform.staff_name}</td> 
			</tr> 
			<tr>
				<td class="label">通知详情：</td> 
				<td class="content" colspan="3">${sessionScope.inform.details}</td>
				<!-- 没设置这个上下对比，colspan没有效果 -->
				<td style="width:1px"></td>
			</tr> 
			<tr>
				<td class="label">创建时间：</td> 
				<td class="content" colspan="3">${sessionScope.inform.create_time}</td> 
			</tr> 
		</tbody>
	</table>
</body>
</html>
