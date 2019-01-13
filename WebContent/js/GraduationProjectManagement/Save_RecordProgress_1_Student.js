function Save_RecordProgress_1_Student() {
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				toastr.success(xhr.responseText)
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	xhr.open("POST","/bysjglxt/graduationProject/GraduationProjectManagement_updateStudentRecordProgressEarlystage");
	var record_progress_id = document.getElementById("record_progress_id");
	var record_progress_record_1 = document.getElementById("record_progress_record_1");
	/*
	 * 
	 */
	formData.append("updateRecordProgressEarlystage.record_progress_id",record_progress_id.value);
	//
	formData.append("updateRecordProgressEarlystage.record_progress_record",record_progress_record_1.value);
	//
	/**
	 * 
	 */
	
	var input_qianqi_recordprogress = document.getElementById("input_qianqi_recordprogress");
	if (input_qianqi_recordprogress != null) {
		if (input_qianqi_recordprogress.files[0] != null) {
			formData.append("dissertation", input_qianqi_recordprogress.files[0]);
		}
	}
	var studentUserId = document.getElementById("student_id_qian_record_progress");
	formData.append("studentUserId",studentUserId.value);
	/*
	 * 
	 */
	var div_old_qianqi_recordprogress = document.getElementsByClassName("div_old_qianqi_recordprogress");

	if (div_old_qianqi_recordprogress.length > 0) {
		var oldDissertation = div_old_qianqi_recordprogress[0].innerHTML;
		formData.append("oldDissertation", oldDissertation);
	} else {
		formData.append("oldDissertation", "");
	}
	/*
	 * 
	 */
	xhr.send(formData);
}