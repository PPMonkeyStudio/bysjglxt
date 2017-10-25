window.onload = List_Student_All;

var student_json = null;

function List_Student_All() {

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {

				student_json = JSON.parse(xhr.responseText);

				var table_student = document.getElementById("table_student");

				for (var num = 0; num < student_json.list_StudentInformationDTO.length; num++) {

					var new_tr = document.createElement("tr");

					new_tr.appendChild(document.createTextNode(''));

					table_student.firstElementChild.appendChild(new_tr)

					new_tr.innerHTML = '<td>'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_num
							+ '</td><td>'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_name
							+ '</td><td>'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_sex
							+ '</td><td>'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_major
							+ '</td><td>'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_year
							+ '</td><td>✔</td><td style="padding: 0;"><button id="'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_num
							+ '" onclick="Student_Information_Display(this)" style="margin:3px 0 0 0;"class="btn btn-default btn-ms">详细信息</button></td><td><label class="fancy-checkbox"> <input type="checkbox"><span></span></label></td>';
				}
				// 让加载图标消失
				document.getElementById("i_pulse").style.display = "none";

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST",
			"/bysjglxt/student/StudentInformationManagement_ListStudentAll");
	xhr.send(null);

}
