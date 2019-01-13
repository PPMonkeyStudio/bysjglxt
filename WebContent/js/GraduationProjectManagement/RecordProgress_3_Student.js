function Initialization_RecordProgress_3_Student() {
	var banner_RecordProgress_3_Student = document
			.getElementById("banner_RecordProgress_3_Student");
	banner_RecordProgress_3_Student.click();
}

function RecordProgress_3_Student() {
	document.getElementById("GraduationProjectTitle").innerHTML = '学生完成进展情况记录（中期自查阶段）';

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
				var recordprocess_3 = JSON.parse(xhr.responseText);
				var tab = document.getElementById("tab11");
				tab.innerHTML = '';
				/*
				 * 
				 */
				var textarea_0 = document.createElement("textarea");
				textarea_0.id = 'record_progress_id';
				textarea_0.style = "display:none;"
				textarea_0.innerHTML = recordprocess_3.record_progress_id;
				tab.appendChild(textarea_0);
				/*
				 * 
				 */
				var h4 = document.createElement("h4");
				h4.innerHTML = '进展情况记录（中期自查阶段）：';
				tab.appendChild(h4);
				var textarea_1 = document.createElement("textarea");
				textarea_1.id = "record_progress_record_3";
				textarea_1.className = 'form-control';
				textarea_1.style = "margin:10px 0 50px 0;resize: none;height:200px;"
				if (recordprocess_3.record_progress_record != null) {
					textarea_1.innerHTML = recordprocess_3.record_progress_record;
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
				textarea_3.id = 'student_id_zhong_record_progress';
				textarea_3.style = "display:none;"
					textarea_3.innerHTML = recordprocess_3.record_progress_student;
				tab.appendChild(textarea_3);
				/*
				 * 
				 */
				var textarea_4 = document.createElement("textarea");
				textarea_4.id = 'student_id_zhong_record_progress_is_xiazai';
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
				div.id = "div_zhong_recordprogress_box";
				tab.appendChild(div);
				if (recordprocess_3.record_progress_student_file != null) {
					var div_2 = document.createElement("div");
					div_2.id = "upload_zhong_button_id";
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addzhongRecordProgressZiliao()">上传</button>';
					tab.appendChild(div_2);
					if (recordprocess_3.record_progress_student_file != '') {
						//TODO 在这里将数量置为空
						num_taskbook = 0;
						zhongRecordProgressFileChange(recordprocess_3.record_progress_student_file);
					}

				} else {
					var div_2 = document.createElement("div");
					div_2.id = "upload_zhong_button_id";
					div_2.innerHTML = '<button class="btn btn-default" '
							+ 'onclick="addzhongRecordProgressZiliao()">上传</button>';
					tab.appendChild(div_2);
				}
				/**
				 * 
				 */
				var ulSave = document.createElement("ul");
				ulSave.className='pager wizard';
				var liSave = document.createElement("li");
				liSave.id='button_SaveGraduationProject';
				var aSave = document.createElement('a');
				aSave.href='####';
				aSave.onclick = Save_RecordProgress_3_Student
				aSave.text='提交更新';
				liSave.appendChild(aSave);
				ulSave.appendChild(liSave);
				tab.appendChild(ulSave);
				
				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				var button_SaveGraduationProject = document
						.getElementById("button_SaveGraduationProject");
				var div_zhong_recordprogress_del = document.getElementById("div_zhong_recordprogress_del");
				var upload_button = document.getElementById("upload_zhong_button_id");
				if ('-1' == k) {
					if ("学生完成进展情况记录（中期自查阶段）" != current_processDefinitionName) {
						textarea_1.disabled = "disabled";
						button_SaveGraduationProject.style.display = "none";
						upload_button.style.display = "none";
						div_zhong_recordprogress_del.style.display = "none";
					} else if (userStudentDTO != null) {
						if (current_processInstanceUserID == userStudentDTO.bysjglxtStudentUser.user_student_id) {
							button_SaveGraduationProject.style.display = "block";
							upload_button.style.display = "block";
							div_zhong_recordprogress_del.style.display = "block";
						} else {
							textarea_1.disabled = "disabled";
							button_SaveGraduationProject.style.display = "none";
							div_zhong_recordprogress_del.style.display = "none";
							upload_button.style.display = "none";
						}
					} else if (userTeacherDTO != null) {
						if (current_processInstanceUserID == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
							button_SaveGraduationProject.style.display = "block";
							upload_button.style.display = "block";
							div_zhong_recordprogress_del.style.display = "block";
						} else {
							textarea_1.disabled = "disabled";
							button_SaveGraduationProject.style.display = "none";
							upload_button.style.display = "none";
							div_zhong_recordprogress_del.style.display = "none";
						}
					} else {
						textarea_1.disabled = "disabled";
						button_SaveGraduationProject.style.display = "none";
						upload_button.style.display = "none";
						div_zhong_recordprogress_del.style.display = "none";
					}
				}else if('1' == k) {
					if (userStudentDTO != null) {
						if (_userId_Task == userStudentDTO.bysjglxtStudentUser.user_student_id) {
							button_SaveGraduationProject.style.display = "block";
							upload_button.style.display = "block";
							div_zhong_recordprogress_del.style.display = "block";
						} else {
							textarea_1.disabled = "disabled";
							button_SaveGraduationProject.style.display = "none";
							upload_button.style.display = "none";
							div_zhong_recordprogress_del.style.display = "none";
						}
					} else if (userTeacherDTO != null) {
						if (_userId_Task == userTeacherDTO.bysjglxtTeacherUser.user_teacher_id) {
							console.log('1')
							button_SaveGraduationProject.style.display = "block";
							upload_button.style.display = "block";
							div_zhong_recordprogress_del.style.display = "block";
						} else {
							console.log('1111111-----2')
							textarea_1.disabled = "disabled";
							button_SaveGraduationProject.style.display = "none";
							upload_button.style.display = "none";
							div_zhong_recordprogress_del.style.display = "none";
						}
					} else {
						textarea_1.disabled = "disabled";
						button_SaveGraduationProject.style.display = "none";
						upload_button.style.display = "none";
						div_zhong_recordprogress_del.style.display = "none";
					
					}
				}
				
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_get_RecordProgress_3");

	xhr.send(formData);

}