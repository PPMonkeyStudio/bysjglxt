function Initialization_dissertation() {

	var banner_dissertation = document.getElementById("banner_dissertation");
	banner_dissertation.click();
}

function dissertation() {
	document.getElementById("GraduationProjectTitle").innerHTML = '学生提交答辩论文';
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
				console.debug("毕业论文：" + xhr.responseText);
				var dissertation = JSON.parse(xhr.responseText);
				var tab = document.getElementById("tab14");
				tab.innerHTML = '';
				/*
				 * 
				 */
				var textarea_0 = document.createElement("textarea");
				textarea_0.id = 'dissertation_id';
				textarea_0.style = "display:none;"
				textarea_0.innerHTML = dissertation.dissertation_id;
				tab.appendChild(textarea_0);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '提交答辩论文：';
				tab.appendChild(h4);
				/*
				 * 
				 */
				var div = document.createElement("div");
				div.id = "div_dissertation_box";
				tab.appendChild(div);
				if (dissertation.dissertation_file != null) {
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
				}
				/*
				 * 
				 */

				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				var button_SaveGraduationProject = document
						.getElementById("button_SaveGraduationProject");
				if ("学生提交答辩论文" != current_processDefinitionName) {
					div_2.parentNode.removeChild(div_2);
					button_SaveGraduationProject.style.display = "none";
				} else if (userStudentDTO != null) {
					if (current_processInstanceUserID == userStudentDTO.bysjglxtStudentUser.user_student_id) {
						button_SaveGraduationProject.style.display = "block";
					} else {
						div_2.parentNode.removeChild(div_2);
						button_SaveGraduationProject.style.display = "none";
					}
				} else if (userTeacherDTO != null) {
					if (current_processInstanceUserID == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
						button_SaveGraduationProject.style.display = "block";
					} else {
						div_2.parentNode.removeChild(div_2);
						button_SaveGraduationProject.style.display = "none";
					}
				} else {
					div_2.parentNode.removeChild(div_2);
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
					"/bysjglxt/graduationProject/GraduationProjectManagement_get_Dissertation");

	xhr.send(formData);

}