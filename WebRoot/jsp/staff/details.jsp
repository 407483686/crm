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
				<td class="label">姓名：</td>
				<td class="content">${sessionScope.staff.name}</td>
				<td class="label">性别：</td>
				<td class="content">${sessionScope.staff.gender}</td>
			</tr>
			<tr>
				<td class="label">工号：</td> 
				<td class="content">${sessionScope.staff.number}</td> 
				<td class="label">职位：</td> 
				<td class="content">${sessionScope.staff.post}</td> 
			</tr> 
			<tr>
				<td class="label">移动电话：</td> 
				<td class="content">${sessionScope.staff.tel}</td> 
				<td class="label">员工类型：</td> 
				<td class="content">${sessionScope.staff.type}</td> 
			</tr> 
			<tr>
				<td class="label">身份证：</td> 
				<td class="content">${sessionScope.staff.id_card}</td> 
				<td class="label">婚姻状况：</td> 
				<td class="content">${sessionScope.staff.marital_status}</td> 
			</tr>
			<tr>
				<td class="label">民族：</td> 
				<td class="content">${sessionScope.staff.nation}</td> 
				<td class="label">入职时间：</td> 
				<td class="content">${sessionScope.staff.entry_date}</td> 
			</tr>
			<tr>
				<td class="label">学历：</td> 
				<td class="content">${sessionScope.staff.education}</td> 
				<td class="label">离职时间：</td> 
				<td class="content">${sessionScope.staff.dimission_date}</td> 
			</tr>
			<tr>
				<td class="label">入职状态：</td> 
				<td class="content">${sessionScope.staff.entry_status}</td> 
				<td class="label">专业：</td> 
				<td class="content">${sessionScope.staff.extend.major}</td> 
			</tr>
			<tr>
				<td class="label">政治面貌：</td> 
				<td class="content">${sessionScope.staff.politics_status}</td> 
				<td class="label">健康状况：</td> 
				<td class="content">${sessionScope.staff.extend.heath}</td> 
			</tr>
			<tr>
				<td class="label">户口：</td> 
				<td class="content">${sessionScope.staff.extend.residence}</td> 
				<td class="label">毕业时间：</td> 
				<td class="content">${sessionScope.staff.extend.graduation_time}</td> 
			</tr>
			<tr>
				<td class="label">户口所在地：</td> 
				<td class="content">${sessionScope.staff.extend.registered_permanent_residence}</td> 
				<td class="label">毕业院校：</td> 
				<td class="content">${sessionScope.staff.extend.school}</td> 
			</tr>
			<tr> 
				<td class="label">关联帐号ID：</td> 
				<td class="content">${sessionScope.staff.user.id}</td> 
				<td class="label">状态：</td> 
				<td class="content">${sessionScope.staff.user.state}</td> 
			</tr>
			<tr> 
				<td class="label">个人简介：</td> 
				<td class="content" colspan="3">${sessionScope.staff.extend.intro}</td> 
			</tr> 
			<tr>
				<td class="label">详情：</td> 
				<td class="content" colspan="3">${sessionScope.staff.extend.details}</td>
			</tr>
			<tr>
				<td class="label">创建时间：</td> 
				<td class="content" colspan="3">${sessionScope.staff.create_time}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
