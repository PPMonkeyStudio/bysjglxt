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
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">

<title>院系管理</title>
</head>
<body>
	<s:action name="LoginLogoutManagement_navbar"
		namespace="/loginLogout" executeResult="true" />
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<div style="margin: 80px 0 0 0; float: left; width: 100%;">
		<!--  -->
		<!---------------------------------------------------------------------------------------------------->
		<!---------------------------------------------------------------------------------------------------->
		<div class="panel" style="width: 960px; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">
				<h3 class="panel-title">院系管理</h3>
			</div>
			<!--  -->
			<div class="panel-body">
				<div style="height: 34px;">
					<div style="width: 500px; float: left;">
						<button class="btn btn-default" onclick="addCollege()">
							<i class="fa fa-plus-square"></i>
							创建院系
						</button>
					</div>
				</div>
				<table id="table_college" class="table table-hover"
					style="text-align: center; margin: 20px 0;">
					<tbody>
						<tr>
							<th>学院名称</th>
							<th>管理员</th>
							<th>操作</th>
							<!-- 							<th> -->
							<!-- 								<label class="fancy-checkbox"> -->
							<!-- 									<input id="checkbox_all_select" type="checkbox" -->
							<!-- 										onclick="all_select()"> -->
							<%-- 									<span>全选</span> --%>
							<!-- 								</label> -->
							<!-- 							</th> -->
						</tr>
					</tbody>
				</table>
				<!--  -->
				<div id="i_pulse" style="text-align: center;">
					<i class="fa fa-spinner fa-pulse fa-3x"></i>
				</div>
				<!--  -->
				<!-- 				<div style="height: 34px; margin: 0 0 20px 0;"> -->
				<!-- 					<button class="btn btn-danger" onclick="Delete_Student()" -->
				<!-- 						style="float: right; margin: 0 10px;"> -->
				<!-- 						<i class="fa fa-trash-o"></i> -->
				<!-- 						删除所选 -->
				<!-- 					</button> -->
				<!-- 				</div> -->
			</div>
			<!--  -->

		</div>
		<!---------------------------------------------------------------------------------------------------->
		<!---------------------------------------------------------------------------------------------------->
	</div>
	<!---------------------------------------------------------------------------------------------------->
</body>
<!---------------------------------------------------------------------------------------------------->
<script type="text/javascript"
	src="<%=basePath%>js/CollegeManagement/List_College.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/CollegeManagement/addCollege.js"></script>
<!---------------------------------------------------------------------------------------------------->

</html>