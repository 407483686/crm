<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'editStaff.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<script type="text/javascript">
		// 检查身份证是否合法的验证
		$.extend($.fn.validatebox.defaults.rules, {
			id_card : {
				validator : function(value) {
					return /^[0-9]{17}[xX0-9]$/.test(value);
				}
			}
		});
  	
  		// 检查民族是否合法的验证
		$.extend($.fn.validatebox.defaults.rules, {
			nation : {
				validator : function(value) {
					return /^.{1,4}族$/.test(value);
				}
			}
		});
  		
  		// 检查员工编号是否合法的验证
		$.extend($.fn.validatebox.defaults.rules, {
			number : {
				validator : function(value) {
					//4位0到9的数字
					return /^[0-9]{4}$/.test(value);
				}
			}
		});
  		
  		//移动电话为11位数字
  		$.extend($.fn.validatebox.defaults.rules, {
		    tel: {
		        validator: function(value, param){
		            return /^[0-9]{11}$/.test(value);
		        }
		    }
		});
  	
  		//员工姓名
  		$("#staff-edit-name").textbox({
  			width:240,
  			height:32,
  			required:true,
  			editable : false,
  			//规定长度在2到10之间
  			validType:"length[2,20]",
  			missingMessage:"请输入员工姓名",
  			invalidMessage:"用户名长度应在2位到20位之间"
  		});
  		
  		//男按钮
  		$("#staff-edit-gender-1").linkbutton({
  			plain : true,
  			//为true时允许用户切换其状态是被选中还是未选择，可实现checkbox复选效果。
  			//如果和group属性搭配还可以实现多选一的效果
  			toggle : true,
			//指定相同组名称的按钮同属于一个组，可实现radio单选效果。
			group : "staff_gender",  			
  			iconCls : "icon-male",
  			//点击时给隐藏输入项赋值
  			onClick : function(){
  				$("#staff-edit-gender").val("男");
  			}
  		});
  		
  		//女按钮
  		$("#staff-edit-gender-2").linkbutton({
  			plain : true,
  			//为true时允许用户切换其状态是被选中还是未选择，可实现checkbox复选效果。
  			//如果和group属性搭配还可以实现多选一的效果
  			toggle : true,
  			//指定相同组名称的按钮同属于一个组，可实现radio单选效果。
  			group : "staff_gender",
  			iconCls : "icon-female",
  			onClick : function(){
  				$("#staff-edit-gender").val("女");
  			}
  		});
  		
  		
  		
  		//员工编号
  		$("#staff-edit-number").textbox({
  			width:240,
  			height:32,
  			required:true,
  			editable : false,
  			validType:"number",
  			missingMessage:"请输入员工编号",
  			invalidMessage:"员工工号不合法"
  		});
  		
  		//职位
  		$("#staff-edit-post").combobox({
			width : 130,
			height:32,
			//通过URL加载远程列表数据
			url : "/CRM/post_loadPost.action", 
			editable : false,
			// 基础数据值对应的名称
			valueField : "name",
			// 基础数据字段对应的名称
			textField : "name",
			// 下拉面板的高度
			panelHeight : "auto",
			//选中职位时给groupId赋值
			onSelect : function(){
				var postName = $("#staff-edit-post").combobox("getValue");
				switch(postName){
					case "总经理":
						$("#group_id").val(1);
						break;
					
					case "副总经理":
						$("#group_id").val(2);
						break;
					
					case "销售":
						$("#group_id").val(3);
						break;
					
					case "人事主管":
						$("#group_id").val(4);
						break;
						
					case "财务":
						$("#group_id").val(5);
						break;
						
					case "前台":
						$("#group_id").val(6);
						break; 
				}
				
			}
  		});
  		
  		//移动电话
  		$("#staff-edit-tel").textbox({
  			width:240,
  			height:32,
  			validType:"tel",
  			missingMessage:"请输入11位移动电话",
  			invalidMessage:"移动电话不合法"
  		});
  		
  		
  		//员工类型
  		$("#staff-edit-type").combobox({
			width : 130,
			height:32,
			editable : false,
			data : [ {
				id : "正式员工",
				text : "正式员工"
			}, {
				id : "合同工",
				text : "合同工"
			}, {
				id : "临时工",
				text : "临时工"
			} ],
			// 基础数据值对应的名称
			valueField : "id",
			// 基础数据字段对应的名称
			textField : "text",
			// 下拉面板的高度
			panelHeight : "auto",
			// 设置无效提示信息的出现位置
			tipPosition : "right",
  		});
  		
  		
  		//身份证
  		$("#staff-edit-id").textbox({
  			width:240,
  			height:32,
  			required:true,
  			editable : false,
  			validType:"id_card",
  			missingMessage:"请输入身份证号",
  			invalidMessage:"身份证格式不正确，且精确到18位"
  		});
  		
		//婚姻状况
  		$("#staff-edit-marital_status").combobox({
			width : 130,
			height:32,
			editable : false,
			data : [ {
				id : "已婚",
				text : "已婚"
			}, {
				id : "未婚",
				text : "未婚"
			}, {
				id : "离异",
				text : "离异"
			},{
				id : "丧偶",
				text : "丧偶"
			} ],
			// 基础数据值对应的名称
			valueField : "id",
			// 基础数据字段对应的名称
			textField : "text",
			// 下拉面板的高度
			panelHeight : "auto",
			// 设置无效提示信息的出现位置
			tipPosition : "right",
  		});
  		
  		//民族
  		$("#staff-edit-nation").textbox({
  			width:240,
  			height:32,
  			validType:"nation",
  			invalidMessage:"民族查询必须填入完整名称，不得小于 2 位，且末尾包含“族” 字"
  		});
  		
  		//入职时间
  		$("#staff-edit-entry_date").datebox({
  			// 日期面板宽度为100
			width : 130,
			//高度和文本框一样
			height : 32,
			// 不允许不通过面板直接输入值
			editable : false,
  		});
  		
  		//学历
  		$("#staff-edit-education").combobox({
  			width : 130,
			height:32,
			editable : false,
			data : [ {
				id : "博士",
				text : "博士"
			}, {
				id : "硕士",
				text : "硕士"
			}, {
				id : "本科",
				text : "本科"
			},{
				id : "专科",
				text : "专科"
			} ],
			// 基础数据值对应的名称
			valueField : "id",
			// 基础数据字段对应的名称
			textField : "text",
			// 下拉面板的高度
			panelHeight : "auto",
			// 设置无效提示信息的出现位置
			tipPosition : "right",
  		});
  		
  		//离职时间
  		$("#staff-edit-dimission_date").datebox({
  			// 日期面板宽度为100
			width : 130,
			//高度和文本框一样
			height : 32,
			// 不允许不通过面板直接输入值
			editable : false,
  		});
  		
  		
  		//在职状态
  		$("#staff-edit-entry_status").combobox({
  			width : 130,
			height:32,
			editable : false,
			data : [ {
				id : "在职",
				text : "在职"
			}, {
				id : "暂休",
				text : "暂休"
			}, {
				id : "离职",
				text : "离职"
			}],
			// 基础数据值对应的名称
			valueField : "id",
			// 基础数据字段对应的名称
			textField : "text",
			// 下拉面板的高度
			panelHeight : "auto",
  		});
  		
  		//专业
  		$("#staff-edit-major").textbox({
  			width:240,
  			height:32,
  		});
  		
  		//政治面貌
  		$("#staff-edit-politics_status").combobox({
  			width : 130,
			height:32,
			editable : false,
			data : [ {
				id : "党员",
				text : "党员"
			}, {
				id : "团员",
				text : "团员"
			}, {
				id : "群众",
				text : "群众"
			}],
			// 基础数据值对应的名称
			valueField : "id",
			// 基础数据字段对应的名称
			textField : "text",
			// 下拉面板的高度
			panelHeight : "auto",
  		});
  		
  		//专业
  		$("#staff-edit-heath").textbox({
  			width:240,
  			height:32,
  		});
  		
  		//户口
  		$("#staff-edit-residence").combobox({
  			width : 130,
			height:32,
			editable : false,
			data : [ {
				id : "城镇",
				text : "城镇"
			}, {
				id : "农村",
				text : "农村"
			}],
			// 基础数据值对应的名称
			valueField : "id",
			// 基础数据字段对应的名称
			textField : "text",
			// 下拉面板的高度
			panelHeight : "auto",
  		});
  		
  		//毕业时间
  		$("#staff-edit-graduation_time").datebox({
  			// 日期面板宽度为100
			width : 130,
			//高度和文本框一样
			height : 32,
			// 不允许不通过面板直接输入值
			editable : false,
  		});
  		
  		//户口所在地
  		$("#staff-edit-registered_permanent_residence").textbox({
  			width:240,
  			height:32,
  		});
  		
  		
  		//毕业院校
  		$("#staff-edit-school").textbox({
  			width:240,
  			height:32,
  		});
  		
  		//详情,编辑器的宽度，可以设置px或%，比textarea输入框样式表宽度优先度高。
  		STAFF_EDIT = KindEditor.create("#staff-edit-details",{
  			width : "91%",
  			height : "200px",
  			//resizeType: 2或1或0，2时可以拖动改变宽度和高度，1时只能改变高度，0时不能拖动。
  			resizeType : 0,
  			//items : 配置编辑器的工具栏，其中”/”表示换行，”|”表示分隔符。
  			//editor_tool在index.js中定义好了
  			items : editor_tool
  		});
  	</script>
  
  
  
    <form id="staff-edit" method="post">
    <input type="hidden" id="group_id" name="group_id">
    <input type="hidden" name="id">
	<table class="form-table" style="max-width:780px;">
		<tbody>
			<tr>
				<td class="label">
					<label for="staff-edit-name" class="form-label" >姓名：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-name" name="name"/>
				</td>
				<td class="label">
					<label for="staff-edit-gender" class="form-label" >性别：</label>
				</td>
				<td class="input">
					<input type="hidden" id="staff-edit-gender" name="gender"/>
					<!-- 这两个name属性用于在上面的js设置group属性 -->
					<a id="staff-edit-gender-1" name="staff_gender" href="javascript:void(0)">男</a>
					<a id="staff-edit-gender-2" name="staff_gender" href="javascript:void(0)">女</a>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="staff-edit-number" class="form-label" >员工编号：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-number" name="number"/>
				</td>
				<td class="label">
					<label for="staff-edit-post" class="form-label" >职位：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-post" name="post"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="staff-edit-tel" class="form-label" >移动电话：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-tel" name="tel"/>
				</td>
				<td class="label">
					<label for="staff-edit-type" class="form-label" >员工类型：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-type" name="type"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="staff-edit-id" class="form-label" >身份证：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-id" name="id_card"/>
				</td>
				<td class="label">
					<label for="staff-edit-marital_status" class="form-label" >婚姻状况：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-marital_status" name="marital_status"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="staff-edit-nation" class="form-label" >民族：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-nation" name="nation"/>
				</td>
				<td class="label">
					<label for="staff-edit-entry_date" class="form-label" >入职时间：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-entry_date" name="entry_date_string"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="staff-edit-education" class="form-label" >学历：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-education" name="education"/>
				</td>
				<td class="label">
					<label for="staff-edit-dimission_date" class="form-label" >离职时间：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-dimission_date" name="dimission_date_string"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="staff-edit-entry_status" class="form-label" >入职状态：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-entry_status" name="entry_status"/>
				</td>
				<td class="label">
					<label for="staff-edit-major" class="form-label" >专业：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-major" name="major"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="staff-edit-politics_status" class="form-label" >政治面貌：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-politics_status" name="politics_status"/>
				</td>
				<td class="label">
					<label for="staff-edit-heath" class="form-label" >健康状况：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-heath" name="heath"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="staff-edit-residence" class="form-label" >户口：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-residence" name="residence"/>
				</td>
				<td class="label">
					<label for="staff-edit-graduation_time" class="form-label" >毕业时间：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-graduation_time" name="graduation_time_string"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="staff-edit-registered_permanent_residence" class="form-label" >户口所在地：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-registered_permanent_residence" name="registered_permanent_residence"/>
				</td>
				<td class="label">
					<label for="staff-edit-school" class="form-label" >毕业院校：</label>
				</td>
				<td class="input">
					<input type="text" id="staff-edit-school" name="school"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					个人简介：
				</td>
				<td class="input" colspan="3">
					<textarea class="textarea" id="staff-edit-intro" name="intro" placeholder="250字内简单介绍下自己"></textarea>
				</td>
			</td>
			<tr>
				<td class="label">
					详情：
				</td>
				<td class="input" colspan="3">
					<textarea class="textarea" id="staff-edit-details" name="details"></textarea>
				</td>
			</td>
		</tbody>
	</table>
	</form>
  </body>
</html>
