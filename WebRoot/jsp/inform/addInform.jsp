<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addinform.jsp' starting page</title>
    
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
  		$("#inform-add-title").textbox({
  			width:350,
  			height:32,
  			required:true,
  			validType : "length[2,50]",
  			missingMessage:"请输入通知标题",
  			invalidMessage : "通知标题2到50位",
  		});
  		
  	</script>
  
  	
  
  
  	<form id="inform-add" method="post">
	<table class="form-table">
		<tbody>
			<tr>
				<td class="label">
					<label for="inform-add-title" class="form-label" >通知标题：</label>
				</td>
				<td class="input">
					<input type="text" id="inform-add-title" name="title"/>
				</td>
				<td class="label">
				</td>
				<td class="input">
				</td>
			</tr>
			<tr>
				<td class="label">
					<label for="inform-add-details" class="form-label" >通知详情：</label>
				</td>
				<td class="input" colspan="3">
					<textarea id="inform-add-details" class="textarea" name="details"></textarea>
				</td>
			</tr>
		</tbody>
	</table>
  </form>
  </body>
</html>
