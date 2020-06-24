<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addPost.jsp' starting page</title>
    
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
  	<!-- 由于要添加的js代码太少，就不另外写一个文件了 -->
  	<script type="text/javascript">
  		//把文本框设置为easyUI自带的文本框
  		$("#post-add-name").textbox({
  			width:220,
  			height:32,
  			required:true,
  			//规定长度在2到10之间
  			validType:"length[2,10]",
  			missingMessage:"请输入职位名称",
  			invalidMessage:"职位名称长度应在2位到10位之间"
  		});
  	</script>
  
  	<form id="form-add" method="post">
	<table class="form-table">
		<tbody>
			<tr>
				<td class="label">
					<!-- 给label标签的for属性绑定到input，这样点击label焦点就i会转到input，用户体验更好 -->
					<label for="post-add-name" class="form-label" >职位名称：</label>
				</td>
				<td class="input">
					<input type="text" id="post-add-name" name="name"/>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
  </body>
</html>
