/*
 * 开始修改
 */
function start_Update_Student() {

	document.getElementById("button_sure_update").style.display = "block";
	document.getElementById("button_stop_update").style.display = "block";
	document.getElementById("button_start_update").style.display = "none";

	/*
	 * 模态框收回触发
	 */
	$('#modal_Student_Information').on('hide.bs.modal', function(e) {
		document.getElementById("button_sure_update").style.display = "none";
		document.getElementById("button_stop_update").style.display = "none";
		document.getElementById("button_start_update").style.display = "block";
	})

	var input_StudentInformation = document
			.getElementsByClassName("input_StudentInformation");
	for (var num = 0; num < input_StudentInformation.length; num++) {
		input_StudentInformation[num].disabled = "";
	}

}
/*
 * 放弃修改
 */
function stop_Update_Student() {

	document.getElementById("button_sure_update").style.display = "none";
	document.getElementById("button_stop_update").style.display = "none";
	document.getElementById("button_start_update").style.display = "block";

	$("#modal_Student_Information").modal("hide");
}
/*
 * 提交修改
 */
function Update_Student() {

	document.getElementById("button_sure_update").style.display = "none";
	document.getElementById("button_stop_update").style.display = "none";
	document.getElementById("button_start_update").style.display = "block";

	$("#modal_Student_Information").modal("hide");

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
					List_Student_By_PageAndSearch(1);
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();

	xhr.open("POST",
			"/bysjglxt/student/StudentInformationManagement_UpdateStudent");

	formData.append("updateStudent.student_basic_id", document
			.getElementById("info_student_basic_id").value);

	formData.append("updateStudent.student_basic_num", document
			.getElementById("info_student_basic_num").value);

	formData.append("updateStudent.student_basic_name", document
			.getElementById("info_student_basic_name").value);

	formData.append("updateStudent.student_basic_year", document
			.getElementById("info_student_basic_year").value);

	formData.append("updateStudent.student_basic_grade", document
			.getElementById("info_student_basic_grade").value);

	formData.append("updateStudent.student_basic_idtype", document
			.getElementById("info_student_basic_idtype").value);

	formData.append("updateStudent.student_basic_idcaard", document
			.getElementById("info_student_basic_idcaard").value);

	formData.append("updateStudent.student_basic_age", document
			.getElementById("info_student_basic_age").value);

	formData.append("updateStudent.student_basic_sex", document
			.getElementById("info_student_basic_sex").value);

	formData.append("updateStudent.student_basic_nation", document
			.getElementById("info_student_basic_nation").value);

	formData.append("updateStudent.student_basic_politicalvisage", document
			.getElementById("info_student_basic_politicalvisage").value);

	formData.append("updateStudent.student_basic_native_place", document
			.getElementById("info_student_basic_native_place").value);

	formData.append("updateStudent.student_basic_studenttype", document
			.getElementById("info_student_basic_studenttype").value);

	formData.append("updateStudent.student_basic_enrollmenttype", document
			.getElementById("info_student_basic_enrollmenttype").value);

	formData.append("updateStudent.student_basic_teachingmethods", document
			.getElementById("info_student_basic_teachingmethods").value);

	formData.append("updateStudent.student_basic_professionalcode", document
			.getElementById("info_student_basic_professionalcode").value);

	formData.append("updateStudent.student_basic_major", document
			.getElementById("info_student_basic_major").value);

	formData
			.append(
					"updateStudent.student_basic_independentmajorname",
					document
							.getElementById("info_student_basic_independentmajorname").value);

	formData.append("updateStudent.student_basic_is_normalmajor", document
			.getElementById("info_student_basic_is_normalmajor").value);

	formData.append("updateStudent.student_basic_is_disability", document
			.getElementById("info_student_basic_is_disability").value);

	formData
			.append(
					"updateStudent.student_basic_householdregistrationtype",
					document
							.getElementById("info_student_basic_householdregistrationtype").value);

	formData.append("updateStudent.student_basic_transactiontypes", document
			.getElementById("info_student_basic_transactiontypes").value);

	formData.append("updateStudent.student_basic_entranceeducation", document
			.getElementById("info_student_basic_entranceeducation").value);

	formData.append("updateStudent.student_basic_enrollmentmode", document
			.getElementById("info_student_basic_enrollmentmode").value);

	formData
			.append(
					"updateStudent.student_basic_reasonsfordroppingoutofschool",
					document
							.getElementById("info_student_basic_reasonsfordroppingoutofschool").value);

	formData.append("updateStudent.student_basic_phone", document
			.getElementById("info_student_basic_phone").value);

	formData.append("updateStudent.student_basic_college", document
			.getElementById("info_student_basic_college").value);

	xhr.send(formData);
}
