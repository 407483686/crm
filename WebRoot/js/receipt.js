
$(function(){
	//加载职位部门的数据
	$("#receipt").datagrid({
		url:'receipt_loadByPage.action', 
		striped:true,
	    columns:[[    
	        {field:'id',title:'自动编号',width:100,checkbox:true},
	        {field:'title',title:'收款标题',width:100},
	        {field:'order_sn',title:'关联订单',width:100},
	        {field:'cost',title:'收款金额(元)',width:100},
	        {field:'enter',title:'录入员',width:100},
	        {field:'create_time',title:'创建时间',width:100,sortable:true},
	    ]],
	    fitColumns:true,
	    rownumbers:true,
	    //分页工具栏
	    pagination:true,
	    //设置初始页面记录数，不设置的话初始是10
	    pageSize : 20,
	    //设置页面分页选项
	    pageList : [10,20,30,40,50],
	    toolbar: "#receipt_tool"
	});
	
	
	
	
	
	//给工具条的重置查询按钮绑定点击事件
	$("#receipt_tool_undoBtn").click(function(){
		//清空关键字
		$("#receipt-search-keywords").textbox("clear");
		//清空dateType，同时关闭非空验证，清空方法和开启验证方法一样会返回本身对象
		$("#receipt-search-date-type").combobox("clear").combobox("disableValidation");
		//清空起始时间和结束时间
		$("#receipt-search-date-from").datebox("clear");
		$("#receipt-search-date-to").datebox("clear");
		//利用jquery的函数触发查询按钮的点击事件
		$('#receipt_tool_searchBtn').trigger('click');	 
	});
	
	
	//给工具条的选择其他按钮绑定点击事件
	$("#receipt_tool_redoBtn").click(function(){
		//取出所有行，再取出选中行，
		var rows = $("#receipt").datagrid("getRows");
		var selects = $("#receipt").datagrid("getSelections");
		if(selects.length == 0){
			//如果一行都没选中，直接变成全选
			$("#receipt").datagrid("selectAll");
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
						$("#receipt").datagrid("unselectRow",i);
						break;
					}else{
						$("#receipt").datagrid("selectRow",i);
					}
				}
			}
		}
		
	});
	
	//给工具条的刷新表按钮绑定点击事件
	$("#receipt_tool_reloadBtn").click(function(){
		$("#receipt").datagrid("reload");
	});
	
	
	
	//给工具条的增加按钮绑定点击事件
	$("#receipt_tool_addBtn").click(function(){
		//获取div，设置为对话框
		$("#receipt_dialog").dialog({    
		    title: '新增面板',    
		    width: 400,    
		    height: 400,    
		    closed: false,    
		    cache: false,    
		    href: '/CRM/jsp/receipt/addReceipt.jsp',    
		    modal: true,
		    iconCls: "icon-add",
		    //设置对话框底部按钮 
		    //其handler属性为点击时触发，相当于onClick属性
		    buttons:[
			    {
					text:'保存',
					handler:function(){
						if($("#receipt-add").form("validate")){
							//这里不采用JQUERY的ajax，而采用easyUI的ajax表单提交
							$("#receipt-add").form("submit",{
								url:"CRM/receipt_addReceipt.action",
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
										$("#receipt_dialog").dialog("close");
										//刷新表单
										$("#receipt").datagrid("load");
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
										$.messager.alert('操作提醒','添加失败!','warning');
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
						$("#receipt_dialog").dialog("close");
					},
					iconCls:"icon-cross",
					size:"large",
				}
			],
			onClose : function(){
				$("#receipt-add-chooseOrder-dialog").dialog("destroy");
			}
		});    
	});
	
	//设置工具条搜索栏的关键字文本框
	$("#receipt-search-keywords").textbox({
		width : 150,
		//这是文本框没输入时默认的提示文本
		prompt : "收款标题|关联订单"
	});
	
	
	//设置工具条搜索栏的时间类型下拉框
	$("#receipt-search-date-type").combobox({
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
	var receiptDate = {
		//日期面板宽度为100
		width : 100,
		//不允许不通过面板直接输入值
		editable : false,
		//设置当两个日期被选中时，就开启前面的时间类型下拉框的非空验证
		//如果非空验证不通过，说明之前没有选择下拉框的值，那么我们把下拉框打开
		onHidePanel : function() {
			//当点击today或者close时，会触发该事件
			if(!$("#receipt-search-date-type").combobox("enableValidation").combobox("isValid")){
				//当返回false时说明验证失败，那么我们打开下拉框
				$("#receipt-search-date-type").combobox("showPanel");
			}
		},
		
		onSelect : function(){
			//注意开启验证方法会把下拉框对象返回来
			if(!$("#receipt-search-date-type").combobox("enableValidation").combobox("isValid")){
				//当返回false时说明验证失败，那么我们打开下拉框
				$("#receipt-search-date-type").combobox("showPanel");
			}
			
		}
	}
	
	
	//可以直接这样增加receiptDate的属性
	receiptDate.prompt = "起始时间";
	//设置工具条搜索栏的起始时间
	$("#receipt-search-date-from").datebox(receiptDate);
	
	
	//也可以这样修改
	receiptDate.prompt = "结束时间";
	//设置工具条搜索栏的结束时间
	$("#receipt-search-date-to").datebox(receiptDate);
	
	
	//设置搜索按钮
	$("#receipt_tool_searchBtn").click(function(){
		if($("#receipt_tool").form("validate")){
			$("#receipt").datagrid("load",{
				//datagrid的load方法通常用于额外传递参数进行查询
				"queryCriteria.keywords" : $("#receipt-search-keywords").textbox("getValue"),
				"queryCriteria.dateType" : $("#receipt-search-date-type").combobox("getValue"),
				"queryCriteria.dateFrom" : $("#receipt-search-date-from").datebox("getValue"),
				"queryCriteria.dateTo" : $("#receipt-search-date-to").datebox("getValue"),
			});
		}
	});
	
	
	
	
	//Jquery当窗口大小改变时触发该事件
	$(window).resize(function() { 
		$("#receipt_dialog").dialog("center");
	});
});
