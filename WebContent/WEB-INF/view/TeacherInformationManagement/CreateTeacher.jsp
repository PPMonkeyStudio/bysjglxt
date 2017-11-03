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
	src="<%=basePath%>js/TeacherInformationManagement/Create_Teacher.js"></script>
<!---------------------------------------------------------------------------------------------------->
<title>学生信息管理</title>
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
		<div class="panel" style="width: 900px; margin: 20px auto;">
			<div class="panel-heading">
				<h3 class="panel-title">新增教师</h3>
			</div>
			<div class="panel-body">
				<table id="" class="table table-hover   "
					style="text-align: center;">
					<tbody>
						<tr>
							<th>工号</th>
							<td><input id="input_job_number" class="form-control"
								type="text"></td>
							<th>姓名</th>
							<td><input id="input_name" class="form-control" type="text"></td>
						</tr>
						<tr>
							<th>性别</th>
							<td><select class="form-control" style="width: auto;"
								id="input_sex"><option value="男">男</option>
									<option value="女">女</option>
							</select></td>
							<th>出生年月</th>
							<td><input id="input_birthday" class="form-control"
								type="text"></td>
						</tr>
						<tr>
							<th>入校时间</th>
							<td><input id="input_induction_date" class="form-control"
								type="text"></td>
							<th>任职状态</th>
							<td><input id="input_job_statue" class="form-control"
								type="text"></td>
						</tr>
						<tr>
							<th>单位编号</th>
							<td><input id="input_unit_number" class="form-control"
								type="text"></td>
							<th>单位名称</th>
							<td><input id="input_unit_name" class="form-control"
								type="text"></td>
						</tr>
						<tr>
							<th>最高学历</th>
							<td><input id="input_highest_education" class="form-control"
								type="text"></td>
							<th>最高学位</th>
							<td><input id="input_highest_degree" class="form-control"
								type="text"></td>
						</tr>
						<tr>
							<th>学缘</th>
							<td><input id="input_learn_edge_structure"
								class="form-control" type="text"></td>
							<th>专业技术职称</th>
							<td><input id="input_professional_title"
								class="form-control" type="text"></td>
						</tr>
						<tr>
							<th>学科类别</th>
							<td><input id="input_subject_category" class="form-control"
								type="text"></td>
							<th>授课类型</th>
							<td><input id="input_teaching_type" class="form-control"
								type="text"></td>
						</tr>
						<tr>
							<th>任教专业名称</th>
							<td><input id="input_teaching_profession_name"
								class="form-control" type="text"></td>
							<th>任教专业代码</th>
							<td><input id="input_teaching_profession_no"
								class="form-control" type="text"></td>
						</tr>
						<tr>
							<th>专业任教时间</th>
							<td><input id="input_profession_teaching_date"
								class="form-control" type="text"></td>
							<th>是否实验技术人员</th>
							<td><input id="input_experimental_technical_personnel"
								class="form-control" type="text"></td>
						</tr>
						<tr>
							<th>是否双师型</th>
							<td><input id="input_double_teacher_type"
								class="form-control" type="text"></td>
							<th>是否工程背景</th>
							<td><input id="input_engineering_background"
								class="form-control" type="text"></td>
						</tr>
						<tr>
							<th>是否行业背景</th>
							<td><input id="input_industry_background"
								class="form-control" type="text"></td>
							<th>研究生导师类型</th>
							<td><input id="input_graduate_tutor_type"
								class="form-control" type="text"></td>
						</tr>
						<tr>
							<th>校内指导博士生人数</th>
							<td><input id="input_number_of_doctor" class="form-control"
								type="text"></td>
							<th>校内指导硕士生人数</th>
							<td><input id="input_number_of_master" class="form-control"
								type="text"></td>
						</tr>
					</tbody>
				</table>
				<div style="height: 34px; margin: 30px 0;">
					<button class="btn btn-default" onclick="Create_Teacher()"
						style="float: right; margin: 0 10px;">
						<i class="fa  fa-check "></i> 确认创建
					</button>
					<button class="btn btn-default"
						onclick="window.location='<%=basePath%>teacher/TeacherInformationManagement_TeacherManagementPage'"
						style="float: right; margin: 0 10px;">
						<i class="fa  fa-close "></i> 返回
					</button>
				</div>
			</div>
		</div>
	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
</body>
<script>
	$('select').selectpicker('refresh');
</script>
</html>