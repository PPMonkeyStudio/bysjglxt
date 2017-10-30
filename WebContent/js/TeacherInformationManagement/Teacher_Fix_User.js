function Teacher_Fix_User(this_button) {

	$("#modal_Teacher_Fix_User").modal("show");

	// 将undefined换成空格
	for (var num = 0; num < teacher_json.list_TeacherInformationDTO.length; num++) {

	}

	var new_tr_1 = null;
	var table_teacher_user = document.getElementById("table_teacher_user");

	table_teacher_user.firstElementChild.innerHTML = "";

	for (var num = 0; num < teacher_json.list_TeacherInformationDTO.length; num++) {

		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_id == this_button.id) {
			/*
			 * 
			 */

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_user.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<input id="info_teacher_basic_id" style="text-align: center;display:none;" class="form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teacher_basic_id
					+ '"></input>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_user.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>最多可指导的学生数</th><td><input id="info_user_teacher_max_guidance" style="text-align: center;" class="form-control"  value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_max_guidance
					+ '"></input></td>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_user.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>所属教研室</th><td><select id="info_section" style="text-align: center;" class="form-control"  ></select></td>';
			$('#info_section').selectpicker('refresh');
		}

	}

	/*
	 * 
	 */

}