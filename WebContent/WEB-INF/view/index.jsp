<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!---------------------------------------------------------------------------------------------------->
<!---------------------------------------------------------------------------------------------------->
<title>说明</title>
</head>
<body>
	<s:action name="LoginLogoutManagement_navbar" namespace="/loginLogout"
		executeResult="true" />
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<div style="margin: 80px 0 0 0; float: left; width: 100%;">
		<div class="panel" style="width: 95%; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">
				<h3 class="panel-title">欢迎使用毕业设计管理系统</h3>
			</div>
			<div class="panel-body">
				<div class="panel" style="width:30%;border:1px solid black;float:left;margin-left:30px;">
					<div class="panel-heading" style="background-color:#BBB;color:#FFF;">
						<h3 class="panel-title">待完成</h3>
					</div>
					<div class="panel-body">
						<table id="daiFinishTask" class="table table-bordered">
							<tbody>
							
							</tbody>
						</table>
					</div>
				</div>
				<div class="panel" style="width:30%;border:1px solid black;float:left;margin-left:30px;">
					<div class="panel-heading" style="background-color:#BBB;color:#FFF;">
						<h3 class="panel-title">已完成</h3>
					</div>
					<div class="panel-body">
						<table id="yiFinishTask" class="table table-bordered" style="text-align: center;">
							<tbody>
								
							</tbody>
						</table>
					</div>
				</div>
				<div class="panel" style="width:30%;border:1px solid black;float:left;margin-left:30px;">
					<div class="panel-heading" style="background-color:#BBB;color:#FFF;">
						<h3 class="panel-title">公告或私信</h3>
					</div>
					<div class="panel-body">
						<table id="yiGongTask" class="table table-bordered" style="text-align: center;">
							<tbody>
								
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<script type="text/javascript"
	src="<%=basePath%>js/loginAndLogout/getTask.js"></script>
</body>
<script>
	
</script>
</html>