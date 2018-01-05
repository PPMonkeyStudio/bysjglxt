var TitleList = null;

function Get_Teacher_Title(select) {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				TitleList = JSON.parse(xhr.responseText);
				for (var num = 0; num < TitleList.length; num++) {
					var option = document.createElement("option");
					option.appendChild(document.createTextNode(TitleList[num]));
					select.appendChild(option);
					option.value = TitleList[num];
				}

				// $('#' + select.id).selectpicker('refresh');
				return TitleList;
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST",
			"/bysjglxt/teacher/TeacherInformationManagement_GetTeacherTitle");

	xhr.send(null);
}