$(function(){
	//加载入库记录数据列表
	$("#inlib").datagrid({
		url : '/CRM/inlib_loadByPage.action',
		striped : true,
		fitColumns : true,
		rownumbers : true,
		border : false,
		singleSelect : true,
		// 分页工具栏
		pagination : true,
		// 设置初始页面记录数，不设置的话初始是10
		pageSize : 10,
		// 设置页面分页选项
		pageList : [ 10, 20, 30, 40, 50 ],
		toolbar : "#inlib_tool",
		columns : [ [ {
			field : 'id',
			title : '自动编号',
			width : 100,
			hidden : true,
		},{
			//如果时嵌套的json串，要获取必须要么使他平级，要么使用formatter
			field : 'product.sn',
			title : '产品编号',
			width : 80,
			formatter: function (value,row) { 
				return row.product.sn; 
			} 
		},{
			//如果时嵌套的json串，要获取必须要么使他平级，要么使用formatter
			field : 'product.name',
			title : '产品名称',
			width : 100,
			formatter: function (value,row) { 
				return row.product.name; 
			} 
		},{
			//如果时嵌套的json串，要获取必须要么使他平级，要么使用formatter
			field : 'product.type',
			title : '产品类型',
			width : 80,
			formatter: function (value,row) { 
				return row.product.type; 
			} 
		},{
			//如果时嵌套的json串，要获取必须要么使他平级，要么使用formatter
			field : 'product.pro_price',
			title : '采购价格(元)',
			width : 60,
			formatter: function (value,row) { 
				return row.product.pro_price; 
			} 
		},{
			field : 'number',
			title : '入库数量',
			width : 60,
		},{
			field : 'staff_name',
			title : '经办人',
			width : 60,
		},{
			field : 'mode',
			title : '入库方式',
			width : 50,
		},{
			field : 'mode_explain',
			title : '入库方式说明',
			width : 100,
		},{
			field : 'enter',
			title : '录入员',
			width : 60,
		},{
			field : 'create_time',
			title : '创建时间',
			width : 120,
		},{
			field : 'details',
			title : '详情',
			width : 40,
			//当fitColumns设置为true时，这一列如果设置了fixed，那么就不会改变宽度
			fixed : true,
			//value是列值，row是行对象
			formatter : function(value,row){
				return "<a href='javascript:void(0)' class='inlib-details' style='height:18px;margin-left:2px;' rowid=" + row.id + "></a>";
			}
		}]],
		onLoadSuccess : function(){
			$(".inlib-details").linkbutton({
				iconCls : "icon-text",
				plain : true,
			});
			
			$(".inlib-details").click(function(){
				var rowid = $(this).attr("rowid");
				$.ajax({
					url : "/CRM/inlib_getDetails.action",
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
						
						$("#inlib_details_dialog").dialog({
							title: '入库详情',    
						    width: 780,    
						    height: 500,    
						    iconCls: "icon-tip",
						    //closed表示对话框是否隐藏
						    closed: false,    
						    modal: true,
						    href: '/CRM/jsp/inlib/details.jsp',    
						    //设置对话框底部按钮 
						    //其handler属性为点击时触发，相当于onClick属性
						    buttons:[
							    {
									text:'关闭',
									iconCls:"icon-cross",
									size:"large",
									handler:function(){
										$("#inlib_details_dialog").dialog("close");
									}
								}
							]
						});
					}
				});
				
			});
		},
		
	});
	
	$("#inlib_tool_addBtn").click(function(){
		$("#inlib_dialog").dialog({
			title: '新增入库',    
		    width: 420,    
		    height: 380,    
		    iconCls: "icon-add",
		    //closed表示对话框是否隐藏
		    closed: false,    
		    modal: true,
		    href: '/CRM/jsp/inlib/addInlib.jsp',    
		    //设置对话框底部按钮 
		    //其handler属性为点击时触发，相当于onClick属性
		    buttons:[
			    {
					text:'保存',
					handler:function(){
						if($("#inlib-add").form("validate")){
							//这里不采用JQUERY的ajax，而采用easyUI的ajax表单提交
							$("#inlib-add").form("submit",{
								url:"/CRM/inlib_addInlib.action",
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
										$("#inlib_dialog").dialog("close");
										//刷新表单
										$("#inlib").datagrid("load");
										//右下角提示信息
										$.messager.show({
										    title:'操作提醒',
										    msg:'新增入库记录成功',
										    timeout:3000,
										    showType:'slide'
										});
									}else{
										$.messager.alert("操作提醒","新增入库记录失败","warning");
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
						$("#inlib_dialog").dialog("close");
					},
					iconCls:"icon-cross",
					size:"large",
				}
			],
			
			onClose:function(){
				$("#inlib-product-dialog").dialog("destroy");
				$("#inlib-productMan-dialog").dialog("destroy");
			}
		});
	});
	
	
	
	
	// 给工具条的刷新表按钮绑定点击事件
	$("#inlib_tool_reloadBtn").click(function() {
		$("#inlib").datagrid("reload");
	});
	
	
	
	// 给工具条的重置查询按钮绑定点击事件
	$("#inlib_tool_undoBtn").click(
			function() {
				// 清空关键字
				$("#inlib-search-keywords").textbox("clear");
				// 清空产品类型
				$("#inlib-search-type").combobox("clear");
				// 清空dateType，同时关闭非空验证，清空方法和开启验证方法一样会返回本身对象
				$("#inlib-search-date-type").combobox("clear").combobox(
						"disableValidation");
				// 清空起始时间和结束时间
				$("#inlib-search-date-from").datebox("clear");
				$("#inlib-search-date-to").datebox("clear");
				// 清空性别
				// 利用jquery的函数触发查询按钮的点击事件
				$('#inlib_tool_searchBtn').trigger('click');
	});
	
	
	
	
	
	
	// 设置工具条搜索栏的关键字文本框
	$("#inlib-search-keywords").textbox({
		width : 70,
		// 这是文本框没输入时默认的提示文本
		prompt : "经办人"
	});
	
	// 设置工具条搜索栏的时间类型下拉框
	$("#inlib-search-date-type").combobox({
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
				if (!$("#inlib-search-date-type").combobox("enableValidation")
						.combobox("isValid")) {
					// 当返回false时说明验证失败，那么我们打开下拉框
					$("#inlib-search-date-type").combobox("showPanel");
				}
			},
			onSelect : function() {
				// 注意开启验证方法会把下拉框对象返回来
				if (!$("#inlib-search-date-type").combobox("enableValidation")
						.combobox("isValid")) {
					// 当返回false时说明验证失败，那么我们打开下拉框
					$("#inlib-search-date-type").combobox("showPanel");
				}

			}
		};

		// 可以直接这样增加postDate的属性
		postDate.prompt = "起始时间";
		// 设置工具条搜索栏的起始时间
		$("#inlib-search-date-from").datebox(postDate);

		// 也可以这样修改
		postDate.prompt = "结束时间";
		// 设置工具条搜索栏的结束时间
		$("#inlib-search-date-to").datebox(postDate);
		
		
		// 设置搜索按钮
		$("#inlib_tool_searchBtn").click(
			function() {
				if ($("#inlib_tool").form("validate")) {
					$("#inlib").datagrid(
						"load",
						{
							// datagrid的load方法通常用于额外传递参数进行查询
							"queryCriteria.keywords" : $("#inlib-search-keywords").textbox("getValue"),
							"queryCriteria.dateType" : $("#inlib-search-date-type").combobox("getValue"),
							"queryCriteria.dateFrom" : $("#inlib-search-date-from").datebox("getValue"),
							"queryCriteria.dateTo" : $("#inlib-search-date-to").datebox("getValue"),
						});
				}
		});
});