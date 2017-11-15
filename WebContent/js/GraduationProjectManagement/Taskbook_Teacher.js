function Initialization_Taskbook_Teacher() {
	var banner_Taskbook_Teacher = document
			.getElementById("banner_Taskbook_Teacher");
	Taskbook_Teacher();
}

function Taskbook_Teacher() {
  	document.getElementById("GraduationProjectTitle").innerHTML = '指导老师完成任务书';

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
				var taskbook = JSON.parse(xhr.responseText);
				var tab = document.getElementById("tab1");
				tab.innerHTML = '';
				/*
				 * 
				 */
				var textarea_0 = document.createElement("textarea");
				textarea_0.id = 'taskbook_id';
				textarea_0.style = "display:none;"
				textarea_0.innerHTML = taskbook.taskbook_id;
				tab.appendChild(textarea_0);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '研究主要内容及基本要求：';
				tab.appendChild(h4);
				var textarea_1 = document.createElement("textarea");
				textarea_1.id = 'taskbook_acontent_required';
				textarea_1.className = 'form-control';
				textarea_1.style = "margin:10px 0 50px 0;resize: none;height:200px;"
				if (taskbook != null
						&& taskbook.taskbook_acontent_required != null) {
					textarea_1.innerHTML = taskbook.taskbook_acontent_required;
				} else {
					textarea_1.innerHTML = '';
				}
				tab.appendChild(textarea_1);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '主要参考资料：';
				tab.appendChild(h4);
				var textarea_2 = document.createElement("textarea");
				textarea_2.id = 'taskbook_reference';
				textarea_2.className = 'form-control';
				textarea_2.style = "margin:10px 0 50px 0;resize: none;height:200px;"
				if (taskbook.taskbook_reference != null) {
					textarea_2.innerHTML = taskbook.taskbook_reference;
				} else {
					textarea_2.innerHTML = '';
				}
				tab.appendChild(textarea_2);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '进程计划：';
				tab.appendChild(h4);
				var textarea_3 = document.createElement("textarea");
				textarea_3.id = 'taskbook_plan';
				textarea_3.className = 'form-control';
				textarea_3.style = "margin:10px 0 50px 0;resize: none;height:200px;"
				if (taskbook.taskbook_plan != null) {
					textarea_3.innerHTML = taskbook.taskbook_plan;
				} else {
					textarea_3.innerHTML = '';
				}
				tab.appendChild(textarea_3);

				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				console.debug("bukebian:"+current_processDefinitionName);
				var button_SaveGraduationProject = document
						.getElementById("button_SaveGraduationProject");
				if ("指导老师完成任务书" != current_processDefinitionName) {
					textarea_1.disabled = "disabled";
					textarea_2.disabled = "disabled";
					textarea_3.disabled = "disabled";
					button_SaveGraduationProject.style.display = "none";
				} else if (userStudentDTO != null) {
					if (current_processInstanceUserID == userStudentDTO.bysjglxtStudentUser.user_student_id) {
						button_SaveGraduationProject.style.display = "block";
					} else {
						textarea_1.disabled = "disabled";
						textarea_2.disabled = "disabled";
						textarea_3.disabled = "disabled";
						button_SaveGraduationProject.style.display = "none";
					}
				} else if (userTeacherDTO != null) {
					if (current_processInstanceUserID == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
						button_SaveGraduationProject.style.display = "block";
					} else {
						textarea_1.disabled = "disabled";
						textarea_2.disabled = "disabled";
						textarea_3.disabled = "disabled";
						button_SaveGraduationProject.style.display = "none";
					}
				} else {
					textarea_1.disabled = "disabled";
					textarea_2.disabled = "disabled";
					textarea_3.disabled = "disabled";
					button_SaveGraduationProject.style.display = "none";
				}

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_get_Taskbook");

	xhr.send(formData);
}