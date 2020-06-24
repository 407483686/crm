$(function(){
	//加载产品信息数据列表
	$("#log").datagrid({
		url : '/CRM/log_loadByPage.action',
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
		toolbar : "#log_tool",
		columns : [ [ {
			field : 'id',
			title : '自动编号',
			width : 100,
			checkbox : true
		},{
			field : 'user',
			title : '管理账号',
			width : 100,
		}, {
			field : 'method',
			title : '操作类型',
			width : 100,
		},{
			field : 'module',
			title : '登陆模块',
			width : 120,
		},{
			field : 'ip',
			title : 'IP地址',
			width : 100,
		},{
			field : 'create_time',
			title : '创建时间',
			width : 100,
			sortable : true
		}]],
	});
	
	
	// 给工具条的刷新表按钮绑定点击事件
	$("#log_tool_reloadBtn").click(function() {
		$("#log").datagrid("reload");
	});
	
	
	// 给工具条的选择其他按钮绑定点击事件
	$("#log_tool_redoBtn").click(function() {
		// 取出所有行，再取出选中行，
		var rows = $("#log").datagrid("getRows");
		var selects = $("#log").datagrid("getSelections");
		if (selects.length == 0) {
			// 如果一行都没选中，直接变成全选
			$("#log").datagrid("selectAll");
		} else {
			/*
			 * 下面这个嵌套循环的意思是: 比较所有行和选中行，比较中， 没比较成功的时候设置未选中然后继续这一行比较选中行直到比完，
			 * 比较成功的话，说明原来该行是选中的，则把该行取消选中然后跳出循环，把所有行的下一行与选中行进行比较
			 */
			for ( var i = 0; i < rows.length; i++) {
				for ( var j = 0; j < selects.length; j++) {
					if (rows[i] == selects[j]) {
						$("#log").datagrid("unselectRow", i);
						break;
					} else {
						$("#log").datagrid("selectRow", i);
					}
				}
			}
		}

	});
	
	
	
	// 给工具条的重置查询按钮绑定点击事件
	$("#log_tool_undoBtn").click(
		function() {
			// 清空关键字
			$("#log-search-keywords").textbox("clear");
			// 清空dateType，同时关闭非空验证，清空方法和开启验证方法一样会返回本身对象
			$("#log-search-date-type").combobox("clear").combobox("disableValidation");
			// 清空起始时间和结束时间
			$("#log-search-date-from").datebox("clear");
			$("#log-search-date-to").datebox("clear");
			// 清空性别
			// 利用jquery的函数触发查询按钮的点击事件
			$('#log_tool_searchBtn').trigger('click');
	});
	
	
	
	
	
	
	// 设置工具条搜索栏的关键字文本框
	$("#log-search-keywords").textbox({
		width : 140,
		// 这是文本框没输入时默认的提示文本
		prompt : "管理账号|操作类型"
	});
	
	
	// 设置工具条搜索栏的时间类型下拉框
	$("#log-search-date-type").combobox({
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
				if (!$("#log-search-date-type").combobox("enableValidation")
						.combobox("isValid")) {
					// 当返回false时说明验证失败，那么我们打开下拉框
					$("#log-search-date-type").combobox("showPanel");
				}
			},
			onSelect : function() {
				// 注意开启验证方法会把下拉框对象返回来
				if (!$("#log-search-date-type").combobox("enableValidation")
						.combobox("isValid")) {
					// 当返回false时说明验证失败，那么我们打开下拉框
					$("#log-search-date-type").combobox("showPanel");
				}

			}
		};

		// 可以直接这样增加postDate的属性
		postDate.prompt = "起始时间";
		// 设置工具条搜索栏的起始时间
		$("#log-search-date-from").datebox(postDate);

		// 也可以这样修改
		postDate.prompt = "结束时间";
		// 设置工具条搜索栏的结束时间
		$("#log-search-date-to").datebox(postDate);
		
		
		// 设置搜索按钮
		$("#log_tool_searchBtn").click(
			function() {
				if ($("#log_tool").form("validate")) {
					$("#log").datagrid(
						"load",
						{
							// datagrid的load方法通常用于额外传递参数进行查询
							"queryCriteria.keywords" : $("#log-search-keywords").textbox("getValue"),
							"queryCriteria.dateType" : $("#log-search-date-type").combobox("getValue"),
							"queryCriteria.dateFrom" : $("#log-search-date-from").datebox("getValue"),
							"queryCriteria.dateTo" : $("#log-search-date-to").datebox("getValue"),
						});
				}
		});
});