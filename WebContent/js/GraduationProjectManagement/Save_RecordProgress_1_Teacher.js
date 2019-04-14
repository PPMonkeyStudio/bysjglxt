function Save_RecordProgress_1_Teacher() {
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				toastr.success("任务“指导老师填写进展情况意见（前期准备阶段）”已完成更新")
				toastr.error("温馨提示：如确定对该任务更新已完成，请在“流程”--“我的任务”中，单击按钮“确定完成此任务”");
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	xhr
			.open(
					"POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_updateTeacherRecordProcessEarlystage");

	var record_progress_id = document.getElementById("record_progress_id");

	var record_progress_opinion_1 = document
			.getElementById("record_progress_opinion_1");

	/*
	 * 
	 */
	formData.append("updateRecordProgressEarlystage.record_progress_id",
			record_progress_id.value);
	//
	formData.append("updateRecordProgressEarlystage.record_progress_opinion",
			record_progress_opinion_1.value);
	//
	/*
	 * 
	 */
	xhr.send(formData);
}