function RecordProgress_1_Teacher() {
	document.getElementById("GraduationProjectTitle").innerHTML = '指导老师填写前期进展情况意见';
	var RecordProgress_1_Teacher = document
			.getElementById("banner_RecordProgress_1_Teacher");
	banner_RecordProgress_1_Teacher.click();
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
				var recordprocess_1 = JSON.parse(xhr.responseText);

				var tab = document.getElementById("tab5");
				tab.innerHTML = '';

				var h4 = document.createElement("h4");
				h4.innerHTML = '指导老师填写前期进展情况意见：';
				tab.appendChild(h4);
				var textarea_2 = document.createElement("textarea");
				textarea_2.className = 'form-control';
				textarea_2.style = "margin:10px 0 50px 0;resize: vertical;"
				if (recordprocess_1.record_progress_opinion != null) {
					textarea_2.innerHTML = recordprocess_1.record_progress_opinion;
				} else {
					textarea_2.innerHTML = '';
				}
				tab.appendChild(textarea_2);

				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				if ("指导老师填写前期进展情况意见" != current_processDefinitionName) {
					textarea_2.disabled = "disabled";
					var button_SaveGraduationProject = document
							.getElementById("button_SaveGraduationProject");
					button_SaveGraduationProject.style.display = "none";
				} else if (current_processInstanceUserID == userStudentDTO.bysjglxtStudentUser.user_student_id
						|| current_processInstanceUserID == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
					button_SaveGraduationProject.style.display = "block";
				} else {
					textarea_2.disabled = "disabled";
					var button_SaveGraduationProject = document
							.getElementById("button_SaveGraduationProject");
					button_SaveGraduationProject.style.display = "none";
				}

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_get_RecordProgress_1");

	xhr.send(formData);

}