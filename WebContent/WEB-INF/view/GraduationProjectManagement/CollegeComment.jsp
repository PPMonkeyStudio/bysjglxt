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



<title>评语管理</title>
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

		<!--  -->

		<div class="panel" style="width: 95%; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">
				<h3 class="panel-title">评语管理</h3>
			</div>
			<!--  -->
			<div class="panel-body">
				<!--  -->
				<div style="height: 34px;">
					<div style="width: 500px; float: left;">
						<button class="btn btn-default" onclick="saveNewComment()">
							<i class="fa fa-plus-square"></i>
							创建评语
						</button>
						<button class="btn btn-default" data-toggle="modal"
							data-target="#modal_excel">
							<i class="fa fa-upload"></i>
							通过Excel导入评语
						</button>
					</div>
				</div>
				<!--  -->
				<table id="table_Comment" class="table table-hover"
					style="text-align: center; margin: 20px 0;">
					<tbody>
						<tr>
							<th>内容</th>
							<th class="teacher_control">
								<select id="select_category" class="form-control"
									style="width: auto; margin: 0 auto;" id=""
									onchange="List_Comment_By_College(1)">
									<option value="-1">类别（全部）</option>
									<option value="引言">引言</option>
									<option value="工作态度">工作态度</option>
									<option value="选题质量">选题质量</option>
									<option value="题目难易度">题目难易度</option>
									<option value="题目工作量">题目工作量</option>
									<option value="题目与生产、科研、实验室建设等实际的结合程度">题目与生产、科研、实验室建设等实际的结合程度</option>
									<option value="综合运用知识">综合运用知识</option>
									<option value="查阅文献资料及资料应用">查阅文献资料及资料应用</option>
									<option value="实验设计">实验设计</option>
									<option value="计算能力">计算能力</option>
									<option value="外语应用">外语应用</option>
									<option value="计算机应用">计算机应用</option>
									<option value="创新">创新</option>
									<option value="对实验结果的分析能力">对实验结果的分析能力</option>
									<option value="插图（或图纸）质量">插图（或图纸）质量</option>
									<option value="设计的实用性与科学性">设计的实用性与科学性</option>
									<option value="设计规范化程度">设计规范化程度</option>
									<option value="设计说明书撰写水平">设计说明书撰写水平</option>
									<option value="总结">总结</option>
								</select>
							</th>
							<th class="teacher_control">
								<select id="select_grade" class="form-control"
									style="width: auto; margin: 0 auto;" id=""
									onchange="List_Comment_By_College(1)">
									<option value="-1">等级（全部）</option>
									<option value="很好">等级（很好）</option>
									<option value="好">等级（好）</option>
									<option value="一般">等级（一般）</option>
									<option value="差">等级（差）</option>
								</select>
							</th>

							<th style="width: 100px;">
								<label class="fancy-checkbox">
									<input id="checkbox_all_select" type="checkbox"
										onclick="all_select()">
									<span>全选</span>
								</label>
							</th>
						</tr>
					</tbody>
				</table>
				<!--  -->
				<div id="i_pulse" style="text-align: center;">
					<i class="fa fa-spinner fa-pulse fa-3x"></i>
				</div>
				<!--  -->
				<div style="height: 34px; margin: 0 0 20px 0;">
					<button class="btn btn-danger" onclick="delete_comment()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-trash-o"></i>
						删除所选
					</button>
				</div>
				<!--  -->
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
				<!--  -->
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
				<!--  -->
			</div>
			<!--  -->
		</div>

		<!--  -->

	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!-------excel模态框------->
	<div class="modal fade " id="modal_excel" data-keyboard="true"
		tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- 模态弹出窗内容 -->
				<!--弹出框头部，一般使用“modal-header”表示，主要包括标题和关闭按钮-->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">通过Excel导入评语</h4>
				</div>
				<!--弹出框主体，一般使用“modal-body”表示，弹出框的主要内容-->
				<div class="modal-body">
					<button class="btn btn-default" onclick="import_click()"
						style="margin: 10px 0;">选择文件</button>
					<input id="input_upload_comment_excel" type="file"
						onchange="Preview_Comment_EXCEL(this)"
						style="display: none;">
					<script type="text/javascript">
						function import_click() {
							document.getElementById(
									"input_upload_comment_excel").click();
						}
					</script>
					<!--  -->
					<div
						style="width: 100%; max-height: 400px; min-height: 0px; overflow-x: auto; overflow-y: auto; -webkit-overflow-scrolling: touch; -ms-overflow-style: -ms-autohiding-scrollbar; border: 1px solid #ddd;">
						<table id="table_excel_comment"
							class="table table-bordered table-hover"
							style="text-align: center;">
							<tbody></tbody>
						</table>
					</div>
					<!--  -->
					<div id="i_pulse_2"
						style="text-align: center; display: none;">
						<i class="fa fa-spinner fa-pulse fa-3x"></i>
					</div>
				</div>
				<!--弹出框脚部，一般使用“modal-footer”表示，主要放置操作按钮-->
				<div class="modal-footer">
					<button class="btn btn-danger"
						onclick="remove_Preview_Comment_EXCEL()">
						<i class="fa fa-trash-o"></i>
						重置数据
					</button>
					<button class="btn btn-default"
						onclick="Save_Comment_EXCEL()">
						<i class="fa fa-check"></i>
						确认导入
					</button>
				</div>
			</div>
		</div>
	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
</body>
<!---------------------------------------------------------------------------------------------------->
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/List_Comment_By_College.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/saveNewComment.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/delete_comment.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Save_Comment_EXCEL.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/Preview_Comment_EXCEL.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/GraduationProjectManagement/remove_Preview_Comment_EXCEL.js"></script>
<!---------------------------------------------------------------------------------------------------->
</html>