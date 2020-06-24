<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addProduct.jsp' starting page</title>
    
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
		$("#product-add-name").textbox({
  			width:240,
  			height:32,
  			required:true,
  			//规定长度在2到10之间
  			validType:"length[2,20]",
  			missingMessage:"请输入产品名称",
  			invalidMessage:"产品名称长度应在2位到20位之间"
  		});
  		
  		//产品编号
  		$("#product-add-sn").textbox({
  			width:240,
  			height:32,
  			required:true,
  			validType:"sn"
  		});
	
		//产品类型
  		$("#product-add-type").combobox({
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
  		});
  		
  		//销售价格
  		$("#product-sell_price").numberbox({
  			width:140,
  			height:32,
  		});
  		
  		//计量单位
  		$("#product-add-unit").textbox({
  			width:140,
  			height:32,
  		});
  		
  		//库存警报
  		$("#product-inventory_alarm").numberbox({
  			width:140,
  			height:32,
  		});
	
		//详情,编辑器的宽度，可以设置px或%，比textarea输入框样式表宽度优先度高。
  		PRODUCT_ADD = KindEditor.create("#product-add-details",{
  			width : "91%",
  			height : "200px",
  			//resizeType: 2或1或0，2时可以拖动改变宽度和高度，1时只能改变高度，0时不能拖动。
  			resizeType : 0,
  			//items : 配置编辑器的工具栏，其中”/”表示换行，”|”表示分隔符。
  			//editor_tool在index.js中定义好了
  			items : editor_tool
  		});
  		
  		// 拓展产品编号验证功能
		$.extend($.fn.validatebox.defaults.rules, {
			sn : {
				validator : function(value) {
					return /^[0-9]{5}$/.test(value);
				},
				message : "产品编号必须是5位"
			}
		});
	</script>

	
    <form id="product-add" method="post">
	<table class="form-table" style="max-width:780px;">
		<tbody>
			<tr>
				<td class="label">
					<label for="product-add-name" class="form-label" >产品名称：</label>
				</td>
				<td class="input">
					<input type="text" id="product-add-name" name="name"/>
				</td>
				<td class="label">
					<label for="product-add-sn" class="form-label" >产品编号：</label>
				</td>
				<td class="input">
					<input type="text" id="product-add-sn"  name="sn"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="product-add-type" class="form-label" >产品类型：</label>
				</td>
				<td class="input">
					<input type="text" id="product-add-type" name="type"/>
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
					<label for="product-add-sell_price" class="form-label" >销售价格：</label>
				</td>
				<td class="input">
					<input type="text" id="product-sell_price" name="sell_price"/>
				</td>
				<td class="label">
					<label for="product-add-unit" class="form-label" >计量单位：</label>
				</td>
				<td class="input">
					<input type="text" id="product-add-unit"  name="unit"/>
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
					<textarea class="textarea" id="product-add-details" name="details"></textarea>
				</td>
			</td>
		</tbody>
	</table>
	</form>
  </body>
</html>
