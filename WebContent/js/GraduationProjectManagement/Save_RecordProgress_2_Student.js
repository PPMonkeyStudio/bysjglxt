function Save_RecordProgress_2_Student() {
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
					"/bysjglxt/graduationProject/GraduationProjectManagement_updateStudentRecordProgressMetaphase");

	var record_progress_id = document.getElementById("record_progress_id");

	var record_progress_record_2 = document
			.getElementById("record_progress_record_2");

	/*
	 * 
	 */
	formData.append("updateRecordProgressMetaphase.record_progress_id",
			record_progress_id.value);
	//
	formData.append("updateRecordProgressMetaphase.record_progress_record",
			record_progress_record_2.value);
	//
	/*
	 * 
	 */
	xhr.send(formData);
}