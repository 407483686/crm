<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'editProduct.jsp' starting page</title>
    
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
		//产品名称
		$("#product-edit-name").textbox({
  			width:240,
  			height:32,
  			editable:false,
  			required:true,
  		});
  		
  		//产品编号
  		$("#product-edit-sn").textbox({
  			width:240,
  			height:32,
  			editable:false,
  		});
	
		//产品类型
  		$("#product-edit-type").combobox({
			width : 140,
			height:32,
			data : [{
				value : "办公耗材",
				text : "办公耗材"
			},{
				value : "数码产品",
				text : "数码产品"
			}],
			editable : false,
			// 基础数据值对应的名称
			valueField : "value",
			// 基础数据字段对应的名称
			textField : "text",
			// 下拉面板的高度
			panelHeight : "auto",
  		});
  		
  		
  		//采购价格
  		$("#product-pro_price").numberbox({
  			width:140,
  			height:32,
  			precision : "2"
  		});
  		
  		//销售价格
  		$("#product-sell_price").numberbox({
  			width:140,
  			height:32,
  			precision:"2"
  		});
  		
  		//计量单位
  		$("#product-edit-unit").textbox({
  			width:140,
  			height:32,
  		});
  		
  		//库存警报
  		$("#product-inventory_alarm").numberbox({
  			width:140,
  			height:32,
  		});
	
		//详情,编辑器的宽度，可以设置px或%，比textarea输入框样式表宽度优先度高。
  		PRODUCT_EDIT = KindEditor.create("#product-edit-details",{
  			width : "91%",
  			height : "200px",
  			//resizeType: 2或1或0，2时可以拖动改变宽度和高度，1时只能改变高度，0时不能拖动。
  			resizeType : 0,
  			//items : 配置编辑器的工具栏，其中”/”表示换行，”|”表示分隔符。
  			//editor_tool在index.js中定义好了
  			items : editor_tool
  		});
  		
	</script>

	
    <form id="product-edit" method="post">
    <input type="hidden" name="id"/>
	<table class="form-table" style="max-width:780px;">
		<tbody>
			<tr>
				<td class="label">
					<label for="product-edit-name" class="form-label" >产品名称：</label>
				</td>
				<td class="input">
					<input type="text" id="product-edit-name" name="name"/>
				</td>
				<td class="label">
					<label for="product-edit-sn" class="form-label" >产品编号：</label>
				</td>
				<td class="input">
					<input type="text" id="product-edit-sn"  name="sn"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="product-edit-type" class="form-label" >产品类型：</label>
				</td>
				<td class="input">
					<input type="text" id="product-edit-type" name="type"/>
				</td>
				<td class="label">
					<label for="product-pro_price" class="form-label" >采购价格：</label>
				</td>
				<td class="input">
					<input type="text" id="product-pro_price"  name="pro_price"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="product-edit-sell_price" class="form-label" >销售价格：</label>
				</td>
				<td class="input">
					<input type="text" id="product-sell_price" name="sell_price"/>
				</td>
				<td class="label">
					<label for="product-edit-unit" class="form-label" >计量单位：</label>
				</td>
				<td class="input">
					<input type="text" id="product-edit-unit"  name="unit"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="product-inventory_alarm" class="form-label" >库存警报：</label>
				</td>
				<td class="input">
					<input type="text" id="product-inventory_alarm" name="inventory_alarm"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					详情备注：
				</td>
				<td class="input" colspan="3">
					<textarea class="textarea" id="product-edit-details" name="details"></textarea>
				</td>
			</td>
		</tbody>
	</table>
	</form>
  </body>
</html>
