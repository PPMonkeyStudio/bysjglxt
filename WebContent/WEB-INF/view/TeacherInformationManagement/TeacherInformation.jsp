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
<!---------------------------------------------------------------------------------------------------->


<!---------------------------------------------------------------------------------------------------->
<title>教师信息</title>
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
				<h3 class="panel-title">教师列表</h3>
			</div>
			<!--  -->
			<div class="panel-body">
				<div style="height: 34px;">
					<div style="width: 500px; float: left;">
						<button class="btn btn-default"
							onclick="window.location='<%=basePath%>teacher/TeacherInformationManagement_CreateTeacherPage'">
							<i class="fa fa-plus-square"></i>
							手动新增
						</button>
						<button class="btn btn-default" data-toggle="modal"
							data-target="#modal_excel">
							<i class="fa fa-upload"></i>
							通过Excel导入
						</button>
						<a class="teacher_control  btn-link"
							onclick="window.location='<%=basePath%>teacher/TeacherInformationManagement_exportTeacherInfoTemplate'">
							下载模板 </a>
					</div>
					<!-- 检索 -->
					<div class="input-group" style="width: 300px; float: right;">
						<input id="input_search" class="form-control"
							oninput="List_Teacher_By_PageAndSearch(1)" type="text">
						<span class="input-group-addon">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
				<table id="table_teacher" class="table table-hover "
					style="text-align: center; margin: 20px 0;">
					<tbody>
						<tr>
							<th>工号</th>
							<th>姓名</th>
							<th>
								<select class="form-control" id="select_sex"
									style="width: auto; margin: 0 auto;"
									onchange="List_Teacher_By_PageAndSearch(1)">
									<option value="-1">性别</option>
									<option value="男">男</option>
									<option value="女">女</option>
								</select>
							</th>
							<th>出生年月</th>
							<th>单位名称</th>
							<th>学历</th>
							<th>最高学位</th>
							<th>专业技术职称</th>
							<th>电话</th>
							<th>QQ</th>
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
					<button class="btn btn-danger" onclick="Delete_Teacher()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-trash-o"></i>
						删除所选
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
					<button class="btn btn-default" onclick="import_click()"
						style="margin: 10px 0;">选择文件</button>
					<input id="input_upload_Teacher_excel" type="file"
						onchange="Preview_Teacher_EXCEL(this)"
						style="display: none;">
					<script type="text/javascript">
						function import_click() {
							document.getElementById(
									"input_upload_Teacher_excel").click();
						}
					</script>
					<!--  -->
					<div
						style="width: 100%; max-height: 400px; min-height: 0px; overflow-x: auto; overflow-y: auto; -webkit-overflow-scrolling: touch; -ms-overflow-style: -ms-autohiding-scrollbar; border: 1px solid #ddd;">
						<table id="table_excel_Teacher"
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
						onclick="remove_Preview_Teacher_EXCEL()">
						<i class="fa fa-trash-o"></i>
						重置数据
					</button>
					<button class="btn btn-default"
						onclick="Save_Teacher_EXCEL()">
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
</body>
<script type="text/javascript"
	src="<%=basePath%>js/TeacherInformationManagement/List_Teacher_Info.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/TeacherInformationManagement/Teacher_Information_Display.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/TeacherInformationManagement/PreviewTeacherEXCEL.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/TeacherInformationManagement/Delete_Teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/TeacherInformationManagement/Teacher_Fix_User.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/TeacherInformationManagement/Get_Teacher_Section.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/TeacherInformationManagement/Get_Teacher_Title.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/TeacherInformationManagement/Teacher_Give_Recorder.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/TeacherInformationManagement/Teacher_Take_Recorder.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/TeacherInformationManagement/Teacher_Give_Leader.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/TeacherInformationManagement/Teacher_Take_Leader.js"></script>
<script>
	// 	$('select').selectpicker('refresh');
</script>
<script>
	Get_Teacher_Section(document.getElementById("select_section"));
	//Get_Teacher_Title(document.getElementById("select_title"));
</script>
</html>