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
<!--页面公用-------------------------------------------------------------------------------------------------->
<!---------------------------------------------------------------------------------------------------->
<script type="text/javascript"
	src="<%=basePath%>js/ProcessManagement/List_ProcessDefinition.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/ProcessManagement/CreatProcessDefinition.js"></script>
	<script type="text/javascript"
	src="<%=basePath%>js/ProcessManagement/ProcessDefinitionDetail.js"></script>
<!---------------------------------------------------------------------------------------------------->

<title>流程</title>
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
		<div id="maxDiv_ProcessDefinitionDetail" class="panel"
			style="width: 960px; margin: 20px auto;display: none;">
			<!--  -->
			<div class="panel-heading">
				<h3 class="panel-title"><span id="ProcessDefinitionTitle">123</span></h3>
			</div>
			<div class="panel-body"></div>
		</div>
		<div id="maxDiv_List_ProcessDefinition" class="panel"
			style="width: 95%; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">
				<h3 class="panel-title">流程定义</h3>
			</div>
			<div class="panel-body">
				<div style="height: 34px;">
					<div style="width: 500px; float: left;">
						<button class="leader_control btn btn-default"
							onclick="CreatProcessDefinition()">
							<i class="fa fa-plus-square"></i>
							创建流程
						</button>
					</div>
				</div>
				<table id="table_processDefinition" class="table table-hover "
					style="text-align: center; margin: 20px 0;">
					<tbody>
						<tr>
							<th>流程定义名称</th>
							<th>
								<select class="form-control" style="width: auto;"
									id="select_process_definition_instance_role"
									onchange="List_processDefinition_By_PageAndSearch(1)">
									<option value="-1">实例化角色</option>
									<option value="1">指导老师</option>
									<option value="2">领导小组主任</option>
									<option value="3">教研室主任</option>
									<option value="4">学生</option>
								</select>
							</th>
							<th>操作</th>
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
					<button class="leader_control btn btn-danger"
						onclick="deleteProcessDefinitionList()"
						style="float: right; margin: 0 10px;">
						<i class="fa fa-trash-o"></i>
						删除所选
					</button>
				</div>

			</div>
		</div>
		<!--  -->
		<!--  -->
		<!--  -->

	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!-------修改教师用户信息模态框------->

	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
</body>
<script>
	$('select').selectpicker('refresh');
</script>
<script>
	
</script>

</html>