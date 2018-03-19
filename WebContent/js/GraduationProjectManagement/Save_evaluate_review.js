function Save_evaluate_review() {
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				toastr.success("保存成功");
				evaluate_review();
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_updateEvaluateReview");
	formData.append("updateEvaluateReview.evaluate_review_id", document
			.getElementById("evaluate_review_id").value);
	formData
			.append(
					"updateEvaluateReview.evaluate_review_grade_training_objective",
					document
							.getElementById("evaluate_review_grade_training_objective").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_difficulty",
			document.getElementById("evaluate_review_grade_difficulty").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_workload",
			document.getElementById("evaluate_review_grade_workload").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_bind", document
			.getElementById("evaluate_review_grade_bind").value);
	formData
			.append(
					"updateEvaluateReview.evaluate_review_grade_comprehensive",
					document
							.getElementById("evaluate_review_grade_comprehensive").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_reference",
			document.getElementById("evaluate_review_grade_reference").value);
	formData
			.append(
					"updateEvaluateReview.evaluate_review_grade_experimental_design",
					document
							.getElementById("evaluate_review_grade_experimental_design").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_computing",
			document.getElementById("evaluate_review_grade_computing").value);
	formData
			.append(
					"updateEvaluateReview.evaluate_review_grade_foreign_language",
					document
							.getElementById("evaluate_review_grade_foreign_language").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_computer",
			document.getElementById("evaluate_review_grade_computer").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_innovate",
			document.getElementById("evaluate_review_grade_innovate").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_analysis",
			document.getElementById("evaluate_review_grade_analysis").value);

	formData.append("updateEvaluateReview.evaluate_review_grade_chart",
			document.getElementById("evaluate_review_grade_chart").value);

	formData
			.append(
					"updateEvaluateReview.evaluate_review_grade_instructions",
					document
							.getElementById("evaluate_review_grade_instructions").value);

	formData
			.append(
					"updateEvaluateReview.evaluate_review_grade_practicability",
					document
							.getElementById("evaluate_review_grade_practicability").value);
	formData
			.append(
					"updateEvaluateReview.evaluate_review_grade_normalization",
					document
							.getElementById("evaluate_review_grade_normalization").value);
	formData.append("updateEvaluateReview.evaluate_review_grade_total",
			document.getElementById("evaluate_review_grade_total").value);
	formData.append("updateEvaluateReview.evaluate_review_teacher_comment",
			document.getElementById("evaluate_review_teacher_comment").value);
	/*
	 * 
	 */

	/*
	 * 
	 */
	xhr.send(formData);
}