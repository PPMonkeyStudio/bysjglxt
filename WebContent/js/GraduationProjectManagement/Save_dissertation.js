function Save_dissertation() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				toastr.success("任务“学生提交答辩论文”已完成更新")
				toastr.error("温馨提示：如确定对该任务更新已完成，请在“流程”--“我的任务”中，单击按钮“确定完成此任务”");
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	var formData = new FormData();
	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_updateDissertation");
	var input_dissertation = document.getElementById("input_dissertation");
	if (input_dissertation != null) {
		if (input_dissertation.files[0] != null) {
			formData.append("dissertation", input_dissertation.files[0]);
		}
	}
	/*
	 * 
	 */
	var div_old_dissertation = document
			.getElementsByClassName("div_old_dissertation");

	if (div_old_dissertation.length > 0) {
		var oldDissertation = div_old_dissertation[0].innerHTML;
		formData.append("oldDissertation", oldDissertation);
	} else {
		formData.append("oldDissertation", "");
	}
	/*
	 * 
	 */
	xhr.send(formData);
}
