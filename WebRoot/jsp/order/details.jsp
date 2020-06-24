<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<td class="label">订单标题：</td>
				<td class="content">${sessionScope.order.title}</td>
				<td class="label">订单编号：</td>
				<td class="content">${sessionScope.order.sn}</td>
			</tr>
			<tr>
				<td class="label">所属公司：</td>
				<td class="content">${sessionScope.order.documentary.company}</td>
				<td class="label">跟单员：</td>
				<td class="content">${sessionScope.order.documentary.staff_name}</td>
			</tr>
			<tr>
				<td class="label">订单原价：</td>
				<td class="content">${sessionScope.order.original}</td>
				<td class="label">订单金额：</td>
				<td class="content">${sessionScope.order.cost}</td>
			</tr>
			<tr>
				<td class="label">订单状态：</td>
				<td class="content">${sessionScope.order.pay_state}</td>
				<td class="label">录入员：</td>
				<td class="content">${sessionScope.order.enter}</td>
			</tr>
			<tr>
				<td class="label">创建时间：</td>
				<td colspan="3" class="content">${sessionScope.order.create_time}</td>
			</tr>
			<tr>
				<td class="label">详情备注：</td>
				<td colspan="3" class="content">${sessionScope.order.details}</td>
			</tr>
		</tbody>
	</table>

	<table class="details-table" style="max-width: 780px;">
		<tbody>
			<tr>
				<td class="label" style="text-align:center;">产品编号</td>
				<td class="label" style="text-align:center;">产品名称</td>
				<td class="label" style="text-align:center;">销售价格</td>
				<td class="label" style="text-align:center;">出货量</td>
				<td class="label" style="text-align:center;">状态</td>
				<td class="label" style="text-align:center;">出货时间</td>
			</tr>
			<c:forEach items="${sessionScope.outlibList}" var="outlib">
				<tr>
					<td class="label" style="text-align:center;">${outlib.product_sn}</td>
					<td class="label" style="text-align:center;">${outlib.product_name}</td>
					<td class="label" style="text-align:center;">${outlib.sell_price}</td>
					<td class="label" style="text-align:center;">${outlib.number}</td>
					<td class="label" style="text-align:center;">${outlib.state}</td>
					<td class="label" style="text-align:center;">${outlib.dispose_time}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
