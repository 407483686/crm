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

<title>My JSP 'editwork.jsp' starting page</title>

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
		$("#work-edit-title").textbox({
			width : 240,
			height : 32,
			editable : false
		});

		$("#work-edit-type").textbox({
			width : 240,
			height : 32,
			editable : false
		});

		$("#work-edit-start-date").textbox({
			width : 240,
			height : 32,
			editable : false
		});

		$("#work-edit-end-date").textbox({
			width : 240,
			height : 32,
			editable : false
		});

		$("#work-edit-state").textbox({
			width : 240,
			height : 32,
			editable : false
		});

		//按钮 
		//取消按钮
		$('#work-add-stage-cancel').linkbutton({ 
			iconCls : 'icon-redo', 
			onClick : function () {
				//rejectChanges : 回滚所有从创建或者上一次调用acceptChanges函数后更改的数据。
				//点击取消按钮时，先回滚在表单上做的改变，然后再隐藏保存取消按钮，并显示添加和完成按钮
				$("#work-edit-stage-list").datagrid("rejectChanges");
				$("#work-add-stage-cancel").hide();
				$("#work-add-stage-save").hide();
				$("#work-add-stage-button").show();
				$("#work-add-stage-finish").show();
			}
		});
		//保存按钮
		$('#work-add-stage-save').linkbutton({ 
			iconCls : 'icon-accept', 
			onClick : function () {
				$("#work-edit-stage-list").datagrid("endEdit",$("#work-edit-stage-list").datagrid("getRows").length-1);
			}
		});
		//添加工作阶段按钮
		$('#work-add-stage-button').linkbutton({
			iconCls : 'icon-add',
			onClick : function() {
				//点击添加按钮后，先添加一个空行
				$("#work-edit-stage-list").datagrid("appendRow",{});
				//然后开启编辑最后一行,开启之前要在列表中写editor属性
				$("#work-edit-stage-list").datagrid("beginEdit",$("#work-edit-stage-list").datagrid("getRows").length-1);
				
				//显示保存和取消按钮，隐藏添加和完成按钮
				$('#work-add-stage-save').show();
				$('#work-add-stage-cancel').show();
				$('#work-add-stage-button').hide();
				$('#work-add-stage-finish').hide();
			}
		});
		//设置工作阶段完成 
		$('#work-add-stage-finish').linkbutton({
			iconCls : 'icon-accept',
			onClick : function() {
				var id = $("#id").val();
				$.ajax({
					url : "work_finish.action",
					type : "POST",
					data : {
						id : id
					},
					success : function(data){
						if(data == 1){
							$("#work-edit-stage-list").datagrid("reload");
							//四个按钮都隐藏
							$('#work-add-stage-save').hide();
							$('#work-add-stage-cancel').hide();
							$('#work-add-stage-button').hide();
							$('#work-add-stage-finish').hide();
							
							//刷新一下大的数据列表
							$("#work").datagrid("reload");
						}
					}
				});
			}
		});
	</script>



	<form id="work-edit" method="post">
		<input type="hidden" name="id" id="id">
		<table class="form-table" style="max-width:780px;">
			<tbody>
				<tr>
					<td class="label"><label for="work-edit-title"
						class="form-label">工作名称：</label></td>
					<td class="input"><input type="text" id="work-edit-title"
						name="title" /></td>
					<td class="label"><label for="work-edit-type"
						class="form-label">工作类型：</label></td>
					<td class="input"><input type="text" id="work-edit-type"
						name="type" /></td>
				</tr>
				<tr>
					<td class="label"><label for="work-edit-start-date"
						class="form-label">开始时间：</label></td>
					<td class="input"><input type="text" id="work-edit-start-date"
						name="state_date" /></td>
					<td class="label"><label for="work-edit-end-date"
						class="form-label">结束时间：</label></td>
					<td class="input"><input type="text" id="work-edit-end-date"
						name="end_date" /></td>
				</tr>
				<tr>
					<td class="label"><label for="work-edit-state">工作状态：</label></td>
					<td colspan="3"><input type="text" id="work-edit-state"
						name="state" /></td>
				</tr>
				<tr>
					<!-- 列表拉长了之后可能会在中间，所以设置一个纵向向上对齐，但又不能太上面不然不好看，所以有8px距离 -->
					<td class="label" valign="top" style="padding-top:8px;"><label>完成阶段：</label>
					</td>
					<td colspan="3">
						<table id="work-edit-stage-list" style="width:620px;"></table></td>
				</tr>
				<!-- 如果状态为已完成，那么这个tr的内容会被利用id隐藏掉 -->
				<tr id="work-button">
					<td>&nbsp;</td>
					<td class="input" colspan="3">
						<a id="work-add-stage-finish" style="width: 90px;height: 26px;float:left;">设置完成</a> 
						<a id="work-add-stage-button" style="width: 70px;height: 26px;float:right;margin-right:50px;">添加</a>
						<a id="work-add-stage-cancel" style="display: none;width: 70px;height: 26px;float:right;margin-right:50px;">取消</a>
						<a id="work-add-stage-save" style="display: none;width: 70px;height: 26px;float:right;margin-right:10px;">保存</a>
					</td>
				</tr>

			</tbody>
		</table>
	</form>
</body>
</html>
