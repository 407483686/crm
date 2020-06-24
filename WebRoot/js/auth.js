$(function(){
	//加载产品信息数据列表
	$("#auth").datagrid({
		url : '/CRM/authGroup_loadByPage.action',
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
		toolbar : "#auth_tool",
		columns : [ [ {
			field : 'id',
			title : '自动编号',
			width : 100,
			checkbox : true
		},{
			field : 'title',
			title : '部门名称',
			width : 100,
		}, {
			field : 'auths',
			title : '授权情况',
			width : 200,
		}, {
			field : 'create_time',
			title : '创建时间',
			width : 100,
		}]],
	});
	
	
	//给工具条的修改按钮绑定点击事件
	$("#auth_tool_editBtn").click(function(){
		if($("#auth").datagrid("getSelections").length == 1){
			$("#auth_dialog").dialog({
				title: '授权管理',    
			    width: 250,    
			    height: 500,    
			    iconCls: "icon-edit",
			    //closed表示对话框是否隐藏
			    closed: false,    
			    modal: true,
			    href: '/CRM/jsp/auth/editAuth.jsp', 
			    onLoad : function(){
			    	//给id赋值
			    	var selectId = $("#auth").datagrid("getSelected").id;
			    	$("#id").val(selectId);
			    	//获取树
			    	$("#privilege_tree").tree({  
			    		checkbox:true,  
					    url:"menu_showPrivilege.action?auth_group_id=" + selectId
					});
			    },
			    //设置对话框底部按钮 
			    //其handler属性为点击时触发，相当于onClick属性
			    buttons:[
				    {
						text:'保存',
						handler:function(){
							//获取树所有选中的
							var arr = $('#privilege_tree').tree('getChecked', ['checked']);
				  			var result = "";
				  			for(var i=0;i<arr.length;i++){
				  				result += arr[i].text + "丨";
				  			}
				  			//结果是树节点的text以空格隔开
				  			$("#privilege_result").val(result);
							$("#privilegeForm").form("submit",{
								url:"CRM/menu_savePrivilege.action",
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
										$("#auth_dialog").dialog("close");
										//刷新表单
										$("#auth").datagrid("load");
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
						},
						iconCls:"icon-accept",
						size:"large"
					},{
						text:'取消',
						handler:function(){
							$("#auth_dialog").dialog("close");
						},
						iconCls:"icon-cross",
						size:"large",
					}
				]
			});
		}else{
			$.messager.alert("操作提醒","请选择一行","warning");
		}
		
		
		
	});
	
	
	
	// 给工具条的刷新表按钮绑定点击事件
	$("#auth_tool_reloadBtn").click(function() {
		$("#auth").datagrid("reload");
	});
	
	
	// 给工具条的选择其他按钮绑定点击事件
	$("#auth_tool_redoBtn").click(function() {
		// 取出所有行，再取出选中行，
		var rows = $("#auth").datagrid("getRows");
		var selects = $("#auth").datagrid("getSelections");
		if (selects.length == 0) {
			// 如果一行都没选中，直接变成全选
			$("#auth").datagrid("selectAll");
		} else {
			/*
			 * 下面这个嵌套循环的意思是: 比较所有行和选中行，比较中， 没比较成功的时候设置未选中然后继续这一行比较选中行直到比完，
			 * 比较成功的话，说明原来该行是选中的，则把该行取消选中然后跳出循环，把所有行的下一行与选中行进行比较
			 */
			for ( var i = 0; i < rows.length; i++) {
				for ( var j = 0; j < selects.length; j++) {
					if (rows[i] == selects[j]) {
						$("#auth").datagrid("unselectRow", i);
						break;
					} else {
						$("#auth").datagrid("selectRow", i);
					}
				}
			}
		}

	});
	
	
	
	// 给工具条的重置查询按钮绑定点击事件
	$("#auth_tool_undoBtn").click(
			function() {
				// 清空关键字
				$("#auth-search-keywords").textbox("clear");
				// 清空dateType，同时关闭非空验证，清空方法和开启验证方法一样会返回本身对象
				$("#auth-search-date-type").combobox("clear").combobox(
						"disableValidation");
				// 清空起始时间和结束时间
				$("#auth-search-date-from").datebox("clear");
				$("#auth-search-date-to").datebox("clear");
				// 清空性别
				// 利用jquery的函数触发查询按钮的点击事件
				$('#auth_tool_searchBtn').trigger('click');
	});
	
	
	
	
	
	
	// 设置工具条搜索栏的关键字文本框
	$("#auth-search-keywords").textbox({
		width : 140,
		// 这是文本框没输入时默认的提示文本
		prompt : "部门名称"
	});
	
	
	// 设置工具条搜索栏的时间类型下拉框
	$("#auth-search-date-type").combobox({
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
				if (!$("#auth-search-date-type").combobox("enableValidation")
						.combobox("isValid")) {
					// 当返回false时说明验证失败，那么我们打开下拉框
					$("#auth-search-date-type").combobox("showPanel");
				}
			},
			onSelect : function() {
				// 注意开启验证方法会把下拉框对象返回来
				if (!$("#auth-search-date-type").combobox("enableValidation")
						.combobox("isValid")) {
					// 当返回false时说明验证失败，那么我们打开下拉框
					$("#auth-search-date-type").combobox("showPanel");
				}

			}
		};

		// 可以直接这样增加postDate的属性
		postDate.prompt = "起始时间";
		// 设置工具条搜索栏的起始时间
		$("#auth-search-date-from").datebox(postDate);

		// 也可以这样修改
		postDate.prompt = "结束时间";
		// 设置工具条搜索栏的结束时间
		$("#auth-search-date-to").datebox(postDate);
		
		
		// 设置搜索按钮
		$("#auth_tool_searchBtn").click(
			function() {
				if ($("#auth_tool").form("validate")) {
					$("#auth").datagrid(
						"load",
						{
							// datagrid的load方法通常用于额外传递参数进行查询
							"queryCriteria.keywords" : $("#auth-search-keywords").textbox("getValue"),
							"queryCriteria.dateType" : $("#auth-search-date-type").combobox("getValue"),
							"queryCriteria.dateFrom" : $("#auth-search-date-from").datebox("getValue"),
							"queryCriteria.dateTo" : $("#auth-search-date-to").datebox("getValue"),
						});
				}
		});
});