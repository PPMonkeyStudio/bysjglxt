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
	src="<%=basePath%>js/SectionInformationManagement/Create_Section.js"></script>

<script type="text/javascript"
	src="<%=basePath%>js/SectionInformationManagement/List_Teacher_All.js"></script>
<!---------------------------------------------------------------------------------------------------->
<title>新增教研室</title>
</head>
<body>
	<jsp:include page="/navbar.jsp" flush="true"></jsp:include>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<div style="margin: 80px 0 0 0; float: left; width: 100%;">
		<!--  -->
		<!---------------------------------------------------------------------------------------------------->
		<!---------------------------------------------------------------------------------------------------->
		<div class="panel" style="width: 90%; margin: 20px auto;">
			<div class="panel-heading">
				<h3 class="panel-title">新增教研室</h3>
			</div>
			<div class="panel-body">
				<table id="" class="table table-bordered"
					style="text-align: center; width: 700px; margin: 0 auto;">
					<tbody>
						<tr>
							<th>教研室名称</th>
							<td><input id="input_section_name" class="form-control"
								type="text"></td>
						</tr>
						<tr>
							<th>教研室主任</th>
							<td><select class="form-control" id="select_section_leader"
								data-live-search="true" style="width: auto;">
									<option value="">无</option>
							</select></td>
						</tr>
					</tbody>
				</table>
				<div style="height: 34px; margin: 30px 0;">
					<button class="btn btn-default" onclick="Create_Section()"
						style="float: right; margin: 0 10px;">
						<i class="fa  fa-check "></i> 确认创建
					</button>
					<button class="btn btn-default"
						onclick="window.location='<%=basePath%>section/SectionInformationManagement_SectionManagementPage'"
						style="float: right; margin: 0 10px;">
						<i class="fa  fa-close "></i> 返回
					</button>
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
	List_Teacher_All(document.getElementById("select_section_leader"));
</script>
</html>