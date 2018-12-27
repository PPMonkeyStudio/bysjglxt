function Save_xia_taskbook() {
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
	xhr.open("POST","/bysjglxt/graduationProject/GraduationProjectManagement_updateXiaTaskbook");
	var input_xia_taskbook = document.getElementById("input_xia_taskbook");
	if (input_xia_taskbook != null) {
		if (input_xia_taskbook.files[0] != null) {
			formData.append("dissertation", input_xia_taskbook.files[0]);
		}
	}
	var studentUserId = document.getElementById("student_id_taskbook");
	formData.append("studentUserId",studentUserId.value);
	/*
	 * 
	 */
	var student_id = student_id_taskbook.value;
	var div_old_xia_taskbook = document
			.getElementsByClassName("div_old_xia_taskbook");

	if (div_old_xia_taskbook.length > 0) {
		var oldDissertation = div_old_xia_taskbook[0].innerHTML;
		formData.append("oldDissertation", oldDissertation);
	} else {
		formData.append("oldDissertation", "");
	}
	
	xhr.send(formData);
}