<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>CRM客户关系管理系统</title>
	<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">   
	<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">   
	<link rel="stylesheet" type="text/css" href="css/main.css">
	
	<script type="text/javascript" src="js/easyui/jquery.min.js"></script>   
	<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript" src="js/echarts.min.js"></script>
  </head>
  
	<body class="easyui-layout">   
		<!-- 软件头部:Logo和登录信息，因为有分辨率关系，加上split会好一点 -->
	    <div data-options="region:'north',split:true,border:false" class="layout-north">
	    	<div class="font">
	    		CRM客户关系管理系统
	    	</div>
	    	
	    	<div class="info">
	    		您好,${user.staff.name},${user.staff.post}!
	    		<a id="main_editbBtn" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit">修改密码</a>
	    		<a id="main_exitbBtn" href="javascript:void(0)" class="easyui-linkbutton"iconCls="icon-remove">退出系统</a>
	    		<div id="main_dialog"></div>
	    	</div>
	    </div>
	    
	    <!-- 软件底部:版权声明 -->   
	    <div data-options="region:'south',split:true" class="layout-south">
	    	&copy;2020 连泽航. Powered by Java and EasyUI
	    </div>   
	    <!-- 软件左侧:软件的菜单-->
	    <div data-options="region:'west',split:true,title:'导航',iconCls:'icon-world'" class="layout-west">
	    	<div id="menu_tree" style="margin: 10px 15px"></div>
	    </div>   
	    <!-- 软件主体:显示每个选修卡内容的地方 -->
	    <div data-options="region:'center'" class="layout-center">
	    	<div id="tabs">
	    		<!-- 设置选项卡的fit和border，其实是要在tabs里面的data-options设置，因为是共有的 
	    			而如果采用js也是获取tabs然后设置，至于详细的信息就写在里面的div中，
	    			如果是add方法生成的，那么fit和这些信息的信息都可以在js里面指定
	    		-->
	    		<div data-options="title:'起始页',iconCls:'icon-house'">
	    			<p>欢迎来到CRM客户关系管理系统</p>
	    		</div>
	    	</div>
	    </div>
	    
	    <div id="youjian_menu" class="easyui-menu">
	    	<div class="closecur">关闭</div>
	    	<div class="closeall">关闭所有</div>
	    	<div class="closeother" data-options="iconCls:'icon-cross'">关闭其他所有</div>
	    </div>
	    
	</body>  
</html>
