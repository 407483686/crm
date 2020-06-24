$(function(){
	//加载产品信息数据列表
	$("#order").datagrid({
		url : '/CRM/order_loadByPage.action',
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
		toolbar : "#order_tool",
		columns : [ [ {
			field : 'id',
			title : '自动编号',
			width : 100,
			checkbox : true
		},{
			field : 'sn',
			title : '订单编号',
			width : 80,
		}, {
			field : 'title',
			title : '订单标题',
			width : 180,
		},{
			field : 'company',
			title : '所属公司',
			width : 80,
			formatter : function(value,row){
				return row.documentary.company;
			}
		},{
			field : 'original',
			title : '订单原价',
			width : 90,
			sortable : true
		},{
			field : 'cost',
			title : '订单金额',
			width : 90,
			sortable : true
		},{
			field : 'enter',
			title : '录入员',
			width : 90,
		},{
			field : 'staff',
			title : '跟单员',
			width : 100,
			formatter : function(value,row){
				return row.documentary.staff_name;
			}
		},{
			field : 'pay_state',
			title : '付款状态',
			width : 100,
		},{
			field : 'create_time',
			title : '创建时间',
			width : 100,
		},{
			field : 'details',
			title : '详情',
			width : 40,
			//当fitColumns设置为true时，这一列如果设置了fixed，那么就不会改变宽度
			fixed : true,
			//value是列值，row是行对象
			formatter : function(value,row){
				return "<a href='javascript:void(0)' class='order-details' style='height:18px;margin-left:2px;' rowid=" + row.id + "></a>";
			}
		}]],
		onLoadSuccess : function(){
			$(".order-details").linkbutton({
				iconCls : "icon-text",
				plain : true,
			});
			
			$(".order-details").click(function(){
				var rowid = $(this).attr("rowid");
				$.ajax({
					url : "/CRM/order_getDetails.action",
					type : "POST",
					data : {
						id : rowid
					},
					beforeSend : function(){
						$.messager.progress({
							text : "正在处理中..."
						});
					},
					success : function(data){
						$.messager.progress("close");
						
						$("#order_details_dialog").dialog({
							title: '订单详情',    
						    width: 780,    
						    height: 500,    
						    iconCls: "icon-tip",
						    //closed表示对话框是否隐藏
						    closed: false,    
						    modal: true,
						    href: '/CRM/jsp/order/details.jsp',    
						    //设置对话框底部按钮 
						    //其handler属性为点击时触发，相当于onClick属性
						    buttons:[
							    {
									text:'关闭',
									iconCls:"icon-cross",
									size:"large",
									handler:function(){
										$("#order_details_dialog").dialog("close");
									}
								}
							]
						});
					}
				});
				
			});
		},
		//点击单元格时触发
		onClickCell : function(index,field){
			//index是行索引，field是列字段名
			if(field == "details"){
				//也就是说，点击详情按钮默认会选中行，那么我们在点击事件里让那一行再选中一次，就变成没选中了
				$("#order").datagrid("selectRow",index);
			}
		},
	});
	
	//给工具条的新增按钮绑定点击事件
	$("#order_tool_addBtn").click(function(){
		$("#order_dialog").dialog({
			title: '新增订单',    
		    width: 780,    
		    height: 430,    
		    //closed表示对话框是否隐藏
		    closed: false,    
		    modal: true,
		    iconCls: "icon-add",
		    href: '/CRM/jsp/order/addOrder.jsp',    
		    //设置对话框底部按钮 
		    //其handler属性为点击时触发，相当于onClick属性
		    buttons:[
			    {
					text:'保存',
					handler:function(){
						if($("#order-add").form("validate")){
							if($("#order-product-list").datagrid("getRows").length == 0){
								$.messager.alert("警告操作","订单没有选择任何产品！","warning");
							}else{
								//给产品json输入框赋值后再提交
								var jsonStr =JSON.stringify($("#order-product-list").datagrid("getData")['rows']);
								$("#order-products").val(jsonStr);
								$("#order-add").form("submit",{
									url:"CRM/order_addOrder.action",
									//本来也可以在这里用onSubmit属性进行表单校验，不过上面已经校验过了这里就不作了
									onSubmit:function(){
										$.messager.progress({
											text:"正在处理中..."
										});
									},
									success: function(data){
										//关闭进度条
										$.messager.progress('close'); 
										
										if(data == 1){
											//关闭对话框
											$("#order_dialog").dialog("close");
											//刷新表单
											$("#order").datagrid("load");
											//右下角提示信息
											$.messager.show({
											    title:'操作提醒',
											    msg:'添加成功',
											    timeout:3000,
											    showType:'slide'
											});
										}else{
											$.messager.alert("操作提醒","添加订单失败","warning");
										}
									}
								});
							}
						}
					},
					iconCls:"icon-accept",
					size:"large"
				},{
					text:'取消',
					handler:function(){
						$("#order_dialog").dialog("close");
					},
					iconCls:"icon-cross",
					size:"large",
				}
			],
		
		
		
			onClose:function(){
				clear();
				$("#order_documentary_dialog").dialog("destroy");
				$("#order_products_dialog").dialog("destroy");
			},
			onOpen : function(){
				//清理两个对话框
				$("#order_documentary_dialog").dialog("destroy");
				$("#order_products_dialog").dialog("destroy");
			}
		});
	});
	
	//给工具条的修改按钮绑定点击事件
	$("#order_tool_editBtn").click(function(){
		var arr = $("#order").datagrid("getSelections");
		if(arr.length == 1){
			$.ajax({
				url : "/CRM/order_getOne.action",
				type : "POST",
				data : {
					id : arr[0].id
				},
				beforeSend : function(){
					$.messager.progress({
						text : "正在处理中..."
					});
				},
				success : function(data){
					$.messager.progress("close");
					$("#order_edit_dialog").dialog({
						title: '修改订单',    
					    width: 780,    
					    height: 430,    
					    iconCls: "icon-edit",
					    //closed表示对话框是否隐藏
					    closed: false,    
					    modal: true,
					    href: '/CRM/jsp/order/editOrder.jsp',    
					    //设置对话框底部按钮 
					    //其handler属性为点击时触发，相当于onClick属性
					    buttons:[
						    {
								text:'保存',
								handler:function(){
									if($("#order-edit").form("validate")){
										//这里不采用JQUERY的ajax，而采用easyUI的ajax表单提交
										$("#order-edit").form("submit",{
											url:"CRM/order_editOrder.action",
											//本来也可以在这里用onSubmit属性进行表单校验，不过上面已经校验过了这里就不作了
											onSubmit:function(){
												$.messager.progress({
													text:"正在处理中..."
												});
											},
											success: function(data){
												//关闭进度条
												$.messager.progress('close'); 
												
												if(data == 1){
													//关闭对话框
													$("#order_edit_dialog").dialog("close");
													//刷新表单
													$("#order").datagrid("load");
													//右下角提示信息
													$.messager.show({
													    title:'操作提醒',
													    msg:'修改成功',
													    timeout:3000,
													    showType:'slide'
													});
												}else{
													$.messager.alert("操作提醒","修改失败","warning");
												}
											}
										});
									}
								},
								iconCls:"icon-accept",
								size:"large"
							},{
								text:'取消',
								handler:function(){
									$("#order_edit_dialog").dialog("close");
								},
								iconCls:"icon-cross",
								size:"large",
							}
						]
						,onLoad : function(){
							if(data){
								console.log(data);
								
								$("#order-edit").form("load",{
									id : data.id,
									title : data.title,
									documentary_title : data.documentary.title,
									cost : data.cost,
									details : data.details
								});
							}
						}
					});
				}
			});
		}else{
			$.messager.alert("操作提醒","请选中一行！","warning");
		}
	});
	
	
	
	// 给工具条的刷新表按钮绑定点击事件
	$("#order_tool_reloadBtn").click(function() {
		$("#order").datagrid("reload");
	});
	
	
	// 给工具条的选择其他按钮绑定点击事件
	$("#order_tool_redoBtn").click(function() {
		// 取出所有行，再取出选中行，
		var rows = $("#order").datagrid("getRows");
		var selects = $("#order").datagrid("getSelections");
		if (selects.length == 0) {
			// 如果一行都没选中，直接变成全选
			$("#order").datagrid("selectAll");
		} else {
			/*
			 * 下面这个嵌套循环的意思是: 比较所有行和选中行，比较中， 没比较成功的时候设置未选中然后继续这一行比较选中行直到比完，
			 * 比较成功的话，说明原来该行是选中的，则把该行取消选中然后跳出循环，把所有行的下一行与选中行进行比较
			 */
			for ( var i = 0; i < rows.length; i++) {
				for ( var j = 0; j < selects.length; j++) {
					if (rows[i] == selects[j]) {
						$("#order").datagrid("unselectRow", i);
						break;
					} else {
						$("#order").datagrid("selectRow", i);
					}
				}
			}
		}

	});
	
	
	
	// 给工具条的重置查询按钮绑定点击事件
	$("#order_tool_undoBtn").click(
			function() {
				// 清空关键字
				$("#order-search-keywords").textbox("clear");
				// 清空dateType，同时关闭非空验证，清空方法和开启验证方法一样会返回本身对象
				$("#order-search-date-type").combobox("clear").combobox(
						"disableValidation");
				// 清空起始时间和结束时间
				$("#order-search-date-from").datebox("clear");
				$("#order-search-date-to").datebox("clear");
				// 清空性别
				// 利用jquery的函数触发查询按钮的点击事件
				$('#order_tool_searchBtn').trigger('click');
	});
	
	
	
	
	
	
	// 设置工具条搜索栏的关键字文本框
	$("#order-search-keywords").textbox({
		width : 140,
		// 这是文本框没输入时默认的提示文本
		prompt : "订单编号|订单标题"
	});
	
	
	// 设置工具条搜索栏的时间类型下拉框
	$("#order-search-date-type").combobox({
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
				if (!$("#order-search-date-type").combobox("enableValidation")
						.combobox("isValid")) {
					// 当返回false时说明验证失败，那么我们打开下拉框
					$("#order-search-date-type").combobox("showPanel");
				}
			},
			onSelect : function() {
				// 注意开启验证方法会把下拉框对象返回来
				if (!$("#order-search-date-type").combobox("enableValidation")
						.combobox("isValid")) {
					// 当返回false时说明验证失败，那么我们打开下拉框
					$("#order-search-date-type").combobox("showPanel");
				}

			}
		};

		// 可以直接这样增加postDate的属性
		postDate.prompt = "起始时间";
		// 设置工具条搜索栏的起始时间
		$("#order-search-date-from").datebox(postDate);

		// 也可以这样修改
		postDate.prompt = "结束时间";
		// 设置工具条搜索栏的结束时间
		$("#order-search-date-to").datebox(postDate);
		
		
		// 设置搜索按钮
		$("#order_tool_searchBtn").click(
			function() {
				if ($("#order_tool").form("validate")) {
					$("#order").datagrid(
						"load",
						{
							// datagrid的load方法通常用于额外传递参数进行查询
							"queryCriteria.keywords" : $("#order-search-keywords").textbox("getValue"),
							"queryCriteria.dateType" : $("#order-search-date-type").combobox("getValue"),
							"queryCriteria.dateFrom" : $("#order-search-date-from").datebox("getValue"),
							"queryCriteria.dateTo" : $("#order-search-date-to").datebox("getValue"),
						});
				}
		});
});