var EXCEL_Student_List = null;

function Preview_Student_EXCEL(file) {

	document.getElementById("i_pulse_2").style.display = "block";

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {

				EXCEL_Student_List = JSON.parse(xhr.responseText);

				var table_excel_student = document
						.getElementById("table_excel_student");

				for (var num = 0; num < EXCEL_Student_List.length; num++) {

					var new_tr = document.createElement("tr");

					new_tr.appendChild(document.createTextNode(''));

					table_excel_student.firstElementChild.appendChild(new_tr)

					new_tr.innerHTML = '<td>'
							+ EXCEL_Student_List[num].bysjglxtStudentBasic.student_basic_num
							+ '</td><td>'
							+ EXCEL_Student_List[num].bysjglxtStudentBasic.student_basic_name
							+ '</td><td>'
							+ EXCEL_Student_List[num].bysjglxtStudentBasic.student_basic_sex
							+ '</td><td>'
							+ EXCEL_Student_List[num].bysjglxtStudentBasic.student_basic_major
							+ '</td><td>'
							+ EXCEL_Student_List[num].bysjglxtStudentBasic.student_basic_year
							+ '</td>';
				}
				// 让加载图标消失
				document.getElementById("i_pulse_2").style.display = "none";

			} else {
				document.getElementById("i_pulse_2").style.display = "none";
				toastr.error(xhr.status);
			}
		}
	}

	xhr
			.open("POST",
					"/bysjglxt/student/StudentInformationManagement_PreviewStudentEXCEL");

	var formData = new FormData();
	formData.append("EXCEL_Student", file.files[0]);
	xhr.send(formData);
}