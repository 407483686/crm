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

<title>My JSP 'editallo.jsp' starting page</title>

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
		$("#allo-edit-title").textbox({
			width : 240,
			height : 32,
			editable : false
		});

		$("#allo-edit-type").textbox({
			width : 240,
			height : 32,
			editable : false
		});

		$("#allo-edit-start-date").textbox({
			width : 240,
			height : 32,
			editable : false
		});

		$("#allo-edit-end-date").textbox({
			width : 240,
			height : 32,
			editable : false
		});

		$("#allo-edit-state").textbox({
			width : 240,
			height : 32,
			editable : false
		});

		
	</script>



	<form id="allo-edit" method="post">
		<input type="hidden" name="id" id="id">
		<table class="form-table" style="max-width:780px;">
			<tbody>
				<tr>
					<td class="label"><label for="allo-edit-title"
						class="form-label">工作名称：</label></td>
					<td class="input"><input type="text" id="allo-edit-title"
						name="title" /></td>
					<td class="label"><label for="allo-edit-type"
						class="form-label">工作类型：</label></td>
					<td class="input"><input type="text" id="allo-edit-type"
						name="type" /></td>
				</tr>
				<tr>
					<td class="label"><label for="allo-edit-start-date"
						class="form-label">开始时间：</label></td>
					<td class="input"><input type="text" id="allo-edit-start-date"
						name="state_date" /></td>
					<td class="label"><label for="allo-edit-end-date"
						class="form-label">结束时间：</label></td>
					<td class="input"><input type="text" id="allo-edit-end-date"
						name="end_date" /></td>
				</tr>
				<tr>
					<td class="label"><label for="allo-edit-state">工作状态：</label></td>
					<td colspan="3"><input type="text" id="allo-edit-state"
						name="state" /></td>
				</tr>
				<tr>
					<!-- 列表拉长了之后可能会在中间，所以设置一个纵向向上对齐，但又不能太上面不然不好看，所以有8px距离 -->
					<td class="label" valign="top" style="padding-top:8px;"><label>完成阶段：</label>
					</td>
					<td colspan="3">
						<table id="allo-edit-stage-list" style="width:620px;"></table></td>
				</tr>

			</tbody>
		</table>
	</form>
</body>
</html>
