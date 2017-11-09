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
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/InitializationMyGraduationProject.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Taskbook_Teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Taskbook_Section.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/report_opening.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/RecordProgress_1_Student.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/RecordProgress_1_Teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/RecordProgress_2_Student.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/RecordProgress_2_Teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/RecordProgress_3_Student.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/RecordProgress_3_Teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/RecordProgress_4_Student.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/RecordProgress_4_Teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/summary_student.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/summary_teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/dissertation.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/examination_formal_teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/examination_formal_leader.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/evaluate_tutor.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/evaluate_review.js"></script>
<!---------------------------------------------------------------------------------------------------->
<title>我的毕业设计</title>
</head>
<body>
	<s:action name="LoginLogoutManagement_navbar"
		namespace="/loginLogout" executeResult="true" />
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<div style="margin: 80px 0 0 0; float: left; width: 100%;">
		<!---------------------------------------------------------------------------------------------------->
		<!---------------------------------------------------------------------------------------------------->
		<section id="wizard" style="width: 100%;">
		<div id="rootwizard">
			<div class="panel"
				style="width: calc(100% - 40px); margin: 20px 20px 0 0; float: right;">
				<div class="panel-body">
					<h3 class="panel-title">进度</h3>
					<br>
					<div id="bar" class="progress">
						<div class="progress-bar" role="progressbar"
							id="my_progress-bar" aria-valuenow="60" aria-valuemin="0"
							aria-valuemax="100" style="width: 0%;"></div>
					</div>
				</div>
			</div>

			<div class="panel"
				style="width: 20%; margin: 20px 20px 0 20px; float: left;">
				<div class="panel-heading">
					<h3 class="panel-title">流程</h3>
				</div>
				<!--------------------------------------------------------------------->
				<!--------------------------------------------------------------------->
				<!--------------------------------------------------------------------->
				<div class="navbar">
					<div class="navbar-inner">
						<div class="container" style="width: 100%;">
							<!--  -->
							<div id="panel-scrolling-demo" class="panel">
								<div class="panel-body">
									<!--  -->
									<ul>
										<li>
											<a href="#tab1" data-toggle="tab"
												id="banner_Taskbook_Teacher">1.指导老师完成任务书</a>
										</li>
										<li>
											<a href="#tab2" data-toggle="tab"
												id="banner_Taskbook_Section">2.教研室主任填写任务书审核意见</a>
										</li>
										<li>
											<a href="#tab3" data-toggle="tab"
												id="banner_report_opening">3.学生完成开题报告</a>
										</li>
										<li>
											<a href="#tab4" data-toggle="tab"
												id="banner_RecordProgress_1_Student">4.学生完成前期进展情况记录</a>
										</li>
										<li>
											<a href="#tab5" data-toggle="tab"
												id="banner_RecordProgress_1_Teacher">5.指导老师填写前期进展情况意见</a>
										</li>
										<li>
											<a href="#tab6" data-toggle="tab"
												id="banner_RecordProgress_2_Student">6.学生完成中期进展情况记录</a>
										</li>
										<li>
											<a href="#tab7" data-toggle="tab"
												id="banner_RecordProgress_2_Teacher">7.指导老师填写中期进展情况意见</a>
										</li>
										<li>
											<a href="#tab8" data-toggle="tab"
												id="banner_RecordProgress_3_Student">8.学生完成后期进展情况记录</a>
										</li>
										<li>
											<a href="#tab9" data-toggle="tab"
												id="banner_RecordProgress_3_Teacher">9.指导老师填写后期进展情况意见</a>
										</li>
										<li>
											<a href="#tab10" data-toggle="tab"
												id="banner_RecordProgress_4_Student">10.学生完成完善期进展情况记录</a>
										</li>
										<li>
											<a href="#tab11" data-toggle="tab"
												id="banner_RecordProgress_4_Teacher">11.指导老师填写完善期进展情况意见</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab"
												id="banner_summary_student">12.学生完成个人学习总结</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab"
												id="banner_summary_teacher">13.指导老师填写个人学习总结意见</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab"
												id="banner_dissertation">14.学生提交答辩论文</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab"
												id="banner_examination_formal_teacher">15.指导老师填写形式审查表</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab"
												id="banner_examination_formal_leader">16.领导小组长填写形式审查表(核查)</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab"
												id="banner_evaluate_tutor">17.指导老师填写评价审阅表</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab"
												id="banner_evaluate_review">18.评阅老师填写评阅审查表</a>
										</li>
									</ul>
									<!--  -->
								</div>
							</div>
							<!--  -->
						</div>
						<!--  -->
						<!--  -->
						<!--  -->
					</div>
				</div>
				<!--  -->
				<!--------------------------------------------------------------------->
				<!--------------------------------------------------------------------->
				<!--------------------------------------------------------------------->
			</div>
			<!--------------------------------------------------------------------->
			<!--------------------------------------------------------------------->
			<!--------------------------------------------------------------------->
			<div class="panel"
				style="width: calc(100% - 20% - 60px); margin: 20px 20px 0 0; float: right;">
				<div class="panel-heading">
					<h3 class="panel-title" id="GraduationProjectTitle"></h3>
				</div>
				<div class="panel-body">
					<div class="tab-content">
						<div class="tab-pane" id="tab1"></div>
						<div class="tab-pane" id="tab2"></div>
						<div class="tab-pane" id="tab3"></div>
						<div class="tab-pane" id="tab4"></div>
						<div class="tab-pane" id="tab5"></div>
						<div class="tab-pane" id="tab6"></div>
						<div class="tab-pane" id="tab7"></div>
						<div class="tab-pane" id="tab8"></div>
						<div class="tab-pane" id="tab9"></div>
						<div class="tab-pane" id="tab10"></div>
						<div class="tab-pane" id="tab11"></div>
						<div class="tab-pane" id="tab12"></div>
						<div class="tab-pane" id="tab13"></div>
						<div class="tab-pane" id="tab14"></div>
						<div class="tab-pane" id="tab15"></div>
						<div class="tab-pane" id="tab16"></div>
						<div class="tab-pane" id="tab17"></div>
						<div class="tab-pane" id="tab18"></div>
						<ul class="pager wizard">
							<!-- <li class="previous first">
								<a href="#">第一个任务</a>
							</li> -->
							<li class="previous">
								<a href="#">上一个任务</a>
							</li>

							<li class="" id="button_SaveGraduationProject">
								<a href="#" onclick='toastr.success()'>保存</a>
							</li>
							<!-- <li class="next last">
								<a href="#">最后一个任务</a>
							</li> -->
							<li class="next">
								<a href="#">下一个任务</a>
							</li>
						</ul>
					</div>
					<!--  -->
				</div>
			</div>
		</div>
		</section>
		<!---------------------------------------------------------------------------------------------------->
		<!---------------------------------------------------------------------------------------------------->
	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
