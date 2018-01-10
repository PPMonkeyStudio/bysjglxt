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
	src="<%=basePath%>js/TopicInformationManagement/List_Topic_By_PageAndSearch.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/TopicInformationManagement/Topic_Information_Display.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/TopicInformationManagement/topic_examine_state.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/TopicInformationManagement/studentSelectTopic.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/TopicInformationManagement/Get_Teacher.js"></script>

<!---------------------------------------------------------------------------------------------------->

<title>课题管理页</title>
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
				<div style="width: 500px; margin: 0 auto;">
					<p style="text-align: center;" id="text_TopicCurrentProcess"></p>
					<div class="progress progress-xs">
						<div id="bar_TopicCurrentProcess"
							class="progress-bar progress-bar-success"
							role="progressbar"></div>
					</div>
				</div>
				<hr> 
				<h3 class="panel-title">课题列表</h3>
			</div>
			<div class="panel-body" id="div_TopicPage">


				<div style="height: 34px;">
					<div style="width: 500px; float: left;">
						<button class="teacher_control btn btn-default"
							onclick="window.location='<%=basePath%>topic/TopicInformationManagement_CreateTopicPage'">
							<i class="fa fa-plus-square"></i>
							创建课题
						</button>
					</div>
					<!-- 检索 -->
					<div class="input-group" style="width: 300px; float: right;">
						<input id="input_search" class="form-control"
							oninput="List_Topic_By_PageAndSearch(1)" type="text">
						<span class="input-group-addon">
							<i class="fa fa-search"></i>
						</span>
					</div>
				</div>
				<table id="table_topic" class="table table-hover "
					style="text-align: center; margin: 20px 0;">
					<tbody>
						<tr>
							<th>中文名称</th>
							<th>
								<select class="form-control"
									style="width: auto; margin: 0 auto;" id="select_source"
									onchange="List_Topic_By_PageAndSearch(1)">
									<option value="-1">课题来源（全部）</option>
									<option value="各类课题项目">各类课题项目</option>
									<option value="导师指定">导师指定</option>
									<option value="题目指南">题目指南</option>
									<option value="自选">自选</option>
									<option value="其它">其它</option>
								</select>
							</th>
							<th>
								<select class="form-control"
									style="width: auto; margin: 0 auto;" id="select_type"
									onchange="List_Topic_By_PageAndSearch(1)">
									<option value="-1">课题性质（全部）</option>
									<option value="理论研究">理论研究</option>
									<option value="应用基础研究">应用基础研究</option>
									<option value="应用与理论结合研究">应用与理论结合研究</option>
									<option value="实际应用">实际应用</option>
								</select>
							</th>
							<th>已选学生数</th>
							<th>
								<select class="form-control"
									style="width: auto; margin: 0 auto;"
									data-live-search="true" id="select_teacher"
									onchange="List_Topic_By_PageAndSearch(1)">
									<option value="-1">指导教师（全部）</option>
								</select>
							</th>
							<th>
								<select class="form-control"
									style="width: auto; margin: 0 auto;" id="select_state"
									onchange="List_Topic_By_PageAndSearch(1)">
									<option value="-1">状态（全部）</option>
									<option value="已关闭">已关闭</option>
									<option value="审核已通过">审核已通过</option>
									<option value="未审核">未审核</option>
									<option value="审核未通过">审核未通过</option>
								</select>
							</th>
							<th>查看</th>
							<th class="">
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
					<button class="college_control btn btn-danger"
						onclick="deleteTopicList()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-trash-o"></i>
						删除所选
					</button>
					<button class="college_control btn btn-default"
						onclick="agreeTopicList()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-legal"></i>
						通过
					</button>
					<button class="college_control btn btn-default"
						onclick="refuseTopicList()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-recycle"></i>
						拒绝
					</button>
					<button class="college_control btn btn-default"
						onclick="closeTopicList()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-lock"></i>
						关闭
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
				<div id="lingshi"
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
		</div>
		<!--  -->

		<!--  -->

	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!-- 	<div class="modal fade" id="modal_Topic_Information" -->
	<!-- 		data-keyboard="true" tabindex="-1"> -->
	<!-- 		<div class="modal-dialog"> -->
	<!-- 			<div class="modal-content"> -->
	<!-- 				模态弹出窗内容 -->
	<!-- 				弹出框头部，一般使用“modal-header”表示，主要包括标题和关闭按钮 -->
	<!-- 				<div class="modal-header"> -->
	<!-- 					<button type="button" class="close" data-dismiss="modal"> -->
	<%-- 						<span aria-hidden="true">&times;</span> --%>
	<%-- 						<span class="sr-only">Close</span> --%>
	<!-- 					</button> -->
	<!-- 					<h4 class="modal-title">课题详细信息</h4> -->
	<!-- 				</div> -->
	<!-- 				弹出框主体，一般使用“modal-body”表示，弹出框的主要内容 -->
	<!-- 				<div class="modal-body"> -->
	<!-- 					<table id="table_topic_detail" -->
	<!-- 						class="table table-hover table-bordered" -->
	<!-- 						style="text-align: center;"> -->
	<!-- 						<tbody></tbody> -->
	<!-- 					</table> -->
	<!-- 				</div> -->
	<!-- 				弹出框脚部，一般使用“modal-footer”表示，主要放置操作按钮 -->
	<!-- 				<div class="modal-footer"> -->
	<!-- 					<button class="student_control btn btn-default" -->
	<!-- 						id="button_selectTopic" onclick="studentSelectTopic()" -->
	<!-- 						style="float: right; margin: 0 10px;"> -->
	<!-- 						<i class="fa fa-check"></i> -->
	<!-- 						选题 -->
	<!-- 					</button> -->
	<!-- 					<button class="student_control btn btn-default" -->
	<!-- 						id="button_selectTopic_early" -->
	<!-- 						onclick="studentSelectTopic_early()" -->
	<!-- 						style="float: right; margin: 0 10px;"> -->
	<!-- 						<i class="fa fa-check"></i> -->
	<!-- 						提前选题 -->
	<!-- 					</button> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
</body>
<script>
	
</script>
<script>
	Get_Teacher(document.getElementById("select_teacher"));
</script>

</html>