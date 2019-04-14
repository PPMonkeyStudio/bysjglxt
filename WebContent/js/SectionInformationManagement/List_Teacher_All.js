function List_Teacher_All(select) {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var TeacherList = JSON.parse(xhr.responseText);

				for (var num = 0; num < TeacherList.length; num++) {
					var option = document.createElement("option");
					option
							.appendChild(document
									.createTextNode(TeacherList[num].bysjglxtTeacherBasic.name));
					select.appendChild(option);
					option.value = TeacherList[num].bysjglxtTeacherUser.user_teacher_id;
				}

				$('#' + select.id).selectpicker('refresh');

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST",
			"/bysjglxt/teacher/TeacherInformationManagement_ListTeacherAll");

	xhr.send(null);
}