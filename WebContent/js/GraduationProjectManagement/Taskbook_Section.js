function Initialization_Taskbook_Section() {
	var banner_Taskbook_Section = document
	.getElementById("banner_Taskbook_Section");
	banner_Taskbook_Section.click();
	
}
function Taskbook_Section() {
	document.getElementById("GraduationProjectTitle").innerHTML = '指导小组组长填写任务书审核意见';
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
				var tab = document.getElementById("tab4");
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
				h4.innerHTML = '指导小组组长审核意见：';
				tab.appendChild(h4);
				var textarea_1 = document.createElement("textarea");
				textarea_1.id = 'taskbook_leader_option';
				textarea_1.className = 'form-control';
				textarea_1.style = "margin:10px 0 50px 0;resize: none;height:200px;"
				if (taskbook.taskbook_leader_option != null) {
					textarea_1.innerHTML = taskbook.taskbook_leader_option;
				} else {
					textarea_1.innerHTML = '';
				}
				tab.appendChild(textarea_1);
				
				
				
				var ulSave = document.createElement("ul");
				ulSave.className='pager wizard';
				var liSave = document.createElement("li");
				liSave.id='button_SaveGraduationProject';
				var aSave = document.createElement('a');
				aSave.href='####';
				aSave.onclick = Save_Taskbook_Section
				aSave.text='提交更新';
				liSave.appendChild(aSave);
				ulSave.appendChild(liSave);
				tab.appendChild(ulSave);
				
				
				
				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				var button_SaveGraduationProject = document
						.getElementById("button_SaveGraduationProject");
				if ('-1' == k) {
					if ("指导小组组长填写任务书审核意见" != current_processDefinitionName) {
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
				}else if('1' == k) {
					if (userStudentDTO != null) {
						if (_userId_Task == userStudentDTO.bysjglxtStudentUser.user_student_id) {
							button_SaveGraduationProject.style.display = "block";
						} else {
							textarea_1.disabled = "disabled";
							button_SaveGraduationProject.style.display = "none";
						}
					} else if (userTeacherDTO != null) {
						if (_userId_Task == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
							button_SaveGraduationProject.style.display = "block";
						} else {
							textarea_1.disabled = "disabled";
							button_SaveGraduationProject.style.display = "none";
						}
					} else {
						textarea_1.disabled = "disabled";
						button_SaveGraduationProject.style.display = "none";
					}
				}
				
				
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	xhr.open("POST","/bysjglxt/graduationProject/GraduationProjectManagement_get_Taskbook");
	xhr.send(formData);
}