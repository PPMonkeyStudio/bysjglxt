function Initialization_Report_Opening_Teacher() {
	var banner_Report_Opening_Teacher = document
			.getElementById("banner_report_opening_teacher");
	banner_Report_Opening_Teacher.click();
}

function Report_Opening_Teacher() {
	document.getElementById("GraduationProjectTitle").innerHTML = '等待指导老师确认开题报告';
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var report_opening = JSON.parse(xhr.responseText);
				var tab = document.getElementById("tab6");
				tab.innerHTML = '';
				/*
				 * 
				 */
				var textarea_0 = document.createElement("textarea");
				textarea_0.id = 'report_opening_id';
				textarea_0.style = "display:none;"
				textarea_0.innerHTML = report_opening.report_opening_id;
				tab.appendChild(textarea_0);
				/**
				 * 
				 */
				// 创建input
				/*
				 * 
				 */
				var textarea_1 = document.createElement("textarea");
				textarea_1.id = 'student_id_report_opening';
				textarea_1.style = "display:none;"
				textarea_1.innerHTML = report_opening.report_opening_student;
				tab.appendChild(textarea_1);
				/*
				 * 
				 */
				var textarea_2 = document.createElement("textarea");
				textarea_2.id = 'report_file_is_xiazai';
				textarea_2.style = "display:none;"
				textarea_2.innerHTML = report_opening.report_file_is_xiazai;
					textarea_2.innerHTML = 1;
				tab.appendChild(textarea_2);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '学生开题报告';
				tab.appendChild(h4);
				/*
				 * 
				 */
				var div = document.createElement("div");
				div.id = "div_student_report_opening_box";
				tab.appendChild(div);
				if (report_opening.report_opening_file != null) {
					/*var div_2 = document.createElement("div");
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addStudentTaskBook()">上传</button>';
					tab.appendChild(div_2);*/
					if (report_opening.report_opening_file != '') {
						studentReportOpeningChange(report_opening.report_opening_file);
					}

				} else {
					/*var div_2 = document.createElement("div");
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addStudentTaskBook()">上传</button>';
					tab.appendChild(div_2);*/
				}

				// 创建input
				/*
				 * 
				 */
				var textarea_3 = document.createElement("textarea");
				textarea_3.id = 'teacher_report_opending';
				textarea_3.style = "display:none;"
				textarea_3.innerHTML = report_opening.report_opening_student;
				tab.appendChild(textarea_3);
				/*
				 * 
				 */
				var textarea_4 = document.createElement("textarea");
				textarea_4.id = 'teacher_report_opending_is_xiazai';
				textarea_4.style = "display:none;"
				textarea_4.innerHTML = report_opening.report_opening_teacher_file_is_xiazai;
				textarea_4.innerHTML = 1;
				tab.appendChild(textarea_4);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '指导老师修改开题报告';
				tab.appendChild(h4);
				/*
				 * 新增的
				 */
				var div = document.createElement("div");
				div.id = "div_teacher_report_opening_box";
				tab.appendChild(div);
				if (report_opening.report_opening_teacher_file != null) {
					var div_2 = document.createElement("div");
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addTeacherReportOpening()">上传</button>';
					tab.appendChild(div_2);
					if (report_opening.report_opening_teacher_file != '') {
						console.log("s:"+report_opening.report_opening_teacher_file);
						teacherReportOpeningChange(report_opening.report_opening_teacher_file);
					}

				} else {
					var div_2 = document.createElement("div");
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addTeacherReportOpening()">上传</button>';
					tab.appendChild(div_2);
				}
				
				var ulSave = document.createElement("ul");
				ulSave.className='pager wizard';
				var liSave = document.createElement("li");
				liSave.id='button_SaveGraduationProject';
				var aSave = document.createElement('a');
				aSave.href='####';
				aSave.onclick = Save_Teacher_Report_Opening
				aSave.text='提交更新';
				liSave.appendChild(aSave);
				ulSave.appendChild(liSave);
				tab.appendChild(ulSave);
				
				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				var button_SaveGraduationProject = document
						.getElementById("button_SaveGraduationProject");
				var div_teacher_report_opening_del = document
						.getElementById("div_teacher_report_opening_del");
				if ('-1' == k) {
					if ("指导老师确认开题报告" != current_processDefinitionName) {
						div_2.parentNode.removeChild(div_2);
						button_SaveGraduationProject.style.display = "none";
						div_teacher_report_opening_del.style.display = "none";
					} else if (userStudentDTO != null) {
						if (current_processInstanceUserID == userStudentDTO.bysjglxtStudentUser.user_student_id) {
							button_SaveGraduationProject.style.display = "block";
							div_teacher_report_opening_del.style.display = "block";
						} else {
							div_2.parentNode.removeChild(div_2);
							button_SaveGraduationProject.style.display = "none";
							div_teacher_report_opening_del.style.display = "none";
						}
					} else if (userTeacherDTO != null) {
						if (current_processInstanceUserID == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
							button_SaveGraduationProject.style.display = "block";
							div_teacher_report_opening_del.style.display = "block";
						} else {
							div_2.parentNode.removeChild(div_2);
							button_SaveGraduationProject.style.display = "none";
							div_teacher_report_opening_del.style.display = "none";
						}
					} else {
						div_2.parentNode.removeChild(div_2);
						button_SaveGraduationProject.style.display = "none";
						div_teacher_report_opening_del.style.display = "none";
					}
				} else if ('1' == k) {
					if (userStudentDTO != null) {
						if (_userId_Task == userStudentDTO.bysjglxtStudentUser.user_student_id) {
							button_SaveGraduationProject.style.display = "block";
							div_teacher_report_opening_del.style.display = "block";
						} else {
							div_2.parentNode.removeChild(div_2);
							button_SaveGraduationProject.style.display = "none";
							div_teacher_report_opening_del.style.display = "none";
						}
					} else if (userTeacherDTO != null) {
						if (_userId_Task == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
							button_SaveGraduationProject.style.display = "block";
							div_teacher_report_opening_del.style.display = "block";
						} else {
							div_2.parentNode.removeChild(div_2);
							button_SaveGraduationProject.style.display = "none";
							div_teacher_report_opening_del.style.display = "none";
						}
					} else {
						div_2.parentNode.removeChild(div_2);
						button_SaveGraduationProject.style.display = "none";
						div_teacher_report_opening_del.style.display = "none";
					}
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	console.log("--------------------------------");
	xhr.open("POST","/bysjglxt/graduationProject/GraduationProjectManagement_get_ReportOpening");
	xhr.send(formData);
}