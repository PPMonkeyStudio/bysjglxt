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
				content : '<table id="table_TopicDistributionStudent" class="table table-hover table-bordered" style="text-align: center; margin: 20px 0;">'
						+ '<tbody>'
						+ '<tr>'
						+ '<th>姓名</th>'
						+ '<th>专业</th>'
						+ '<th>级别</th>'
						+ '<th><label class="fancy-checkbox"><input id="checkbox_all_select" type="checkbox"onclick="all_select()"><span>全选</span></label></th>'
						+ '</tr>' + '</tbody>' + '</table>',
				type : 'blue',
				buttons : {
					返回 : function() {
					}
				},
				onContentReady : function() {
				}
			});
}