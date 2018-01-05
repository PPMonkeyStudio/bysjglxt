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
						+ '<select class="form-control" id="select_major" data-live-search="true" style="width: auto;margin:0 auto;" onchange="list_exportStudentsGraduation()">'
						+ '<option value="-1">专业名称</option>'
						+ '</select>'
						+ '</th>'
						+ '<th>'
						+ '<select class="form-control" id="select_level" data-live-search="true" style="width: auto;margin:0 auto;" onchange="list_exportStudentsGraduation()">'
						+ '<option value="-1">级别</option>'
						+ '</select>'
						+ '</th>'
						+ '<th><label class="fancy-checkbox"><input id="checkbox_exportStudentsGraduation_select" type="checkbox" onclick="class_select(\'checkbox_exportStudentsGraduation_select\', \'exportStudentsGraduation_select\')"><span>全选</span></label></th>'
						+ '</tr>' + '</tbody>' + '</table>',
				type : 'blue',
				buttons : {
					'返回' : function() {
					},
					'导出所选学生的《毕业设计过程管理手册》' : {
						btnClass : 'btn-blue',
						action : function() {
							var checkbox_select = document
									.getElementsByClassName("exportStudentsGraduation_select");
							var ListDeleteStudentNum = null;
							var formData = new FormData();

							var StringUse = '';
							var i = 0;
							for (var num = 0; num < checkbox_select.length; num++) {
								if (checkbox_select[num].checked) {
									if (i == 0) {
										StringUse = checkbox_select[num].id;
									} else {
										StringUse = StringUse + ','
												+ checkbox_select[num].id;
									}
									i++;
								}
							}
							window.location = '/bysjglxt/graduationProject/GraduationProjectManagement_exportAll?StringUse='
									+ StringUse;

							return false;
						}
					}
				},
				onContentReady : function() {
					var select_major = document.getElementById("select_major");
					var select_level = document.getElementById("select_level");
					Get_Student_Major(select_major);
					Get_Student_Level(select_level);
					list_exportStudentsGraduation();
				}
			});
}

function list_exportStudentsGraduation() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {

				var exportStudentsGraduation_json = JSON
						.parse(xhr.responseText);
				/*
				 * 
				 */
				var new_tr_list = document
						.getElementsByClassName("new_tr_exportStudentsGraduation");
				var long = new_tr_list.length;
				/*
				 * 
				 */
				var table_student = document
						.getElementById("table_exportStudentsGraduation");
				var new_tr = null;
				var new_td = null;
				for (var num = 0; num < long; num++) {
					new_tr_list[0].parentNode.removeChild(new_tr_list[0]);
				}
				/*
				 * 
				 */
				for (var num = 0; num < exportStudentsGraduation_json.listStudentInformationDTO.length; num++) {

					new_tr = document.createElement("tr");
					table_student.firstElementChild.appendChild(new_tr);
					new_tr.className = "new_tr_exportStudentsGraduation";

					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = exportStudentsGraduation_json.listStudentInformationDTO[num].bysjglxtStudentBasic.student_basic_name;
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = exportStudentsGraduation_json.listStudentInformationDTO[num].bysjglxtStudentBasic.student_basic_major;
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = exportStudentsGraduation_json.listStudentInformationDTO[num].bysjglxtStudentBasic.student_basic_level;
					/*
					 * 
					 */

					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<label class="fancy-checkbox">'
							+ '<input id="'
							+ exportStudentsGraduation_json.listStudentInformationDTO[num].bysjglxtStudentUser.user_student_id
							+ '" type="checkbox" class="exportStudentsGraduation_select">'
							+ '<span></span></label>';
					/*
					 * 
					 */
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	var formData = new FormData();

	formData.append("exportGeaduationStudentDTO.major", document
			.getElementById("select_major").value);
	formData.append("exportGeaduationStudentDTO.level", document
			.getElementById("select_level").value);

	xhr
			.open("POST",
					"/bysjglxt/student/StudentInformationManagement_listStudentGreauation");
	xhr.send(formData);
}