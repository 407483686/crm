<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addletter.jsp' starting page</title>
    
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
  		$("#letter-add-staff_name").textbox({
  			width:240,
  			height:32,
  			required:true,
  			missingMessage:"请选择收件人",
  			icons : [{
  				iconCls : "icon-zoom",
  				handler : function(){
  					$("#addLetterChooseStaff-dialog").dialog({
  						title : "选择关联的跟单记录",
						width : 550,
						height : 380,
						iconCls : "icon-zoom",
						modal : true,
						href : "/CRM/jsp/letter/addLetterChooseStaff.jsp"
  					});	
  				}
  			}]
  		});
  	</script>
  
  	
  
  
  	<form id="letter-add" method="post">
  	<input type="hidden" id="letter-add-staff_id" name="staff_id"/>
	<table class="form-table">
		<tbody>
			<tr>
				<td class="label">收件人：</td>
				<td class="content">
					<input type="text" id="letter-add-staff_name" name="staff_name"/>
				</td>
			</tr>
			<tr>
				<td class="label">私信内容：</td> 
				<td class="content" colspan="3" id="letter-add-message">
					<textarea class="textarea" name="message"></textarea>
				</td> 
			</tr> 
		</tbody>
	</table>
  </form>
  
  <div id="addLetterChooseStaff-dialog"></div>
  </body>
</html>
