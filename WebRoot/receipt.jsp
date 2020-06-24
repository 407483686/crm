<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'receipt.jsp' starting page</title>
    
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
		<script type="text/javascript" src="js/receipt.js"></script>
  	
  	<!-- receipt数据列表 -->
    <table id="receipt"></table>
    
    <!-- 定义一个工具条 -->
    <form id="receipt_tool" style="padding: 5px">
    	<div class="tool-opt">
    		<a id="receipt_tool_addBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-add">增加</a>
    		<a id="receipt_tool_reloadBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-reload">刷新表</a>
    		<a id="receipt_tool_redoBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-redo">选择其他</a>
    		<a id="receipt_tool_undoBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-undo">重置查询</a>
    	</div>
    	<div class="tool-search">
    		<!-- 这下面用另一个div换行（div是块级元素，默认占一行），然后写查询的一些东西 -->
    		<label for="receipt-search-keywords">关键字：</label>
    		<input id="receipt-search-keywords" type="text"/>
    		<input id="receipt-search-date-type" type="text"/>
   			<input id="receipt-search-date-from" type="text"/>
    		<label for="receipt-search-date-to">-</label>
    		<input id="receipt-search-date-to" type="text"/>
    		<a id="receipt_tool_searchBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-search">查询</a>
    	</div>
    </form>
    
    <!-- 对话框 -->
    <div id="receipt_dialog"></div>  
  </body>
</html>
