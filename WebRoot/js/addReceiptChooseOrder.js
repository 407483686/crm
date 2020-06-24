
$(function(){
	$("#receipt-search-order").datagrid({
		url : '/CRM/order_loadByPage.action',
		striped : true,
		fitColumns : true,
		rownumbers : true,
		border : false,
		// 分页工具栏
		pagination : true,
		queryParams : {
			//只显示未支付的订单
			zhiFu : false
		},
		// 设置初始页面记录数，不设置的话初始是10
		pageSize : 10,
		// 设置页面分页选项
		pageList : [ 10, 20, 30, 40, 50 ],
		toolbar : "#receipt_tool-order",
		columns : [ [ {
			field : 'sn',
			title : '订单编号',
			width : 100,
		},{
			field : 'title',
			title : '订单标题',
			width : 160,
		},{
			field : 'cost',
			title : '订单报价(元)',
			width : 80,
		},{
			field : 'select',
			title : '选择订单',
			width : 70,
			formatter : function(value,row){
				return "<a href='javascript:void(0)'rowSn=" + row.sn + " rowTitle=" + row.title + " rowCost=" + row.cost + " class='selectorder-button' style='height:18px;margin-left:5px;'>选择</a>";
			}
		}]],
		//加载完之后给formatter定义的超链接加载图标
		onLoadSuccess : function(){
			//给选择产品列添加图标
			$(".selectorder-button").linkbutton({
				iconCls : "icon-tick",
				plain : true
			});
			//给选择产品列添加点击事件
			$(".selectorder-button").click(function(){
				var rowSn = $(this).attr("rowSn");
				var rowTitle = $(this).attr("rowTitle");
				var rowCost = $(this).attr("rowCost");
				$("#receipt-add-order-sn").val(rowSn);
				$("#receipt-add-order-title").textbox("setValue",rowTitle);
				$("#receipt-add-order-cost").text("￥" + rowCost);
				$("#receipt-add-chooseOrder-dialog").dialog("close");
			});
		},
		//让点击无法选中
		onClickCell : function(index){
			$("#receipt-search-order").datagrid("selectRow",index);										
		}
	});
		
	// 给工具条的重置查询按钮绑定点击事件
	$("#receipt_tool_undoBtn-order").click(
			function() {
				// 清空关键字
				$("#receipt-search-keywords-order").textbox("clear");
				// 利用jquery的函数触发查询按钮的点击事件
				$('#receipt_tool_searchBtn-order').trigger('click');
	});
	
	
	//设置关键字
	$("#receipt-search-keywords-order").textbox({
		prompt : "订单编号|订单标题"
	});
	
	// 设置查询按钮
	$("#receipt_tool_searchBtn-order").click(
		function() {
			if ($("#receipt_tool-order").form("validate")) {
				$("#receipt-search-order").datagrid(
					"load",
					{
						// datagrid的load方法通常用于额外传递参数进行查询
						//这里虽然只有根据关键字进行查询，但是其他不写有可能会空指针
						"queryCriteria.keywords" : $("#receipt-search-keywords-order").textbox("getValue"),
						"queryCriteria.dateType" : "",
						"queryCriteria.dateFrom" : "",
						"queryCriteria.dateTo" : "",
						"zhiFu" : false
					});
			}
	});
});
