$(function(){
	//加载库存警报数据列表
	$("#alarm").datagrid({
		url : '/CRM/product_loadByPage.action',
		striped : true,
		fitColumns : true,
		rownumbers : true,
		border : false,
		singleSelect : true,
		//发送额外的参数alarm，值为true
		queryParams : {
			alarm : true
		},
		// 分页工具栏
		pagination : true,
		// 设置初始页面记录数，不设置的话初始是10
		pageSize : 10,
		// 设置页面分页选项
		pageList : [ 10, 20, 30, 40, 50 ],
		toolbar : "#alarm_tool",
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
			width : 100,
		},{
			field : 'type',
			title : '产品类别',
			width : 100,
			sortable : true
		},{
			field : 'pro_price',
			title : '采购价格(元)',
			width : 100,
		},{
			field : 'sell_price',
			title : '销售价格(元)',
			width : 100,
		},{
			field : 'unit',
			title : '计量单位',
			width : 100,
		},{
			field : 'inventory',
			title : '库存量',
			width : 100,
			sortable : true
		},{
			field : 'inventory_in',
			title : '入库总量',
			width : 100,
		},{
			field : 'inventory_out',
			title : '出库总量',
			width : 100,
		},{
			field : 'inventory_alarm',
			title : '库存警报量',
			width : 100,
		},{
			field : 'create_time',
			title : '创建时间',
			width : 120,
			sortable : true
		}]],
		
	});
	
	
	// 给工具条的刷新表按钮绑定点击事件
	$("#alarm_tool_reloadBtn").click(function() {
		$("#alarm").datagrid("reload");
	});
	
	
	// 给工具条的重置查询按钮绑定点击事件
	$("#alarm_tool_undoBtn").click(
			function() {
				// 清空关键字
				$("#alarm-search-keywords").textbox("clear");
				// 清空产品类型
				$("#alarm-search-type").combobox("clear");
				// 清空dateType，同时关闭非空验证，清空方法和开启验证方法一样会返回本身对象
				$("#alarm-search-date-type").combobox("clear").combobox(
						"disableValidation");
				// 清空起始时间和结束时间
				$("#alarm-search-date-from").datebox("clear");
				$("#alarm-search-date-to").datebox("clear");
				// 清空性别
				// 利用jquery的函数触发查询按钮的点击事件
				$('#alarm_tool_searchBtn').trigger('click');
	});
	
	
	
	
	
	
	// 设置工具条搜索栏的关键字文本框
	$("#alarm-search-keywords").textbox({
		width : 130,
		// 这是文本框没输入时默认的提示文本
		prompt : "产品名称|编号"
	});
	
	// 设置工具条搜索栏的产品类别下拉框
	$("#alarm-search-type").combobox({
		width : 100,
		editable : false,
		prompt : "产品类型",
		data : [{
		    "value":"办公耗材",
		    "text":"办公耗材"
		},{
		    "value":"数码产品",
		    "text":"数码产品"
		}],
		// 基础数据值对应的名称
		valueField : "value",
		// 基础数据字段对应的名称
		textField : "text",
		// 下拉面板的高度
		panelHeight : "auto",
	});
	
	// 设置工具条搜索栏的时间类型下拉框
	$("#alarm-search-date-type").combobox({
		width : 100,
		editable : false,
		prompt : "时间类型",
		data : [ {
			value : "create_time",
			text : "创建时间"
		}],
		// 基础数据值对应的名称
		valueField : "value",
		// 基础数据字段对应的名称
		textField : "text",
		// 下拉面板的高度
		panelHeight : "auto",
		// 设置验证：非空
		required : true,
		// 设置无效提示信息的出现位置
		tipPosition : "left",
		// 当用户没有输入时，出现的提示信息
		missingMessage : "请选择时间类型",
		// 如果没选择后面的起始时间和结束时间时，先不启用非空验证
		// 不启用的时候相当于没验证，此时表单的validate方法能通过，
		// 如果点击了后面的时间，那么开启验证就必须要填，不填表单的validate无法通过
		novalidate : true,
	});
	
	var postDate = {
			// 日期面板宽度为100
			width : 100,
			// 不允许不通过面板直接输入值
			editable : false,
			// 设置当两个日期被选中时，就开启前面的时间类型下拉框的非空验证
			// 如果非空验证不通过，说明之前没有选择下拉框的值，那么我们把下拉框打开
			onHidePanel : function() {
				// 当点击today或者close时，会触发该事件
				if (!$("#alarm-search-date-type").combobox("enableValidation")
						.combobox("isValid")) {
					// 当返回false时说明验证失败，那么我们打开下拉框
					$("#alarm-search-date-type").combobox("showPanel");
				}
			},
			onSelect : function() {
				// 注意开启验证方法会把下拉框对象返回来
				if (!$("#alarm-search-date-type").combobox("enableValidation")
						.combobox("isValid")) {
					// 当返回false时说明验证失败，那么我们打开下拉框
					$("#alarm-search-date-type").combobox("showPanel");
				}

			}
		};

		// 可以直接这样增加postDate的属性
		postDate.prompt = "起始时间";
		// 设置工具条搜索栏的起始时间
		$("#alarm-search-date-from").datebox(postDate);

		// 也可以这样修改
		postDate.prompt = "结束时间";
		// 设置工具条搜索栏的结束时间
		$("#alarm-search-date-to").datebox(postDate);
		
		
		// 设置搜索按钮
		$("#alarm_tool_searchBtn").click(
			function() {
				if ($("#alarm_tool").form("validate")) {
					$("#alarm").datagrid(
						"load",
						{
							// datagrid的load方法通常用于额外传递参数进行查询
							"queryCriteria.keywords" : $("#alarm-search-keywords").textbox("getValue"),
							"queryCriteria.type" : $("#alarm-search-type").combobox("getValue"),
							"queryCriteria.dateType" : $("#alarm-search-date-type").combobox("getValue"),
							"queryCriteria.dateFrom" : $("#alarm-search-date-from").datebox("getValue"),
							"queryCriteria.dateTo" : $("#alarm-search-date-to").datebox("getValue"),
						});
				}
		});
});