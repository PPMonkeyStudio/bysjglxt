function Save_Taskbook_Section() {
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
	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_updateSectionTaskbook");
	var taskbook_id = document.getElementById("taskbook_id");
	var taskbook_opinion = document.getElementById("taskbook_opinion");
	/*
	 * 
	 */
	formData.append("updateTaskbook.taskbook_id", taskbook_id.innerHTML);
	formData.append("updateTaskbook.taskbook_opinion", taskbook_opinion.value);
	/*
	 * 
	 */
	xhr.send(formData);
}