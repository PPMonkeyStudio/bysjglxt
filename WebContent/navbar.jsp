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
<!--------------------------------------------------------------------------------->
<script type="text/javascript" src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
<!--------------------------------------------------------------------------------->
<link rel="stylesheet" href="<%=basePath%>css/bootstrap-select.min.css">
<script type="text/javascript" src="<%=basePath%>js/bootstrap-select.js"></script>
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
<script type="text/javascript" src="<%=basePath%>js/toastr.js"></script>
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
			<ul class="nav navbar-nav navbar-right" style="margin: 0 50px 0 0">
				<li class="dropdown"><a href="#"
					class="dropdown-toggle icon-menu" data-toggle="dropdown"> <i
						class="lnr lnr-alarm"></i> <span class="badge bg-danger">2</span>
				</a>
					<ul class="dropdown-menu notifications">
						<li><a href="#" class="notification-item"><span
								class="dot bg-warning"></span>任务一</a></li>
						<li><a href="#" class="notification-item"><span
								class="dot bg-success"></span>任务一</a></li>
					</ul></li>

				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <span>江鑫鑫</span> <i
						class="icon-submenu lnr lnr-chevron-down"></i></a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="lnr lnr-user"></i> <span>My
									Profile</span></a></li>
						<li><a href="#"><i class="lnr lnr-envelope"></i> <span>Message</span></a>
						</li>
						<li><a href="#"><i class="lnr lnr-cog"></i> <span>Settings</span></a>
						</li>
						<li><a href="#"><i class="lnr lnr-exit"></i> <span>Logout</span></a>
						</li>
					</ul></li>

			</ul>
		</div>
		</nav>

		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<ul class="nav">
					<!----------------------------------------------------------------------->
					<li><a href="#informationManagement" data-toggle="collapse"
						class="collapsed"><i class="lnr lnr-user"></i> <span>信息</span>
							<i class="icon-submenu lnr lnr-chevron-left"></i></a>
						<div id="informationManagement" class="collapse ">
							<ul class="nav">
								<li><a
									href="<%=basePath%>student/StudentInformationManagement_CreateStudentPage">添加学生信息</a></li></li>
					<li><a
						href="<%=basePath%>student/StudentInformationManagement_listPage"
						class="">学生信息列表</a></li>
					<li><a href="#">添加教师信息</a></li>
					<li><a href="#">教师信息列表</a></li>
				</ul>
			</div>
			</li>
			<!----------------------------------------------------------------------->
			<li><a href="#topic" data-toggle="collapse" class="collapsed"><i
					class="lnr lnr-book"></i> <span>课题</span> <i
					class="icon-submenu lnr lnr-chevron-left"></i></a>
				<div id="topic" class="collapse ">
					<ul class="nav">
						<li><a
							href="<%=basePath%>topic/TopicManagement_CreateTopicPage">创建课题</a></li></li>
			<li><a href="#">课题列表</a></li>
			<li><a href="#">选题列表</a></li>

			</ul>
			<!----------------------------------------------------------------------->
			<li><a href="#process" data-toggle="collapse" class="collapsed"><i
					class="lnr lnr-list"></i> <span>流程</span> <i
					class="icon-submenu lnr lnr-chevron-left"></i></a>
				<div id="process" class="collapse ">
					<ul class="nav">
						<li><a href="#">流程定义列表</a></li></li>
			<li><a href="#">流程实例列表</a></li>
			<li><a href="#">我的流程</a></li>
			</ul>
		</div>
		</li>
		<!----------------------------------------------------------------------->
		<li><a href="#task" data-toggle="collapse" class="collapsed"><i
				class="lnr lnr lnr-map"></i> <span>任务</span> <i
				class="icon-submenu lnr lnr-chevron-left"></i></a>
			<div id="task" class="collapse ">
				<ul class="nav">
					<li><a href="#">任务定义列表</a></li>
					<li><a href="#">任务实例列表</a></li>
					<li><a href="#">我的任务</a></li>
				</ul>
			</div></li>

		<!----------------------------------------------------------------------->
		<li><a href="#" class=""><i class="lnr lnr-exit"></i> <span>退出登录</span></a></li>
		<!----------------------------------------------------------------------->
		</ul>
	</div>
	</div>
	<!--  -->



	<!-- END WRAPPER -->

</body>

</html>