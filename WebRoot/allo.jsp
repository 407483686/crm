<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'allo.jsp' starting page</title>
    
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
    <script type="text/javascript" src="js/allo.js"></script>
  
 	<!-- 加载allo数据列表 -->
    <table id="allo"></table>
  	
  	
  	<!-- 数据列表上面的工具栏 -->
    <form id="allo_tool" style="padding: 5px">
    	<div class="tool-opt">
    		<a id="allo_tool_addBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-add">增加</a>
    		<a id="allo_tool_editBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-edit">查看结果</a>
    		<a id="allo_tool_removeBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-remove">作废</a>
    		<a id="allo_tool_reloadBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-reload">刷新表</a>
    		<a id="allo_tool_redoBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-redo">选择其他</a>
    		<a id="allo_tool_undoBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-undo">重置查询</a>
    	</div>
    	<div class="tool-search">
    		<!-- 想要在下面就用另一个div换行（div是块级元素，默认占一行），然后写查询的一些东西 -->
    		<label for="allo-search-keywords">关键字：</label>
    		<input id="allo-search-keywords" type="text"/>
    		<input id="allo-search-state" type="text"/>
    		<input id="allo-search-type" type="text"/>
    		<input id="allo-search-date-type" type="text"/>
   			<input id="allo-search-date-from" type="text"/>
    		<label for="allo-search-date-to">-</label>
    		<input id="allo-search-date-to" type="text"/>
    		<a id="allo_tool_searchBtn" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-search">查询</a>
    	</div>
    </form>
    
    
    <!-- 产品信息页面的对话框 -->
    <div id="allo_dialog"></div> 
    <div id="allo_edit_dialog"></div>
    
  </body>
</html>
