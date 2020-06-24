$(function() {
	// 加载职位部门的数据
	$("#staff").datagrid({
		url : '/CRM/staff_loadByPage.action',
		striped : true,
		fitColumns : true,
		columns : [ [ {
			field : 'id',
			title : '自动编号',
			width : 100,
			checkbox : true
		}, {
			field : 'number',
			title : '员工编号',
			width : 100,
			sortable : true
		}, {
			field : 'name',
			title : '员工姓名',
			width : 100
		}, {
			field : 'gender',
			title : '员工性别',
			width : 100,
			sortable : true
		},{
			field : 'nation',
			title : '民族',
			width : 100
		},{
			field : 'id_card',
			title : '身份证',
			width : 100
		},{
			field : 'post',
			title : '职位',
			width : 100
		}, {
			field : 'type',
			title : '员工类型',
			width : 100,
			sortable : true
		}, {
			field : 'tel',
			title : '手机号码',
			width : 100
		}, {
			field : 'entry_status',
			title : '入职状态',
			width : 100,
			sortable : true
		}, {
			field : 'entry_date',
			title : '入职时间',
			width : 100,
			sortable : true
		}, {
			field : 'marital_status',
			title : '婚姻状况',
			width : 100,
			sortable : true
		}, {
			field : 'education',
			title : '学历',
			width : 100,
			sortable : true
		}, {
			field : 'create_time',
			title : '创建时间',
			width : 100,
			sortable : true
		}, {
			field : 'dimission_date',
			title : '离职时间',
			width : 100,
			sortable : true
		}, {
			field : 'details',
			title : '详情',
			width : 40,
			//当fitColumns设置为true时，这一列如果设置了fixed，那么就不会改变宽度
			fixed : true,
			//value是列值，row是行对象
			formatter : function(value,row){
				return "<a href='javascript:void(0)' class='staff-details' style='height:18px;margin-left:2px;' rowid=" + row.id + "></a>";
			}
		}, ] ],
		onLoadSuccess : function(){
			$(".staff-details").linkbutton({
				iconCls : "icon-text",
				plain : true,
			});
			
			$(".staff-details").click(function(){
				var rowid = $(this).attr("rowid");
				$.ajax({
					url : "/CRM/staff_getDetails.action",
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
						
						$("#details_dialog").dialog({
							title: '档案详情',    
						    width: 780,    
						    height: 500,    
						    iconCls: "icon-tip",
						    //closed表示对话框是否隐藏
						    closed: false,    
						    modal: true,
						    href: '/CRM/jsp/staff/details.jsp',    
						    //设置对话框底部按钮 
						    //其handler属性为点击时触发，相当于onClick属性
						    buttons:[
							    {
									text:'关闭',
									iconCls:"icon-cross",
									size:"large",
									handler:function(){
										$("#details_dialog").dialog("close");
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
				$("#staff").datagrid("selectRow",index);
			}
		},
		rownumbers : true,
		border : false,
		sortName : "number",
		sortOrder : "asc",
		// 设置上面两个还不够，下面这个属性默认值是true，表示只以json数据的顺序来判断，而忽略sortName和sortOrder，
		// 因此我们需要设置为false才能排序，至于列的属性sortable,加了之后，表示可以点击进行排序
		remoteSort : false,
		// 分页工具栏
		pagination : true,
		// 设置初始页面记录数，不设置的话初始是10
		pageSize : 20,
		// 设置页面分页选项
		pageList : [ 10, 20, 30, 40, 50 ],
		toolbar : "#staff_tool"
	});

	
	//给工具条的增加按钮绑定点击事件
	$("#staff_tool_addBtn").click(function(){
		$("#staff_dialog").dialog({
			title: '新增档案',    
		    width: 780,    
		    height: 500,    
		    iconCls: "icon-add",
		    //closed表示对话框是否隐藏
		    closed: false,    
		    modal: true,
		    href: '/CRM/jsp/staff/addStaff.jsp',    
		    //设置对话框底部按钮 
		    //其handler属性为点击时触发，相当于onClick属性
		    buttons:[
			    {
					text:'保存',
					handler:function(){
						STAFF_ADD.sync();
						if($("#staff-add").form("validate")){
							//这里不采用JQUERY的ajax，而采用easyUI的ajax表单提交
							$("#staff-add").form("submit",{
								url:"CRM/staff_addStaff.action",
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
										$("#staff_dialog").dialog("close");
										//刷新表单
										$("#staff").datagrid("load");
										//右下角提示信息
										$.messager.show({
										    title:'操作提醒',
										    msg:'添加成功',
										    timeout:3000,
										    showType:'slide'
										});
									}else{
										$.messager.alert("操作提醒","添加失败，身份证或员工编号已经存在","warning");
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
						$("#staff_dialog").dialog("close");
					},
					iconCls:"icon-cross",
					size:"large",
				}
			]
			,onClose : function(){
				//清除系统的编辑器内容
				STAFF_ADD.html("");
			}
		});
	});
	
	
	// 给工具条的修改按钮绑定点击事件
	$("#staff_tool_editBtn").click(function(){
		//弹出删除面板之前先判断是否选中一行或者多行
		var arr = $("#staff").datagrid("getSelections");
		if(arr.length == 1){
			$.ajax({
				url : "/CRM/staff_getOne.action",
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
					$("#staff_edit_dialog").dialog({
						title: '修改档案',    
					    width: 780,    
					    height: 500,    
					    iconCls: "icon-edit",
					    //closed表示对话框是否隐藏
					    closed: false,    
					    modal: true,
					    href: '/CRM/jsp/staff/editStaff.jsp',    
					    //设置对话框底部按钮 
					    //其handler属性为点击时触发，相当于onClick属性
					    buttons:[
						    {
								text:'保存',
								handler:function(){
									STAFF_EDIT.sync();
									if($("#staff-edit").form("validate")){
										//这里不采用JQUERY的ajax，而采用easyUI的ajax表单提交
										$("#staff-edit").form("submit",{
											url:"CRM/staff_editStaff.action",
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
													$("#staff_edit_dialog").dialog("close");
													//刷新表单
													$("#staff").datagrid("load");
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
									$("#staff_edit_dialog").dialog("close");
								},
								iconCls:"icon-cross",
								size:"large",
							}
						],
						onClose : function(){
							//清除创建的富文本框内容
							STAFF_EDIT.html("");
						},onLoad : function(){
							if(data){
								$("#staff-edit").form("load",{
									id : data.id,
									name : data.name,
									number : data.number,
									gender : data.gender,
									type : data.type,
									id_card : data.id_card,
									post : data.post,
									tel : data.tel,
									nation : data.nation,
									marital_status : data.marital_status,
									entry_status : data.entry_status,
									entry_date_string : data.entry_date,
									dimission_date_string : data.dimission_date,
									politics_status : data.politics_status,
									education : data.education,
									intro : data.extend.intro,
									major : data.extend.major,
									heath : data.extend.heath,
									residence : data.extend.residence,
									registered_permanent_residence : data.extend.registered_permanent_residence,
									school : data.extend.school,
									graduation_time_string : data.extend.graduation_time
								});
								//datebox不认可yyyy-MM-dd这种格式，因此要么把时间转回原来的格式，要么导入easyui/locale/easyui-lang-zh_CN.js
								//这里采用转化格式，只需要在action的gsonBuilder设置即可
								//详情是编辑器，要用下面这种方式设置
								STAFF_EDIT.html(data.extend.details);
								if(data.gender == "男"){
									$("#staff-edit-gender-1").linkbutton("select");
								}else{
									$("#staff-edit-gender-2").linkbutton("select");
								}
							}
						}
					});
				}
			});
			
		}else{
			$.messager.alert("操作提醒","请选中一行！","warning");
		}
	});
	
	
	// 给工具条的删除按钮绑定点击事件
	$("#staff_tool_removeBtn").click(function(){
		//弹出删除面板之前先判断是否选中一行或者多行
		var arr = $("#staff").datagrid("getSelections");
		if(arr.length > 0){
			//弹出确认框
			$.messager.confirm("确认操作","您真的要删除这  <b>" + arr.length + "</b> 行吗？<br/>已绑定账号的员工档案将不会被删除。",function(flag){
				//flag为布尔值，点OK返回true，点取消返回false
				if(flag){
					var result = "";
					for(var i=0;i<arr.length;i++){
						result += arr[i].id + "丨";
					}
					//这里由于没有弹出对话框，没有去写一个表单，所以就采用jquery的ajax而不是easyui的form的submit方法
					$.ajax({
						url:"CRM/staff_remove.action",
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
								$("#staff").datagrid("load");
								//右下角提示信息
								$.messager.show({
									title:"操作提醒",
									msg:data + "条记录被成功删除！"
								});
							}else{
								$.messager.alert("操作提醒","没有删除任何数据","warning");
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
	$("#staff_tool_reloadBtn").click(function() {
		$("#staff").datagrid("reload");
	});

	// 给工具条的选择其他按钮绑定点击事件
	$("#staff_tool_redoBtn").click(function() {
		// 取出所有行，再取出选中行，
		var rows = $("#staff").datagrid("getRows");
		var selects = $("#staff").datagrid("getSelections");
		if (selects.length == 0) {
			// 如果一行都没选中，直接变成全选
			$("#staff").datagrid("selectAll");
		} else {
			/*
			 * 下面这个嵌套循环的意思是: 比较所有行和选中行，比较中， 没比较成功的时候设置未选中然后继续这一行比较选中行直到比完，
			 * 比较成功的话，说明原来该行是选中的，则把该行取消选中然后跳出循环，把所有行的下一行与选中行进行比较
			 */
			for ( var i = 0; i < rows.length; i++) {
				for ( var j = 0; j < selects.length; j++) {
					if (rows[i] == selects[j]) {
						$("#staff").datagrid("unselectRow", i);
						break;
					} else {
						$("#staff").datagrid("selectRow", i);
					}
				}
			}
		}

	});

	// 给工具栏的展开查询字段设置点击事件
	$("#staff_tool_fieldBtn").click(function() {
		// 按钮的options能返回本身，text是其值
		if ($("#staff_tool_fieldBtn").linkbutton("options").text == "展开查询字段") {
			$(".more").show();

			$("#staff_tool_fieldBtn").linkbutton({
				iconCls : "icon-reducesearch",
				text : "收起查询字段"
			}).linkbutton("select");
		} else {
			$(".more").hide();

			$("#staff_tool_fieldBtn").linkbutton({
				iconCls : "icon-addsearch",
				text : "展开查询字段"
			}).linkbutton("unselect");
		}
	});

	// 给工具条的重置查询按钮绑定点击事件
	$("#staff_tool_undoBtn").click(
			function() {
				// 清空关键字
				$("#staff-search-keywords").textbox("clear");
				// 清空在职状态
				$("#staff-search-entrystatus").combobox("clear");
				// 清空dateType，同时关闭非空验证，清空方法和开启验证方法一样会返回本身对象
				$("#staff-search-date-type").combobox("clear").combobox(
						"disableValidation");
				// 清空起始时间和结束时间
				$("#staff-search-date-from").datebox("clear");
				$("#staff-search-date-to").datebox("clear");
				// 清空性别
				$("#staff-search-gender").combobox("clear");
				// 清空身份证
				$("#staff-search-id-card").textbox("clear");
				// 清空婚姻
				$("#staff-search-marital-status").combobox("clear");
				//清空名族
				$("#staff-search-nation").textbox("clear");
				//清空职位
				$("#staff-search-post").combobox("clear");
				// 利用jquery的函数触发查询按钮的点击事件
				$('#staff_tool_searchBtn').trigger('click');
			});

	// 设置工具条搜索栏的关键字文本框
	$("#staff-search-keywords").textbox({
		width : 160,
		// 这是文本框没输入时默认的提示文本
		prompt : "姓名丨员工编号丨电话"
	});

	// 设置工具条搜索栏的职位下拉框
	$("#staff-search-post").combobox({
		width : 70,
		editable : false,
		prompt : "职位",
		url : "post_loadPost.action",
		// 基础数据值对应的名称
		valueField : "name",
		// 基础数据字段对应的名称
		textField : "name",
		// 下拉面板的高度
		panelHeight : "auto",
	});

	// 设置工具条搜索栏的入职状态下拉框
	$("#staff-search-entrystatus").combobox({
		width : 100,
		editable : false,
		prompt : "入职状态",
		data : [ {
			id : "在职",
			text : "在职"
		}, {
			id : "离职",
			text : "离职"
		} ],
		// 基础数据值对应的名称
		valueField : "id",
		// 基础数据字段对应的名称
		textField : "text",
		// 下拉面板的高度
		panelHeight : "auto",
	});

	// 设置工具条搜索栏的时间类型下拉框
	$("#staff-search-date-type").combobox({
		width : 100,
		editable : false,
		prompt : "时间类型",
		data : [ {
			id : "create_time",
			text : "创建时间"
		}, {
			id : "entry_date",
			text : "入职时间"
		}, {
			id : "dimission_date",
			text : "离职时间"
		} ],
		// 基础数据值对应的名称
		valueField : "id",
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

	var staffDate = {
		// 日期面板宽度为100
		width : 100,
		// 不允许不通过面板直接输入值
		editable : false,
		// 设置当两个日期被选中时，就开启前面的时间类型下拉框的非空验证
		// 如果非空验证不通过，说明之前没有选择下拉框的值，那么我们把下拉框打开
		onHidePanel : function() {
			// 当点击today或者close时，会触发该事件
			if (!$("#staff-search-date-type").combobox("enableValidation")
					.combobox("isValid")) {
				// 当返回false时说明验证失败，那么我们打开下拉框
				$("#staff-search-date-type").combobox("showPanel");
			}
		},
		onSelect : function() {
			// 注意开启验证方法会把下拉框对象返回来
			if (!$("#staff-search-date-type").combobox("enableValidation")
					.combobox("isValid")) {
				// 当返回false时说明验证失败，那么我们打开下拉框
				$("#staff-search-date-type").combobox("showPanel");
			}

		}
	};

	// 可以直接这样增加postDate的属性
	staffDate.prompt = "起始时间";
	// 设置工具条搜索栏的起始时间
	$("#staff-search-date-from").datebox(staffDate);

	// 也可以这样修改
	staffDate.prompt = "结束时间";
	// 设置工具条搜索栏的结束时间
	$("#staff-search-date-to").datebox(staffDate);

	// 设置搜索按钮
	$("#staff_tool_searchBtn").click(
			function() {
				if ($("#staff_tool").form("validate")) {
					$("#staff").datagrid(
							"load",
							{
								// datagrid的load方法通常用于额外传递参数进行查询
								"queryCriteria.keywords" : $("#staff-search-keywords").textbox("getValue"),
								"queryCriteria.entry_status" : $("#staff-search-entrystatus").combobox("getValue"),
								"queryCriteria.dateType" : $("#staff-search-date-type").combobox("getValue"),
								"queryCriteria.dateFrom" : $("#staff-search-date-from").datebox("getValue"),
								"queryCriteria.dateTo" : $("#staff-search-date-to").datebox("getValue"),
								"queryCriteria.gender" : $("#staff-search-gender").combobox("getValue"),
								"queryCriteria.id_card" : $("#staff-search-id-card").textbox("getValue"),
								"queryCriteria.marital_status" : $("#staff-search-marital-status").combobox("getValue"),
								"queryCriteria.nation" : $("#staff-search-nation").textbox("getValue"),
								"queryCriteria.post" : $("#staff-search-post").textbox("getValue"),
							});
				}
			});

	// 设置工具条下隐藏的的性别输入框
	$("#staff-search-gender").combobox({
		width : 73,
		editable : false,
		prompt : "性别",
		data : [ {
			id : "男",
			text : "男"
		}, {
			id : "女",
			text : "女"
		} ],
		// 基础数据值对应的名称
		valueField : "id",
		// 基础数据字段对应的名称
		textField : "text",
		// 下拉面板的高度
		panelHeight : "auto",
	});

	// 设置工具条下隐藏的婚姻下拉框
	// 婚姻状态搜索
	$("#staff-search-marital-status").combobox({
		width : 73,
		prompt : '婚姻',
		data : [ {
			id : '未婚',
			text : '未婚'
		}, {
			id : '已婚',
			text : '已婚'
		}, {
			id : '离异',
			text : '离异'
		}, {
			id : '丧偶',
			text : '丧偶'
		} ],
		editable : false,
		valueField : 'id',
		textField : 'text',
		panelHeight : 'auto'
	});

	// 设置工具条下隐藏的身份证搜索栏
	$("#staff-search-id-card").textbox({
		width : 230,
		// 这是文本框没输入时默认的提示文本
		prompt : "身份证(请输入精准18位身份证号码)",
		validType : "id_card",
		invalidMessage : "身份证格式不正确，且精确到18位",
		tipPostion : "bottom"
	});
	// 检查身份证是否合法的验证
	$.extend($.fn.validatebox.defaults.rules, {
		id_card : {
			validator : function(value) {
				return /^[0-9]{17}[xX0-9]$/.test(value);
			}
		}
	});

	
	
	// 设置工具条下隐藏的民族搜索栏
	$("#staff-search-nation").textbox({ 
		width : 232, 
		prompt :'民族(请输入精准关键字，如：汉族)',
		validType : 'nation',
		invalidMessage:'民族查询必须填入完整名称，不得小于 2 位，且末尾包含“族” 字', 
		tipPosition : 'bottom'
	 });

	// 检查民族是否合法的验证
	$.extend($.fn.validatebox.defaults.rules, {
		nation : {
			validator : function(value) {
				return /^.{1,4}族$/.test(value);
			}
		}
	});
	
	
	// Jquery当窗口大小改变时触发该事件
	$(window).resize(function() {
		$("#staff_dialog").dialog("center");
	});
});
