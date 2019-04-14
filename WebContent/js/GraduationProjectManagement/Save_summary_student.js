function Save_summary_student() {
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				toastr.success("任务“学生完成个人学习工作总结”已完成更新")
				toastr.error("温馨提示：如确定对该任务更新已完成，请在“流程”--“我的任务”中，单击按钮“确定完成此任务”");
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_updateStudentSummary");
	var summary_id = document.getElementById("summary_id");
	var summary_summary = document.getElementById("summary_summary");
	/*
	 * 
	 */
	formData.append("updateSummary.summary_id", summary_id.value);
	formData.append("updateSummary.summary_summary", summary_summary.value);
	/*
	 * 
	 */
	xhr.send(formData);
}