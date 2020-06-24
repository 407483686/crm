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
				<td class="label">产品名称：</td>
				<td class="content">${sessionScope.inlib.product.name}</td>
				<td class="label">产品编号：</td>
				<td class="content">${sessionScope.inlib.product.sn}</td>
			</tr>
			<tr>
				<td class="label">产品类别：</td> 
				<td class="content">${sessionScope.inlib.product.type}</td> 
				<td class="label">采购价格：</td> 
				<td class="content">${sessionScope.inlib.product.pro_price}</td> 
			</tr> 
			<tr>
				<td class="label">入库数量：</td> 
				<td class="content">${sessionScope.inlib.number}</td> 
				<td class="label">经办人：</td> 
				<td class="content">${sessionScope.inlib.staff_name}</td> 
			</tr> 
			<tr>
				<td class="label">入库方式：</td> 
				<td class="content">${sessionScope.inlib.mode}</td>
				<td class="label">入库说明：</td> 
				<td class="content">${sessionScope.inlib.mode_explain}</td>
			</tr>
			<tr>
				<td class="label">录入员：</td> 
				<td class="content">${sessionScope.inlib.enter}</td>
				<td class="label">创建时间：</td> 
				<td class="content">${sessionScope.inlib.create_time}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
