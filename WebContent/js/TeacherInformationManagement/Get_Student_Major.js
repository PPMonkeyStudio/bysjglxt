function Get_Student_Major(select) {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var MajorList = JSON.parse(xhr.responseText);

				for (var num = 0; num < MajorList.length; num++) {
					var option = document.createElement("option");
					option.appendChild(document.createTextNode(MajorList[num]));
					select.appendChild(option);
					option.value = MajorList[num];
				}

				$('#' + select.id).selectpicker('refresh');

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST",
			"/bysjglxt/student/StudentInformationManagement_GetStudentMajor");

	xhr.send(null);
}