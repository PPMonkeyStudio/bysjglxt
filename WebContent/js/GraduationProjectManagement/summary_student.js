function summary_student() {
	document.getElementById("GraduationProjectTitle").innerHTML = '学生完成个人学习总结';
	var banner_summary_student = document
			.getElementById("banner_summary_student");
	banner_summary_student.click();
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

				var tab = document.getElementById("tab12");
				tab.innerHTML = '';

				var h4 = document.createElement("h4");
				h4.innerHTML = '学习工作总结：';
				tab.appendChild(h4);

				var textarea_1 = document.createElement("textarea");
				textarea_1.className = 'form-control';
				textarea_1.style = "margin:10px 0 50px 0;resize: vertical;"
				if (recordprocess_1.summary_summary != null) {
					textarea_1.innerHTML = recordprocess_1.summary_summary;
				} else {
					textarea_1.innerHTML = '';
				}

				tab.appendChild(textarea_1);
				var h4 = document.createElement("h4");
				h4.innerHTML = '指导老师意见：';
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
					textarea_1.disabled = "disabled";
					textarea_2.disabled = "disabled";
					var button_SaveGraduationProject = document
							.getElementById("button_SaveGraduationProject");
					button_SaveGraduationProject.style.display = "none";
				} else {
					button_SaveGraduationProject.style.display = "block";
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