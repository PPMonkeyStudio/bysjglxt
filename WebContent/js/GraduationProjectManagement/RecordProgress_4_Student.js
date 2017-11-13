function Initialization_RecordProgress_4_Student() {
	var banner_RecordProgress_4_Student = document
			.getElementById("banner_RecordProgress_4_Student");
	banner_RecordProgress_4_Student.click();
}

function RecordProgress_4_Student() {
	document.getElementById("GraduationProjectTitle").innerHTML = '学生完成进展情况记录（完善阶段）';

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
				var recordprocess_4 = JSON.parse(xhr.responseText);
				var tab = document.getElementById("tab10");
				tab.innerHTML = '';
				/*
				 * 
				 */
				var textarea_0 = document.createElement("textarea");
				textarea_0.id = 'record_progress_id';
				textarea_0.style = "display:none;"
				textarea_0.innerHTML = recordprocess_4.record_progress_id;
				tab.appendChild(textarea_0);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '进展情况记录（完善阶段）：';
				tab.appendChild(h4);
				var textarea_1 = document.createElement("textarea");
				textarea_1.id = "record_progress_record_4";
				textarea_1.className = 'form-control';
				textarea_1.style = "margin:10px 0 50px 0;resize: none;height:200px;"
				if (recordprocess_4.record_progress_record != null) {
					textarea_1.innerHTML = recordprocess_4.record_progress_record;
				} else {
					textarea_1.innerHTML = '';
				}
				tab.appendChild(textarea_1);
				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				var button_SaveGraduationProject = document
						.getElementById("button_SaveGraduationProject");
				if ("学生完成进展情况记录（完善阶段）" != current_processDefinitionName) {
					textarea_1.disabled = "disabled";
					button_SaveGraduationProject.style.display = "none";
				} else if (userStudentDTO != null) {
					if (current_processInstanceUserID == userStudentDTO.bysjglxtStudentUser.user_student_id) {
						button_SaveGraduationProject.style.display = "block";
					} else {
						textarea_1.disabled = "disabled";
						button_SaveGraduationProject.style.display = "none";
					}
				} else if (userTeacherDTO != null) {
					if (current_processInstanceUserID == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
						button_SaveGraduationProject.style.display = "block";
					} else {
						textarea_1.disabled = "disabled";
						button_SaveGraduationProject.style.display = "none";
					}
				} else {
					textarea_1.disabled = "disabled";
					button_SaveGraduationProject.style.display = "none";
				}
				/*
				 * 
				 */

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_get_RecordProgress_4");

	xhr.send(formData);

}