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
<script type="text/javascript"
	src="<%=basePath%>js/TopicInformationManagement/Create_Topic.js"></script>
<!---------------------------------------------------------------------------------------------------->
<title>创建课题</title>
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
		<div class="panel" style="width: 900px; margin: 20px auto;">
			<div class="panel-heading">
				<h3 class="panel-title">新增课题</h3>
			</div>
			<div class="panel-body">
				<div>
					<h4>课题中文名称</h4>
					<input id="topic_name_chinese" class="form-control"
						style="margin: 0 0 20px 0;">
					<h4>课题英文名称</h4>
					<input id="topic_name_english" class="form-control"
						style="margin: 0 0 20px 0;">
					<h4>课题来源</h4>
					<select id="topic_source" class="selectpicker"
						style="width: auto;">
						<option value="各类课题项目">各类课题项目</option>
						<option value="导师指定">导师指定</option>
						<option value="题目指南">题目指南</option>
						<option value="自选">自选</option>
						<option value="其它">其它</option>
					</select>
					<h4>课题性质</h4>
					<select id="topic_type" class="selectpicker"
						style="width: auto;">
						<option value="理论研究">理论研究</option>
						<option value="应用基础研究">应用基础研究</option>
						<option value="应用与理论结合研究">应用与理论结合研究</option>
						<option value="实际应用">实际应用</option>
					</select>
					<h4>课题实现要求</h4>
					<textarea id="topic_requirement" class="form-control"
						style="resize: none; margin: 0 0 20px 0;"></textarea>
					<h4>课题备注</h4>
					<textarea id="topic_remark" class="form-control"
						style="resize: none;"></textarea>
					<h4>课题学生上限<span style="">（-1表示无上限）</span></h4>
					<input id="topic_student_max" class="form-control"
						style="margin: 0 0 20px 0;" value="-1">
				</div>
				<div style="height: 34px; margin: 30px 0;">
					<button class="btn btn-primary" onclick="Create_Topic()"
						style="float: right; margin: 0 10px;">
						<i class="fa  fa-check "></i>
						确认创建
					</button>
					<button class="btn btn-default"
						onclick="window.location='<%=basePath%>topic/TopicInformationManagement_TopicListPage'"
						style="float: right; margin: 0 10px;">
						返回
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
<style>
</style>
</html>