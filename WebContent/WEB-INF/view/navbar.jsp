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
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<!--------------------------------------------------------------------------------->
<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
<%-- <script src="<%=basePath%>js/jquery-2.1.0.min.js"></script> --%>
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
<!--------------------------------------------------------------------------------->
<link rel="stylesheet"
	href="<%=basePath%>css/bootstrap-select.min.css">
<script src="<%=basePath%>js/bootstrap-select.js"></script>
<!--------------------------------------------------------------------------------->
<link rel="stylesheet"
	href="<%=basePath%>css/navbar/chartist-custom.css">
<link rel="stylesheet" href="<%=basePath%>css/navbar/main.css">
<link rel="stylesheet"
	href="<%=basePath%>css/navbar/font-awesome.min.css">
<link rel="stylesheet" href="<%=basePath%>css/navbar/style.css">
<link rel="stylesheet" href="<%=basePath%>css/table.css">
<!--------------------------------------------------------------------------------->
<link rel="stylesheet" href="<%=basePath%>css/toastr.css" />
<script src="<%=basePath%>js/toastr.js"></script>
<!--------------------------------------------------------------------------------->
<link rel="stylesheet" href="<%=basePath%>css/jquery-confirm.css" />
<script src="<%=basePath%>js/jquery-confirm.js"></script>
<!--------------------------------------------------------------------------------->
<script src="<%=basePath%>js/jquery.bootstrap.wizard.js"></script>
<script src="<%=basePath%>js/jquery.slimscroll.min.js"></script>
<script src="<%=basePath%>js/klorofil-common.js"></script>
<!--------------------------------------------------------------------------------->
<!---页面公用------------------------------------------------------------------------------>
<script type="text/javascript"
	src="<%=basePath%>js/loginAndLogout/Input_Select.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/loginAndLogout/getUserSessionForAjax.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/loginAndLogout/roleControl.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/NoticeManagement/List_Navbar_Notice.js"></script>
<!--------------------------------------------------------------------------------->
<!--------------------------------------------------------------------------------->
<title>Insert title here</title>
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-fixed-top">
		<div
			style="width: auto; float: left; line-height: 78px; margin: 0 0 0 30px; font-size: 30px;">毕业设计管理系统</div>
		<div id="navbar-menu">
			<ul class="nav navbar-nav navbar-left"
				style="margin: 0 0 0 30px">
				<li class="dropdown" style="float: left;">
					<a
						href="<%=basePath%>loginLogout/LoginLogoutManagement_index">
						<span>首页</span>
					</a>
				</li>
				<!--  -->
				<li class="leader_control dropdown" style="float: left;">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<span>信息</span>
						<i class="icon-submenu lnr lnr-chevron-down"></i>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a
								href="<%=basePath%>student/StudentInformationManagement_listPage"
								class="">学生</a>
						</li>
						<li>
							<a
								href="<%=basePath%>teacher/TeacherInformationManagement_TeacherManagementPage">教师</a>
						</li>
						<li>
							<a
								href="<%=basePath%>section/SectionInformationManagement_SectionManagementPage">教研室</a>
						</li>
					</ul>
				</li>
				<!--  -->
				<li class="dropdown" style="float: left;">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<span>课题</span>
						<i class="icon-submenu lnr lnr-chevron-down"></i>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a
								href="<%=basePath%>topic/TopicInformationManagement_TopicListPage"
								class="">课题列表</a>
						</li>
						<li>
							<a href="<%=basePath%>topic/TopicInformationManagement_MyTopicListPage">我的课题</a>
						</li>
					</ul>
				</li>
				<!--  -->
				<li class="dropdown" style="float: left;">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<span>流程</span>
						<i class="icon-submenu lnr lnr-chevron-down"></i>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a
								href="<%=basePath%>process/ProcessManagement_ProcessDefinitionListPage">流程列表</a>
						</li>
						<li>
							<a href="<%=basePath%>process/ProcessManagement_MyTask">我的任务</a>
						</li>
					</ul>
				</li>
				<!--  -->
				<li class="dropdown" style="float: left;">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<span>毕业设计</span>
						<i class="icon-submenu lnr lnr-chevron-down"></i>
					</a>
					<ul class="dropdown-menu">
						<li class="teacher_control">
							<a
								href="<%=basePath%>graduationProject/GraduationProjectManagement_MyManagementGraduationProjectPage">毕业设计管理</a>
						</li>
						<li class="teacher_control">
							<a
								href="<%=basePath%>graduationProject/GraduationProjectManagement_MyTutorGraduationProjectPage">我指导的学生</a>
						</li>
						<li class="student_control">
							<a
								href="<%=basePath%>graduationProject/GraduationProjectManagement_MyGraduationProjectPage">我的毕业设计</a>
						</li>
						<li class="student_control">
							<a
								href="<%=basePath%>graduationProject/GraduationProjectManagement_exportAll">导出我的毕业设计</a>
						</li>
					</ul>
				</li>
				<!--  -->
			</ul>
			<!--  -->
			<ul class="nav navbar-nav navbar-right"
				style="margin: 0 50px 0 0">
				<!--  -->
				<li class="dropdown">
					<a href="#" class="dropdown-toggle icon-menu"
						data-toggle="dropdown">
						<i class="lnr lnr-alarm"></i>
						<span class="badge bg-danger" id="num_Navbar_Notice"></span>
					</a>
					<ul class="dropdown-menu notifications"
						id="ul_Navbar_Notice">
						<li>
							<a
								href="<%=basePath%>notice/NoticeManagement_NoticeManagementPage"
								class="notification-item" style="text-align: center;">查看历史消息</a>
						</li>
					</ul>
				</li>

				<!--  -->
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-user-circle"></i>
						<span id="USER_NAME"></span>
						<i class="icon-submenu lnr lnr-chevron-down"></i>
					</a>
					<ul class="dropdown-menu">
						<%-- <li>
							<a href="#">
								<i class="lnr lnr-user"></i>
								<span>我的信息</span>
							</a>
						</li> --%>
						<li>
							<a href="#">
								<i class="lnr lnr-lock"></i>
								<span>修改密码</span>
							</a>
						</li>
						<li>
							<a
								href="<%=basePath%>loginLogout/LoginLogoutManagement_logout">
								<i class="lnr lnr-exit"></i>
								<span>退出登录</span>
							</a>
						</li>
					</ul>
				</li>
				<!--  -->
			</ul>
		</div>
		</nav>
</body>
<script type="text/javascript">
	getUserSessionForAjax();
</script>
<style>
th {
	vertical-align: middle !important;
}

td {
	line-height: 33px !important;
	vertical-align: middle !important;
}

td i {
	line-height: 33px !important;
}
table select {
	text-align: center !important;
}
td button i {
	line-height: 20px !important;
}

td .label {
	line-height: 33px !important;
}
</style>
<script>
	
</script>
</html>