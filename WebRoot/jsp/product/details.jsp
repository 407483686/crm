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
				<td class="content">${sessionScope.product.name}</td>
				<td class="label">产品编号：</td>
				<td class="content">${sessionScope.product.sn}</td>
			</tr>
			<tr>
				<td class="label">产品类别：</td> 
				<td class="content">${sessionScope.product.type}</td> 
				<td class="label">计量单位：</td> 
				<td class="content">${sessionScope.product.unit}</td> 
			</tr> 
			<tr>
				<td class="label">采购价格：</td> 
				<td class="content">${sessionScope.product.pro_price}</td> 
				<td class="label">销售价格：</td> 
				<td class="content">${sessionScope.product.sell_price}</td> 
			</tr> 
			<tr>
				<td class="label">库存：</td> 
				<td class="content">${sessionScope.product.inventory}</td>
				<td class="label">库存警报量：</td> 
				<td class="content">${sessionScope.product.inventory_alarm}</td>
			</tr>
			<tr>
				<td class="label">入库总量：</td> 
				<td class="content">${sessionScope.product.inventory_in}</td>
				<td class="label">出库总量：</td> 
				<td class="content">${sessionScope.product.inventory_out}</td>
			</tr>
			<tr>
				<td class="label">创建时间：</td> 
				<td class="content" colspan="3">${sessionScope.product.create_time}</td>
			</tr>
			<tr>
				<td class="label">详情：</td> 
				<td class="content" colspan="3">${sessionScope.product.productExtend.details}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
