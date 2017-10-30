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
	src="<%=basePath%>js/TopicManagement/Create_Topic.js"></script>
<!---------------------------------------------------------------------------------------------------->
<title>创建课题</title>
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
				<h3 class="panel-title">新增课题</h3>
			</div>
			<div class="panel-body">
				<table id="" class="table table-hover table-bordered  "
					style="text-align: center; width: 700px; margin: 0 auto;">
					<tbody>
						<tr>
							<th>课题中文名称</th>
							<td><input id="topic_name_chinese" class="form-control"
								style="width: 500px;"></td>
						</tr>
						<tr>
							<th>课题英文名称</th>
							<td><input id="topic_name_english" class="form-control"
								style="width: 500px;"></td>
						</tr>
						<tr>
							<th>协助教师姓名</th>
							<td><input id="topic_invite_teacher_name"
								class="form-control" style="width: 500px;"></td>
						</tr>
						<tr>
							<th>协助教师性别</th>
							<td><select id="topic_invite_teacher_sex"
								class="form-control" style="width: auto;">
									<option value="男">男</option>
									<option value="女">女</option>
							</select></td>
						</tr>
						<tr>
							<th>协助教师工作单位</th>
							<td><input id="topic_invite_teacher_unitname"
								class="form-control" style="width: 500px;"></td>
						</tr>
						<tr>
							<th>协助教师职称</th>
							<td><input id="topic_invite_teacher_technicalitle"
								class="form-control" style="width: 500px;"></td>
						</tr>
						<tr>
							<th>协助教师职务</th>
							<td><input id="topic_invite_teacher_duties"
								class="form-control" style="width: 500px;"></td>
						</tr>
						<tr>
							<th>课题实现要求</th>
							<td><textarea id="topic_requirement" class="form-control"
									style="max-width: 500px; min-width: 500px;"></textarea></td>
						</tr>
						<tr>
							<th>课题来源</th>
							<td><select id="topic_source" class="form-control"
								style="width: auto;">
									<option value="各类课题项目">各类课题项目</option>
									<option value="导师指定">导师指定</option>
									<option value="题目指南">题目指南</option>
									<option value="自选">自选</option>
									<option value="其它">其它</option>
							</select></td>
						</tr>
						<tr>
							<th>课题性质</th>
							<td><select id="topic_type" class="form-control"
								style="width: auto;">
									<option value="理论研究">理论研究</option>
									<option value="应用基础研究">应用基础研究</option>
									<option value="应用与理论结合研究">应用与理论结合研究</option>
									<option value="实际应用">实际应用</option>
							</select></td>
						</tr>
						<tr>
							<th>课题备注</th>
							<td><textarea id="topic_remark" class="form-control"
									style="max-width: 500px; min-width: 500px;"></textarea></td>
						</tr>
					</tbody>
				</table>
				<div style="height: 34px; margin: 30px 0;">
					<button class="btn btn-default" onclick="Create_Topic()"
						style="float: right; margin: 0 10px;">
						<i class="fa  fa-check "></i> 确认创建
					</button>
					<button class="btn btn-default"
						onclick="window.location='<%=basePath%>topic/TopicManagement_TopicListPage'"
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