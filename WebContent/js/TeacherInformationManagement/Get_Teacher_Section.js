function Get_Teacher_Section(select) {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var SectionList = JSON.parse(xhr.responseText);

				for (var num = 0; num < SectionList.length; num++) {
					var option = document.createElement("option");
					option.appendChild(document
							.createTextNode(SectionList[num].section_name));
					select.appendChild(option);
					option.value = SectionList[num].section_name;
				}

				$('#' + select.id).selectpicker('refresh');

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST",
			"/bysjglxt/teacher/TeacherInformationManagement_GetTeacherSection");

	xhr.send(null);
}