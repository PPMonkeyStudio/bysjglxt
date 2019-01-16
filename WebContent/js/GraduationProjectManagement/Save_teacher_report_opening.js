function Save_Teacher_Report_Opening() {
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
	xhr.open("POST","/bysjglxt/graduationProject/GraduationProjectManagement_updateTeacherReportOpening");
	var input_report_opening = document.getElementById("input_report_opening");
	if (input_report_opening != null) {
		if (input_report_opening.files[0] != null) {
			formData.append("dissertation", input_report_opening.files[0]);
		}
	}
	var studentUserId = document.getElementById("student_id_report_opening");
	console.log(studentUserId.value);
	formData.append("studentUserId",studentUserId.value);
	/*
	 * 
	 */
	var div_old_report_opening = document
			.getElementsByClassName("div_old_report_opening");

	if (div_old_report_opening.length > 0) {
		var oldDissertation = div_old_report_opening[0].innerHTML;
		formData.append("oldDissertation", oldDissertation);
	} else {
		formData.append("oldDissertation", "");
	}
	xhr.send(formData);
}