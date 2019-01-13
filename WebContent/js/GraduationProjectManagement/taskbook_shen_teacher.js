function Initialization_shen_Taskbook_Student() {

	var banner_shen_Taskbook_Teacher = document
			.getElementById("banner_shen_Taskbook_Teacher");
	banner_shen_Taskbook_Teacher.click();
}

function shen_taskbook() {
	document.getElementById("GraduationProjectTitle").innerHTML = '指导老师审核任务书';
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var taskbook = JSON.parse(xhr.responseText);
				var tab = document.getElementById("tab3");
				tab.innerHTML = '';
				/*
				 * 
				 */
				var textarea_0 = document.createElement("textarea");
				textarea_0.id = 'taskbook_id';
				textarea_0.style = "display:none;"
				textarea_0.innerHTML = taskbook.taskbook_id;
				tab.appendChild(textarea_0);
				/**
				 * 
				 */
				// 创建input
				/*
				 * 
				 */
				var textarea_1 = document.createElement("textarea");
				textarea_1.id = 'student_id_taskbook';
				textarea_1.style = "display:none;"
				textarea_1.innerHTML = taskbook.bysjglxt_taskbook_student;
				tab.appendChild(textarea_1);
				/*
				 * 
				 */
				var textarea_2 = document.createElement("textarea");
				textarea_2.id = 'shen_taskbook_is_xiazai';
				textarea_2.style = "display:none;"
				textarea_2.innerHTML = taskbook.taskbook_wan_file_xiazai;
				tab.appendChild(textarea_2);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '学生任务书';
				tab.appendChild(h4);
				/*
				 * 
				 */
				var div = document.createElement("div");
				div.id = "div_shen_taskbook_box";
				tab.appendChild(div);
				if (taskbook.taskbook_wan_file != null) {
					/*var div_2 = document.createElement("div");
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addStudentTaskBook()">上传</button>';
					tab.appendChild(div_2);*/
					if (taskbook.taskbook_wan_file != '') {
						taskbookShenFileChange(taskbook.taskbook_wan_file);
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
				textarea_3.id = 'shen2_id_taskbook';
				textarea_3.style = "display:none;"
				textarea_3.innerHTML = taskbook.bysjglxt_taskbook_student;
				tab.appendChild(textarea_3);
				/*
				 * 
				 */
				var textarea_4 = document.createElement("textarea");
				textarea_4.id = 'shen2_is_xiazai';
				textarea_4.style = "display:none;"
				textarea_4.innerHTML = taskbook.taskbook_shen_file_xiazai;
				tab.appendChild(textarea_4);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '修改后的任务书';
				tab.appendChild(h4);
				/*
				 * 新增的
				 */
				var div = document.createElement("div");
				div.id = "div_shen_taskbook_box2";
				tab.appendChild(div);
				if (taskbook.taskbook_shen_file != null) {
					var div_2 = document.createElement("div");
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addShenTaskBook()">上传</button>';
					tab.appendChild(div_2);
					if (taskbook.taskbook_shen_file != '') {
						taskbookShenFileChange2(taskbook.taskbook_shen_file);
					}

				} else {
					var div_2 = document.createElement("div");
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addShenTaskBook()">上传</button>';
					tab.appendChild(div_2);
				}
				
				var ulSave = document.createElement("ul");
				ulSave.className='pager wizard';
				var liSave = document.createElement("li");
				liSave.id='button_SaveGraduationProject';
				var aSave = document.createElement('a');
				aSave.href='####';
				aSave.onclick = Save_shen_taskbook
				aSave.text='提交更新';
				liSave.appendChild(aSave);
				ulSave.appendChild(liSave);
				tab.appendChild(ulSave);
				
				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				var button_SaveGraduationProject = document
						.getElementById("button_SaveGraduationProject");
				var div_wan_taskbook_del = document
						.getElementById("div_shen_taskbook_del2");
				if ('-1' == k) {
					if ("指导老师审核任务书" != current_processDefinitionName) {
						div_2.parentNode.removeChild(div_2);
						button_SaveGraduationProject.style.display = "none";
						div_shen_taskbook_del2.style.display = "none";
					} else if (userStudentDTO != null) {
						if (current_processInstanceUserID == userStudentDTO.bysjglxtStudentUser.user_student_id) {
							button_SaveGraduationProject.style.display = "block";
							div_shen_taskbook_del2.style.display = "block";
						} else {
							div_2.parentNode.removeChild(div_2);
							button_SaveGraduationProject.style.display = "none";
							div_shen_taskbook_del2.style.display = "none";
						}
					} else if (userTeacherDTO != null) {
						if (current_processInstanceUserID == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
							button_SaveGraduationProject.style.display = "block";
							div_shen_taskbook_del2.style.display = "block";
						} else {
							div_2.parentNode.removeChild(div_2);
							button_SaveGraduationProject.style.display = "none";
							div_shen_taskbook_del2.style.display = "none";
						}
					} else {
						div_2.parentNode.removeChild(div_2);
						button_SaveGraduationProject.style.display = "none";
						div_shen_taskbook_del2.style.display = "none";
					}
				} else if ('1' == k) {
					if (userStudentDTO != null) {
						if (_userId_Task == userStudentDTO.bysjglxtStudentUser.user_student_id) {
							button_SaveGraduationProject.style.display = "block";
							div_shen_taskbook_del2.style.display = "block";
						} else {
							div_2.parentNode.removeChild(div_2);
							button_SaveGraduationProject.style.display = "none";
							div_shen_taskbook_del2.style.display = "none";
						}
					} else if (userTeacherDTO != null) {
						if (_userId_Task == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
							button_SaveGraduationProject.style.display = "block";
							div_shen_taskbook_del2.style.display = "block";
						} else {
							div_2.parentNode.removeChild(div_2);
							button_SaveGraduationProject.style.display = "none";
							div_shen_taskbook_del2.style.display = "none";
						}
					} else {
						div_2.parentNode.removeChild(div_2);
						button_SaveGraduationProject.style.display = "none";
						div_shen_taskbook_del2.style.display = "none";
					}
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	console.log("--------------------------------");
	xhr.open("POST","/bysjglxt/graduationProject/GraduationProjectManagement_get_Taskbook");
	xhr.send(formData);
}