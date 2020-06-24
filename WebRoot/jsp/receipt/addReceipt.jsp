<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addreceipt.jsp' starting page</title>
    
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
  	
  		$("#receipt-add-title").textbox({
  			width:240,
  			height:32,
  			required:true,
  			missingMessage:"请输入收款标题",
  			validType:"length[2,20]",
  			missingMessage:"收款标题长度在2到20之间"
  		});
  		
  		$("#receipt-add-order-title").textbox({
  			width:240,
  			height:32,
  			required:true,
  			missingMessage:"请点击右侧放大镜选择收款订单",
  			icons : [{
		        iconCls:'icon-zoom',
			    handler: function(){
			        $("#receipt-add-chooseOrder-dialog").dialog({
			        	title : "选择收款的订单",
						width : 650,
						height : 380,
						iconCls : "icon-add",
						modal : true,
						href : "/CRM/jsp/receipt/addReceiptChooseOrder.jsp"
			        });
			    }
	  		}]
	  	});
  		
  		$("#receipt-add-cost").numberbox({
  			width:240,
  			height:32,
  			required:true,
  			precision:2,
  			missingMessage:"请输入收款金额",
  		});
  		
  		
  		$("#receipt-add-remark").textbox({
  			width:240,
  			height:32,
  			prompt:"20字以内备注",
  			validType:"length[0,20]",
  			invalidMessage:"备注只能在20字之内"
  		});
  	</script>
  
  	
  
  
  	<form id="receipt-add" method="post">
  	<input type="hidden" id="receipt-add-order-sn" name="order_sn"/>
	<table class="form-table">
		<tbody>
			<tr>
				<td class="label">
					<label for="receipt-add-title" class="form-label" >收款标题：</label>
				</td>
				<td class="input">
					<input type="text" id="receipt-add-title" name="title"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="receipt-add-order-title" class="form-label" >收款订单：</label>
				</td>
				<td class="input">
					<!-- 实际有关的输入项是上面的订单编号 -->
					<input type="text" id="receipt-add-order-title"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="receipt-add-order-cost" class="form-label" >订单报价：</label>
				</td>
				<td class="input" id="receipt-add-order-cost">
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="receipt-add-cost" class="form-label" >收款金额：</label>
				</td>
				<td class="input">
					<input type="text" id="receipt-add-cost" name="cost"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="receipt-add-remark" class="form-label" >收款备注：</label>
				</td>
				<td class="input">
					<input type="text" id="receipt-add-remark" name="remark"/>
				</td>
			</tr>
		</tbody>
	</table>
	
	<div id="receipt-add-chooseOrder-dialog"></div>
  </form>
  </body>
</html>
