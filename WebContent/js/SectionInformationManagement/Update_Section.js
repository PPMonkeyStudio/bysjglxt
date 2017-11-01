function Update_Section() {

	document.getElementById("button_sure_update").style.display = "none";
	document.getElementById("button_stop_update").style.display = "none";
	document.getElementById("button_start_update").style.display = "block";

	$("#modal_Section_Information").modal("hide");

	/*
	 * 
	 */
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				if (xhr.responseText == "success") {
					toastr.success("更新成功");
					List_Section_By_PageAndSearch(1);
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();

	xhr.open("POST",
			"/bysjglxt/student/SectionInformationManagement_UpdateSection");

	formData.append("updateSection.student_basic_id", document
			.getElementById("info_student_basic_id").value);

	xhr.send(formData);
}
