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
	src="<%=basePath%>js/GraduationProjectManagement/get_taskinstance_byname.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/callback.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/InitializationMyGraduationProject.js"></script>
<!-------------------------------------------------------------------------------------------------->
<%-- <script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Taskbook_Teacher.js"></script> --%>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Taskbook_Section.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/report_opening.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/taskbook_xia_teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/taskbook_wan_student.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/taskbook_shen_teacher.js"></script>
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
	src="<%=basePath%>js/GraduationProjectManagement/Taskbook_Student.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/examination_formal_teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/examination_formal_leader.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/evaluate_tutor.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/evaluate_review.js"></script>
<!-------------------------------------------------------------------------------------------------->
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_Taskbook_Teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_Taskbook_Section.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_report_opening.js"></script>
	
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_RecordProgress_1_Student.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_RecordProgress_1_Teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_RecordProgress_2_Student.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_RecordProgress_2_Teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_RecordProgress_3_Student.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_RecordProgress_3_Teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_RecordProgress_4_Student.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_RecordProgress_4_Teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_summary_student.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_summary_teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_dissertation.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/report_opening_teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_examination_formal_teacher.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_examination_formal_leader.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_evaluate_tutor.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_evaluate_review.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_xia_taskbook.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_wan_taskbook.js"></script>
<!-------------------------------------------------------------------------------------------------->
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/saveGraduationProject.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/uploadDissertation.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/uploadReportOpening.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/uploadTaskbook.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/uploadStudentTaskbook.js"></script>		
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/uploadShenTaskbook.js"></script>	
	<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/uploadShenTaskbook2.js"></script>		
	<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_shen_taskbook.js"></script>	
	<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/uploadTeacherReportOpening.js"></script>	
	<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_teacher_report_opening.js"></script>	
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
					<div class="progress" style="">
						<div class="progress-bar" role="progressbar"
							id="my_progress-bar" aria-valuenow="60" aria-valuemin="0"
							aria-valuemax="100" style="width: 0%;"></div>
					</div>
					<!--  -->
					<!-- 					<div style="text-align: center;padding: 20px 0 0 0;"> -->
					<button class="btn btn-default student_control"
						onclick="exportMyGraduation()" style="float: right;">导出我的《毕业设计过程管理手册》</button>
					<!-- 					</div> -->
					<!--  -->
				</div>
			</div>
			<!--------------------------------------------------------------------->
			<!--------------------------------------------------------------------->
			<!--------------------------------------------------------------------->

			<!--------------------------------------------------------------------->
			<!--------------------------------------------------------------------->
			<!--------------------------------------------------------------------->
			<div class="panel"
				style="width: 20%; margin: 20px 20px 0 20px; float: left;">
				<div class="panel-heading">
					<h3 class="panel-title">流程</h3>
				</div>
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
												id="banner_xia_Taskbook_Teacher">1.指导老师下发任务书</a>
										</li>
										<li>	
											<a href="#tab2" data-toggle="tab"
												id="banner_wan_Taskbook_Student">2.学生完善任务书</a>
										</li>
										<li>
											<a href="#tab3" data-toggle="tab"
												id="banner_shen_Taskbook_Teacher">3.指导老师审核任务书</a>
										</li>
										<li>
											<a href="#tab4" data-toggle="tab"
												id="banner_Taskbook_Section">4.指导小组组长填写任务书审核意见</a>
										</li>
										<!-- <li>
											<a href="#tab3" data-toggle="tab"
												id="banner_Taskbook_Student">3.学生接受任务</a>
										</li> -->
										<li>
											<a href="#tab5" data-toggle="tab"
												id="banner_report_opening">5.学生上传开题报告</a>
										</li>
										<li>
											<a href="#tab6" data-toggle="tab"
												id="banner_report_opening_teacher">6.指导老师确认开题报告</a>
										</li>
										<li>
											<a href="#tab7" data-toggle="tab"
												id="banner_RecordProgress_1_Student">7.学生完成进展情况记录（前期准备阶段）</a>
										</li>
										<li>
											<a href="#tab8" data-toggle="tab"
												id="banner_RecordProgress_1_Teacher">8.指导老师填写进展情况意见（前期准备阶段）</a>
										</li>
										<li>
											<a href="#tab9" data-toggle="tab"
												id="banner_RecordProgress_2_Student">9.学生完成进展情况记录（撰写阶段）</a>
										</li>
										<li>
											<a href="#tab10" data-toggle="tab"
												id="banner_RecordProgress_2_Teacher">10.指导老师填写进展情况意见（撰写阶段）</a>
										</li>
										<li>
											<a href="#tab11" data-toggle="tab"
												id="banner_RecordProgress_3_Student">11.学生完成进展情况记录（中期自查阶段）</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab"
												id="banner_RecordProgress_3_Teacher">12.指导老师填写进展情况意见（中期自查阶段）</a>
										</li>
										<li>
											<a href="#tab13" data-toggle="tab"
												id="banner_RecordProgress_4_Student">13.学生完成进展情况记录（完善阶段）</a>
										</li>
										<li>
											<a href="#tab14" data-toggle="tab"
												id="banner_RecordProgress_4_Teacher">14.指导老师填写进展情况意见（完善阶段）</a>
										</li>
										<li>
											<a href="#tab15" data-toggle="tab"
												id="banner_summary_student">15.学生完成个人学习总结</a>
										</li>
										<li>
											<a href="#tab16" data-toggle="tab"
												id="banner_summary_teacher">16.指导老师填写个人学习总结意见</a>
										</li>
										<li>
											<a href="#tab17" data-toggle="tab"
												id="banner_dissertation">17.学生提交答辩论文</a>
										</li>
										<li>
											<a href="#tab18" data-toggle="tab"
												id="banner_examination_formal_teacher">18.指导老师填写形式审查表</a>
										</li>
										<li>
											<a href="#tab19" data-toggle="tab"
												id="banner_examination_formal_leader">19.领导小组长填写形式审查表(核查)</a>
										</li>
										<li>
											<a href="#tab20" data-toggle="tab"
												id="banner_evaluate_tutor">20.指导老师填写评价审阅表</a>
										</li>
										<li>
											<a href="#tab21" data-toggle="tab"
												id="banner_evaluate_review">21.评阅老师填写评阅审查表</a>
										</li>
										
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!--------------------------------------------------------------------->
			<!--------------------------------------------------------------------->
			<!--------------------------------------------------------------------->
			<div class="panel"
				style="width: calc(100% - 20% - 60px); margin: 20px 20px 50px 0; float: right;">
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
						<div class="tab-pane" id="tab19"></div>
						<div class="tab-pane" id="tab20"></div>
						<div class="tab-pane" id="tab21"></div>
						<!-- <ul class="pager wizard">
							<li class="previous first">
								<a href="#">第一个任务</a>
							</li>
							<li class="" id="button_SaveGraduationProject">
								<a href="####" onclick='saveGraduationProject()'>提交更新</a>
							</li>
							<li class="previous">
								<a href="#">上一个任务</a>
							</li>


							<li class="next last">
								<a href="#">最后一个任务</a>
							</li>
							<li class="next">
								<a href="#">下一个任务</a>
							</li>
						</ul> -->
					</div>
					<!--  -->
				</div>
			</div>

			<!--------------------------------------------------------------------->
			<!--------------------------------------------------------------------->
			<!--------------------------------------------------------------------->

		</div>
		</section>
		<!---------------------------------------------------------------------------------------------------->
		<!---------------------------------------------------------------------------------------------------->
	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
