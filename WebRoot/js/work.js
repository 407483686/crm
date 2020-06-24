$(function(){
	//加载产品信息数据列表
	$("#work").datagrid({
		url : '/CRM/work_loadByPage.action',
		striped : true,
		fitColumns : true,
		rownumbers : true,
		border : false,
		queryParams : {
			ShiXing : true
		},
		// 分页工具栏
		pagination : true,
		// 设置初始页面记录数，不设置的话初始是10
		pageSize : 10,
		// 设置页面分页选项
		pageList : [ 10, 20, 30, 40, 50 ],
		toolbar : "#work_tool",
		columns : [ [ {
			field : 'id',
			title : '自动编号',
			width : 100,
			checkbox : true
		},{
			field : 'title',
			title : '工作名称',
			width : 100,
		}, {
			field : 'type',
			title : '业务类型',
			width : 70,
		},{
			field : 'stage',
			title : '完成阶段',
			width : 130,
		},{
			field : 'state',
			title : '状态',
			width : 100,
			sortable : true
		},{
			field : 'staff_name',
			title : '实行人',
			width : 100,
		},{
			field : 'enter',
			title : '发起人',
			width : 100,
		},{
			field : 'state_date',
			title : '开始时间',
			width : 100,
			formatter : function(value,row){
				return (""+value).split(" ")[0];
			}
		},{
			field : 'end_date',
			title : '结束时间',
			width : 100,
			formatter : function(value,row){
				return (""+value).split(" ")[0];
			}
		},{
			field : 'create_time',
			title : '创建时间',
			width : 100,
		}]],
	});
	
	//给工具条的新增按钮绑定点击事件
	$("#work_tool_addBtn").click(function(){
		$("#work_dialog").dialog({
			title: '创建工作',    
		    width: 400,    
		    height: 300,    
		    //closed表示对话框是否隐藏
		    closed: false,    
		    modal: true,
		    iconCls: "icon-add",
		    href: '/CRM/jsp/work/addWork.jsp',  
		    onClose : function(){
				$("#work_dialog").dialog("center");
			},
		    //设置对话框底部按钮 
		    //其handler属性为点击时触发，相当于onClick属性
		    buttons:[
			    {
					text:'保存',
					handler:function(){
						if($("#work-add").form("validate")){
							//这里不采用JQUERY的ajax，而采用easyUI的ajax表单提交
							$("#work-add").form("submit",{
								url:"CRM/work_addWork.action",
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
										$("#work_dialog").dialog("close");
										//刷新表单
										$("#work").datagrid("load");
										//右下角提示信息
										$.messager.show({
										    title:'操作提醒',
										    msg:'创建成功',
										    timeout:3000,
										    showType:'slide'
										});
									}else{
										$.messager.alert("操作提醒","创建失败","warning");
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
						$("#work_dialog").dialog("close");
					},
					iconCls:"icon-cross",
					size:"large",
				}
			]
		});
	});
	
	//给工具条的跟新阶段按钮绑定点击事件
	$("#work_tool_editBtn").click(function(){
		var arr = $("#work").datagrid("getSelections");
		if(arr.length == 1){
			$.ajax({
				url : "/CRM/work_getOne.action",
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
					$("#work_edit_dialog").dialog({
						title: '更新工作阶段',    
					    width: 780,    
					    height: 512,    
					    iconCls: "icon-edit",
					    //closed表示对话框是否隐藏
					    closed: false,    
					    modal: true,
					    href: '/CRM/jsp/work/editWork.jsp',    
					    //设置对话框底部按钮 
					    //其handler属性为点击时触发，相当于onClick属性
					    buttons:[
						   {
								text:'关闭',
								handler:function(){
									$("#work_edit_dialog").dialog("close");
								},
								iconCls:"icon-cross",
								size:"large",
							}
						]
						,onLoad : function(){
							if(data){
								//打开对话框之前，判断用户的state是否是已完成，如果是，把按钮那一行都隐藏掉
								if(data.state == "已完成"){
									$("#work-button").hide();
								}else{
									$("#work-button").show();
								}
								
								//打开对话框之前，给表单加载
								$("#work-edit").form("load",{
									id : data.id,
									type : data.type,
									title : data.title,
									state_date : data.state_date,
									end_date : data.end_date,
									state : data.state
								});
								
								//打开对话框之前，把工作计划对应的工作阶段全都加载出来
						  		$("#work-edit-stage-list").datagrid({
						  			url : '/CRM/work_getWorkStage.action',
						  			queryParams : {
						  				id : data.id
						  			},
									striped : true,
									fitColumns : true,
									rownumbers : true,
									border : true,
									columns : [ [ {
										field : "title",
										title : "完成阶段",
										width : 100,
										editor : {
											type : "combobox",
											options : {
												required : true,
												valueField : "value",
												textField : "text",
												data : [{
													value : "跟单进行中",
													text : "跟单进行中"
												},{
													value : "达成订单",
													text : "达成订单"
												},{
													value : "订单已付款",
													text : "订单已付款"
												},{
													value : "申请采购资金",
													text : "申请采购资金"
												},{
													value : "采购货物",
													text : "采购货物"
												},],
												missingMessage : "请输入或选择工作阶段"
											}
										}
									},{
										field : "create_time",
										title : "创建时间",
										width : 100,
										sortable : true
									}]],
									remoteSort : false,
									sortName : "create_time",
									sortOrder : "asc",
									onClickCell : function(index){
										$("#work-edit-stage-list").datagrid("selectRow",index);
									},
									//row：对应于完成编辑的行的记录。
									onAfterEdit : function(index,row){
										var id = $("#id").val();
										$.ajax({
											url : "work_addWorkStage.action",
											type :"POST",
											data : {
												id : id,
												stage_title : row.title
											},
											success : function(data){
												if(data == 1){
													$("#work-edit-stage-list").datagrid("reload");
													$("#work").datagrid("reload");
													//隐藏保存和取消按钮，显示添加和完成按钮
													$('#work-add-stage-save').hide();
													$('#work-add-stage-cancel').hide();
													$('#work-add-stage-button').show();
													$('#work-add-stage-finish').show();
												}else{
													$.messager.alert("操作警告","保存失败","warning");
												}
											}
										});
									}
						  		});
							}
						}
					,onClose : function(){
						$("#work_edit_dialog").dialog("center");
					}
					});
				}
			});
		}else{
			$.messager.alert("操作提醒","请选中一行！","warning");
		}
	});
	
	
	// 给工具条的删除按钮绑定点击事件
	$("#work_tool_removeBtn").click(function(){
		//弹出删除面板之前先判断是否选中一行或者多行
		var arr = $("#work").datagrid("getSelections");
		if(arr.length > 0){
			//弹出确认框
			$.messager.confirm("确认操作","您真的要作废这  <b>" + arr.length + "</b> 行吗？",function(flag){
				//flag为布尔值，点OK返回true，点取消返回false
				if(flag){
					var result = "";
					for(var i=0;i<arr.length;i++){
						result += arr[i].id + "丨";
					}
					//这里由于没有弹出对话框，没有去写一个表单，所以就采用jquery的ajax而不是easyui的form的submit方法
					$.ajax({
						url:"CRM/work_removeWork.action",
						type:"POST",
						data:{
							//可以这样写，后面一个会被识别成js对象
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
								$("#work").datagrid("load");
								//右下角提示信息
								$.messager.show({
									title:"操作提醒",
									msg:data + "条记录被成功作废！"
								});
							}else{
								$.messager.alert("操作提醒","没有作废任何数据","warning");
							}
						}
					});
				}
			});
		}else{
			$.messager.alert("操作提醒","请选中一行或者多行！","warning");
		}
	});
	
	// 给工具条的刷新表按钮绑定点击事件
	$("#work_tool_reloadBtn").click(function() {
		$("#work").datagrid("reload");
	});
	
	
	// 给工具条的选择其他按钮绑定点击事件
	$("#work_tool_redoBtn").click(function() {
		// 取出所有行，再取出选中行，
		var rows = $("#work").datagrid("getRows");
		var selects = $("#work").datagrid("getSelections");
		if (selects.length == 0) {
			// 如果一行都没选中，直接变成全选
			$("#work").datagrid("selectAll");
		} else {
			/*
			 * 下面这个嵌套循环的意思是: 比较所有行和选中行，比较中， 没比较成功的时候设置未选中然后继续这一行比较选中行直到比完，
			 * 比较成功的话，说明原来该行是选中的，则把该行取消选中然后跳出循环，把所有行的下一行与选中行进行比较
			 */
			for ( var i = 0; i < rows.length; i++) {
				for ( var j = 0; j < selects.length; j++) {
					if (rows[i] == selects[j]) {
						$("#work").datagrid("unselectRow", i);
						break;
					} else {
						$("#work").datagrid("selectRow", i);
					}
				}
			}
		}

	});
	
	
	
	// 给工具条的重置查询按钮绑定点击事件
	$("#work_tool_undoBtn").click(
			function() {
				// 清空关键字
				$("#work-search-keywords").textbox("clear");
				// 清空dateType，同时关闭非空验证，清空方法和开启验证方法一样会返回本身对象
				$("#work-search-date-type").combobox("clear").combobox(
						"disableValidation");
				// 清空起始时间和结束时间
				$("#work-search-date-from").datebox("clear");
				$("#work-search-date-to").datebox("clear");
				$("#work-search-state").combobox("clear");
				$("#work-search-type").combobox("clear");
				// 清空性别
				// 利用jquery的函数触发查询按钮的点击事件
				$('#work_tool_searchBtn').trigger('click');
	});
	
	
	
	
	
	
	// 设置工具条搜索栏的关键字文本框
	$("#work-search-keywords").textbox({
		width : 140,
		// 这是文本框没输入时默认的提示文本
		prompt : "工作名称"
	});
	
	
	// 设置工具条搜索栏的任务状态下拉框
	$("#work-search-state").combobox({
		width : 80,
		editable : false,
		prompt : "状态",
		data : [ {
			value : "进行中",
			text : "进行中"
		},{
			value : "已完成",
			text : "已完成"
		},{
			value : "已作废",
			text : "已作废"
		}],
		// 基础数据值对应的名称
		valueField : "value",
		// 基础数据字段对应的名称
		textField : "text",
		// 下拉面板的高度
		panelHeight : "auto",
		// 设置无效提示信息的出现位置
		tipPosition : "left",
	});
	
	// 设置工具条搜索栏的任务类型下拉框
	$("#work-search-type").combobox({
		width : 80,
		editable : false,
		prompt : "类型",
		data : [ {
			value : "内勤",
			text : "内勤"
		},{
			value : "业务",
			text : "业务"
		}],
		// 基础数据值对应的名称
		valueField : "value",
		// 基础数据字段对应的名称
		textField : "text",
		// 下拉面板的高度
		panelHeight : "auto",
		// 设置无效提示信息的出现位置
		tipPosition : "left",
	});
	
	
	// 设置工具条搜索栏的时间类型下拉框
	$("#work-search-date-type").combobox({
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
				if (!$("#work-search-date-type").combobox("enableValidation")
						.combobox("isValid")) {
					// 当返回false时说明验证失败，那么我们打开下拉框
					$("#work-search-date-type").combobox("showPanel");
				}
			},
			onSelect : function() {
				// 注意开启验证方法会把下拉框对象返回来
				if (!$("#work-search-date-type").combobox("enableValidation")
						.combobox("isValid")) {
					// 当返回false时说明验证失败，那么我们打开下拉框
					$("#work-search-date-type").combobox("showPanel");
				}

			}
		};

		// 可以直接这样增加postDate的属性
		postDate.prompt = "起始时间";
		// 设置工具条搜索栏的起始时间
		$("#work-search-date-from").datebox(postDate);

		// 也可以这样修改
		postDate.prompt = "结束时间";
		// 设置工具条搜索栏的结束时间
		$("#work-search-date-to").datebox(postDate);
		
		
		// 设置搜索按钮
		$("#work_tool_searchBtn").click(
			function() {
				if ($("#work_tool").form("validate")) {
					$("#work").datagrid(
						"load",
						{
							// datagrid的load方法通常用于额外传递参数进行查询
							"queryCriteria.keywords" : $("#work-search-keywords").textbox("getValue"),
							"queryCriteria.dateType" : $("#work-search-date-type").combobox("getValue"),
							"queryCriteria.dateFrom" : $("#work-search-date-from").datebox("getValue"),
							"queryCriteria.dateTo" : $("#work-search-date-to").datebox("getValue"),
							"queryCriteria.work_state" : $("#work-search-state").combobox("getValue"),
							"queryCriteria.work_type" : $("#work-search-type").combobox("getValue")
						});
				}
		});
});