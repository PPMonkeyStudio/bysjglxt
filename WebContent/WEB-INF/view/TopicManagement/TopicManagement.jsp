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

<!---------------------------------------------------------------------------------------------------->
<title>课题管理页</title>
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
			<!--  -->
			<div class="panel-heading">
				<h3 class="panel-title">课题列表</h3>
			</div>
			<div class="panel-body">
				<div style="height: 34px;">
					<div style="width: 500px; float: left;">
						<button class="btn btn-default"
							onclick="window.location='<%=basePath%>topic/TopicManagement_CreateTopicPage'">
							<i class="fa fa-plus-square"></i> 创建课题
						</button>
					</div>
					<!-- 检索 -->
					<div class="input-group" style="width: 300px; float: right;">
						<input id="input_search" class="form-control"
							oninput="List_Student_By_PageAndSearch(1)" type="text"><span
							class="input-group-addon"><i class="fa fa-search"></i></span>
					</div>
				</div>
				<table id="table_student" class="table table-hover table-bordered"
					style="text-align: center; margin: 20px 0;">
					<tbody>
						<tr>
							<th>中文名称</th>
							<th><select class="form-control" style="width: auto;">
									<option value="-1">课题来源</option>
									<option value="各类课题项目">各类课题项目</option>
									<option value="导师指定">导师指定</option>
									<option value="题目指南">题目指南</option>
									<option value="自选">自选</option>
									<option value="其它">其它</option>
							</select></th>
							<th><select class="form-control" style="width: auto;">
									<option value="-1">课题性质</option>
									<option value="理论研究">理论研究</option>
									<option value="应用基础研究">应用基础研究</option>
									<option value="应用与理论结合研究">应用与理论结合研究</option>
									<option value="实际应用">实际应用</option>
							</select></th>
							<th>已选学生数</th>
							<th>指导教师</th>
							<th>协助教师</th>
							<th>状态</th>
							<th>详细</th>
							<th><label class="fancy-checkbox"> <input
									id="checkbox_all_select" type="checkbox" onclick="all_select()"><span>全选</span>
							</label></th>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td>1/8</td>
							<td></td>
							<td></td>
							<td></td>
							<td style="padding: 3px 0 0 0;">
								<button style="" class="btn btn-default btn-ms">查看</button>
							</td>
							<td><label class="fancy-checkbox"><input
									type="checkbox" class="checkbox_select"><span></span></label></td>
						</tr>
					</tbody>
				</table>
				<div id="i_pulse" style="text-align: center;">
					<i class="fa fa-spinner fa-pulse fa-3x"></i>
				</div>
				<div style="height: 34px; margin: 0 0 20px 0;">

					<button class="btn btn-danger" onclick="Delete_Student()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-trash-o"></i> 删除所选
					</button>
				</div>
				<div style="margin: 0 auto; width: 400px; text-align: center;">
					<button id="button_HomePage" class="btn btn-default"
						onclick="flip(1)">首页</button>
					<button id="button_PrePage" class="btn btn-default"
						onclick="flip(2)">上一页</button>
					<button id="button_NextPage" class="btn btn-default"
						onclick="flip(3)">下一页</button>
					<button id="button_EndPage" class="btn btn-default"
						onclick="flip(4)">尾页</button>
				</div>
				<div
					style="margin: 20px auto 20px; width: 200px; text-align: center;">
					第<span id="span_pageIndex">1</span>页<br>共<span
						id="span_totalPages">1</span>页<br>共<span
						id="span_totalRecords">0</span>条记录
				</div>
			</div>
		</div>
		<div class="panel" style="width: 95%; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">
				<h3 class="panel-title">我的课题</h3>
			</div>
			<div class="panel-body">
				<div style="height: 34px;">
					<!-- 检索 -->
					<div class="input-group" style="width: 300px; float: right;">
						<input id="input_search" class="form-control"
							oninput="List_Student_By_PageAndSearch(1)" type="text"><span
							class="input-group-addon"><i class="fa fa-search"></i></span>
					</div>
				</div>
				<table id="table_student" class="table table-hover table-bordered"
					style="text-align: center; margin: 20px 0;">
					<tbody>
						<tr>
							<th>中文名称</th>
							<th><select class="form-control" style="width: auto;">
									<option value="-1">课题来源</option>
									<option value="各类课题项目">各类课题项目</option>
									<option value="导师指定">导师指定</option>
									<option value="题目指南">题目指南</option>
									<option value="自选">自选</option>
									<option value="其它">其它</option>
							</select></th>
							<th><select class="form-control" style="width: auto;">
									<option value="-1">课题性质</option>
									<option value="理论研究">理论研究</option>
									<option value="应用基础研究">应用基础研究</option>
									<option value="应用与理论结合研究">应用与理论结合研究</option>
									<option value="实际应用">实际应用</option>
							</select></th>
							<th>已选学生数</th>
							<th>指导老师</th>
							<th>协助老师</th>
							<th>状态</th>
							<th>操作</th>
							<th><label class="fancy-checkbox"> <input
									id="checkbox_all_select" type="checkbox" onclick="all_select()"><span>全选</span>
							</label></th>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td>1/8</td>
							<td style="padding: 0;"><button style="margin: 3px 0 0 0;"
									class="btn btn-default btn-ms">详细</button></td>
							<td><label class="fancy-checkbox"><input
									type="checkbox" class="checkbox_select"><span></span></label></td>
						</tr>
					</tbody>
				</table>
				<div id="i_pulse" style="text-align: center;">
					<i class="fa fa-spinner fa-pulse fa-3x"></i>
				</div>
				<div style="height: 34px; margin: 0 0 20px 0;">
					<button class="btn btn-danger" onclick="Delete_Student()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-trash-o"></i> 删除所选
					</button>
				</div>
				<div style="margin: 0 auto; width: 400px; text-align: center;">
					<button id="button_HomePage" class="btn btn-default"
						onclick="flip(1)">首页</button>
					<button id="button_PrePage" class="btn btn-default"
						onclick="flip(2)">上一页</button>
					<button id="button_NextPage" class="btn btn-default"
						onclick="flip(3)">下一页</button>
					<button id="button_EndPage" class="btn btn-default"
						onclick="flip(4)">尾页</button>
				</div>
				<div
					style="margin: 20px auto 20px; width: 200px; text-align: center;">
					第<span id="span_pageIndex">1</span>页<br>共<span
						id="span_totalPages">1</span>页<br>共<span
						id="span_totalRecords">0</span>条记录
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
<script>
	var select_major = document.getElementById("select_major");
	var select_grade = document.getElementById("select_grade")
	Get_Student_Major(select_major);
	Get_Student_Grade(select_grade);
</script>
</html>