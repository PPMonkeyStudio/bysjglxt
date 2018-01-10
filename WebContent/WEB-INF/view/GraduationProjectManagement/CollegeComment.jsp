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
<!--页面公用-------------------------------------------------------------------------------------------------->



<title>评语管理</title>
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

		<!--  -->

		<div class="panel" style="width: 95%; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">
				<h3 class="panel-title">评语管理</h3>
			</div>
			<!--  -->
			<div class="panel-body">
				<!--  -->
				<table id="table_Comment"
					class="table table-hover table-borderd"
					style="text-align: center; margin: 20px 0;">
					<tbody>
						<tr>
							<th>内容</th>
							<th class="teacher_control">
								<select id="select_grade" class="form-control"
									style="width: auto; margin: 0 auto;" id=""
									onchange="List_Comment_By_College(1)">
									<option value="-1">等级</option>
								</select>
							</th>
							<th class="teacher_control">
								<select id="select_category" class="form-control"
									style="width: auto; margin: 0 auto;" id=""
									onchange="List_Comment_By_College(1)">
									<option value="-1">类别</option>
								</select>
							</th>
							<th>操作</th>
						</tr>
					</tbody>
				</table>
				<!--  -->
				<div id="i_pulse" style="text-align: center;">
					<i class="fa fa-spinner fa-pulse fa-3x"></i>
				</div>
				<!--  -->
				<div style="height: 34px; margin: 0 0 20px 0;">
					<button class="btn btn-danger" onclick="Delete_Comment()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-trash-o"></i>
						删除所选
					</button>
				</div>
				<!--  -->
				<div
					style="margin: 0 auto; width: 400px; text-align: center;">
					<button id="button_HomePage" class="btn btn-default"
						onclick="flip(1)">首页</button>
					<button id="button_PrePage" class="btn btn-default"
						onclick="flip(2)">上一页</button>
					<button id="button_NextPage" class="btn btn-default"
						onclick="flip(3)">下一页</button>
					<button id="button_EndPage" class="btn btn-default"
						onclick="flip(4)">尾页</button>
				</div>
				<!--  -->
				<div id="lingshi"
					style="margin: 20px auto 20px; width: 200px; text-align: center;">
					第
					<span id="span_pageIndex">1</span>
					页
					<br>
					共
					<span id="span_totalPages">1</span>
					页
					<br>
					共
					<span id="span_totalRecords">0</span>
					条记录
				</div>
				<!--  -->
			</div>
			<!--  -->
		</div>

		<!--  -->

	</div>

	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
</body>
<!---------------------------------------------------------------------------------------------------->
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/List_Comment_By_College.js"></script>
<!---------------------------------------------------------------------------------------------------->
</html>