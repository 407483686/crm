<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addorder.jsp' starting page</title>
    
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
	<script type="text/javascript">
		//订单标题
		$("#order-add-title").textbox({
  			width:240,
  			height:32,
  			required:true,
  			//规定长度在2到10之间
  			validType:"length[2,20]",
  			missingMessage:"请输入订单标题",
  			invalidMessage:"订单标题长度应在2位到20位之间"
  		});
  		
  		//关联跟单
  		$("#order-add-documentary").textbox({
  			width:240,
  			height:32,
  			required:true,
  			icons : [{
  				iconCls : "icon-zoom",
  				handler : function(){
  					$("#order_documentary_dialog").dialog({
  						title : "选择关联的跟单记录",
						width : 550,
						height : 380,
						iconCls : "icon-zoom",
						modal : true,
						href : "/CRM/jsp/order/addOrderChooseDocumentary.jsp"
  					});	
  				}
  			}]
  		});
	
  		
  		//订单金额
  		$("#order-add-cost").numberbox({
  			width:140,
  			height:32,
  			required:true,
  			precision:2
  		});
  		
  		
  		//订单产品列表加载，动态datagrid
  		$("#order-add-product-button").linkbutton({
  			iconCls : "icon-add",
  			//点击弹出产品列表窗口
  			onClick : function(){
  				$("#order_products_dialog").dialog({
  					title : "选择订单的产品",
					width : 650,
					height : 380,
					iconCls : "icon-add",
					modal : true,
					href : "/CRM/jsp/order/addOrderChooseProduct.jsp"
  				});
  			}
  		});
  		
  		
  		//加载新增面板之前先给列表初始化
    	$("#order-product-list").datagrid({
    		width : "95%",
    		columns : [ [ {
    			field : 'id',
    			title : '自动编号',
    			width : 100,
    			hidden : true
    		}, {
    			field : 'sn',
    			title : '产品编号',
    			width : 100,
    		},{
    			field : 'name',
    			title : '产品名称',
    			width : 130,
    		},{
    			field : 'unit',
    			title : '计量单位',
    			width : 80,
    		},{
    			field : 'sell_price',
    			title : '出售价(元)',
    			width : 80,
    		},{
    			//数量是自定义的属性
    			field : 'number',
    			title : '数量',
    			width : 80,
    		},{
    			field : 'opt',
    			title : '操作',
    			width : 40,
    			formatter : function(value,row,index){
    				//可以写成按钮添加iconCls也可以直接引入img
    				return "<a href='javascript:void(0)'onclick=delete1(" + index + "," + row.id + "," + row.number + "," + row.sell_price + ") style='height:18px;margin-left:2px;'><img style='padding-top:5px;' src='/CRM/js/easyui/themes/icons/remove.png'/></a>";
    			}
    		}
    		]],
    		onClickCell : function(index){
    			$("#order-product-list").datagrid("selectRow",index);
    		}
    	});
    	
    	//index用于输出指定行，rowid用于ids的删除，number和sellPrice用于原始价格的计算
    	function delete1(index,rowid,number,sellPrice){
			$("#order-product-list").datagrid("deleteRow",index);
			//splice参数，删除的位置和删除的数量
			ids.splice($.inArray(rowid,ids),1);

			//这是为了解决误差
			original_price = ((original_price * 100) - (number * sellPrice * 100)) / 100;
			//计算完还是显示
			//给真正要传入数据库的原始价格参数赋值
			$("#order-original-price").val(original_price.toFixed(2));
			//让原始总价格显示在页面上
			$("#order-add-original").text('￥' + original_price.toFixed(2));
			
			//loadDat是根据传入参数重新加载列表，传入的是删除后列表剩余的所有行对象，加载后index会被重新赋值，就不会出现删除不了的情况
			$("#order-product-list").datagrid("loadData",$("#order-product-list").datagrid("getRows"));
    	}
    	
    	//检查产品是否被添加的数组
    	var ids = [];
    	//原始总价格
    	var original_price = 0;
    	//清理方法
    	function clear(){
    		//清空ids
    		ids = [];
    		//初始化原价
    		original_price = 0;
    		$("#order-add-original").text("￥0.00");
    		//清理动态的列表之前添加的行
			$("#order-product-list").datagrid("loadData",[]);
    	}
	</script>

	
    <form id="order-add" method="post">
    <input type="hidden" id="documentary_id" name="documentary_id"/>
    <input type="hidden" id="order-original-price" name="original"/>
    <input type="hidden" id="order-products" name="products"/>
	<table class="form-table" style="max-width:780px;">
		<tbody>
			<tr>
				<td class="label">
					<label for="order-add-title" class="form-label" >订单标题：</label>
				</td>
				<td class="input">
					<input type="text" id="order-add-title" name="title"/>
				</td>
				<td class="label">
					<label for="order-add-documentary" class="form-label" >关联跟单：</label>
				</td>
				<td class="input">
					<input type="text" id="order-add-documentary"/>
				</td>
			</tr>
			<tr>
				<td class="label" valign="top">
					<label for="order-add-product-button" class="form-label" >订单产品：</label>
				</td>
				<td class="input" colspan="3">
					<a id="order-add-product-button" style="width:70px;height:26px;">添加</a>
				</td>
			</tr>
			<tr>
				<td class="label">
				</td>
				<td class="input" colspan="3">
					<table id="order-product-list"></table>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="order-add-original" class="form-label" >订单原价：</label>
				</td>
				<td class="input" id="order-add-original">
					￥0.00
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="order-add-cost" class="form-label" >订单金额：</label>
				</td>
				<td class="input">
					<input type="text" id="order-add-cost" name="cost"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					订单备注：
				</td>
				<td class="input" colspan="3">
					<textarea class="textarea" name="details"></textarea>
				</td>
			</td>
		</tbody>
	</table>
	</form>
	
	<!-- 选择订单对应的跟单记录的对话框 -->
	<div id="order_documentary_dialog"></div>
	
	<!-- 选择订单包含的产品的对话框 -->
	<div id="order_products_dialog"></div>
  </body>
</html>
