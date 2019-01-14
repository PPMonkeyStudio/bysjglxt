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
	src="<%=basePath%>js/SectionInformationManagement/List_Section_By_Page.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/SectionInformationManagement/Delete_Section.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/SectionInformationManagement/Update_Section.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/SectionInformationManagement/Get_Section_Teacher.js"></script>
<!---------------------------------------------------------------------------------------------------->
<title>学生信息管理</title>
</head>
<body>
	<s:action name="LoginLogoutManagement_navbar" namespace="/loginLogout"
		executeResult="true" />
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
				<h3 class="panel-title">教研室列表</h3>
			</div>
			<!--  -->
			<div class="panel-body">
				<div style="height: 34px;">

					<div style="width: 500px; float: left;">
						<button class="btn btn-default"
							onclick="window.location='<%=basePath%>section/SectionInformationManagement_CreateSectionPage'">
							<i class="fa fa-plus-square"></i> 新增
						</button>
					</div>
				</div>
				<table id="table_section" class="table table-hover "
					style="text-align: center; margin: 20px 0;">
					<tbody>
						<tr>
							<th>教研室名称</th>
							<th>教研室主任</th>
							<th>操作</th>
							<%-- <th><label class="fancy-checkbox"> <input
									id="checkbox_all_select" type="checkbox" onclick="all_select()"><span>全选</span>
							</label></th> --%>
						</tr>
					</tbody>
				</table>
				<div id="i_pulse" style="text-align: center;">
					<i class="fa fa-spinner fa-pulse fa-3x"></i>
				</div>
				<div style="height: 34px; margin: 0 0 20px 0;">

				<!-- 	<button class="btn btn-danger" onclick="Delete_Section()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-trash-o"></i> 删除所选
					</button> -->

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
			<!--  -->

		</div>
		<!---------------------------------------------------------------------------------------------------->
		<!---------------------------------------------------------------------------------------------------->
	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!-------修改教师用户信息模态框------->
	<div class="modal fade" id="modal_Section" data-keyboard="true"
		tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- 模态弹出窗内容 -->
				<!--弹出框头部，一般使用“modal-header”表示，主要包括标题和关闭按钮-->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">修改教研室</h4>
				</div>
				<!--弹出框主体，一般使用“modal-body”表示，弹出框的主要内容-->
				<div class="modal-body">
					
				</div>
				<!--弹出框脚部，一般使用“modal-footer”表示，主要放置操作按钮-->
				<div class="modal-footer">
					<button class="btn btn-default" id=""
						onclick="Update_Section()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-check"></i> 确认修改
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
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
</body>
<script>
</script>
<script>
	/**
	 * 取到所有老师的列表
	 */
	Get_Section_Teacher();
</script>
</html>