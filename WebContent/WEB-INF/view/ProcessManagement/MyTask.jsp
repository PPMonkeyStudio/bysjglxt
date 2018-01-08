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
<!---------------------------------------------------------------------------------------------------->

<!---------------------------------------------------------------------------------------------------->

<title>我的任务</title>
</head>
<body>
	<s:action name="LoginLogoutManagement_navbar"
		namespace="/loginLogout" executeResult="true" />
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<div style="margin: 80px 0 0 0; float: left; width: 100%;">
		<div class="panel" style="width: 95%; margin: 20px auto;">

			<div class="panel-heading">
				<h3 class="panel-title">我的任务</h3>
			</div>
			<!--  -->
			<div class="panel-body">
				<div style="height: 34px;">
					<!-- 检索 -->
					<div class="input-group" style="width: 300px; float: right;">
						<input id="input_search" class="form-control"
							oninput="List_Teacher_By_PageAndSearch(1)" type="text">
						<span class="input-group-addon">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
				<table id="table_myTask" class="table table-hover "
					style="text-align: center; margin: 20px 0;">
					<tbody>
						<tr>
							<th>任务名</th>
							<th>流程定义</th>
							<th>流程实例</th>
							<th>
								<select class="form-control" id="select_state"
									style="width: auto;margin:0 auto;" data-live-search="true"
									onchange="List_MyTask(1)">
									<option value="-1">状态（全部）</option>
									<option value="1">正在进项</option>
									<option value="2">未开始</option>
									<option value="3">已结束</option>
								</select>
							</th>
							<th>操作</th>
							<th>完成时间</th>
						</tr>
					</tbody>
				</table>
				<div id="i_pulse" style="text-align: center;">
					<i class="fa fa-spinner fa-pulse fa-3x"></i>
				</div>
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
				<div
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
			</div>
			<!--  -->
		</div>
		<!--  -->
		<!--  -->
		<!--  -->
	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
</body>
<script type="text/javascript"
	src="<%=basePath%>js/ProcessManagement/List_MyTask.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/ProcessManagement/passTask.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/ProcessManagement/repulseTask.js"></script>

</html>