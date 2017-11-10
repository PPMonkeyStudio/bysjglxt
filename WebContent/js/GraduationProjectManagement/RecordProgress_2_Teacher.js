function RecordProgress_2_Teacher() {
	document.getElementById("GraduationProjectTitle").innerHTML = '指导老师填写中期进展情况意见';
	var RecordProgress_2_Teacher = document
			.getElementById("banner_RecordProgress_2_Teacher");
	banner_RecordProgress_2_Teacher.click();
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

				var h4 = document.createElement("h4");
				h4.innerHTML = '进展情况记录：';
				tab.appendChild(h4);

				var textarea_1 = document.createElement("textarea");
				textarea_1.className = 'form-control';
				textarea_1.style = "margin:10px 0 50px 0;resize: vertical;"
				if (recordprocess_1.record_progress_record != null) {
					textarea_1.innerHTML = recordprocess_1.record_progress_record;
				} else {
					textarea_1.innerHTML = '';
				}

				tab.appendChild(textarea_1);
				var h4 = document.createElement("h4");
				h4.innerHTML = '指导老师填写中期进展情况意见：';
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
				if ("指导老师填写中期进展情况意见" != current_processDefinitionName) {
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
					"/bysjglxt/graduationProject/GraduationProjectManagement_get_RecordProgress_2");

	xhr.send(formData);

}