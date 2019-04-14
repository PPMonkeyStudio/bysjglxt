function Save_evaluate_tutor() {
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				toastr.success("保存成功");
				evaluate_tutor();
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	formData.append("updateEvaluateTutor.evaluate_tutor_id", document
			.getElementById("evaluate_tutor_id").value);
	formData
			.append(
					"updateEvaluateTutor.evaluate_tutor_grade_training_objective",
					document
							.getElementById("evaluate_tutor_grade_training_objective").value);
	formData.append("updateEvaluateTutor.evaluate_tutor_grade_difficulty",
			document.getElementById("evaluate_tutor_grade_difficulty").value);
	formData.append("updateEvaluateTutor.evaluate_tutor_grade_workload",
			document.getElementById("evaluate_tutor_grade_workload").value);
	formData.append("updateEvaluateTutor.evaluate_tutor_grade_bind", document
			.getElementById("evaluate_tutor_grade_bind").value);
	formData
			.append(
					"updateEvaluateTutor.evaluate_tutor_grade_comprehensive",
					document
							.getElementById("evaluate_tutor_grade_comprehensive").value);
	formData.append("updateEvaluateTutor.evaluate_tutor_grade_reference",
			document.getElementById("evaluate_tutor_grade_reference").value);
	formData
			.append(
					"updateEvaluateTutor.evaluate_tutor_grade_experimental_design",
					document
							.getElementById("evaluate_tutor_grade_experimental_design").value);
	formData.append("updateEvaluateTutor.evaluate_tutor_grade_computing",
			document.getElementById("evaluate_tutor_grade_computing").value);
	formData
			.append(
					"updateEvaluateTutor.evaluate_tutor_grade_foreign_language",
					document
							.getElementById("evaluate_tutor_grade_foreign_language").value);
	formData.append("updateEvaluateTutor.evaluate_tutor_grade_computer",
			document.getElementById("evaluate_tutor_grade_computer").value);
	formData.append("updateEvaluateTutor.evaluate_tutor_grade_innovate",
			document.getElementById("evaluate_tutor_grade_innovate").value);
	formData.append("updateEvaluateTutor.evaluate_tutor_grade_analysis",
			document.getElementById("evaluate_tutor_grade_analysis").value);

	formData.append("updateEvaluateTutor.evaluate_tutor_grade_chart", document
			.getElementById("evaluate_tutor_grade_chart").value);

	formData.append("updateEvaluateTutor.evaluate_tutor_grade_instructions",
			document.getElementById("evaluate_tutor_grade_instructions").value);

	formData
			.append(
					"updateEvaluateTutor.evaluate_tutor_grade_practicability",
					document
							.getElementById("evaluate_tutor_grade_practicability").value);
	formData
			.append(
					"updateEvaluateTutor.evaluate_tutor_grade_normalization",
					document
							.getElementById("evaluate_tutor_grade_normalization").value);
	formData.append("updateEvaluateTutor.evaluate_tutor_grade_total", document
			.getElementById("evaluate_tutor_grade_total").value);
	formData.append("updateEvaluateTutor.evaluate_tutor_teacher_comment",
			document.getElementById("evaluate_tutor_teacher_comment").value);

	/*
	 * 
	 */
	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_updateEvaluateTutor");
	/*
	 * 
	 */
	xhr.send(formData);
}