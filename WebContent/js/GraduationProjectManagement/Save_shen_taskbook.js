function Save_shen_taskbook() {
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
	xhr.open("POST","/bysjglxt/graduationProject/GraduationProjectManagement_updateShenTaskbook");
	var input_shen_taskbook = document.getElementById("input_shen_taskbook2");
	if (input_shen_taskbook != null) {
		if (input_shen_taskbook.files[0] != null) {
			formData.append("dissertation", input_shen_taskbook.files[0]);
		}
	}
	var studentUserId = document.getElementById("shen2_id_taskbook");
	formData.append("studentUserId",studentUserId.value);
	/*
	 * 
	 */
	var student_id = student_id_taskbook.value;
	var div_old_shen2_taskbook = document.getElementsByClassName("div_old_shen2_taskbook");

	if (div_old_shen2_taskbook.length > 0) {
		var oldDissertation = div_old_shen2_taskbook[0].innerHTML;
		formData.append("oldDissertation", oldDissertation);
	} else {
		formData.append("oldDissertation", "");
	}
	
	xhr.send(formData);
}