</body>
<script>
	
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
					get_taskinstance_byname("指导老师下发任务书");
					break;
				}
				case 1: {
					get_taskinstance_byname("学生完善任务书");
					break;
				}
				case 2: {
					get_taskinstance_byname("指导老师审核任务书");
					break;
				}
				case 3: {
					get_taskinstance_byname("指导小组组长填写任务书审核意见");
					break;
				}
				case 4: {
					get_taskinstance_byname("学生上传开题报告");
					break;
				}
				case 5: {
					get_taskinstance_byname("指导老师确认开题报告");
					/* Report_Opening_Teacher(); */
					break;
				}
				case 6: {
					get_taskinstance_byname("学生完成进展情况记录（前期准备阶段）");
					break;
				}
				case 7: {
					get_taskinstance_byname("指导老师填写进展情况意见（前期准备阶段）");
					break;
				}
				case 8: {
					get_taskinstance_byname("学生完成进展情况记录（撰写阶段）");
					break;
				}
				case 9: {
					get_taskinstance_byname("指导老师填写进展情况意见（撰写阶段）");
					break;
				}
				case 10: {
					get_taskinstance_byname("学生完成进展情况记录（中期自查阶段）");
					break;
				}
				case 11: {
					get_taskinstance_byname("指导老师填写进展情况意见（中期自查阶段）");
					break;
				}
				case 12: {
					get_taskinstance_byname("学生完成进展情况记录（完善阶段）");
					break;
				}
				case 13: {
					get_taskinstance_byname("指导老师填写进展情况意见（完善阶段）");
					break;
				}
				case 14: {
					get_taskinstance_byname("学生完成个人学习工作总结");
					break;
				}
				case 15: {
					get_taskinstance_byname("指导老师填写个人学习工作总结意见");
					break;
				}
				case 16: {
					get_taskinstance_byname("学生提交答辩论文");
					break;
				}
				case 17: {
					get_taskinstance_byname("指导老师填写形式审查表");
					break;
				}
				case 18: {
					examination_formal_leader();
					break;
				}
				case 19: {
					get_taskinstance_byname("指导老师填写评价审阅表");
					break;
				}
				case 20: {
					get_taskinstance_byname("评阅老师填写评阅审查表");
					/* evaluate_review(); */
					break;
				}
				}

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
<link rel="stylesheet" href="<%=basePath%>css/square/blue.css" />
<script type="text/javascript" src="<%=basePath%>js/icheck.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/exportGraduation.js"></script>
</html>