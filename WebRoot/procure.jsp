<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'procure.jsp' starting page</title>
    
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
  		<!-- 采购记录模块只是单纯地把入库中所有采购的记录加载过来，既然如此，为什么还需要分离出一个采购模块呢？
  			因为入库记录里面，不仅有采购的记录，还可能有其他的比如退货什么的，而且查看采购记录一般来说也是需要经常用到的功能，
  			总之，一切都看需求分析
  		 -->
  		<script type="text/javascript" src="js/procure.js"></script>
  
		<!-- 加载procure数据列表 -->
    	<table id="procure"></table>
    	
    	<!-- 数据列表上面的工具栏 -->
	   <form id="procure_tool" style="padding: 5px">
	   	<div class="tool-opt">
	   		<a id="procure_tool_reloadBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-reload">刷新表</a>
	   		<a id="procure_tool_undoBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-undo">重置查询</a>
	   	</div>
	   	<div class="tool-search">
	   		<!-- 想要在下面就用另一个div换行（div是块级元素，默认占一行），然后写查询的一些东西 -->
	   		<label for="procure-search-keywords">关键字：</label>
	   		<input id="procure-search-keywords" type="text"/>
	   		<input id="procure-search-date-type" type="text"/>
	  		<input id="procure-search-date-from" type="text"/>
	   		<label for="procure-search-date-to">-</label>
	   		<input id="procure-search-date-to" type="text"/>
	   		<a id="procure_tool_searchBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-search">查询</a>
	   	</div>
	   </form>
  </body>
</html>
