function Get_Student_Level(select) {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var LevelList = JSON.parse(xhr.responseText);

				for (var num = 0; num < LevelList.length; num++) {
					var option = document.createElement("option");
					option.appendChild(document.createTextNode(LevelList[num]));
					select.appendChild(option);
					option.value = LevelList[num];
				}
				// $('#' + select.id).selectpicker('refresh');
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	xhr.open("POST",
			"/bysjglxt/student/StudentInformationManagement_GetStudentLevel");

	xhr.send(null);
}