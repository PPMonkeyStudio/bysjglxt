function Get_Student_Grade(select) {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var json = JSON.parse(xhr.responseText);
				alert(json);
				$('#' + select.id).selectpicker('refresh');
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST",
			"/bysjglxt/student/StudentInformationManagement_GetStudentGrade");

	xhr.send(null);
}