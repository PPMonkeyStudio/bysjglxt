function Start_Update_Section(thisSection) {

	$("#modal_Section").modal("show");

	var new_tr_1 = null;
	var table_section_update = document.getElementById("table_section_update");
	table_section_update.firstElementChild.innerHTML = "";
	for (var num = 0; num < section_json.teacherInformationDTO.length; num++) {
		if (section_json.teacherInformationDTO[num].bysjglxtSection.section_id == thisSection.id) {
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_section_update.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<input id="info_section_id" style="text-align: center;display:none;" class="form-control" disabled="disabled" value="'
					+ section_json.teacherInformationDTO[num].bysjglxtSection.section_id
					+ '"></input>'
					+ '<input id="info_section_gmt_create" style="text-align: center;display:none;" class="form-control" disabled="disabled" value="'
					+ section_json.teacherInformationDTO[num].bysjglxtSection.section_gmt_create
					+ '"></input>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_section_update.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>教研室名称</th><td><input id="info_section_name" style="text-align: center;" class="form-control"  value="'
					+ section_json.teacherInformationDTO[num].bysjglxtSection.section_name
					+ '"></input></td>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_section_update.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>教研室主任</th><td><select id="info_section_leader" style="text-align: center;" class="form-control"  value="'
					+ section_json.teacherInformationDTO[num].bysjglxtSection.section_leader
					+ '"><option class="option_info_section" value="">无</option></select></td>';

			for (var num2 = 0; num2 < TeacherList.length; num2++) {
				var option = document.createElement("option");
				option
						.appendChild(document
								.createTextNode(TeacherList[num2].bysjglxtTeacherBasic.name));
				document.getElementById("info_section_leader").appendChild(
						option);
				option.value = TeacherList[num2].bysjglxtTeacherUser.user_teacher_id;
				if (option.value == section_json.teacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_id) {
					option.selected = "selected";
				}
			}

		}
	}

	$('#info_section_leader').selectpicker('refresh');

}

function Update_Section() {

	$("#modal_Section").modal("hide");

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
					List_Section_By_Page(1);
					$("#modal_Section").modal("hide");
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();
	/*
	 * 不可以更改
	 */
	formData.append("updateSection.section_id", document
			.getElementById("info_section_id").value);
	formData.append("updateSection.section_gmt_create", document
			.getElementById("info_section_gmt_create").value);
	/*
	 * 可以更改
	 */
	formData.append("updateSection.section_name", document
			.getElementById("info_section_name").value);
	formData.append("updateSection.section_leader", document
			.getElementById("info_section_leader").value);

	xhr.open("POST",
			"/bysjglxt/section/SectionInformationManagement_UpdateSection");

	xhr.send(formData);
}
