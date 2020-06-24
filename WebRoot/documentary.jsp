<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'documentary.jsp' starting page</title>
    
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
  
  	<script type="text/javascript" src="js/documentary.js"></script>
  
  	  	<!-- 加载documentary数据列表 -->
    	<table id="documentary"></table>
  	
  	
  		<!-- 数据列表上面的工具栏 -->
    <form id="documentary_tool" style="padding: 5px">
    	<div class="tool-opt">
    		<a id="documentary_tool_addBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-add">增加</a>
    		<a id="documentary_tool_editBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-edit">修改</a>
    		<a id="documentary_tool_removeBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-remove">删除</a>
    		<a id="documentary_tool_reloadBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-reload">刷新表</a>
    		<a id="documentary_tool_redoBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-redo">选择其他</a>
    		<a id="documentary_tool_undoBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-undo">重置查询</a>
    	</div>
    	<div class="tool-search">
    		<!-- 想要在下面就用另一个div换行（div是块级元素，默认占一行），然后写查询的一些东西 -->
    		<label for="documentary-search-keywords">关键字：</label>
    		<input id="documentary-search-keywords" type="text"/>
    		<input id="documentary-search-date-type" type="text"/>
   			<input id="documentary-search-date-from" type="text"/>
    		<label for="documentary-search-date-to">-</label>
    		<input id="documentary-search-date-to" type="text"/>
    		<a id="documentary_tool_searchBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-search">查询</a>
    	</div>
    </form>
    
    
    <!-- 跟单记录页面的对话框 -->
    <div id="documentary_dialog"></div> 
    
  </body>
</html>
