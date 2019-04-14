function Save_RecordProgress_2_Student() {
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				toastr.success("任务“学生完成进展情况记录（撰写阶段）”已完成更新")
				toastr.error("温馨提示：如确定对该任务更新已完成，请在“流程”--“我的任务”中，单击按钮“确定完成此任务”");
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
	/**
	 * 
	 */
	

	var input_zhuan_recordprogress = document.getElementById("input_zhuan_recordprogress");
	if (input_zhuan_recordprogress != null) {
		if (input_zhuan_recordprogress.files[0] != null) {
			formData.append("dissertation", input_zhuan_recordprogress.files[0]);
		}
	}
	var studentUserId = document.getElementById("student_id_zhuan_record_progress");
	formData.append("studentUserId",studentUserId.value);
	/*
	 * 
	 */
	var div_old_zhuan_recordprogress = document.getElementsByClassName("div_old_zhuan_recordprogress");

	if (div_old_zhuan_recordprogress.length > 0) {
		var oldDissertation = div_old_zhuan_recordprogress[0].innerHTML;
		formData.append("oldDissertation", oldDissertation);
	} else {
		formData.append("oldDissertation", "");
	}
	
	
	/*
	 * 
	 */
	xhr.send(formData);
}