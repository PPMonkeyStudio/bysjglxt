function Teacher_Fix_User(this_button) {

	$("#modal_Teacher_Fix_User").modal("show");

	var new_tr_1 = null;
	var table_teacher_user = document.getElementById("table_teacher_user");

	table_teacher_user.firstElementChild.innerHTML = "";
	for (var num = 0; num < teacher_json.list_TeacherInformationDTO.length; num++) {

		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_id == this_button.id) {
			/*
			 * 不参与更改的的隐藏的
			 */
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_user.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<input id="info_user_teacher_id" style="text-align: center;display:none;" class="form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_id
					+ '"></input>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_user.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<input id="info_user_teacher_num" style="text-align: center;display:none;" class="form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_num
					+ '"></input>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_user.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<input id="info_user_teacher_password" style="text-align: center;display:none;" class="form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_password
					+ '"></input>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_user.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<input id="info_user_teacher_basic" style="text-align: center;display:none;" class="form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_basic
					+ '"></input>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_user.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<input id="info_user_teacher_gmt_create" style="text-align: center;display:none;" class="form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_gmt_create
					+ '"></input>';
			/*
			 * 更改的
			 */
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_user.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>最多可指导的学生数</th><td><input id="info_user_teacher_max_guidance" style="text-align: center;" class="form-control"  value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_max_guidance
					+ '"></input></td>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_user.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>所属教研室</th><td><select  style="text-align: center;" id="info_user_teacher_section" class="form-control"  >'
					+ '<option class="option_info_section" value="">无</option>'
					+ '</select></td>';
			for (var num2 = 0; num2 < SectionList.length; num2++) {
				var option = document.createElement("option");
				option.appendChild(document
						.createTextNode(SectionList[num2].section_name));
				document.getElementById("info_user_teacher_section")
						.appendChild(option);
				option.value = SectionList[num2].section_id;
				option.className = "option_info_section";

				if (option.value == teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_section) {
					option.selected = "selected";
				}
			}
			$('#info_user_teacher_section').selectpicker('refresh');
		}

	}

	/*
	 * 
	 */

}

function Commit_Fix_Teacher_User() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				if (xhr.responseText == "success") {
					toastr.success("更新成功");
					List_Teacher_By_PageAndSearch(1);
					$("#modal_Teacher_Fix_User").modal("hide");
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();
	formData.append("updateTeacherUser.user_teacher_id", document
			.getElementById("info_user_teacher_id").value);
	formData.append("updateTeacherUser.user_teacher_num", document
			.getElementById("info_user_teacher_num").value);
	formData.append("updateTeacherUser.user_teacher_password", document
			.getElementById("info_user_teacher_password").value);
	formData.append("updateTeacherUser.user_teacher_basic", document
			.getElementById("info_user_teacher_basic").value);
	formData.append("updateTeacherUser.user_teacher_gmt_create", document
			.getElementById("info_user_teacher_gmt_create").value);
	formData.append("updateTeacherUser.user_teacher_max_guidance", document
			.getElementById("info_user_teacher_max_guidance").value);
	formData.append("updateTeacherUser.user_teacher_section", document
			.getElementById("info_user_teacher_section").value);
	xhr.open("POST",
			"/bysjglxt/teacher/TeacherInformationManagement_UpdateTeacherUser");
	xhr.send(formData);
}
