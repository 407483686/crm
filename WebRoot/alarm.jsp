<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'alarm.jsp' starting page</title>
    
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
  		<script type="text/javascript" src="js/alarm.js"></script>
  
  	  	<!-- 加载alarm数据列表 -->
    	<table id="alarm"></table>
  	
  	
  		<!-- 数据列表上面的工具栏 -->
	   <form id="alarm_tool" style="padding: 5px">
	   	<div class="tool-opt">
	   		<a id="alarm_tool_reloadBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-reload">刷新表</a>
	   		<a id="alarm_tool_undoBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-undo">重置查询</a>
	   	</div>
	   	<div class="tool-search">
	   		<!-- 想要在下面就用另一个div换行（div是块级元素，默认占一行），然后写查询的一些东西 -->
	   		<label for="alarm-search-keywords">关键字：</label>
	   		<input id="alarm-search-keywords" type="text"/>
	   		<input id="alarm-search-type" type="text"/>
	   		<input id="alarm-search-date-type" type="text"/>
	  			<input id="alarm-search-date-from" type="text"/>
	   		<label for="alarm-search-date-to">-</label>
	   		<input id="alarm-search-date-to" type="text"/>
	   		<a id="alarm_tool_searchBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-search">查询</a>
	   	</div>
	   </form>
    
    
  </body>
</html>
