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
<script type="text/javascript"
	src="<%=basePath%>js/StudentInformationManagement/List_Student_All.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/StudentInformationManagement/Student_Information_Display.js"></script>
<!---------------------------------------------------------------------------------------------------->
<title>学生信息管理</title>
</head>
<body>
	<jsp:include page="/navbar.jsp" flush="true"></jsp:include>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<div
		style="margin: 80px 0 0 260px; width: calc(100% - 260px); float: left;">
		<!--  -->
		<!---------------------------------------------------------------------------------------------------->
		<!---------------------------------------------------------------------------------------------------->
		<div class="panel" style="width: 95%; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">
				<h3 class="panel-title">学生信息列表</h3>
			</div>
			<!--  -->
			<div class="panel-body">
				<div style="height: 34px;">

					<div style="width: 500px; float: left;">
						<button class="btn btn-default">
							<i class="fa fa-plus-square"></i> 手动新增
						</button>
						<button class="btn btn-default">
							<i class="fa fa-upload"></i> 通过Excel导入
						</button>

					</div>

					<div class="input-group" style="width: 300px; float: right;">
						<input class="form-control" type="text"> <span
							class="input-group-btn"><button class="btn btn-primary"
								type="button">
								<i class="fa fa-search"></i> 检索
							</button></span>
					</div>
				</div>
				<table id="table_student" class="table table-hover table-bordered"
					style="text-align: center; margin: 20px 0;">
					<tr>
						<th>学号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>专业</th>
						<th>届别</th>
						<th>操作权限</th>
						<th>操作</th>
						<th><label class="fancy-checkbox"> <input
								type="checkbox"><span>全选</span>
						</label></th>
					</tr>
				</table>
				<div id="i_pulse" style="text-align: center;">
					<i class="fa fa-spinner fa-pulse fa-3x"></i>
				</div>
				<div style="height: 34px">

					<button class="btn btn-danger"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-trash-o"></i> 删除所选
					</button>

					<button class="btn btn-default"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-reply-all"></i> 收回操作权限
					</button>
					<button class="btn btn-default"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-handshake-o"></i> 赋予操作权限
					</button>
				</div>
				<div style="margin: 0 auto; width: 400px; text-align: center;">
					<button class="btn btn-default">首页</button>
					<button class="btn btn-default">上一页</button>
					<button class="btn btn-default">下一页</button>
					<button class="btn btn-default">尾页</button>
				</div>
				<div
					style="margin: 20px auto 20px; width: 200px; text-align: center;">
					第 1 页<br>共 2 页<br>共 14 条记录
				</div>
			</div>
			<!--  -->

		</div>
		<!---------------------------------------------------------------------------------------------------->
		<!---------------------------------------------------------------------------------------------------->
	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<div class="modal fade" id="modal_Student_Information"
		data-keyboard="true" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- 模态弹出窗内容 -->
				<!--弹出框头部，一般使用“modal-header”表示，主要包括标题和关闭按钮-->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">详细信息</h4>
				</div>
				<!--弹出框主体，一般使用“modal-body”表示，弹出框的主要内容-->
				<div class="modal-body">
					<table id="table_student_detail"
						class="table table-hover table-bordered"
						style="text-align: center; margin: 20px 0;">
						<!--  -->
						<tbody></tbody>
					</table>
				</div>
				<!--弹出框脚部，一般使用“modal-footer”表示，主要放置操作按钮-->
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
</body>
</html>