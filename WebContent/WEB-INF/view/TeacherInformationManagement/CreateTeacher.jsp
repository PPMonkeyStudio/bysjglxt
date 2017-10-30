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
	src="<%=basePath%>js/StudentInformationManagement/Create_Student.js"></script>
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
			<div class="panel-heading">
				<h3 class="panel-title">新增学生信息</h3>
			</div>
			<div class="panel-body">
				<table id="" class="table table-hover table-bordered  table-striped"
					style="text-align: center;">
					<tbody>
						<tr>
							<th>学号</th>
							<td><input id="student_basic_num" class="form-control"
								type="text"></td>
							<th>姓名</th>
							<td><input id="student_basic_name" class="form-control"
								type="text"></td>
						</tr>
						<tr>
							<th>年制</th>
							<td><input id="student_basic_year" class="form-control"
								type="text"></td>
							<th>年级</th>
							<td><input id="student_basic_grade" class="form-control"
								type="text"></td>
						</tr>
						<tr>
							<th>证件类型</th>
							<td><input id="student_basic_idtype" class="form-control"
								type="text"></td>
							<th>身份证/护照号码</th>
							<td><input id="student_basic_idcaard" class="form-control"
								type="text"></td>
						</tr>
						<tr>
							<th>年龄</th>
							<td><input id="student_basic_age" class="form-control"
								type="text"></td>
							<th>性别</th>
							<td><select class="form-control" style="width: auto;"
								id="student_basic_sex"><option value="男">男</option>
									<option value="女">女</option>
							</select></td>
						</tr>
						<tr>
							<th>民族</th>
							<td><input id="student_basic_nation" class="form-control"
								type="text"></td>
							<th>政治面貌</th>
							<td><input id="student_basic_politicalvisage"
								class="form-control" type="text"></td>
						</tr>
						<tr>
							<th>籍贯</th>
							<td><input id="student_basic_native_place"
								class="form-control" type="text"></td>
							<th>学生类型</th>
							<td><input id="student_basic_studenttype"
								class="form-control" type="text"></td>
						</tr>
						<tr>
							<th>招生类型</th>
							<td><input id="student_basic_enrollmenttype"
								class="form-control" type="text"></td>
							<th>授课方式</th>
							<td><input id="student_basic_teachingmethods"
								class="form-control" type="text"></td>
						</tr>
						<tr>
							<th>专业代码</th>
							<td><input id="student_basic_professionalcode"
								class="form-control" type="text"></td>
							<th>专业名称</th>
							<td><input id="student_basic_major" class="form-control"
								type="text"></td>
						</tr>
						<tr>
							<th>自主专业名称</th>
							<td><input id="student_basic_independentmajorname"
								class="form-control" type="text"></td>
							<th>是否是师范类专业</th>
							<td><input id="student_basic_is_normalmajor"
								class="form-control" type="text"></td>
						</tr>
						<tr>
							<th>是否残疾</th>
							<td><input id="student_basic_is_disability"
								class="form-control" type="text"></td>
							<th>户口类型</th>
							<td><input id="student_basic_householdregistrationtype"
								class="form-control" type="text"></td>
						</tr>
						<tr>
							<th>异动类型</th>
							<td><input id="student_basic_transactiontypes"
								class="form-control" type="text"></td>
							<th>入学学历</th>
							<td><input id="student_basic_entranceeducation"
								class="form-control" type="text"></td>
						</tr>
						<tr>
							<th>招生方式</th>
							<td><input id="student_basic_enrollmentmode"
								class="form-control" type="text"></td>
							<th>休退学原因</th>
							<td><input id="student_basic_reasonsfordroppingoutofschool"
								class="form-control" type="text"></td>
						</tr>
						<tr>
							<th>联系方式</th>
							<td><input id="student_basic_phone" class="form-control"
								type="text"></td>
							<th>学院</th>
							<td><input id="student_basic_college" class="form-control"
								type="text"></td>
						</tr>
					</tbody>
				</table>
				<div style="height: 34px; margin: 30px 0;">
					<button class="btn btn-default" onclick="Create_Student()"
						style="float: right; margin: 0 10px;">
						<i class="fa  fa-check "></i> 确认创建
					</button>
					<button class="btn btn-default"
						onclick="window.location='<%=basePath%>student/StudentInformationManagement_listPage'"
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