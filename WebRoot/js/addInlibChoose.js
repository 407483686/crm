
$(function(){
	$("#inlib-search-product").datagrid({
		url : '/CRM/product_loadByPage.action',
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
		toolbar : "#inlib_tool-product",
		columns : [ [ {
			field : 'sn',
			title : '产品编号',
			width : 50,
		},{
			field : 'name',
			title : '产品名称',
			width : 120,
		},{
			field : 'unit',
			title : '计量单位',
			width : 60,
		},{
			field : 'pro_price',
			title : '采购价格(元)',
			width : 70,
		},{
			field : 'select',
			title : '选择产品',
			width : 70,
			formatter : function(value,row){
				return "<a href='javascript:void(0)' rowid=" + row.id + " rowName=" + row.name + " class='selectProduct-button' style='height:18px;margin-left:5px;'>选择</a>";
			}
		},{
			field : 'create_time',
			title : '创建时间',
			width : 100,
			hidden : true
		}]],
		//加载完之后给formatter定义的超链接加载图标
		onLoadSuccess : function(){
			//给选择产品列添加图标
			$(".selectProduct-button").linkbutton({
				iconCls : "icon-tick",
				plain : true
			});
			//给选择产品列添加点击事件
			$(".selectProduct-button").click(function(){
				var rowId = $(this).attr("rowid");
				var rowName = $(this).attr("rowName");
				$("#inlib-add-product-id").val(rowId);
				$("#inlib-add-product").textbox("setValue",rowName);
				$("#inlib-product-dialog").dialog("close");
			});
		},
		//让点击无法选中
		onClickCell : function(index){
			$("#inlib-search-product").datagrid("selectRow",index);										
		}
	});
		
	// 给工具条的重置查询按钮绑定点击事件
	$("#inlib_tool_undoBtn-product").click(
			function() {
				// 清空关键字
				$("#inlib-search-keywords-product").textbox("clear");
				// 利用jquery的函数触发查询按钮的点击事件
				$('#inlib_tool_searchBtn-product').trigger('click');
	});
	
	
	//设置关键字
	$("#inlib-search-keywords-product").textbox({
		prompt : "产品名称|编号"
	});
	
	// 设置查询按钮
	$("#inlib_tool_searchBtn-product").click(
		function() {
			if ($("#inlib_tool-product").form("validate")) {
				$("#inlib-search-product").datagrid(
					"load",
					{
						// datagrid的load方法通常用于额外传递参数进行查询
						//这里虽然只有根据关键字进行查询，但是其他不写有可能会空指针
						"queryCriteria.keywords" : $("#inlib-search-keywords-product").textbox("getValue"),
						"queryCriteria.type" : "",
						"queryCriteria.dateType" : "",
						"queryCriteria.dateFrom" : "",
						"queryCriteria.dateTo" : "",
					});
			}
	});
});
