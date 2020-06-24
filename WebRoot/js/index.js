//用来设置main.jsp中选项卡的参数
$(function(){
	editor_tool = [ 'source', '|', 'formatblock', 'fontname', 'fontsize', '|',
			'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'link',
			'removeformat', '|', 'justifyleft', 'justifycenter',
			'justifyright', '|', 'insertorderedlist', 'insertunorderedlist',
			'|', 'emoticons', 'image', 'baidumap', '|', 'fullscreen' ];

	
	
	//设置面板右键的功能
	$('#tabs').tabs({
		fit : true,
		border : false,
		onClose : function(title,index){
			switch(title)
			{
				case "员工档案":
					$("#staff_dialog").dialog("destroy");
					$("#details_dialog").dialog("destroy");
					$("#staff_edit_dialog").dialog("destroy");
					break;
					
				case "登录账号":
					$("#user_dialog").dialog("destroy");
					break;
					
				case "职位部门":
					$("#post_dialog").dialog("destroy");
					break;
					
				case "产品信息":
					$("#product_dialog").dialog("destroy");
					$("#product_details_dialog").dialog("destroy");
					break;
					
				case "入库记录":
					$("#inlib_dialog").dialog("destroy");
					$("#inlib_details_dialog").dialog("destroy");
					$("#inlib-product-dialog").dialog("destroy");
					$("#inlib-productMan-dialog").dialog("destroy");
					break;
					
				case "产品信息":
					$("#client_dialog").dialog("destroy");
					$("#client_details_dialog").dialog("destroy");
					break;
					
				case "跟单记录":
					$("#documentary_dialog").dialog("destroy");
					$("#documentary-add-chooseCompany-dialog").dialog("destroy");
					$("#documentary-add-chooseStaff-dialog").dialog("destroy");
					break;
					
				case "销售订单":
					$("#order_dialog").dialog("destroy");
					$("#order_edit_dialog").dialog("destroy");
					$("#order_details_dialog").dialog("destroy");
					$("#order_documentary_dialog").dialog("destroy");
					$("#order_products_dialog").dialog("destroy");
					break;
					
				case "收款记录":
					$("#receipt_dialog").dialog("destroy");
					$("#receipt-add-chooseOrder-dialog").dialog("destroy");
					break;
					
				case "出库记录":
					$("#outlib_dialog").dialog("destroy");
					break;
					
				case "工作计划":
					$("#work_dialog").dialog("destroy");
					$("#work_edit_dialog").dialog("destroy");
					break;
					
				case "分配任务":
					$("#allo_dialog").dialog("destroy");
					$("#allo_edit_dialog").dialog("destroy");
					$("#addAlloChooseStaff-dialog").dialog("destroy");
					break;
					
				case "通知管理":
					$("#inform_dialog").dialog("destroy");
					$("#inform_details_dialog").dialog("destroy");
					$("#inform_edit_dialog").dialog("destroy");
					break;
					
				case "私信收发":
					$("#letter_dialog").dialog("destroy");
					$("#letter_details_dialog").dialog("destroy");
					$("#addLetterChooseStaff-dialog").dialog("destroy");
					break;
					
				case "操作日志":
					$("#log_dialog").dialog("destroy");
					break;	
					
				case "权限管理":
					$("#auth_dialog").dialog("destroy");
					break;	
			}
		},
		onContextMenu : function(e,title,index){
			//e是js的内定对象event，在这里特指右键点击事件
			//该方法通知浏览器不要执行与此事件关联的默认动作
		    //即屏蔽了浏览器在tab页上的鼠标右键事件
			e.preventDefault();
			
			//弹出菜单
			$("#youjian_menu").menu("show",{
				//e.pageX代表鼠标离页面左边缘的长度，即鼠标当前位置的X坐标
				//e.pageY代表鼠标离页面上边缘的长度，即鼠标当前位置的Y坐标
				left : e.pageX,
				top : e.pageY
			});
			
			//如果是起始页，那么禁用第一个子菜单，即关闭当前页的选项,参数是子菜单对象，用设置好的class获取
			if(index == 0){
				$("#youjian_menu").menu("disableItem",$(".closecur"));
			}else{
				$("#youjian_menu").menu("enableItem",$(".closecur"));
			}
			
			//index是从左到右第几个选项卡页面，从0开始
			//三个关闭方法
			$("#youjian_menu").menu({
				//设置菜单的点击事件,参数item是当前选择的菜单项对象
				onClick:function(item){
					//获取所有选项卡
					var arr = $('#tabs').tabs("tabs");
					//利用switch进行判断可比if少写代码，item.text即为菜单的文本
					switch(item.text){
						case "关闭":
							//传入当前的选项卡索引，调用选项卡的close方法关闭
							if(index != 0){
								$('#tabs').tabs("close",index);
							}
							break;
					
						case "关闭所有":
							//从选项卡后面往前面删除，直到初始页即i=0为止，其他的都关掉
							for(var i = arr.length-1;i > 0;i--){
								$('#tabs').tabs("close",i);
							}
							break;
							
						case "关闭其他所有":
							for(var i = arr.length-1;i > 0;i--){
								//页面索引不是当前页时关闭
								if(i != index){
									$('#tabs').tabs("close",i);
								}
							}
							$('#tabs').tabs("select",1);
							break;
					}
				},
			});
		},
	});
});

