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
	xhr
			.open(
					"POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_updateStudentRecordProgressEarlystage");

	var record_progress_id = document.getElementById("record_progress_id");

	var record_progress_record_1 = document
			.getElementById("record_progress_record_1");

	/*
	 * 
	 */
	formData.append("updateRecordProgressEarlystage.record_progress_id",
			record_progress_id.value);
	//
	formData.append("updateRecordProgressEarlystage.record_progress_record",
			record_progress_record_1.value);
	//
	/*
	 * 
	 */
	xhr.send(formData);
}