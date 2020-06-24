<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'staff.jsp' starting page</title>
    
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
  	
  
  	<script type="text/javascript" src="js/staff.js"></script>
  	<script type="text/javascript" src="js/kindeditor/kindeditor-all-min.js"></script>
  	<script type="text/javascript" src="js/kindeditor/lang/zh_CN.js"></script>
  	
  	<!-- 加载staff数据列表 -->
    <table id="staff"></table>
    
    <!-- 数据列表上面的工具栏 -->
    <form id="staff_tool" style="padding: 5px">
    	<div class="tool-opt">
    		<a id="staff_tool_addBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-add">增加</a>
    		<a id="staff_tool_editBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-edit">修改</a>
    		<a id="staff_tool_removeBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-remove">删除</a>
    		<a id="staff_tool_reloadBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-reload">刷新表</a>
    		<a id="staff_tool_redoBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-redo">选择其他</a>
    		<a id="staff_tool_fieldBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-addsearch">展开查询字段</a>
    		<a id="staff_tool_undoBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-undo">重置查询</a>
    	</div>
    	<div class="tool-search">
    		<!-- 想要在下面就用另一个div换行（div是块级元素，默认占一行），然后写查询的一些东西 -->
    		<label for="staff-search-keywords">关键字：</label>
    		<input id="staff-search-keywords" type="text"/>
    		<input id="staff-search-post" type="text"/>
    		<input id="staff-search-entrystatus" type="text"/>
    		<input id="staff-search-date-type" type="text"/>
   			<input id="staff-search-date-from" type="text"/>
    		<label for="staff-search-date-to">-</label>
    		<input id="staff-search-date-to" type="text"/>
    		<a id="staff_tool_searchBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-search">查询</a>
    	</div>
    	<!-- 这和class和上面的class一样，都用在设置样式，边距,因为这两个需要的边距和上面不一样，而且需要设置默认是display:none，所以需要重新设置class-->
    	<div class="more">
    		<input type="text" id="staff-search-gender">
    		<input type="text" id="staff-search-marital-status"> 
    	</div>
    	<div class="more">
    		<input type="text" id="staff-search-id-card">
    		<input type="text" id="staff-search-nation">
    	</div>
    </form>
    
    <!-- 员工档案页面的对话框 -->
    <div id="staff_dialog"></div> 
    
    <!-- 员工档案页面的对话框 -->
    <div id="staff_edit_dialog"></div> 
    
    <!-- 详情页对话框 -->
    <div id="details_dialog"></div>
  </body>
</html>
