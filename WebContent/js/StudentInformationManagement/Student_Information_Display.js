function Student_Information_Display(this_button) {

	$("#modal_Student_Information").modal("show");

	for ( var num in student_json) {

		if (student_json[num][0].student_basic_num == this_button.id) {
			/*
			 * 
			 */
			var new_tr_1 = document.createElement("tr");
			var table_student_detail = document
					.getElementById("table_student_detail");
			new_tr_1.appendChild(document.createTextNode(''));
			table_student_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>学号</th><td>'
					+ student_json[num][0].student_basic_num
					+ '</td><th>姓名</th><td>'
					+ student_json[num][0].student_basic_name + '</td>';
			/*
			 * 
			 */

		}

	}

	/*
	 * 
	 */

}