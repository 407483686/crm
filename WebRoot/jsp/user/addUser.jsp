<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addPost.jsp' starting page</title>
    
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
  	<!-- 引入随机密码js -->
  	<script type="text/javascript" src="/CRM/js/random.js"></script>
  	
  
  	<!-- 由于要添加的js代码太少，就不另外写一个文件了 -->
  	<script type="text/javascript">
  		//把文本框设置为easyUI自带的文本框
  		$("#user-add-account").textbox({
  			width:220,
  			height:32,
  			required:true,
  			//规定长度在2到10之间
  			validType:"length[2,10]",
  			missingMessage:"请输入用户名",
  			invalidMessage:"用户名长度应在2位到10位之间"
  		});
  		
  		$("#user-add-password").textbox({
  			width:220,
  			height:32,
  			required:true,
  			//规定长度在6到30之间
  			validType:"length[6,30]",
  			missingMessage:"请输入密码",
  			invalidMessage:"密码长度应在6位到30位之间"
  		});
  		
  		$(".rand-add").click(function(){
  			$("#user-add-password").textbox("setValue",getRandPassword(8,16));
  		});
  		
  		$("#user-add-staff-id").combogrid({
  			width : 120,
  			height : 32,
  			editable :false,
  			required : true,
			url:"/CRM/staff_getNotRelationList.action",
			panelWidth :450,
			panelHeight : "auto",
			panelMaxHeight : 227,
			fitColumns : true,
			rownumbers : true,
			border : false,
			idField : "id",
			textField : "name",
			pagination : true,
			pageSize : 10,
			pageList : [10,20,30,40,50],
			pageNumber : 1,
		    columns:[[    
		        {field:'id',title:'自动编号',width:50,hidden:true},   
		        {field : 'name', title : '姓名', width : 80},
				{field : 'number', title : '工号', width : 50, sortable : true}, 
				{field : 'gender', title : '性别',width : 50, sortable : true}, 
				{field : 'id_card', title : '身份证', width : 150}, 
				{field : 'post', title : '职位', width : 50}, 
				{field : 'create_time', title : '创建时间', width : 100, hidden: true}
	  		]],
	  		onOpen : function(){
	  			//在每一次打开这个表格的时候，重新刷新一下，因为有可能有新增，而这里面还是缓存
	  			$("#user-add-staff-id").combogrid("grid").datagrid("reload");
	  		}
	  	});
  		
  	</script>
  
  	<form id="user-add" method="post">
  	<!-- 如果是登录页面传来的，那么会给这个input赋值为冻结 -->
  	<input type="hidden" name="state" value="正常">
	<table class="form-table">
		<tbody>
			<tr>
				<td class="label">
					<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 -->
					<label for="user-add-account" class="form-label" >用户名：</label>
				</td>
				<td class="input">
					<input type="text" id="user-add-account" name="accounts"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 -->
					<label for="user-add-password" class="form-label" >密码：</label>
				</td>
				<td class="input">
					<input type="text" id="user-add-password" name="password"/>
					<span class="link rand-add">生成</span>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="user-add-staff-name" class="form-label" >关联档案：</label>
				</td>
				<td class="input">
					<input type="text" id="user-add-staff-id" name="staff_id"/>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
  </body>
</html>
