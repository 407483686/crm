
$(function(){
	$("#order-search-documentary").datagrid({
		url : '/CRM/documentary_loadByPage.action',
		queryParams : {
			tanPan : true
		},
		striped : true,
		fitColumns : true,
		rownumbers : true,
		border : false,
		// 分页工具栏
		pagination : true,
		// 设置初始页面记录数，不设置的话初始是10
		pageSize : 10,
		// 设置页面分页选项
		pageList : [ 10, 20, 30, 40, 50 ],
		toolbar : "#order_tool-documentary",
		columns : [ [ {
			field : 'sn',
			title : '跟单编号',
			width : 100,
		},{
			field : 'title',
			title : '跟单标题',
			width : 80,
		},{
			field : 'company',
			title : '所属公司',
			width : 120,
		},{
			field : 'staff_name',
			title : '跟单员',
			width : 120,
		},{
			field : 'select',
			title : '选择跟单',
			width : 70,
			formatter : function(value,row){
				return "<a href='javascript:void(0)' rowid=" + row.id + " rowTitle=" + row.title + " class='selectdocumentary-button' style='height:18px;margin-left:5px;'>选择</a>";
			}
		}]],
		//加载完之后给formatter定义的超链接加载图标
		onLoadSuccess : function(){
			//给选择产品列添加图标
			$(".selectdocumentary-button").linkbutton({
				iconCls : "icon-tick",
				plain : true
			});
			//给选择产品列添加点击事件
			$(".selectdocumentary-button").click(function(){
				var rowId = $(this).attr("rowid");
				var rowName = $(this).attr("rowTitle");
				$("#order-add-documentary").textbox("setValue",rowName);
				$("#documentary_id").val(rowId);
				$("#order_documentary_dialog").dialog("close");
			});
		},
		//让点击无法选中
		onClickCell : function(index){
			$("#order-search-documentary").datagrid("selectRow",index);										
		}
	});
		
	// 给工具条的重置查询按钮绑定点击事件
	$("#order_tool_undoBtn-documentary").click(
			function() {
				// 清空关键字
				$("#order-search-keywords-documentary").textbox("clear");
				// 利用jquery的函数触发查询按钮的点击事件
				$('#order_tool_searchBtn-documentary').trigger('click');
	});
	
	
	//设置关键字
	$("#order-search-keywords-documentary").textbox({
		prompt : "标题|公司名称|跟单员"
	});
	
	// 设置查询按钮
	$("#order_tool_searchBtn-documentary").click(
		function() {
			if ($("#order_tool-documentary").form("validate")) {
				$("#order-search-documentary").datagrid(
					"load",
					{
						// datagrid的load方法通常用于额外传递参数进行查询
						//这里虽然只有根据关键字进行查询，但是其他不写有可能会空指针
						"queryCriteria.keywords" : $("#order-search-keywords-documentary").textbox("getValue"),
						"queryCriteria.dateType" : "",
						"queryCriteria.dateFrom" : "",
						"queryCriteria.dateTo" : "",
						"tanPan" : true
					});
			}
	});
});
