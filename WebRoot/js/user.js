
$(function(){
	//加载职位部门的数据
	$("#user").datagrid({
		url:'/CRM/user_loadByPage.action',  
		striped:true,
	    columns:[[    
	        {field:'id',title:'自动编号',width:100,checkbox:true},    
	        {field:'accounts',title:'用户名称',width:100}, 
	        {field:'staff_name',title:'真实姓名',width:100,
	        	formatter : function(value,row,index){
	        		if(row.staff != null){
	        			return row.staff.name;
	        		}
	        		
	        	}
	        }, 
	        {field:'staff_post',title:'职位',width:100,
	        	formatter : function(value,row,index){
	        		if(row.staff != null){
	        			return row.staff.post;
	        		}
	        		
	        	}
	        }, 
	        {field:'last_login_time',title:'最后登录时间',width:100,sortable:true},
	        {field:'last_login_ip',title:'最后登录ip',width:100},
	        {field:'login_count',title:'登录次数',width:80,sortable:true},
	        {field:'create_time',title:'用户创建时间',width:100,sortable:true},
	        //formatter格式化器函数，只是改变在页面上显示的效果，不会改变字段名字和值
	        //fixed:如果为true，在"fitColumns"设置为true的时候阻止其自适应宽度。
	        {field:'state',title:'状态',width:60,sortable:true,fixed:true,formatter:function(value,row){
	        	//value是字段字段的值，如正常或者冻结
	        	//row是选中的行对象，可以通过row.id获取id
	        	var state = "";
	        	
	        	switch(value){
		        	//由于这是动态加载出来的，因此样式最好直接写在这里面或者是加载成功之后的事件，不然可能设置的时候数据还没出来，就没效果了
	        		//设置相同的user-state是为了一起触发点击事件，设置不同的user-state-*，是为了设置不同的按钮样式
	        		case "正常" :
		        		state = "<a href='javascript:void(0)' user-id='"+ row.id +"' user-state='正常' class='user-state user-state-1' style='height:18px;margin-left:11px'></a>";
		        		
		        		break;
		        		
		        	case "冻结":
		        		state = "<a href='javascript:void(0)'  user-id='"+ row.id +"' user-state='冻结' class='user-state user-state-2' style='height:18px;margin-left:11px'></a>";
		        		
		        		break;
	        	}
	        	
	        	return state;
	        }},
	    ]],
	    //当加载表单数据成功之后，给状态设置按钮样式和点击事件
	    onLoadSuccess : function(){
	    	//给两个按钮设置样式
	    	$(".user-state-1").linkbutton({
	    		iconCls : "icon-ok",
	    		plain : true
	    	});
	    	$(".user-state-2").linkbutton({
	    		iconCls : "icon-lock",
	    		plain : true
	    	});
	    	
	    	//给两个按钮设置点击事件
	    	$(".user-state").click(function(){
	    		//$(this)表示调用方法的当前对象，和js的this差不多，只不过这个是jquery对象
	    		//在这里表示按钮
	    		//attr()是jq方法，获取或者设置（看参数）属性
	    		var id = $(this).attr("user-id");
	    		var state = $(this).attr("user-state");
	    		
	    		switch(state){
	    			case "正常":
	    				//确定框点击确认会返回true
	    				$.messager.confirm("确认","是否冻结该用户？",function(flag){
	    					if(flag){
	    						//如果用户确定要冻结，那么发送ajax请求
	    						$.ajax({
	    							url:"CRM/user_editOnlyState.action",
	    							type:"POST",
	    							data:{
	    								id : id,
	    								state : "冻结"
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
	    									$("#user").datagrid("load");
	    									//右下角提示信息
	    									$.messager.show({
	    										title:"操作提醒",
	    										msg:"账号冻结成功！"
	    									});
	    								}else{
	    									$.messager.alert("操作提醒","未知原因冻结失败","warning");
	    								}
	    							}
	    						});
	    					}
	    				});
	    				
	    				break;
	    				
	    			case "冻结":
	    				$.messager.confirm("确认","是否解冻该用户？",function(flag){
	    					if(flag){
	    						//如果用户确定要解冻，那么发送ajax请求
	    						$.ajax({
	    							url:"CRM/user_editOnlyState.action",
	    							type:"POST",
	    							data:{
	    								id : id,
	    								state : "正常"
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
	    									$("#user").datagrid("load");
	    									//右下角提示信息
	    									$.messager.show({
	    										title:"操作提醒",
	    										msg:"账号解冻成功！"
	    									});
	    								}else{
	    									$.messager.alert("操作提醒","未知原因解冻失败","warning");
	    								}
	    							}
	    						});
	    					}
	    				});
	    				
	    				break;
	    		}
	    	});
	    },
	    fitColumns:true,
	    rownumbers:true,
	    border:false,
	    sortName:"create_time",
	    sortOrder:"asc",
	    //设置上面两个还不够，下面这个属性默认值是true，表示只以json数据的顺序来判断，而忽略sortName和sortOrder，
	    //因此我们需要设置为false才能排序，至于列的属性sortable,加了之后，表示可以点击进行排序
	    remoteSort:false,
	    //分页工具栏
	    pagination:true,
	    //设置初始页面记录数，不设置的话初始是10
	    pageSize : 20,
	    //设置页面分页选项
	    pageList : [10,20,30,40,50],
	    toolbar: "#user_tool"
	});
	
	
	//给工具条的重置查询按钮绑定点击事件
	$("#user_tool_undoBtn").click(function(){
		//清空关键字
		$("#user-search-keywords").textbox("clear");
		//清空用户状态
		$("#user-search-state").combobox("clear");
		//清空dateType，同时关闭非空验证，清空方法和开启验证方法一样会返回本身对象
		$("#user-search-date-type").combobox("clear").combobox("disableValidation");
		//清空起始时间和结束时间
		$("#user-search-date-from").datebox("clear");
		$("#user-search-date-to").datebox("clear");
		//利用jquery的函数触发查询按钮的点击事件
		$('#user_tool_searchBtn').trigger('click');	 
	});
	
	
	//给工具条的选择其他按钮绑定点击事件
	$("#user_tool_redoBtn").click(function(){
		//取出所有行，再取出选中行，
		var rows = $("#user").datagrid("getRows");
		var selects = $("#user").datagrid("getSelections");
		if(selects.length == 0){
			//如果一行都没选中，直接变成全选
			$("#user").datagrid("selectAll");
		}else{
			/*
			 * 下面这个嵌套循环的意思是:
			 * 	比较所有行和选中行，比较中，
			 * 		没比较成功的时候设置未选中然后继续这一行比较选中行直到比完，
			 * 		比较成功的话，说明原来该行是选中的，则把该行取消选中然后跳出循环，把所有行的下一行与选中行进行比较
			 * */
			for(var i=0;i<rows.length;i++){
				for(var j=0;j<selects.length;j++){
					if(rows[i] == selects[j]){
						$("#user").datagrid("unselectRow",i);
						break;
					}else{
						$("#user").datagrid("selectRow",i);
					}
				}
			}
		}
		
	});
	
	
	//给工具条的刷新表按钮绑定点击事件
	$("#user_tool_reloadBtn").click(function(){
		$("#user").datagrid("reload");
	});
	
	
	
	//给工具条的删除按钮绑定点击事件
	$("#user_tool_removeBtn").click(function(){
		//弹出删除面板之前先判断是否选中一行或者多行
		var arr = $("#user").datagrid("getSelections");
		if(arr.length > 0){
			//弹出确认框
			$.messager.confirm("确认操作","您真的要删除这  <b>" + arr.length + "</b> 行吗？",function(flag){
				//flag为布尔值，点OK返回true，点取消返回false
				if(flag){
					var result = "";
					var staff_ids = "";
					for(var i=0;i<arr.length;i++){
						result += arr[i].id + "丨";
						staff_ids += arr[i].staff.id + "丨";
					}
					//这里由于没有弹出对话框，没有去写一个表单，所以就采用jquery的ajax而不是easyui的form的submit方法
					$.ajax({
						url:"CRM/user_removeUser.action",
						type:"POST",
						data:{
							//可以这样写，后面一个会被识别成js对象
							result : result,
							staff_ids : staff_ids
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
								$("#user").datagrid("load");
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
	
	
	//设置工具条搜索栏的关键字文本框
	$("#user-search-keywords").textbox({
		width : 150,
		//这是文本框没输入时默认的提示文本
		prompt : "用户名"
	});
	
	//设置工具条的用户状态
	$("#user-search-state").combobox({
		width : 70,
		editable : false,
		prompt : "状态",
		data : [{
			id : "正常",
			text : "正常"
		},{
			id : "冻结",
			text : "冻结"
		}],
		//基础数据值对应的名称
		valueField : "id",
		//基础数据字段对应的名称
		textField : "text",
		panelHeight : "auto"
	});
	
	//设置工具条搜索栏的时间类型下拉框
	$("#user-search-date-type").combobox({
		width : 100,
		editable : false,
		prompt : "时间类型",
		data : [{
			id : "create_time",
			text : "创建时间"
		},{
			id : "last_login_time",
			text : "最后登录时间"
		}],
		//基础数据值对应的名称
		valueField : "id",
		//基础数据字段对应的名称
		textField : "text",
		//下拉面板的高度
		panelHeight : "auto",
		//设置验证：非空
		required : true,
		//设置无效提示信息的出现位置
		tipPosition : "left",
		//当用户没有输入时，出现的提示信息
		missingMessage : "请选择时间类型",
		//如果没选择后面的起始时间和结束时间时，先不启用非空验证
		//不启用的时候相当于没验证，此时表单的validate方法能通过，
		//如果点击了后面的时间，那么开启验证就必须要填，不填表单的validate无法通过
		novalidate : true,
		
	});
	
	
	var postDate = {
		//日期面板宽度为100
		width : 100,
		//不允许不通过面板直接输入值
		editable : false,
		//设置当两个日期被选中时，就开启前面的时间类型下拉框的非空验证
		//如果非空验证不通过，说明之前没有选择下拉框的值，那么我们把下拉框打开
		onHidePanel : function() {
			//当点击today或者close时，会触发该事件
			if(!$("#user-search-date-type").combobox("enableValidation").combobox("isValid")){
				//当返回false时说明验证失败，那么我们打开下拉框
				$("#user-search-date-type").combobox("showPanel");
			}
		},
		
		onSelect : function(){
			//注意开启验证方法会把下拉框对象返回来
			if(!$("#user-search-date-type").combobox("enableValidation").combobox("isValid")){
				//当返回false时说明验证失败，那么我们打开下拉框
				$("#user-search-date-type").combobox("showPanel");
			}
		}
	};
	
	
	//可以直接这样增加postDate的属性
	postDate.prompt = "起始时间";
	//设置工具条搜索栏的起始时间
	$("#user-search-date-from").datebox(postDate);
	
	
	//也可以这样修改
	postDate.prompt = "结束时间";
	//设置工具条搜索栏的结束时间
	$("#user-search-date-to").datebox(postDate);
	
	
	//设置搜索按钮
	$("#user_tool_searchBtn").click(function(){
		if($("#user_tool").form("validate")){
			$("#user").datagrid("load",{
				//datagrid的load方法通常用于额外传递参数进行查询
				"queryCriteria.keywords" : $("#user-search-keywords").textbox("getValue"),
				"queryCriteria.state" : $("#user-search-state").combobox("getValue"),
				"queryCriteria.dateType" : $("#user-search-date-type").combobox("getValue"),
				"queryCriteria.dateFrom" : $("#user-search-date-from").datebox("getValue"),
				"queryCriteria.dateTo" : $("#user-search-date-to").datebox("getValue")
			});
		}
	});
	
	
	//设置新增按钮
	$("#user_tool_addBtn").click(function(){
		//获取div，设置为对话框
		$("#user_dialog").dialog({    
		    title: '新增面板',    
		    width: 400,    
		    height: 300, 
		    //closed表示对话框是否隐藏
		    closed: false,    
		    cache: false,    
		    href: '/CRM/jsp/user/addUser.jsp',    
		    modal: true,
		    iconCls: "icon-add",
		    //设置对话框底部按钮 
		    //其handler属性为点击时触发，相当于onClick属性
		    buttons:[
			    {
					text:'保存',
					handler:function(){
						if($("#user-add").form("validate")){
							//这里不采用JQUERY的ajax，而采用easyUI的ajax表单提交
							$("#user-add").form("submit",{
								url:"CRM/user_addUser.action",
								//本来也可以在这里用onSubmit属性进行表单校验，不过上面已经校验过了这里就不作了
								onSubmit:function(){
									$.messager.progress({
										text:"正在处理中..."
									});
								},
								success: function(data){
									if(data == 1){
										//关闭进度条
										$.messager.progress('close'); 
										//关闭对话框
										$("#user_dialog").dialog("close");
										//刷新表单
										$("#user").datagrid("load");
										//右下角提示信息
										$.messager.show({
										    title:'操作提醒',
										    msg:'添加成功',
										    timeout:3000,
										    showType:'slide'
										});
									}else{
										//关闭进度条
										$.messager.progress('close'); 
										$.messager.alert('操作提醒','用户名被占用！','warning',function(){
											//这是在失败之后，自动选中那一行输入框，让用户修改,是js的原生方法，获取文本框对象之后select就能选中
											$("#user-add-password").textbox("textbox").select();
										});
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
						$("#user_dialog").dialog("close");
					},
					iconCls:"icon-cross",
					size:"large",
				}
			]
		});    
	});
	
	//设置修改按钮
	//给工具条的修改按钮绑定点击事件
	$("#user_tool_editBtn").click(function(){
		//弹出修改面板之前先判断是否选中一行
		if($("#user").datagrid("getSelections").length == 1){
			//获取div，设置为对话框
			$("#user_dialog").dialog({    
			    title: '修改面板',    
			    width: 400,    
			    height: 300,    
			    closed: false,    
			    cache: false,    
			    href: '/CRM/jsp/user/editUser.jsp',    
			    modal: true,
			    iconCls: "icon-edit",
			    //设置对话框底部按钮 
			    //其handler属性为点击时触发，相当于onClick属性
			    buttons:[
				    {
						text:'保存',
						handler:function(){
							if($("#user-edit").form("validate")){
								//这里不采用JQUERY的ajax，而采用easyUI的ajax表单提交
								$("#user-edit").form("submit",{
									url:"CRM/user_edit.action",
									//本来也可以在这里用onSubmit属性进行表单校验，不过上面已经校验过了这里就不作了
									onSubmit:function(){
										$.messager.progress({
											text:"正在处理中..."
										});
									},
									success: function(data){
										if(data == 1){
											//关闭进度条
											$.messager.progress('close'); 
											//关闭对话框
											$("#user_dialog").dialog("close");
											//刷新表单
											$("#user").datagrid("load");
											//右下角提示信息
											$.messager.show({
											    title:'操作提醒',
											    msg:'修改成功',
											    timeout:3000,
											    showType:'slide'
											});
										}else{
											//关闭进度条
											$.messager.progress('close'); 
											$.messager.alert('操作提醒','新密码不能与原密码相同','warning',function(){
												//这是在失败之后，自动选中那一行输入框，让用户修改,是js的原生方法，获取文本框对象之后select就能选中
												$("#user-edit-password").textbox("textbox").select();
											});
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
							$("#user_dialog").dialog("close");
						},
						iconCls:"icon-cross",
						size:"large",
					}
				],
				//在加载页面到对话框之前，把选中的行的部门名称赋给页面中的textbox
				onLoad:function(){
					$("#user-edit :hidden:eq(0)").val($("#user").datagrid("getSelected").id);
					//easyui的textbox不能用$('#text').val('text');赋值
					//其他类似，唯一有一个validatebox,这个还是可以用原来jquery的赋值方式。
					//设置用户名
					$("#user-edit-account").textbox("setValue",$("#user").datagrid("getSelected").accounts);
					//设置用户关联档案的名称
					//combogrid组件的setValue是设置组件值，JQ的val又用不了，只能用加载表单把参数传进去
					$("#user-edit").form("load",{
						staff_id : $("#user").datagrid("getSelected").staff.name
					});
					//设置状态按钮的开关情况
					var state = $("#user").datagrid("getSelected").state;
					if(state == "正常"){
						//如果选中那行的状态时正常，启用开关按钮同时给隐藏的state输入项赋值
						$("#user-edit-state-button").switchbutton("check");
						$("#user-edit-state").val("正常");
					}else{
						$("#user-edit-state-button").switchbutton("uncheck");
						$("#user-edit-state").val("冻结");
					}
				}
			}); 
		}else{
			$.messager.alert("操作提醒","请选中一行！","warning");
		}
	});
	
	
	
	//Jquery当窗口大小改变时触发该事件
	$(window).resize(function() { 
		$("#user_dialog").dialog("center");
	});
});
