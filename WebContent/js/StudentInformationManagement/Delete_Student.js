function Delete_Student() {

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
					toastr.success("删除成功");
					List_Student_By_PageAndSearch(1);
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var checkbox_select = document.getElementsByClassName("checkbox_select");

	var ListDeleteStudentNum = null;

	var formData = new FormData();

	for (var num = 0; num < checkbox_select.length; num++) {
		if (checkbox_select[num].checked) {
			formData.append("ListDeleteStudentID", checkbox_select[num].id);
		}

	}

	xhr.open("POST",
			"/bysjglxt/student/StudentInformationManagement_DeleteStudent");

	xhr.send(formData);

}