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
				<td class="label">公司名称：</td>
				<td class="content">${sessionScope.client.company}</td>
				<td class="label">联系人：</td>
				<td class="content">${sessionScope.client.name}</td>
			</tr>
			<tr>
				<td class="label">移动电话：</td> 
				<td class="content">${sessionScope.client.tel}</td> 
				<td class="label">客户来源：</td> 
				<td class="content">${sessionScope.client.source}</td> 
			</tr> 
			<tr>
				<td class="label">录入员：</td> 
				<td class="content">${sessionScope.client.enter}</td> 
				<td class="label">创建时间：</td> 
				<td class="content">${sessionScope.client.create_time}</td> 
			</tr> 
		</tbody>
	</table>
</body>
</html>
