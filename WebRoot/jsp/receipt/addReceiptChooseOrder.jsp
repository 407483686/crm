<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addreceiptChoose.jsp' starting page</title>
    
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
  	<script type="text/javascript" src="/CRM/js/addReceiptChooseOrder.js"></script>
  
		<!-- 数据列表上面的工具栏 -->
	  <form id="receipt_tool-order" style="padding: 5px">
	  	<div class="tool-search">
	  		<!-- 想要在下面就用另一个div换行（div是块级元素，默认占一行），然后写查询的一些东西 -->
	  		<label for="receipt-search-keywords-order">关键字：</label>
	  		<input id="receipt-search-keywords-order" type="text" class="easyui-textbox"/>
	  		<a id="receipt_tool_searchBtn-order" href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-search">查询</a>
	  		<a id="receipt_tool_undoBtn-order" style="float:right;" href="javascript:void(0)" class="easyui-linkbutton" plain=true iconCls="icon-undo">重置查询</a>
	  	</div>
	  </form>
	  
	  <!-- 加载跟单数据列表 -->
	  <div id="receipt-search-order"></div>
  </body>
</html>
