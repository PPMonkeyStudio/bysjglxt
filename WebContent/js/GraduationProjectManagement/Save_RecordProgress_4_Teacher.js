function Save_RecordProgress_4_Teacher() {
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
					"/bysjglxt/graduationProject/GraduationProjectManagement_updateTeacherRecordProgressPerfect");

	var record_progress_id = document.getElementById("record_progress_id");

	var record_progress_opinion_4 = document
			.getElementById("record_progress_opinion_4");

	/*
	 * 
	 */
	formData.append("updateRecordProgressPerfect.record_progress_id",
			record_progress_id.value);
	//
	formData.append("updateRecordProgressPerfect.record_progress_opinion",
			record_progress_opinion_4.value);
	//
	/*
	 * 
	 */
	xhr.send(formData);
}