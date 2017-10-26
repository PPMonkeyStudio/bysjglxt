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
							+ EXCEL_Student_List[num].student_basic_num
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_name
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basicd_year
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_grade
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_idtype
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_idcaard
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_age
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_sex
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_nation
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_politicalvisage
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_native_place
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_studenttype
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_enrollmenttype
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_teachingmethods
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_professionalcode
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_major
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_independentmajorname
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_is_normalmajor
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_is_disability
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_householdregistrationtype
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_transactiontypes
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_entranceeducation
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_enrollmentmode
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_reasonsfordroppingoutofschool
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_phone
							+ '</td><td>'
							+ EXCEL_Student_List[num].student_basic_college
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