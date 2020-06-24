<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adduser.jsp' starting page</title>
    
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
  
  	<script type="text/javascript">
  		//把文本框设置为easyUI自带的文本框
  		$("#user-edit-account").textbox({
  			width:220,
  			height:32,
  			//不可以修改用户名，只能修改密码
  			editable:false
  		});
  		
  		$("#user-edit-password").textbox({
  			width:220,
  			height:32,
  			//没有加非空是因为有的人不想修改密码，只想修改状态
  			//当没有加非空，只加了长度验证时，用户没有输入内容的话，就不会开启长度验证
  			//规定长度在6到30之间
  			validType:"length[6,30]",
  			invalidMessage:"密码长度应在6位到30位之间"
  		});
  		
  		//span元素也有点击事件，设置生成随机密码
  		$(".rand-edit").click(function(){
  			$("#user-edit-password").textbox("setValue",getRandPassword(8,16));
  		});
  		
  		//设置状态按钮
  		$("#user-edit-state-button").switchbutton({
  			width : 65,
  			onText : "正常",
  			offText : "冻结",
  			onChange : function(checked){
  				//当状态按钮被改变时
  				if(checked){
  					$("#user-edit-state").val("正常");
  				}else{
  					$("#user-edit-state").val("冻结");
  				}
  			}
  		});
  		
  		
  		//修改关联档案
  		$("#user-edit-staff-id").combogrid({
  			width : 120,
  			height : 32,
  			editable :false,
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
	  			$("#user-edit-staff-id").combogrid("grid").datagrid("reload");
	  		}
	  	});
  	</script>
  
  	<form id="user-edit" method="post">
  	<!-- 存储用户id -->
  	<input type="hidden" name="id">
  	
	<table class="form-table">
		<tbody>
			<tr>
				<td class="label">
					<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 -->
					<label for="user-edit-name" class="form-label" >用户名：</label>
				</td>
				<td class="input">
					<input type="text" id="user-edit-account" name="accounts"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 -->
					<label for="user-edit-password" class="form-label" >密码：</label>
				</td>
				<td class="input">
					<input type="text" id="user-edit-password" name="password"/>
					<span class="link rand-edit">生成</span>
				</td>
			</tr>
			<tr>
				<td class="label">
					<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 -->
					<label for="user-edit-state-button" class="form-label" >状态：</label>
				</td>
				<td class="input">
					<input id="user-edit-state-button"/>
					<input type="hidden" id="user-edit-state" name="state">
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="user-edit-staff-id" class="form-label" >关联档案：</label>
				</td>
				<td class="input">
					<input type="text" id="user-edit-staff-id" name="staff_id"/>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
  </body>
</html>
