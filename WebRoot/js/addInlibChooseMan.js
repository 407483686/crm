
$(function(){
	$("#inlib-search-productMan").datagrid({
		url : '/CRM/staff_loadByPage.action',
		striped : true,
		fitColumns : true,
		rownumbers : true,
		border : false,
		// 分页工具栏
		pagination : true,
		// 设置初始页面记录数，不设置的话初始是10
		pageSize : 7,
		// 设置页面分页选项
		pageList : [ 7, 10, 20, 30, 40 ],
		toolbar : "#inlib_tool-productMan",
		columns : [ [ {
			field : 'number',
			title : '员工编号',
			width : 50,
		},{
			field : 'name',
			title : '员工姓名',
			width : 70,
		},{
			field : 'tel',
			title : '员工手机',
			width : 100,
		},{
			field : 'gender',
			title : '性别',
			width : 30,
		},{
			field : 'type',
			title : '员工类型',
			width : 70,
		},{
			field : 'select',
			title : '选择员工',
			width : 70,
			formatter : function(value,row){
				return "<a href='javascript:void(0)'rowid=" + row.id + " rowName=" + row.name + " class='selectMan-button' style='height:18px;margin-left:5px;'>选择</a>";
			}
		},{
			field : 'create_time',
			title : '创建时间',
			width : 100,
			hidden : true
		}]],
		//加载完之后给formatter定义的超链接加载图标
		onLoadSuccess : function(){
			$(".selectMan-button").linkbutton({
				iconCls : "icon-tick",
				plain : true
			});
			
			//给选择产品列添加点击事件
			$(".selectMan-button").click(function(){
				var rowId = $(this).attr("rowid");
				var rowName = $(this).attr("rowName");
				$("#inlib-add-productMan").textbox("setValue",rowName);
				$("#inlib-productMan-dialog").dialog("close");
			});
		},
		//让点击无法选中
		onClickCell : function(index){
			$("#inlib-search-productMan").datagrid("selectRow",index);										
		}
	});
		
	// 给工具条的重置查询按钮绑定点击事件
	$("#inlib_tool_undoBtn-productMan").click(
			function() {
				// 清空关键字
				$("#inlib-search-keywords-productMan").textbox("clear");
				// 利用jquery的函数触发查询按钮的点击事件
				$('#inlib_tool_searchBtn-productMan').trigger('click');
	});
	
	
	//设置关键字
	$("#inlib-search-keywords-productMan").textbox({
		prompt : "员工姓名|编号|手机"
	});
	
	// 设置查询按钮
	$("#inlib_tool_searchBtn-productMan").click(
		function() {
			if ($("#inlib_tool-productMan").form("validate")) {
				$("#inlib-search-productMan").datagrid(
					"load",
					{
						"queryCriteria.keywords" : $("#inlib-search-keywords-productMan").textbox("getValue"),
						"queryCriteria.entry_status" : "",
						"queryCriteria.dateType" : "",
						"queryCriteria.dateFrom" : "",
						"queryCriteria.dateTo" : "",
						"queryCriteria.gender" : "",
						"queryCriteria.id_card" : "",
						"queryCriteria.marital_status" : "",
						"queryCriteria.nation" : "",
						"queryCriteria.post" : "",
					});
			}
	});
});
