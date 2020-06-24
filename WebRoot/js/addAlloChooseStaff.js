
$(function(){
	$("#allo-search-staff").datagrid({
		url : '/CRM/staff_loadByPage.action',
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
		toolbar : "#allo_tool-staff",
		columns : [ [ {
			field : 'id',
			title : '自动编号',
			width : 100,
			hidden : true
		},{
			field : 'number',
			title : '员工编号',
			width : 100,
		},{
			field : 'name',
			title : '员工姓名',
			width : 100,
		},{
			field : 'gender',
			title : '性别',
			width : 50,
		},{
			field : 'type',
			title : '员工类型',
			width : 80,
		},{
			field : 'select',
			title : '选择员工',
			width : 70,
			formatter : function(value,row){
				return "<a href='javascript:void(0)' rowid=" + row.id + " rowName=" + row.name + " class='selectStaff-button' style='height:18px;margin-left:5px;'>选择</a>";
			}
		}]],
		//加载完之后给formatter定义的超链接加载图标
		onLoadSuccess : function(){
			//给选择产品列添加图标
			$(".selectStaff-button").linkbutton({
				iconCls : "icon-tick",
				plain : true
			});
			//给选择产品列添加点击事件
			$(".selectStaff-button").click(function(){
				var rowId = $(this).attr("rowid");
				var rowName = $(this).attr("rowName");
				$("#staff_id").val(rowId);
				$("#allo-add-staff").textbox("setValue",rowName);
				$("#addAlloChooseStaff-dialog").dialog("close");
			});
		},
		//让点击无法选中
		onClickCell : function(index){
			$("#allo-search-staff").datagrid("selectRow",index);										
		}
	});
		
	// 给工具条的重置查询按钮绑定点击事件
	$("#allo_tool_undoBtn-staff").click(
			function() {
				// 清空关键字
				$("#allo-search-keywords-staff").textbox("clear");
				// 利用jquery的函数触发查询按钮的点击事件
				$('#allo_tool_searchBtn-staff').trigger('click');
	});
	
	
	//设置关键字
	$("#allo-search-keywords-staff").textbox({
		prompt : "员工姓名|员工编号|电话"
	});
	
	// 设置查询按钮
	$("#allo_tool_searchBtn-staff").click(
		function() {
			if ($("#allo_tool-staff").form("validate")) {
				$("#allo-search-staff").datagrid(
					"load",
					{
						// datagrid的load方法通常用于额外传递参数进行查询
						//这里虽然只有根据关键字进行查询，但是其他不写有可能会空指针
						"queryCriteria.keywords" : $("#allo-search-keywords-staff").textbox("getValue"),
						"queryCriteria.dateType" : "",
						"queryCriteria.dateFrom" : "",
						"queryCriteria.dateTo" : "",
						"queryCriteria.post" : "",
						"queryCriteria.nation" : "",
						"queryCriteria.marital_status" : "",
						"queryCriteria.id_card" : "",
						"queryCriteria.gender" : "",
						"queryCriteria.entry_status" : "",
					});
			}
	});
});
