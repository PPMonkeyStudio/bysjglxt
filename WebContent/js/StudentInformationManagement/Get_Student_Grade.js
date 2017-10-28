function Get_Student_Grade(select) {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var GradeList = JSON.parse(xhr.responseText);

				for (var num = 0; num < GradeList.length; num++) {
					var option = document.createElement("option");
					option.appendChild(document.createTextNode(GradeList[num]));
					select.appendChild(option);
					option.value = GradeList[num];
				}
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