/*
 * 学生导出自己的毕业设计
 */
function exportMyGraduation() {
	window.location = '/bysjglxt/graduationProject/GraduationProjectManagement_exportAll?StringUse='
			+ userStudentDTO.bysjglxtStudentUser.user_student_id;
}

/*
 * 导出选中学生的毕业设计
 */
function exportStudentsGraduation() {
	var jc = $
			.confirm({
				icon : 'fa fa-suitcase',
				columnClass : 'col-md-12',
				title : '批量导出《毕业设计过程管理手册》',
				content : '<table id="table_exportStudentsGraduation" class="table table-hover table-bordered" style="text-align: center; margin: 20px 0;">'
						+ '<tbody>'
						+ '<tr>'
						+ '<th>姓名</th>'
						+ '<th>'
						+ '<select class="form-control" id="select_major" data-live-search="true" style="width: auto;margin:0 auto;" onchange="(1)">'
						+ '<option value="-1">专业名称</option>'
						+ '</select>'
						+ '</th>'
						+ '<th>'
						+ '<select class="form-control" id="select_level" data-live-search="true" style="width: auto;margin:0 auto;" onchange="(1)">'
						+ '<option value="-1">级别</option>'
						+ '</select>'
						+ '</th>'
						+ '<th><label class="fancy-checkbox"><input id="checkbox_all_select" type="checkbox"onclick="all_select()"><span>全选</span></label></th>'
						+ '</tr>' + '</tbody>' + '</table>',
				type : 'blue',
				buttons : {
					返回 : function() {
					}
				},
				onContentReady : function() {
					var select_major = document.getElementById("select_major");
					var select_level = document.getElementById("select_level");
					Get_Student_Major(select_major);
					Get_Student_Level(select_level);
				}
			});
}