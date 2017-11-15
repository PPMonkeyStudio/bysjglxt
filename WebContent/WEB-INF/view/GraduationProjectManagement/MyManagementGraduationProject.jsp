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
<!--页面公用-------------------------------------------------------------------------------------------------->

<!---------------------------------------------------------------------------------------------------->
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/List_MyManagementGraduationProject_By_PageAndSearch.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/update_defence_record.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/update_defence_leader.js"></script>
<!---------------------------------------------------------------------------------------------------->

<title>毕业设计管理</title>
</head>
<body>
	<s:action name="LoginLogoutManagement_navbar" namespace="/loginLogout"
		executeResult="true" />
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
				<h3 class="panel-title">毕业设计管理</h3>
			</div>
			<div class="panel-body">
				<div style="height: 34px;">
					<!-- 检索 -->
					<div class="input-group" style="width: 300px; float: right;">
						<input id="input_search" class="form-control"
							oninput="List_MyManagementGraduationProject_By_PageAndSearch(1)"
							type="text"> <span class="input-group-addon"> <i
							class="fa fa-search"></i>
						</span>
					</div>
				</div>
				<table id="table_myManagementGraduationProject"
					class="table table-hover "
					style="text-align: center; margin: 20px 0;">
					<tbody>
						<tr>
							<th>学号</th>
							<th>学生</th>
							<th>毕业设计名称</th>
							<th class="teacher_control"><select class="form-control"
								style="width: auto;" id="select_state"
								onchange="List_MyManagementGraduationProject_By_PageAndSearch(1)">
									<option value="-1">状态</option>
									<option value="1">正在进行</option>
									<option value="2">未开始</option>
									<option value="3">已结束</option>
							</select></th>
							<th>进程</th>
							<th>毕业设计</th>
							<th>答辩</th>
						</tr>
					</tbody>
				</table>
				<div id="i_pulse" style="text-align: center;">
					<i class="fa fa-spinner fa-pulse fa-3x"></i>
				</div>
				<div style="margin: 0 auto; width: 400px; text-align: center;">
					<button id="button_HomePage" class="btn btn-default"
						onclick="flip(1)">首页</button>
					<button id="button_PrePage" class="btn btn-default"
						onclick="flip(2)">上一页</button>
					<button id="button_NextPage" class="btn btn-default"
						onclick="flip(3)">下一页</button>
					<button id="button_EndPage" class="btn btn-default"
						onclick="flip(4)">尾页</button>
				</div>
				<div id="lingshi"
					style="margin: 20px auto 20px; width: 200px; text-align: center;">
					第 <span id="span_pageIndex">1</span> 页 <br> 共 <span
						id="span_totalPages">1</span> 页 <br> 共 <span
						id="span_totalRecords">0</span> 条记录
				</div>
			</div>
		</div>

		<!--  -->

	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!-------修改教师用户信息模态框------->
	<div class="modal fade" id="modal_Topic_Information"
		data-keyboard="true" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- 模态弹出窗内容 -->
				<!--弹出框头部，一般使用“modal-header”表示，主要包括标题和关闭按钮-->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">课题详细信息</h4>
				</div>
				<!--弹出框主体，一般使用“modal-body”表示，弹出框的主要内容-->
				<div class="modal-body">
					<table id="table_topic_detail"
						class="table table-hover table-bordered"
						style="text-align: center;">
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
</body>
<script>
	$('select').selectpicker('refresh');
</script>
<script>
	
</script>
</html>