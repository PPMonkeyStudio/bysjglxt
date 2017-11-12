function report_opening() {
	document.getElementById("GraduationProjectTitle").innerHTML = '学生完成开题报告';
	var banner_report_opening = document
			.getElementById("banner_report_opening");
	banner_report_opening.click();
	/*
	 * 
	 */
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
				var h4 = document.createElement("h4");
				h4.innerHTML = '文献综述：';
				tab.appendChild(h4);
				var textarea_1 = document.createElement("textarea");
				textarea_1.className = 'form-control';
				textarea_1.style = "margin:10px 0 50px 0;resize: vertical;"
				if (report_opening.report_opening_documentary_survey != null) {
					textarea_1.innerHTML = report_opening.report_opening_documentary_survey;
				} else {
					textarea_1.innerHTML = '';
				}
				tab.appendChild(textarea_1);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '主要内容：';
				tab.appendChild(h4);
				var textarea_2 = document.createElement("textarea");
				textarea_2.className = 'form-control';
				textarea_2.style = "margin:10px 0 50px 0;resize: vertical;"
				if (report_opening.report_opening_main != null) {
					textarea_2.innerHTML = report_opening.report_opening_main;
				} else {
					textarea_2.innerHTML = '';
				}
				tab.appendChild(textarea_2);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '详细提纲：';
				tab.appendChild(h4);
				var textarea_3 = document.createElement("textarea");
				textarea_3.className = 'form-control';
				textarea_3.style = "margin:10px 0 50px 0;resize: vertical;"
				if (report_opening.report_opening_detail != null) {
					textarea_3.innerHTML = report_opening.report_opening_detail;
				} else {
					textarea_3.innerHTML = '';
				}
				tab.appendChild(textarea_3);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '参考文献：';
				tab.appendChild(h4);
				var textarea_4 = document.createElement("textarea");
				textarea_4.className = 'form-control';
				textarea_4.style = "margin:10px 0 50px 0;resize: vertical;"
				if (report_opening.report_opening_reference != null) {
					textarea_4.innerHTML = report_opening.report_opening_reference;
				} else {
					textarea_4.innerHTML = '';
				}
				tab.appendChild(textarea_4);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '进程计划：';
				tab.appendChild(h4);
				var textarea_5 = document.createElement("textarea");
				textarea_5.className = 'form-control';
				textarea_5.style = "margin:10px 0 50px 0;resize: vertical;"
				if (report_opening.report_opening_plan != null) {
					textarea_5.innerHTML = report_opening.report_opening_plan;
				} else {
					textarea_5.innerHTML = '';
				}
				tab.appendChild(textarea_5);
				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				var button_SaveGraduationProject = document
						.getElementById("button_SaveGraduationProject");
				if ("学生完成开题报告" != current_processDefinitionName) {
					textarea_1.disabled = "disabled";
					textarea_2.disabled = "disabled";
					textarea_3.disabled = "disabled";
					textarea_4.disabled = "disabled";
					textarea_5.disabled = "disabled";
					button_SaveGraduationProject.style.display = "none";
				} else if (userStudentDTO != null) {
					console.log("current_processInstanceUserID:"
							+ current_processInstanceUserID);
					console
							.log("sessionUser:"
									+ userStudentDTO.bysjglxtStudentUser.user_student_id);
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