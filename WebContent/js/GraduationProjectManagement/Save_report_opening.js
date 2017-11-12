function Save_report_opening() {
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
					"/bysjglxt/graduationProject/GraduationProjectManagement_updateReportOpening");

	var report_opening_id = document.getElementById("report_opening_id");

	var report_opening_documentary_survey = document
			.getElementById("report_opening_documentary_survey");

	var report_opening_main = document.getElementById("report_opening_main");

	var report_opening_detail = document
			.getElementById("report_opening_detail");

	var report_opening_reference = document
			.getElementById("report_opening_reference");

	var report_opening_plan = document.getElementById("report_opening_plan");
	/*
	 * 
	 */
	formData.append("updateReportOpening.report_opening_id",
			report_opening_id.value);
	//
	formData.append("updateReportOpening.report_opening_documentary_survey",
			report_opening_documentary_survey.value);
	//
	formData.append("updateReportOpening.report_opening_main",
			report_opening_main.value);
	//
	formData.append("updateReportOpening.report_opening_detail",
			report_opening_detail.value);
	//
	formData.append("updateReportOpening.report_opening_reference",
			report_opening_reference.value);
	//
	formData.append("updateReportOpening.report_opening_plan",
			report_opening_plan.value);
	/*
	 * 
	 */
	xhr.send(formData);
}