//用来加载main.jsp中左侧的菜单栏
$(function(){
	$("#menu_tree").tree({
		url:"CRM/menu_loadMenus.action",
		lines:true,//显示虚线
		animate:true,//展开和折叠时有动画效果
		onClick:function(node){
			//如果菜单存在网址，那就新建选项卡，这样可以防止父菜单生成选项卡
			if(node.attributes.href){
				
				//判断选项卡是否已经生成
				if($("#tabs").tabs("exists",node.text)){
					//若存在，则选择对应选项卡
					$("#tabs").tabs("select",node.text);
				}else{
					//若不存在，则新建选项卡
					$("#tabs").tabs("add",{
						title:node.text,
						iconCls:node.iconCls,
						closable:true,
						href:node.attributes.href,
					});
				}
			}
		}
	});
});


//设置右上角的推出系统和修改密码按钮
$(function(){
	$("#main_editbBtn").click(function(){
		$("#main_dialog").dialog({
			title: '修改密码', 
		    width: 400,   
		    href : "jsp/user/editUserPassword.jsp",
		    height: 300, 
		    //是否显示关闭按钮
		    closable : true,
		    draggable : false,
		    modal: true,
		    iconCls: "icon-edit",
		    //设置对话框底部按钮 
		    //其handler属性为点击时触发，相当于onClick属性
		    buttons:[
		    {
				text:'保存',
				handler:function(){
					if($("#form-editPassword").form("validate")){
						//这里不采用JQUERY的ajax，而采用easyUI的ajax表单提交
						$("#form-editPassword").form("submit",{
							url:"/CRM/user_editPassword.action",
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
									$("#main_dialog").dialog("close");
									//右下角提示信息
									$.messager.show({
									    title:'操作提醒',
									    msg:'修改密码成功',
									    timeout:3000,
									    showType:'slide'
									});
									$.messager.alert("操作提醒","密码修改成功,请重新登录","info",function(){
										location.href = "/CRM/login.jsp";
									});
								}else{
									//关闭进度条
									$.messager.progress('close'); 
									$.messager.alert('操作提醒','修改密码失败，新密码不能与原来的密码一样','warning',function(){
										//function会在关闭确认框之后自动调用
										//这是在失败之后，自动选中那一行输入框，让用户修改,是js的原生方法，获取文本框对象之后select就能选中
										$("#user-editPassword-password").textbox("textbox").select();
									});
								}
							}
						});
					}
				},
				iconCls:"icon-accept",
				size:"large",
			},{
				text:'取消',
				handler:function(){
					$("#main_dialog").dialog("close");
				},
				iconCls:"icon-cross",
				size:"large",
			}
			],
			onLoad:function(){
				
			},
		});
	});
	
	$("#main_exitbBtn").click(function(){
		$.messager.confirm("操作提醒","是否退出系统?",function(flag){
			if(flag){
				//由于js无法直接访问到session域，故而需要绕个弯去后台
				$.ajax({
					url:"CRM/user_logout.action",
					type:"POST",
					beforeSend:function(){
						//弹出进度条
						$.messager.progress({
							text : "退出系统中"
						});
					},
					success:function(data){
						//关闭进度条
						$.messager.progress("close");
						if(data == 1){
							$.messager.show({
								title:"操作提醒",
								msg:"退出系统成功！"
							});
							
							location.href = "/CRM/login.jsp";
						}else{
							$.messager.alert("操作提醒","未知原因退出系统失败","warning");
						}
					}
				});
			}
		});
		
	});
});

