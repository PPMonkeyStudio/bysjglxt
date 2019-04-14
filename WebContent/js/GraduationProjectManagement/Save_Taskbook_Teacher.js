function Save_Taskbook_Teacher() {
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				toastr.success("任务“指导老师填写个人学习工作总结意见”已完成更新")
				toastr.error("温馨提示：如确定对该任务更新已完成，请在“流程”--“我的任务”中，单击按钮“确定完成此任务”");
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_updateTeacherTaskbook");
	var taskbook_id = document.getElementById("taskbook_id");
	var taskbook_acontent_required = document
			.getElementById("taskbook_acontent_required");
	var taskbook_reference = document.getElementById("taskbook_reference");
	var taskbook_plan = document.getElementById("taskbook_plan");
	/*
	 * 
	 */
	formData.append("updateTaskbook.taskbook_id", taskbook_id.value);
	formData.append("updateTaskbook.taskbook_acontent_required",
			taskbook_acontent_required.value);
	formData.append("updateTaskbook.taskbook_reference",
			taskbook_reference.value);
	formData.append("updateTaskbook.taskbook_plan", taskbook_plan.value);
	/*
	 * 
	 */
	xhr.send(formData);

}