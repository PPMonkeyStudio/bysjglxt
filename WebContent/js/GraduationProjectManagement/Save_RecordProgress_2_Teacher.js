function Save_RecordProgress_2_Teacher() {
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
					"/bysjglxt/graduationProject/GraduationProjectManagement_updateTeacherRecordProgressMetaphase");

	var record_progress_id = document.getElementById("record_zq_progress_id");

	var record_progress_opinion_2 = document
			.getElementById("record_progress_opinion_2");

	/*
	 * 
	 */
	formData.append("updateRecordProgressMetaphase.record_progress_id",
			record_progress_id.value);
	console.log("12:",record_progress_id.value)
	//
	formData.append("updateRecordProgressMetaphase.record_progress_opinion",
			record_progress_opinion_2.value);
	//
	/*
	 * 
	 */
	xhr.send(formData);
}