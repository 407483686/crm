<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addalloChoose.jsp' starting page</title>
    
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
  	<script type="text/javascript" src="/CRM/js/addAlloChooseStaff.js"></script>
  
		<!-- 数据列表上面的工具栏 -->
	  <form id="allo_tool-staff" style="padding: 5px">
	  	<div class="tool-search">
	  		<!-- 想要在下面就用另一个div换行（div是块级元素，默认占一行），然后写查询的一些东西 -->
	  		<label for="allo-search-keywords-staff">关键字：</label>
	  		<input id="allo-search-keywords-staff" type="text" class="easyui-textbox"/>
	  		<a id="allo_tool_searchBtn-staff" href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-search">查询</a>
	  		<a id="allo_tool_undoBtn-staff" style="float:right;" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-undo">重置查询</a>
	  	</div>
	  </form>
	  
	  <!-- 加载客户数据列表 -->
	  <div id="allo-search-staff"></div>
  </body>
</html>
