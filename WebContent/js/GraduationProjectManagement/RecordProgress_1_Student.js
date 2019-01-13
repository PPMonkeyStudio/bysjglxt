function Initialization_RecordProgress_1_Student() {

	var banner_RecordProgress_1_Student = document
			.getElementById("banner_RecordProgress_1_Student");
	banner_RecordProgress_1_Student.click();
}

function RecordProgress_1_Student() {
	document.getElementById("GraduationProjectTitle").innerHTML = '学生完成进展情况记录（前期准备阶段）';
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
				var tab = document.getElementById("tab7");
				tab.innerHTML = '';
				/*
				 * 
				 */
				var textarea_0 = document.createElement("textarea");
				textarea_0.id = 'record_progress_id';
				textarea_0.style = "display:none;"
				textarea_0.innerHTML = recordprocess_1.record_progress_id;
				tab.appendChild(textarea_0);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '进展情况记录（前期准备阶段）：';
				tab.appendChild(h4);
				var textarea_1 = document.createElement("textarea");
				textarea_1.id = "record_progress_record_1";
				textarea_1.className = 'form-control';
				textarea_1.style = "margin:10px 0 50px 0;resize: none;height:200px;"
				if (recordprocess_1.record_progress_record != null) {
					textarea_1.innerHTML = recordprocess_1.record_progress_record;
				} else {
					textarea_1.innerHTML = '';
				}
				tab.appendChild(textarea_1);
				/**
				 * 
				 */
				// 创建input
				/*
				 * 
				 */
				var textarea_3 = document.createElement("textarea");
				textarea_3.id = 'student_id_qian_record_progress';
				textarea_3.style = "display:none;"
					textarea_3.innerHTML = recordprocess_1.record_progress_student;
				tab.appendChild(textarea_3);
				/*
				 * 
				 */
				var textarea_4 = document.createElement("textarea");
				textarea_4.id = 'student_id_qian_record_progress_is_xiazai';
				textarea_4.style = "display:none;"
					textarea_4.innerHTML=1;
//				textarea_2.innerHTML = taskbook.taskbook_xia_file_xiazai;
				tab.appendChild(textarea_4);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '过程资料：';
				tab.appendChild(h4);
				/*
				 * 
				 */
				var div = document.createElement("div");
				div.id = "div_qianqi_recordprogress_box";
				tab.appendChild(div);
				if (recordprocess_1.record_progress_student_file != null) {
					var div_2 = document.createElement("div");
					div_2.id = "upload_button_id";
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addRecordProgressZiliao()">上传</button>';
					tab.appendChild(div_2);
					if (recordprocess_1.record_progress_student_file != '') {
						//TODO 在这里将数量置为空
						num_taskbook = 0;
						qianQiRecordProgressFileChange(recordprocess_1.record_progress_student_file);
					}

				} else {
					var div_2 = document.createElement("div");
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addRecordProgressZiliao()">上传</button>';
					tab.appendChild(div_2);
				}
				
				
				var ulSave = document.createElement("ul");
				ulSave.className='pager wizard';
				var liSave = document.createElement("li");
				liSave.id='button_SaveGraduationProject';
				var aSave = document.createElement('a');
				aSave.href='####';
				aSave.onclick = Save_RecordProgress_1_Student
				aSave.text='提交更新';
				liSave.appendChild(aSave);
				ulSave.appendChild(liSave);
				tab.appendChild(ulSave);
				
				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				var button_SaveGraduationProject = document
						.getElementById("button_SaveGraduationProject");
				var div_qianqi_recordprogress_del = document.getElementById("div_qianqi_recordprogress_del");
				var upload_button = document.getElementById("upload_button_id");
				if ('-1' == k) {
					if ("学生完成进展情况记录（前期准备阶段）" != current_processDefinitionName) {
						textarea_1.disabled = "disabled";
						button_SaveGraduationProject.style.display = "none";
						upload_button.style.display = "none";
						div_qianqi_recordprogress_del.style.display = "none";
					} else if (userStudentDTO != null) {
						if (current_processInstanceUserID == userStudentDTO.bysjglxtStudentUser.user_student_id) {
							button_SaveGraduationProject.style.display = "block";
							upload_button.style.display = "block";
							div_qianqi_recordprogress_del.style.display = "block";
						} else {
							textarea_1.disabled = "disabled";
							button_SaveGraduationProject.style.display = "none";
							upload_button.style.display = "none";
							div_qianqi_recordprogress_del.style.display = "none";
						}
					} else if (userTeacherDTO != null) {
						if (current_processInstanceUserID == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
							button_SaveGraduationProject.style.display = "block";
							upload_button.style.display = "block";
							div_qianqi_recordprogress_del.style.display = "block";
						} else {
							textarea_1.disabled = "disabled";
							button_SaveGraduationProject.style.display = "none";
							upload_button.style.display = "none";
							div_qianqi_recordprogress_del.style.display = "none";
						}
					} else {
						textarea_1.disabled = "disabled";
						button_SaveGraduationProject.style.display = "none";
						upload_button.style.display = "none";
						div_qianqi_recordprogress_del.style.display = "none";
					}
				}else if('1' == k) {
					if (userStudentDTO != null) {
						if (_userId_Task == userStudentDTO.bysjglxtStudentUser.user_student_id) {
							button_SaveGraduationProject.style.display = "block";
							upload_button.style.display = "block";
							div_qianqi_recordprogress_del.style.display = "block";
						} else {
							textarea_1.disabled = "disabled";
							button_SaveGraduationProject.style.display = "none";
							upload_button.style.display = "none";
							div_qianqi_recordprogress_del.style.display = "none";
						}
					} else if (userTeacherDTO != null) {
						if (_userId_Task == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
							button_SaveGraduationProject.style.display = "block";
							upload_button.style.display = "block";
							div_qianqi_recordprogress_del.style.display = "block";
						} else {
							textarea_1.disabled = "disabled";
							button_SaveGraduationProject.style.display = "none";
							upload_button.style.display = "none";
							div_qianqi_recordprogress_del.style.display = "none";
						}
					} else {
						textarea_1.disabled = "disabled";
						button_SaveGraduationProject.style.display = "none";
						upload_button.style.display = "none";
						div_qianqi_recordprogress_del.style.display = "none";
					}
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