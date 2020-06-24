$(function(){
	//为什么要写在js不在css里面设置样式，因为要随机就要计算，就必须得用到js
	//Math.random返回0到1（不包含）
	//*5 为 0到4.几（不包含5）
	//+1 为1到5.几（不包含6）
	//然后再向下取整，获得1到5之间的整数
	var rand = Math.floor(Math.random() * 5) + 1;

	//使用jquery设置body的属性
	$("body").css("background","url(/CRM/img/bg" + rand + ".jpg) no-repeat center center fixed")
	.css("background-size","cover").css("overflow","hidden");
	
	//设置登录面板
	$("#form-login").dialog({
		title: '登录面板', 
	    width: 370,    
	    height: 260, 
	    //不显示关闭按钮
	    closable : false,
	    draggable : false,
	    modal: false,
	    iconCls: "icon-lock",
	    //设置对话框底部按钮 
	    //其handler属性为点击时触发，相当于onClick属性
	    buttons:[
	    {
	    	//可以写id直接给按钮添加id属性,为了在下面的onOpen事件中，利用按钮获取其父元素再设置css让按钮居中
	    	id : "login-btn",
			text:'登录',
			handler:function(){
				if($("#form-login").form("validate")){
					//这里不采用JQUERY的ajax，而采用easyUI的ajax表单提交
					$("#form-login").form("submit",{
						url:"CRM/user_login.action",
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
								//右下角提示信息
								$.messager.show({
								    title:'操作提醒',
								    msg:'登录成功',
								    timeout:3000,
								    showType:'slide'
								});
								
								//跳转页面
								location.href = "/CRM/main.jsp";
							}else if(data == 0){
								//关闭进度条
								$.messager.progress('close'); 
								$.messager.alert('操作提醒','登录失败，用户名或密码不正确！','warning',function(){
									//这是在失败之后，自动选中那一行输入框，让用户修改,是js的原生方法，获取文本框对象之后select就能选中
									$("#login-account").textbox("textbox").select();
								});
							}else if(data == -1){
								//关闭进度条
								$.messager.progress('close'); 
								$.messager.alert('操作提醒','登录失败，用户处于冻结状态！','warning',function(){
									//这是在失败之后，自动选中那一行输入框，让用户修改,是js的原生方法，获取文本框对象之后select就能选中
									$("#login-account").textbox("textbox").select();
								});
							}
						}
					});
				}
			},
			iconCls:"icon-go",
			size:"large",
		}
		],
		//onOpen：在打开面板之后触发
		onOpen : function(){
			$(function(){
				$("#login-btn").parent().css("text-align","center");
				$(".window").css("background","#A2C2B5");
			});
		}
	});
	
	//设置用户名文本框
	$("#login-account").textbox({
		width:220,
		height:32,
		required:true,
		iconCls:"icon-man",
		//规定长度在2到10之间
		validType:"length[2,10]",
		missingMessage:"请输入用户名",
		invalidMessage:"用户名长度应在2位到10位之间"
  	});
  		
	//设置密码文本框
	$("#login-password").textbox({
		width:220,
		height:32,
		required:true,
		iconCls:"icon-lock2",
		//规定长度在6到30之间
		validType:"length[6,30]",
		missingMessage:"请输入密码",
		invalidMessage:"密码长度应在6位到30位之间"
	});
	
	/*//设置快速申请超链接
	$("#register").click(function(){
		//获取div，设置为对话框
		$("#user_dialog").dialog({    
		    title: '新增面板',    
		    width: 400,    
		    height: 300,    
		    closed: false,    
		    cache: false,    
		    href: '/CRM/jsp/user/addUserByLogin.jsp',    
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
								url:"CRM/user_addUser.action",
								//本来也可以在这里用onSubmit属性进行表单校验，不过上面已经校验过了这里就不作了
								onSubmit:function(){
									$.messager.progress({
										text:"正在处理中..."
									});
									//登录页面申请的账号的默认状态为冻结
									$("#form-add :hidden:eq(0)").val("冻结");
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
	});*/
	
});
