function Save_examination_formal_teacher() {
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				toastr.success("任务“指导老师填写形式审查表”已完成更新")
				toastr.error("温馨提示：如确定对该任务更新已完成，请在“流程”--“我的任务”中，单击按钮“确定完成此任务”");
				examination_formal_teacher();
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	xhr
			.open(
					"POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_updateTeacherExaminationFormal");
	var examination_formal_id = document
			.getElementById("examination_formal_id");
	formData.append("updateExaminationFormal.examination_formal_id",
			examination_formal_id.value);

	var examination_formal_is_cover = document
			.getElementsByName("examination_formal_is_cover");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_cover[num].checked) {
			formData.append(
					"updateExaminationFormal.examination_formal_is_cover",
					examination_formal_is_cover[num].value);
		}
	}

	var examination_formal_is_a4 = document
			.getElementsByName("examination_formal_is_a4");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_a4[num].checked) {
			formData.append("updateExaminationFormal.examination_formal_is_a4",
					examination_formal_is_a4[num].value);
		}
	}
	var examination_formal_is_format = document
			.getElementsByName("examination_formal_is_format");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_format[num].checked) {
			formData.append(
					"updateExaminationFormal.examination_formal_is_format",
					examination_formal_is_format[num].value);
		}
	}
	var examination_formal_is_abstract_chinese = document
			.getElementsByName("examination_formal_is_abstract_chinese");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_abstract_chinese[num].checked) {
			formData
					.append(
							"updateExaminationFormal.examination_formal_is_abstract_chinese",
							examination_formal_is_abstract_chinese[num].value);
		}
	}
	var examination_formal_is_chinese_keyword = document
			.getElementsByName("examination_formal_is_chinese_keyword");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_chinese_keyword[num].checked) {
			formData
					.append(
							"updateExaminationFormal.examination_formal_is_chinese_keyword",
							examination_formal_is_chinese_keyword[num].value);
		}
	}
	var examination_formal_is_catalog = document
			.getElementsByName("examination_formal_is_catalog");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_catalog[num].checked) {
			formData.append(
					"updateExaminationFormal.examination_formal_is_catalog",
					examination_formal_is_catalog[num].value);
		}
	}
	var examination_formal_is_abstract_foreign = document
			.getElementsByName("examination_formal_is_abstract_foreign");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_abstract_foreign[num].checked) {
			formData
					.append(
							"updateExaminationFormal.examination_formal_is_abstract_foreign",
							examination_formal_is_abstract_foreign[num].value);
		}
	}
	var examination_formal_is_headline = document
			.getElementsByName("examination_formal_is_headline");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_headline[num].checked) {
			formData.append(
					"updateExaminationFormal.examination_formal_is_headline",
					examination_formal_is_headline[num].value);
		}
	}
	var examination_formal_is_punctuation = document
			.getElementsByName("examination_formal_is_punctuation");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_punctuation[num].checked) {
			formData
					.append(
							"updateExaminationFormal.examination_formal_is_punctuation",
							examination_formal_is_punctuation[num].value);
		}
	}
	var examination_formal_is_typo = document
			.getElementsByName("examination_formal_is_typo");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_typo[num].checked) {
			formData.append(
					"updateExaminationFormal.examination_formal_is_typo",
					examination_formal_is_typo[num].value);
		}
	}
	var examination_formal_is_reference_ten = document
			.getElementsByName("examination_formal_is_reference_ten");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_reference_ten[num].checked) {
			formData
					.append(
							"updateExaminationFormal.examination_formal_is_reference_ten",
							examination_formal_is_reference_ten[num].value);
		}
	}
	var examination_formal_is_reference_foreign = document
			.getElementsByName("examination_formal_is_reference_foreign");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_reference_foreign[num].checked) {
			formData
					.append(
							"updateExaminationFormal.examination_formal_is_reference_foreign",
							examination_formal_is_reference_foreign[num].value);
		}
	}
	var examination_formal_is_reference_new = document
			.getElementsByName("examination_formal_is_reference_new");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_reference_new[num].checked) {
			formData
					.append(
							"updateExaminationFormal.examination_formal_is_reference_new",
							examination_formal_is_reference_new[num].value);
		}
	}
	var examination_formal_is_reference_num = document
			.getElementsByName("examination_formal_is_reference_num");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_reference_num[num].checked) {
			formData
					.append(
							"updateExaminationFormal.examination_formal_is_reference_num",
							examination_formal_is_reference_num[num].value);
		}
	}
	var examination_formal_is_reference_format = document
			.getElementsByName("examination_formal_is_reference_format");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_reference_format[num].checked) {
			formData
					.append(
							"updateExaminationFormal.examination_formal_is_reference_format",
							examination_formal_is_reference_format[num].value);
		}
	}
	var examination_formal_is_progress_metaphase = document
			.getElementsByName("examination_formal_is_progress_metaphase");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_progress_metaphase[num].checked) {
			formData
					.append(
							"updateExaminationFormal.examination_formal_is_progress_metaphase",
							examination_formal_is_progress_metaphase[num].value);
		}
	}
	var examination_formal_is_progress_summary = document
			.getElementsByName("examination_formal_is_progress_summary");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_progress_summary[num].checked) {
			formData
					.append(
							"updateExaminationFormal.examination_formal_is_progress_summary",
							examination_formal_is_progress_summary[num].value);
		}
	}
	var examination_formal_is_progress_actual = document
			.getElementsByName("examination_formal_is_progress_actual");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_progress_actual[num].checked) {
			formData
					.append(
							"updateExaminationFormal.examination_formal_is_progress_actual",
							examination_formal_is_progress_actual[num].value);
		}
	}
	var examination_formal_is_progress_complete = document
			.getElementsByName("examination_formal_is_progress_complete");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_progress_complete[num].checked) {
			formData
					.append(
							"updateExaminationFormal.examination_formal_is_progress_complete",
							examination_formal_is_progress_complete[num].value);
		}
	}
	var examination_formal_is_progress_logic = document
			.getElementsByName("examination_formal_is_progress_logic");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_progress_logic[num].checked) {
			formData
					.append(
							"updateExaminationFormal.examination_formal_is_progress_logic",
							examination_formal_is_progress_logic[num].value);
		}
	}
	var examination_formal_is_chart = document
			.getElementsByName("examination_formal_is_chart");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_chart[num].checked) {
			formData.append(
					"updateExaminationFormal.examination_formal_is_chart",
					examination_formal_is_chart[num].value);
		}
	}
	var examination_formal_is_enclosure = document
			.getElementsByName("examination_formal_is_enclosure");
	for (var num = 0; num < 3; num++) {
		if (examination_formal_is_enclosure[num].checked) {
			formData.append(
					"updateExaminationFormal.examination_formal_is_enclosure",
					examination_formal_is_enclosure[num].value);
		}
	}
	/*
	 * 
	 */

	var input_lunwen_recordprogress = document.getElementById("input_lunwen_recordprogress");
	if (input_lunwen_recordprogress != null) {
		if (input_lunwen_recordprogress.files[0] != null) {
			formData.append("dissertation", input_lunwen_recordprogress.files[0]);
		}
	}
	var studentUserId = document.getElementById("student_id_lunwen_record_progress");
	formData.append("studentUserId",studentUserId.value);
	/*
	 * 
	 */
	var div_old_qianqi_recordprogress = document.getElementsByClassName("div_old_lunwen_recordprogress");

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