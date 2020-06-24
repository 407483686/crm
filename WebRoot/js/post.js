
$(function(){
	//加载职位部门的数据
	$("#post").datagrid({
		url:'post_loadByPage.action', 
		striped:true,
	    columns:[[    
	        {field:'id',title:'自动编号',width:100,checkbox:true},    
	        {field:'name',title:'职位名称',width:100},    
	        {field:'create_time',title:'创建时间',width:100,sortable:true}    
	    ]],
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
	    toolbar: "#post_tool"
	});
	
	//给工具条的重置查询按钮绑定点击事件
	$("#post_tool_undoBtn").click(function(){
		//清空关键字
		$("#post-search-keywords").textbox("clear");
		//清空dateType，同时关闭非空验证，清空方法和开启验证方法一样会返回本身对象
		$("#post-search-date-type").combobox("clear").combobox("disableValidation");
		//清空起始时间和结束时间
		$("#post-search-date-from").datebox("clear");
		$("#post-search-date-to").datebox("clear");
		//利用jquery的函数触发查询按钮的点击事件
		$('#post_tool_searchBtn').trigger('click');	 
	});
	
	
	//给工具条的选择其他按钮绑定点击事件
	$("#post_tool_redoBtn").click(function(){
		//取出所有行，再取出选中行，
		var rows = $("#post").datagrid("getRows");
		var selects = $("#post").datagrid("getSelections");
		if(selects.length == 0){
			//如果一行都没选中，直接变成全选
			$("#post").datagrid("selectAll");
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
						$("#post").datagrid("unselectRow",i);
						break;
					}else{
						$("#post").datagrid("selectRow",i);
					}
				}
			}
		}
		
	});
	
	//给工具条的刷新表按钮绑定点击事件
	$("#post_tool_reloadBtn").click(function(){
		$("#post").datagrid("reload");
	});
	
	//给工具条的删除按钮绑定点击事件
	/*$("#post_tool_removeBtn").click(function(){
		//弹出删除面板之前先判断是否选中一行或者多行
		var arr = $("#post").datagrid("getSelections");
		if(arr.length > 0){
			//弹出确认框
			$.messager.confirm("确认操作","您真的要删除这  <b>" + arr.length + "</b> 行吗？",function(flag){
				//flag为布尔值，点OK返回true，点取消返回false
				if(flag){
					var result = "";
					for(var i=0;i<arr.length;i++){
						result += arr[i].id + "丨";
					}
					//这里由于没有弹出对话框，没有去写一个表单，所以就采用jquery的ajax而不是easyui的form的submit方法
					$.ajax({
						url:"CRM/post_removePost.action",
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
								$("#post").datagrid("load");
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
	
	//给工具条的修改按钮绑定点击事件
	$("#post_tool_editBtn").click(function(){
		//弹出修改面板之前先判断是否选中一行
		if($("#post").datagrid("getSelections").length == 1){
			//获取div，设置为对话框
			$("#post_dialog").dialog({    
			    title: '修改面板',    
			    width: 400,    
			    height: 190,    
			    closed: false,    
			    cache: false,    
			    href: '/CRM/jsp/post/editPost.jsp',    
			    modal: true,
			    iconCls: "icon-edit",
			    //设置对话框底部按钮 
			    //其handler属性为点击时触发，相当于onClick属性
			    buttons:[
				    {
						text:'保存',
						handler:function(){
							if($("#form-edit").form("validate")){
								//这里不采用JQUERY的ajax，而采用easyUI的ajax表单提交
								$("#form-edit").form("submit",{
									url:"CRM/post_editPost.action",
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
											$("#post_dialog").dialog("close");
											//刷新表单
											$("#post").datagrid("load");
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
											$.messager.alert('操作提醒','职位名称已存在，无法修改！','warning',function(){
												//这是在失败之后，自动选中那一行输入框，让用户修改,是js的原生方法，获取文本框对象之后select就能选中
												$("#post-edit-name").textbox("textbox").select();
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
							$("#post_dialog").dialog("close");
						},
						iconCls:"icon-cross",
						size:"large",
					}
				],
				//在加载页面到对话框之前，把选中的行的部门名称赋给页面中的textbox
				onLoad:function(){
					$("#form-edit :hidden:eq(0)").val($("#post").datagrid("getSelected").id);
					//easyui的textbox不能用$('#text').val('text');赋值
					//其他类似，唯一有一个validatebox,这个还是可以用原来jquery的赋值方式。
					$("#post-edit-name").textbox("setValue",$("#post").datagrid("getSelected").name);
				}
			}); 
		}else{
			$.messager.alert("操作提醒","请选中一行！","warning");
		}
	});
	
	//给工具条的增加按钮绑定点击事件
	$("#post_tool_addBtn").click(function(){
		//获取div，设置为对话框
		$("#post_dialog").dialog({    
		    title: '新增面板',    
		    width: 400,    
		    height: 190,    
		    closed: false,    
		    cache: false,    
		    href: '/CRM/jsp/post/addPost.jsp',    
		    modal: true,
		    iconCls: "icon-add",
		    //设置对话框底部按钮 
		    //其handler属性为点击时触发，相当于onClick属性
		    buttons:[
			    {
					text:'保存',
					handler:function(){
						if($("#form-add").form("validate")){
							//这里不采用JQUERY的ajax，而采用easyUI的ajax表单提交
							$("#form-add").form("submit",{
								url:"CRM/post_addPost.action",
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
										$("#post_dialog").dialog("close");
										//刷新表单
										$("#post").datagrid("load");
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
										$.messager.alert('操作提醒','职位名称被占用！','warning',function(){
											//这是在失败之后，自动选中那一行输入框，让用户修改,是js的原生方法，获取文本框对象之后select就能选中
											$("#post-add-name").textbox("textbox").select();
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
						$("#post_dialog").dialog("close");
					},
					iconCls:"icon-cross",
					size:"large",
				}
			]
		});    
	});*/
	
	//设置工具条搜索栏的关键字文本框
	$("#post-search-keywords").textbox({
		width : 150,
		//这是文本框没输入时默认的提示文本
		prompt : "职位"
	});
	
	
	//设置工具条搜索栏的时间类型下拉框
	$("#post-search-date-type").combobox({
		width : 100,
		editable : false,
		prompt : "时间类型",
		data : [{
			id : "create_time",
			text : "创建时间"
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
	
	
	/*
	 * 这里要介绍一种新的给多个同样组件设置同样属性的方法
	 * 以往都是写重复代码，其实可以定义一个var对象，设置好值，然后组件方法直接传入即可，具体见下
	 * */
	var postDate = {
		//日期面板宽度为100
		width : 100,
		//不允许不通过面板直接输入值
		editable : false,
		//设置当两个日期被选中时，就开启前面的时间类型下拉框的非空验证
		//如果非空验证不通过，说明之前没有选择下拉框的值，那么我们把下拉框打开
		onHidePanel : function() {
			//当点击today或者close时，会触发该事件
			if(!$("#post-search-date-type").combobox("enableValidation").combobox("isValid")){
				//当返回false时说明验证失败，那么我们打开下拉框
				$("#post-search-date-type").combobox("showPanel");
			}
		},
		
		onSelect : function(){
			//注意开启验证方法会把下拉框对象返回来
			if(!$("#post-search-date-type").combobox("enableValidation").combobox("isValid")){
				//当返回false时说明验证失败，那么我们打开下拉框
				$("#post-search-date-type").combobox("showPanel");
			}
			
		}
	}
	
	
	//可以直接这样增加postDate的属性
	postDate.prompt = "起始时间";
	//设置工具条搜索栏的起始时间
	$("#post-search-date-from").datebox(postDate);
	
	
	//也可以这样修改
	postDate.prompt = "结束时间";
	//设置工具条搜索栏的结束时间
	$("#post-search-date-to").datebox(postDate);
	
	
	//设置搜索按钮
	$("#post_tool_searchBtn").click(function(){
		if($("#post_tool").form("validate")){
			$("#post").datagrid("load",{
				//datagrid的load方法通常用于额外传递参数进行查询
				"queryCriteria.keywords" : $("#post-search-keywords").textbox("getValue"),
				"queryCriteria.dateType" : $("#post-search-date-type").combobox("getValue"),
				"queryCriteria.dateFrom" : $("#post-search-date-from").datebox("getValue"),
				"queryCriteria.dateTo" : $("#post-search-date-to").datebox("getValue")
			});
		}
	});
	
	
	
	
	//Jquery当窗口大小改变时触发该事件
	$(window).resize(function() { 
		$("#post_dialog").dialog("center");
	});
});
