
$(function(){
	$("#documentary-search-client").datagrid({
		url : '/CRM/client_loadByPage.action',
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
		toolbar : "#documentary_tool-client",
		columns : [ [ {
			field : 'company',
			title : '公司名称',
			width : 100,
		},{
			field : 'name',
			title : '联系人',
			width : 80,
		},{
			field : 'tel',
			title : '移动电话',
			width : 120,
		},{
			field : 'select',
			title : '选择客户',
			width : 70,
			formatter : function(value,row){
				return "<a href='javascript:void(0)' rowid=" + row.id + " rowName=" + row.company + " class='selectclient-button' style='height:18px;margin-left:5px;'>选择</a>";
			}
		}]],
		//加载完之后给formatter定义的超链接加载图标
		onLoadSuccess : function(){
			//给选择产品列添加图标
			$(".selectclient-button").linkbutton({
				iconCls : "icon-tick",
				plain : true
			});
			//给选择产品列添加点击事件
			$(".selectclient-button").click(function(){
				var rowId = $(this).attr("rowid");
				var rowName = $(this).attr("rowName");
				$("#documentary-add-company").textbox("setValue",rowName);
				$("#documentary-add-chooseCompany-dialog").dialog("close");
			});
		},
		//让点击无法选中
		onClickCell : function(index){
			$("#documentary-search-client").datagrid("selectRow",index);										
		}
	});
		
	// 给工具条的重置查询按钮绑定点击事件
	$("#documentary_tool_undoBtn-client").click(
			function() {
				// 清空关键字
				$("#documentary-search-keywords-client").textbox("clear");
				// 利用jquery的函数触发查询按钮的点击事件
				$('#documentary_tool_searchBtn-client').trigger('click');
	});
	
	
	//设置关键字
	$("#documentary-search-keywords-client").textbox({
		prompt : "公司名|联系人|电话"
	});
	
	// 设置查询按钮
	$("#documentary_tool_searchBtn-client").click(
		function() {
			if ($("#documentary_tool-client").form("validate")) {
				$("#documentary-search-client").datagrid(
					"load",
					{
						// datagrid的load方法通常用于额外传递参数进行查询
						//这里虽然只有根据关键字进行查询，但是其他不写有可能会空指针
						"queryCriteria.keywords" : $("#documentary-search-keywords-client").textbox("getValue"),
						"queryCriteria.dateType" : "",
						"queryCriteria.dateFrom" : "",
						"queryCriteria.dateTo" : "",
					});
			}
	});
});
