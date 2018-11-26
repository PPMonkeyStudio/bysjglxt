function Initialization_report_opening() {

	var banner_report_opening = document
			.getElementById("banner_report_opening");
	banner_report_opening.click();
}

function report_opening() {
	document.getElementById("GraduationProjectTitle").innerHTML = '学生上传开题报告';
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var report_opening = JSON.parse(xhr.responseText);
				var tab = document.getElementById("tab3");
				tab.innerHTML = '';
				/*
				 * 
				 */
				var textarea_0 = document.createElement("textarea");
				textarea_0.id = 'report_opening_id';
				textarea_0.style = "display:none;"
				textarea_0.innerHTML = report_opening.report_opening_id;
				tab.appendChild(textarea_0);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '提交开题报告：';
				tab.appendChild(h4);
				/*
				 * 
				 */
				var div = document.createElement("div");
				div.id = "div_report_opening_box";
				tab.appendChild(div);
				/*if (dissertation.dissertation_file != null) {
					var div_2 = document.createElement("div");
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addDissertation()">上传</button>';
					tab.appendChild(div_2);
					if (dissertation.dissertation_file != '') {
						dissertationChange(dissertation.dissertation_file);
					}

				} else {
					var div_2 = document.createElement("div");
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addDissertation()">上传</button>';
					tab.appendChild(div_2);
				}*/
				
				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				var button_SaveGraduationProject = document
						.getElementById("button_SaveGraduationProject");
				if ("学生上传开题报告" != current_processDefinitionName) {
					textarea_1.disabled = "disabled";
					textarea_2.disabled = "disabled";
					textarea_3.disabled = "disabled";
					textarea_4.disabled = "disabled";
					textarea_5.disabled = "disabled";
					button_SaveGraduationProject.style.display = "none";
				} else if (userStudentDTO != null) {
					if (current_processInstanceUserID == userStudentDTO.bysjglxtStudentUser.user_student_id) {
						button_SaveGraduationProject.style.display = "block";
					} else {
						textarea_1.disabled = "disabled";
						textarea_2.disabled = "disabled";
						textarea_3.disabled = "disabled";
						textarea_4.disabled = "disabled";
						textarea_5.disabled = "disabled";
						button_SaveGraduationProject.style.display = "none";
					}
				} else if (userTeacherDTO != null) {
					if (current_processInstanceUserID == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
						button_SaveGraduationProject.style.display = "block";
					} else {
						textarea_1.disabled = "disabled";
						textarea_2.disabled = "disabled";
						textarea_3.disabled = "disabled";
						textarea_4.disabled = "disabled";
						textarea_5.disabled = "disabled";
						button_SaveGraduationProject.style.display = "none";
					}
				} else {
					textarea_1.disabled = "disabled";
					textarea_2.disabled = "disabled";
					textarea_3.disabled = "disabled";
					textarea_4.disabled = "disabled";
					textarea_5.disabled = "disabled";
					button_SaveGraduationProject.style.display = "none";
				}

			} else {
				toastr.error(xhr.status);
			}
		}
	}
	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_get_ReportOpening");
	xhr.send(formData);
}