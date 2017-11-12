function Initialization_RecordProgress_2_Teacher() {
	document.getElementById("GraduationProjectTitle").innerHTML = '指导老师填写进展情况意见（撰写阶段）';
	var RecordProgress_2_Teacher = document
			.getElementById("banner_RecordProgress_2_Teacher");
	banner_RecordProgress_2_Teacher.click();
}

function RecordProgress_2_Teacher() {

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
				var recordprocess_2 = JSON.parse(xhr.responseText);
				var tab = document.getElementById("tab7");
				tab.innerHTML = '';
				/*
				 * 
				 */
				var textarea_0 = document.createElement("textarea");
				textarea_0.id = 'record_progress_id';
				textarea_0.style = "display:none;"
				textarea_0.innerHTML = recordprocess_2.record_progress_id;
				tab.appendChild(textarea_0);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '进展情况意见（撰写阶段）：';
				tab.appendChild(h4);
				var textarea_1 = document.createElement("textarea");
				textarea_1.id = "record_progress_opinion_2"
				textarea_1.className = 'form-control';
				textarea_1.style = "margin:10px 0 50px 0;resize: none;height:200px;"
				if (recordprocess_2.record_progress_opinion != null) {
					textarea_1.innerHTML = recordprocess_2.record_progress_opinion;
				} else {
					textarea_1.innerHTML = '';
				}
				tab.appendChild(textarea_1);
				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				var button_SaveGraduationProject = document
						.getElementById("button_SaveGraduationProject");
				if ("指导老师填写进展情况意见（撰写阶段）" != current_processDefinitionName) {
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
					"/bysjglxt/graduationProject/GraduationProjectManagement_get_RecordProgress_2");

	xhr.send(formData);

}