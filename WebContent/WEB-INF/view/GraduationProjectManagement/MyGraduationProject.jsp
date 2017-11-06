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
				style="width: 250px; margin: 20px 20px 0 20px; float: left;">
				<div class="panel-heading">
					<h3 class="panel-title">流程</h3>
				</div>
				<!--------------------------------------------------------------------->
				<!--------------------------------------------------------------------->
				<!--------------------------------------------------------------------->

				<div class="navbar">
					<div class="navbar-inner">
						<!--  -->
						<!--  -->
						<!--  -->
						<div class="container" style="width: 100%;">
							<!--  -->
							<div id="panel-scrolling-demo" class="panel">
								<div class="panel-body">
									<!--  -->
									<ul>
										<li>
											<a href="#tab1" data-toggle="tab">1.指导老师完成任务书</a>
										</li>
										<li>
											<a href="#tab2" data-toggle="tab">2.教研室主任填写任务书审核意见</a>
										</li>
										<li>
											<a href="#tab3" data-toggle="tab">3.学生完成开题报告</a>
										</li>
										<li>
											<a href="#tab4" data-toggle="tab">4.学生完成前期进展情况记录</a>
										</li>
										<li>
											<a href="#tab5" data-toggle="tab">5.指导老师填写前期进展情况意见</a>
										</li>
										<li>
											<a href="#tab6" data-toggle="tab">6.学生完成中期进展情况记录</a>
										</li>
										<li>
											<a href="#tab7" data-toggle="tab">7.指导老师填写中期进展情况意见</a>
										</li>
										<li>
											<a href="#tab8" data-toggle="tab">8.学生完成后期进展情况记录</a>
										</li>
										<li>
											<a href="#tab9" data-toggle="tab">9.指导老师填写后期进展情况意见</a>
										</li>
										<li>
											<a href="#tab10" data-toggle="tab">10.学生完成完善期进展情况记录</a>
										</li>
										<li>
											<a href="#tab11" data-toggle="tab">11.指导老师填写完善期进展情况意见</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab">12.学生完成个人学习总结</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab">13.指导老师填写个人学习总结意见</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab">14.学生提交答辩论文</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab">15.指导老师填写形式审查表</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab">16.领导小组长填写形式审查表(核查)</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab">17.指导老师填写评价审阅表</a>
										</li>
										<li>
											<a href="#tab12" data-toggle="tab">18.评阅老师填写评阅审查表</a>
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
				style="width: calc(100% - 310px); margin: 20px 20px 0 0; float: right;">
				<div class="panel-heading">
					<h3 class="panel-title">我的毕业设计</h3>
				</div>
				<div class="panel-body">
					<div id="bar" class="progress">
						<div class="progress-bar" role="progressbar"
							aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
							style="width: 0%;"></div>
					</div>
					<!--  -->
					<div class="tab-content">
						<div class="tab-pane" id="tab1">
							<p>Howdy, I'm in Section 1.</p>
						</div>
						<div class="tab-pane" id="tab2">
							<p>Howdy, I'm in Section 2.</p>
						</div>
						<div class="tab-pane" id="tab3">3</div>
						<div class="tab-pane" id="tab4">4</div>
						<div class="tab-pane" id="tab5">5</div>
						<div class="tab-pane" id="tab6">6</div>
						<div class="tab-pane" id="tab7">7</div>
						<ul class="pager wizard">
							<li class="previous first">
								<a href="#">第一个任务</a>
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
				console.log('onShow');
			},
			onNext : function(tab, navigation, index) {
				console.log('onNext');
			},
			onPrevious : function(tab, navigation, index) {
				console.log('onPrevious');
			},
			onLast : function(tab, navigation, index) {
				console.log('onLast');
			},
			onTabClick : function(tab, navigation, index) {
				console.log('onTabClick');
			},
			onTabShow : function(tab, navigation, index) {
				console.log('onTabShow');
				var $total = navigation.find('li').length;
				var $current = index + 1;
				var $percent = ($current / $total) * 100;
				$('#rootwizard .progress-bar').css({
					width : $percent + '%'
				});
			}
		});

	});
	$('#panel-scrolling-demo .panel-body').slimScroll({
		height : '500px',
		wheelStep : 2,
	});
</script>

</html>