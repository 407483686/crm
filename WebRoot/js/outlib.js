$(function(){
	//加载产品信息数据列表
	$("#outlib").datagrid({
		url : '/CRM/outlib_loadByPage.action',
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
		toolbar : "#outlib_tool",
		columns : [ [ {
			field : 'id',
			title : '自动编号',
			width : 100,
			checkbox : true
		},{
			field : 'order_sn',
			title : '订单编号',
			width : 80,
		}, {
			field : 'product_name',
			title : '产品名称',
			width : 100,
		},{
			field : 'sell_price',
			title : '销售价格',
			width : 80,
		},{
			field : 'number',
			title : '出货量',
			width : 90,
		},{
			field : 'clerk',
			title : '发货员',
			width : 90,
		},{
			field : 'enter',
			title : '录入员',
			width : 90,
		},{
			field : 'state',
			title : '状态',
			width : 100,
		},{
			field : 'dispose_time',
			title : '出库时间',
			width : 100,
		},{
			field : 'create_time',
			title : '创建时间',
			width : 100,
			sortable : true
		}]],
		remoteSort : false,
		onLoadSuccess: function(){
	        $(this).datagrid("autoMergeCells");
	    }
	});
	
	//出库
	$("#outlib_tool_deliverBtn").click(function(){
		var state = 0;
		var arr = $("#outlib").datagrid("getSelections");
		if(arr.length > 0){
			for(var i=0;i<arr.length;i++){
				if(arr[i].state != "已支付"){
					$.messager.alert("操作警告","选择出库的货物必须是已支付状态","warning");
					state = 1;
					break;
				}
			}
			

			if(state == 0){
				$.messager.confirm("确认操作","您要批量发货<strong>" + arr.length +"</strong>件订单产品吗",function(flag){
					if(flag){
						var result = "";
						for(var i=0;i<arr.length;i++){
							result += arr[i].id + "丨";
						}
						$.ajax({
							url:"CRM/outlib_deliver.action",
							type:"POST",
							data:{
								result : result
							},
							beforeSend:function(){
								//弹出进度条
								$.messager.progress({
									text : "正在处理中"
								});
							},
							success:function(data){
								//关闭进度条
								$.messager.progress("close");
								if(data > 0){
									//刷新表单
									$("#outlib").datagrid("load");
									//右下角提示信息
									$.messager.show({
										title:"操作提醒",
										msg:data + "件订单产品成功出库！"
									});
								}else{
									$.messager.alert("操作提醒","出库失败","warning");
								}
							}
						});
					}
				});
			}
		}else{
			$.messager.alert("操作警告","请选择一行或多行","warning");
		}
	});
	
	// 给工具条的刷新表按钮绑定点击事件
	$("#outlib_tool_reloadBtn").click(function() {
		$("#outlib").datagrid("reload");
	});
	
	
	
	
	// 给工具条的重置查询按钮绑定点击事件
	$("#outlib_tool_undoBtn").click(
			function() {
				// 清空关键字
				$("#outlib-search-keywords").textbox("clear");
				// 清空dateType，同时关闭非空验证，清空方法和开启验证方法一样会返回本身对象
				$("#outlib-search-date-type").combobox("clear").combobox("disableValidation");
				// 清空起始时间和结束时间
				$("#outlib-search-date-from").datebox("clear");
				$("#outlib-search-date-to").datebox("clear");
				// 清空性别
				// 利用jquery的函数触发查询按钮的点击事件
				$('#outlib_tool_searchBtn').trigger('click');
	});
	
	
	//合并相同单元格
	//合并相同内容的单元格，自动化的
	$.extend($.fn.datagrid.methods, {
	    autoMergeCells : function (jq, fields) {
	        return jq.each(function () {
	            var target = $(this);
	            if (!fields) {
	                fields = target.datagrid("getColumnFields");
	            }
	            var rows = target.datagrid("getRows");
	            var i = 0,
	                j = 0,
	                temp = {};
	            for (i; i < rows.length; i++) {
	                var row = rows[i];
	                j = 0;
	                for (j; j < fields.length; j++) {
	                    var field = fields[j];
	                    var tf = temp[field];
	                    if (!tf) {
	                        tf = temp[field] = {};
	                        tf[row[field]] = [i];
	                    } else {
	                        var tfv = tf[row[field]];
	                        if (tfv) {
	                            tfv.push(i);
	                        } else {
	                            tfv = tf[row[field]] = [i];
	                        }
	                    }
	                }
	            }
	            $.each(temp, function (field, colunm) {
	                $.each(colunm, function () {
	                    var group = this;

	                    if (group.length > 1) {
	                        var before,
	                            after,
	                            megerIndex = group[0];
	                        for (var i = 0; i < group.length; i++) {
	                            before = group[i];
	                            after = group[i + 1];
	                            if (after && (after - before) == 1) {
	                                continue;
	                            }
	                            var rowspan = before - megerIndex + 1;
	                            //这里的&& field == 'order_sn'是我添加的，限制只有订单号合并
	                            if (rowspan > 1 && field == 'order_sn') {
	                                target.datagrid('mergeCells', {
	                                    index : megerIndex,
	                                    field : field,
	                                    rowspan : rowspan
	                                });
	                            }

	                            if (after && (after - before) != 1) {
	                                megerIndex = after;
	                            }
	                        }
	                    }
	                });
	            });
	        });
	    }
	});
	
	
	//定义订单编号的验证
	$.extend($.fn.validatebox.defaults.rules, {
	    orderSn: {
	        validator: function(value, param){
	            return /^[0-9]{12}$/.test(value);
	        },
	        message: '请输入精确12位订单编号'
	    }
	});
	
	// 设置工具条搜索栏的关键字文本框
	$("#outlib-search-keywords").textbox({
		width : 140,
		// 这是文本框没输入时默认的提示文本
		prompt : "订单编号(精确12位)",
		validType : "orderSn"
	});
	
	
	// 设置工具条搜索栏的时间类型下拉框
	$("#outlib-search-date-type").combobox({
		width : 100,
		editable : false,
		prompt : "时间类型",
		data : [ {
			value : "create_time",
			text : "创建时间"
		},{
			value : "dispose_time",
			text : "出库时间"
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
				if (!$("#outlib-search-date-type").combobox("enableValidation")
						.combobox("isValid")) {
					// 当返回false时说明验证失败，那么我们打开下拉框
					$("#outlib-search-date-type").combobox("showPanel");
				}
			},
			onSelect : function() {
				// 注意开启验证方法会把下拉框对象返回来
				if (!$("#outlib-search-date-type").combobox("enableValidation")
						.combobox("isValid")) {
					// 当返回false时说明验证失败，那么我们打开下拉框
					$("#outlib-search-date-type").combobox("showPanel");
				}

			}
		};

		// 可以直接这样增加postDate的属性
		postDate.prompt = "起始时间";
		// 设置工具条搜索栏的起始时间
		$("#outlib-search-date-from").datebox(postDate);

		// 也可以这样修改
		postDate.prompt = "结束时间";
		// 设置工具条搜索栏的结束时间
		$("#outlib-search-date-to").datebox(postDate);
		
		
		// 设置搜索按钮
		$("#outlib_tool_searchBtn").click(
			function() {
				if ($("#outlib_tool").form("validate")) {
					$("#outlib").datagrid(
						"load",
						{
							// datagrid的load方法通常用于额外传递参数进行查询
							"queryCriteria.keywords" : $("#outlib-search-keywords").textbox("getValue"),
							"queryCriteria.dateType" : $("#outlib-search-date-type").combobox("getValue"),
							"queryCriteria.dateFrom" : $("#outlib-search-date-from").datebox("getValue"),
							"queryCriteria.dateTo" : $("#outlib-search-date-to").datebox("getValue"),
						});
				}
		});
});