</body>
<script>
	$('select').selectpicker('refresh');
</script>

<script>
	$(document).ready(function() {
		$('#rootwizard').bootstrapWizard({
			'tabClass' : 'nav',
			'debug' : true,
			onShow : function(tab, navigation, index) {
			},
			onNext : function(tab, navigation, index) {
			},
			onPrevious : function(tab, navigation, index) {
			},
			onLast : function(tab, navigation, index) {
			},
			onTabClick : function(tab, navigation, index) {
			},
			onTabShow : function(tab, navigation, index) {
				switch (index) {
				case 0: {
					Taskbook_Teacher();
					break;
				}
				case 1: {
					Taskbook_Section();
					break;
				}
				case 2: {
					report_opening();
					break;
				}
				case 3: {
					RecordProgress_1_Student();
					break;
				}
				case 4: {
					RecordProgress_1_Teacher();
					break;
				}
				case 5: {
					RecordProgress_2_Student();
					break;
				}
				case 6: {
					RecordProgress_2_Teacher();
					break;
				}
				case 7: {
					RecordProgress_3_Student();
					break;
				}
				case 8: {
					RecordProgress_3_Teacher();
					break;
				}
				case 9: {
					RecordProgress_4_Student();
					break;
				}
				case 10: {
					RecordProgress_4_Teacher();
					break;
				}
				case 11: {
					summary_student();
					break;
				}
				case 12: {
					summary_teacher();
					break;
				}
				case 13: {
					dissertation();
					break;
				}
				case 14: {
					examination_formal_teacher();
					break;
				}
				case 15: {
					examination_formal_leader();
					break;
				}
				case 16: {
					evaluate_tutor();
					break;
				}
				case 17: {
					evaluate_review();
					break;
				}

				}
				var $total = navigation.find('li').length;
				var $current = index + 1;
				var $percent = ($current / $total) * 100;
				$('#my_progress-bar').innerHTML = $percent + '%';
				$('#rootwizard .progress-bar').css({
					width : $percent + '%',

				});
				$('#my_progress-bar').html($percent.toFixed(1) + '%');

			}
		});
		InitializationMyGraduationProject();
	});

	var myClientHeight = (document.documentElement.clientHeight - 380) + 'px';
	$('#panel-scrolling-demo .panel-body').slimScroll({
		height : myClientHeight,
		wheelStep : 1,
	});
</script>
</html>