function Save_summary_teacher() {
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
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_updateTeacherSummary");
	var summary_id = document.getElementById("summary_id");
	var summary_opinion = document.getElementById("summary_opinion");
	/*
	 * 
	 */
	formData.append("updateSummary.summary_id", summary_id.value);
	formData.append("updateSummary.summary_opinion", summary_opinion.value);
	/*
	 * 
	 */
	xhr.send(formData);
}