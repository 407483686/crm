<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addPost.jsp' starting page</title>
    
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
  		//入库产品
  		$("#inlib-add-product").textbox({
  			width:240,
  			height:32,
  			editable:false,
  			icons:[{
  				iconCls : "icon-zoom",
  				handler : function(){
					$("#inlib-product-dialog").dialog({
						title : "选择产品",
						width : 550,
						height : 380,
						iconCls : "icon-zoom",
						modal : true,
						href : "/CRM/jsp/inlib/addInlibChoose.jsp"
					});
  				}
  			}],
  			required:true,
  			missingMessage:"请点击放大镜图标选中产品",
  			invalidMessage:"产品不得为空"
  		});
  		
  		//入库数量
  		$("#inlib-add-productCount").numberbox({
  			width:240,
  			height:32,
  			required:true,
  			invalidMessage:"入库数量不得为空"
  		});
  		
  		//经办人
  		$("#inlib-add-productMan").textbox({
  			width:240,
  			height:32,
  			required:true,
  			invalidMessage:"经办人不得为空",
  			icons : [{
  				iconCls : "icon-zoom",
  				handler : function(){
  					$("#inlib-productMan-dialog").dialog({
  						title : "选择经办人",
						width : 550,
						height : 380,
						iconCls : "icon-zoom",
						modal : true,
						href : "/CRM/jsp/inlib/addInlibChooseMan.jsp"
  					});
  				}
  			}]
  		});
  		
  		
  		//入库方式
  		$("#inlib-add-productWay").combobox({
  			width : 70,
			editable : false,
			data : [{
				text : "采购",
				value : "采购"
			},{
				text : "退货",
				value : "退货"
			}],
			// 基础数据值对应的名称
			valueField : "value",
			// 基础数据字段对应的名称
			textField : "text",
			// 下拉面板的高度
			panelHeight : "auto",
  		});
  		
  		//方式说明
  		$("#inlib-add-productWayDetails").textbox({
  			width:240,
  			height:32,
  			prompt : "如，从哪里采购或退货（20字内）"
  		});
  	</script>
  
  	<form id="inlib-add" method="post">
  	<input type="hidden" id="inlib-add-product-id" name="product_id"/>
	<table class="form-table">
		<tbody>
			<tr>
				<td class="label">
					<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 -->
					<label for="inlib-add-product" class="form-label" >入库产品：</label>
				</td>
				<td class="input">
					<!-- 这个入库产品的名字似乎只是用来看的，而不用传到后台，后台只需要一个id -->
					<input type="text" id="inlib-add-product" name=""/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 -->
					<label for="inlib-add-productCount" class="form-label" >入库数量：</label>
				</td>
				<td class="input">
					<input type="text" id="inlib-add-productCount" name="number"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 -->
					<label for="inlib-add-productMan" class="form-label" >经办人：</label>
				</td>
				<td class="input">
					<input type="text" id="inlib-add-productMan" name="staff_name"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 -->
					<label for="inlib-add-productWay" class="form-label" >入库方式：</label>
				</td>
				<td class="input">
					<input type="text" id="inlib-add-productWay" name="mode"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 -->
					<label for="inlib-add-productWayDetails" class="form-label" >方式说明：</label>
				</td>
				<td class="input">
					<input type="text" id="inlib-add-productWayDetails" name="mode_explain"/>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
	
	<!-- 选择产品的对话框 -->
	<div id="inlib-product-dialog"></div>
	
	<!-- 选择经办人的对话框 -->
	<div id="inlib-productMan-dialog"></div>
  </body>
</html>
