function Save_wan_taskbook() {
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
	xhr.open("POST","/bysjglxt/graduationProject/GraduationProjectManagement_updateWanTaskbook");
	var input_wan_taskbook = document.getElementById("input_wan_taskbook");
	if (input_wan_taskbook != null) {
		if (input_wan_taskbook.files[0] != null) {
			formData.append("dissertation", input_wan_taskbook.files[0]);
		}
	}
	var studentUserId = document.getElementById("student_id_taskbook");
	formData.append("studentUserId",studentUserId.value);
	/*
	 * 
	 */
	var student_id = student_id_taskbook.value;
	var div_old_wan_taskbook = document
			.getElementsByClassName("div_old_wan_taskbook");

	if (div_old_wan_taskbook.length > 0) {
		var oldDissertation = div_old_wan_taskbook[0].innerHTML;
		formData.append("oldDissertation", oldDissertation);
	} else {
		formData.append("oldDissertation", "");
	}
	
	xhr.send(formData);
}