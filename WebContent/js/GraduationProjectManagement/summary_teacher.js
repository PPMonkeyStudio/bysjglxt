function Initialization_summary_teacher() {
	var banner_summary_teacher = document
			.getElementById("banner_summary_teacher");
	banner_summary_teacher.click();
}

function summary_teacher() {
	document.getElementById("GraduationProjectTitle").innerHTML = '指导老师填写个人学习工作总结意见';

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
				var summary = JSON.parse(xhr.responseText);
				var tab = document.getElementById("tab13");
				tab.innerHTML = '';
				/*
				 * 
				 */
				var textarea_0 = document.createElement("textarea");
				textarea_0.id = 'summary_id';
				textarea_0.style = "display:none;"
				textarea_0.innerHTML = summary.summary_id;
				tab.appendChild(textarea_0);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '个人学习工作总结意见：';
				tab.appendChild(h4);
				var textarea_1 = document.createElement("textarea");
				textarea_1.id = "summary_opinion";
				textarea_1.className = 'form-control';
				textarea_1.style = "margin:10px 0 50px 0;resize: none;height:200px;"
				if (summary.summary_opinion != null) {
					textarea_1.innerHTML = summary.summary_opinion;
				} else {
					console.debug("个人学习工作总结意见为空");
					textarea_1.innerHTML = '';
				}
				tab.appendChild(textarea_1);
				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				var button_SaveGraduationProject = document
						.getElementById("button_SaveGraduationProject");
				if ("指导老师填写个人学习工作总结意见" != current_processDefinitionName) {
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
					"/bysjglxt/graduationProject/GraduationProjectManagement_get_Summary");
	xhr.send(formData);
}