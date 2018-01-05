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

<title>学生信息管理</title>
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
		<div class="panel" style="width: 95%; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">
				<h3 class="panel-title">学生信息列表</h3>
			</div>
			<!--  -->
			<div class="panel-body">
				<div style="height: 34px;">

					<div style="width: 500px; float: left;">
						<button class="btn btn-default"
							onclick="window.location='<%=basePath%>student/StudentInformationManagement_CreateStudentPage'">
							<i class="fa fa-plus-square"></i>
							手动新增
						</button>
						<button class="btn btn-default" data-toggle="modal"
							data-target="#modal_excel">
							<i class="fa fa-upload"></i>
							通过Excel导入
						</button>
					</div>
					<!-- 检索 -->
					<div class="input-group" style="width: 300px; float: right;">
						<input id="input_search" class="form-control"
							oninput="List_Student_By_PageAndSearch(1)" type="text">
						<span class="input-group-addon">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
				<table id="table_student" class="table table-hover"
					style="text-align: center; margin: 20px 0;">
					<tbody>
						<tr>
							<th>学号</th>
							<th>姓名</th>
							<th>
								<select class="form-control" id="select_sex"
									style="width: auto;margin:0 auto;"
									onchange="List_Student_By_PageAndSearch(1)">
									<option value="-1">性别</option>
									<option value="男">男</option>
									<option value="女">女</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="select_major"
									data-live-search="true" style="width: auto;margin:0 auto;"
									onchange="List_Student_By_PageAndSearch(1)">
									<option value="-1">专业名称</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="select_level"
									data-live-search="true" style="width: auto;margin:0 auto;"
									onchange="List_Student_By_PageAndSearch(1)">
									<option value="-1">级别</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="select_premission"
									style="width: auto;margin:0 auto;"
									onchange="List_Student_By_PageAndSearch(1)">
									<option value="-1">状态</option>
									<option value="1">活动</option>
									<option value="0">已关闭</option>
								</select>
							</th>
							<th>
								<select class="form-control" id="select_is_select_topic"
									style="width: auto;margin:0 auto;"
									onchange="List_Student_By_PageAndSearch(1)">
									<option value="-1">选题状态</option>
									<option value="1">已选题</option>
									<option value="2">未选题</option>
								</select>
							</th>
							<th>操作</th>
							<th>
								<label class="fancy-checkbox">
									<input id="checkbox_all_select" type="checkbox"
										onclick="all_select()">
									<span>全选</span>
								</label>
							</th>
						</tr>
					</tbody>
				</table>


				<div id="i_pulse" style="text-align: center;">
					<i class="fa fa-spinner fa-pulse fa-3x"></i>
				</div>



				<div style="height: 34px; margin: 0 0 20px 0;">

					<button class="btn btn-danger" onclick="Delete_Student()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-trash-o"></i>
						删除所选
					</button>
					<button class="btn btn-default"
						onclick="Student_Take_Operate_Premission()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-exclamation-triangle"></i>
						关闭学生
					</button>
					<button class="btn btn-default"
						onclick="Student_Give_Operate_Premission()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-key"></i>
						打开学生
					</button>
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
		<!---------------------------------------------------------------------------------------------------->
		<!---------------------------------------------------------------------------------------------------->
	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!-------详细信息模态框------->
	<div class="modal fade" id="modal_Student_Information"
		data-keyboard="true" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- 模态弹出窗内容 -->
				<!--弹出框头部，一般使用“modal-header”表示，主要包括标题和关闭按钮-->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">详细信息</h4>
				</div>
				<!--弹出框主体，一般使用“modal-body”表示，弹出框的主要内容-->
				<div class="modal-body">
					<table id="table_student_detail"
						class="table table-hover table-bordered"
						style="text-align: center;">
						<tbody></tbody>
					</table>
				</div>
				<!--弹出框脚部，一般使用“modal-footer”表示，主要放置操作按钮-->
				<div class="modal-footer">
					<button class="btn btn-default" id="button_sure_update"
						onclick="Update_Student()"
						style="float: right; margin: 0 10px; display: none;">
						<i class="fa fa-check"></i>
						确认修改
					</button>
					<button class="btn btn-default" id="button_stop_update"
						onclick="stop_Update_Student()"
						style="float: right; margin: 0 10px; display: none;">
						<i class="fa fa-times"></i>
						放弃修改
					</button>
					<button class="btn btn-default" id="button_start_update"
						onclick="start_Update_Student()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-pencil-square-o"></i>
						修改
					</button>
				</div>
			</div>
		</div>
	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!-------excel模态框------->
	<div class="modal fade " id="modal_excel" data-keyboard="true"
		tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- 模态弹出窗内容 -->
				<!--弹出框头部，一般使用“modal-header”表示，主要包括标题和关闭按钮-->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">通过Excel导入</h4>
				</div>
				<!--弹出框主体，一般使用“modal-body”表示，弹出框的主要内容-->
				<div class="modal-body">
					<input id="input_upload_student_excel" type="file"
						onchange="Preview_Student_EXCEL(this)">
					<!--  -->
					<div
						style="width: 100%; max-height: 400px; min-height: 0px; overflow-x: auto; overflow-y: auto; -webkit-overflow-scrolling: touch; -ms-overflow-style: -ms-autohiding-scrollbar; border: 1px solid #ddd;">
						<table id="table_excel_student"
							class="table table-bordered table-hover"
							style="text-align: center;">
							<tbody></tbody>
						</table>
					</div>
					<!--  -->
					<div id="i_pulse_2"
						style="text-align: center; display: none;">
						<i class="fa fa-spinner fa-pulse fa-3x"></i>
					</div>
				</div>
				<!--弹出框脚部，一般使用“modal-footer”表示，主要放置操作按钮-->
				<div class="modal-footer">
					<button class="btn btn-danger"
						onclick="remove_Preview_Student_EXCEL()">
						<i class="fa fa-trash-o"></i>
						重置数据
					</button>
					<button class="btn btn-default"
						onclick="Save_Student_EXCEL()">
						<i class="fa fa-check"></i>
						确认导入
					</button>
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
<!---------------------------------------------------------------------------------------------------->
<script type="text/javascript"
	src="<%=basePath%>js/StudentInformationManagement/List_Student_By_PageAndSearch.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/StudentInformationManagement/Student_Information_Display.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/StudentInformationManagement/PreviewStudentEXCEL.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/StudentInformationManagement/Delete_Student.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/StudentInformationManagement/Get_Student_Major.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/StudentInformationManagement/Get_Student_Level.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/StudentInformationManagement/Update_Student.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/StudentInformationManagement/Student_Give_Operate_Premission.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/StudentInformationManagement/Student_Take_Operate_Premission.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/StudentInformationManagement/assignmentStudentTopic.js"></script>
<!---------------------------------------------------------------------------------------------------->
<script>
	//$('select').selectpicker('refresh');
</script>
<script> 
	var select_major = document.getElementById("select_major");
	var select_level = document.getElementById("select_level");
	Get_Student_Major(select_major);
	Get_Student_Level(select_level);
</script>

</html>