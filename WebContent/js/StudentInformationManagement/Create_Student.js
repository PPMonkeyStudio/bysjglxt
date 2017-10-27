function Create_Student() {

	var student_basic_num = document.getElementById("student_basic_num");
	var student_basic_name = document.getElementById("student_basic_name");
	var student_basic_year = document.getElementById("student_basic_year");
	var student_basic_grade = document.getElementById("student_basic_grade");
	var student_basic_idtype = document.getElementById("student_basic_idtype");
	var student_basic_idcaard = document
			.getElementById("student_basic_idcaard");
	var student_basic_age = document.getElementById("student_basic_age");
	var student_basic_sex = document.getElementById("student_basic_sex");
	var student_basic_nation = document.getElementById("student_basic_nation");
	var student_basic_politicalvisage = document
			.getElementById("student_basic_politicalvisage");
	var student_basic_native_place = document
			.getElementById("student_basic_native_place");
	var student_basic_studenttype = document
			.getElementById("student_basic_studenttype");
	var student_basic_enrollmenttype = document
			.getElementById("student_basic_enrollmenttype");
	var student_basic_teachingmethods = document
			.getElementById("student_basic_teachingmethods");
	var student_basic_professionalcode = document
			.getElementById("student_basic_professionalcode");
	var student_basic_major = document.getElementById("student_basic_major");
	var student_basic_independentmajorname = document
			.getElementById("student_basic_independentmajorname");
	var student_basic_is_normalmajor = document
			.getElementById("student_basic_is_normalmajor");
	var student_basic_is_disability = document
			.getElementById("student_basic_is_disability");
	var student_basic_householdregistrationtype = document
			.getElementById("student_basic_householdregistrationtype");
	var student_basic_transactiontypes = document
			.getElementById("student_basic_transactiontypes");
	var student_basic_entranceeducation = document
			.getElementById("student_basic_entranceeducation");
	var student_basic_enrollmentmode = document
			.getElementById("student_basic_enrollmentmode");
	var student_basic_reasonsfordroppingoutofschool = document
			.getElementById("student_basic_reasonsfordroppingoutofschool");
	var student_basic_phone = document.getElementById("student_basic_phone");
	var student_basic_college = document
			.getElementById("student_basic_college");
	/*
	 * 
	 */
	var re = /^[0-9]*$/;
	if (!re.test(student_basic_num.value)
			|| student_basic_num.value.length != 8) {
		toastr.error("学号必须为8位纯数字");
		return;
	}
	/*
	 * 
	 */
	var re = /^[0-9]*$/;
	if (student_basic_name.value.length == 0) {
		toastr.error("姓名不能为空");
		return;
	}

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();

	xhr.open("POST",
			"/bysjglxt/student/StudentInformationManagement_CreateStudent");

	formData.append("NewStudent.student_basic_num", student_basic_num.value);
	formData.append("NewStudent.student_basic_name", student_basic_name.value);
	formData.append("NewStudent.student_basic_year", student_basic_year.value);
	formData
			.append("NewStudent.student_basic_grade", student_basic_grade.value);
	formData.append("NewStudent.student_basic_idtype",
			student_basic_idtype.value);
	formData.append("NewStudent.student_basic_idcaard",
			student_basic_idcaard.value);
	formData.append("NewStudent.student_basic_age", student_basic_age.value);
	formData.append("NewStudent.student_basic_sex", student_basic_sex.value);
	formData.append("NewStudent.student_basic_nation",
			student_basic_nation.value);
	formData.append("NewStudent.student_basic_politicalvisage",
			student_basic_politicalvisage.value);
	formData.append("NewStudent.student_basic_native_place",
			student_basic_native_place.value);
	formData.append("NewStudent.student_basic_studenttype",
			student_basic_studenttype.value);
	formData.append("NewStudent.student_basic_enrollmenttype",
			student_basic_enrollmenttype.value);
	formData.append("NewStudent.student_basic_teachingmethods",
			student_basic_teachingmethods.value);
	formData.append("NewStudent.student_basic_professionalcode",
			student_basic_professionalcode.value);
	formData
			.append("NewStudent.student_basic_major", student_basic_major.value);
	formData.append("NewStudent.student_basic_independentmajorname",
			student_basic_independentmajorname.value);
	formData.append("NewStudent.student_basic_is_normalmajor",
			student_basic_is_normalmajor.value);
	formData.append("NewStudent.student_basic_is_disability",
			student_basic_is_disability.value);
	formData.append("NewStudent.student_basic_householdregistrationtype",
			student_basic_householdregistrationtype.value);
	formData.append("NewStudent.student_basic_transactiontypes",
			student_basic_transactiontypes.value);
	formData.append("NewStudent.student_basic_entranceeducation",
			student_basic_entranceeducation.value);
	formData.append("NewStudent.student_basic_enrollmentmode",
			student_basic_enrollmentmode.value);
	formData.append("NewStudent.student_basic_reasonsfordroppingoutofschool",
			student_basic_reasonsfordroppingoutofschool.value);
	formData.append("NewStudent.student_basic_college",
			student_basic_college.value);
	formData
			.append("NewStudent.student_basic_phone", student_basic_phone.value);

	xhr.send(formData);
}