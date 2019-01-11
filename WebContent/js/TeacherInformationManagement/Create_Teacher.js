function Create_Teacher() {

	var input_job_number = document.getElementById("input_job_number");
	var input_name = document.getElementById("input_name");
	var input_sex = document.getElementById("input_sex");
	var input_birthday = document.getElementById("input_birthday");
	var input_induction_date = document.getElementById("input_induction_date");
	var input_job_statue = document.getElementById("input_job_statue");
	/*var input_unit_number = document.getElementById("input_unit_number");
	var input_unit_name = document.getElementById("input_unit_name");*/
	var input_highest_education = document
			.getElementById("input_highest_education");
	var input_highest_degree = document.getElementById("input_highest_degree");
	var input_learn_edge_structure = document
			.getElementById("input_learn_edge_structure");
	var input_professional_title = document
			.getElementById("input_professional_title");
	var input_subject_category = document
			.getElementById("input_subject_category");
	var input_teaching_type = document.getElementById("input_teaching_type");
	var input_teaching_profession_name = document
			.getElementById("input_teaching_profession_name");
	var input_teaching_profession_no = document
			.getElementById("input_teaching_profession_no");
	var input_teaching_section = document.getElementById("belong_section");
	var input_profession_teaching_date = document
			.getElementById("input_profession_teaching_date");
	var input_experimental_technical_personnel = document
			.getElementById("input_experimental_technical_personnel");
	var input_double_teacher_type = document
			.getElementById("input_double_teacher_type");
	var input_engineering_background = document
			.getElementById("input_engineering_background");
	var input_industry_background = document
			.getElementById("input_industry_background");
	var input_graduate_tutor_type = document
			.getElementById("input_graduate_tutor_type");
	var input_number_of_doctor = document
			.getElementById("input_number_of_doctor");
	var input_number_of_master = document
			.getElementById("input_number_of_master");
	/*
	 * 
	 */
	var re = /^[0-9]*$/;
	if (!re.test(input_job_number.value || input_job_number.value.length == 0)) {
		toastr.error("工号必须纯数字");
		return;
	}
	/*
	 * 
	 */
	if (input_name.value.length == 0) {
		toastr.error("姓名不能为空");
		return;
	}

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				if (xhr.responseText == "success") {
					window.location = "/bysjglxt/teacher/TeacherInformationManagement_TeacherManagementPage";
				}

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();

	xhr.open("POST",
			"/bysjglxt/teacher/TeacherInformationManagement_CreateTeacher");
	formData.append("newTeacher.job_number", input_job_number.value);
	formData.append("newTeacher.name", input_name.value);
	formData.append("newTeacher.sex", input_sex.value);
	formData.append("newTeacher.birthday", input_birthday.value);
	formData.append("newTeacher.induction_date", input_induction_date.value);
	formData.append("newTeacher.job_statue", input_job_statue.value);
	formData.append("section.section_id",input_teaching_section.value);
	/*formData.append("newTeacher.unit_number", input_unit_number.value);
	formData.append("newTeacher.unit_name", input_unit_name.value);*/
	formData.append("newTeacher.highest_education",
			input_highest_education.value);
	formData.append("newTeacher.highest_degree", input_highest_degree.value);
	formData.append("newTeacher.learn_edge_structure",
			input_learn_edge_structure.value);
	formData.append("newTeacher.professional_title",
			input_professional_title.value);
	formData
			.append("newTeacher.subject_category", input_subject_category.value);
	formData.append("newTeacher.teaching_type", input_teaching_type.value);
	formData.append("newTeacher.teaching_profession_name",
			input_teaching_profession_name.value);
	formData.append("newTeacher.teaching_profession_no",
			input_teaching_profession_no.value);
	formData.append("newTeacher.profession_teaching_date",
			input_profession_teaching_date.value);
	formData.append("newTeacher.experimental_technical_personnel",
			input_experimental_technical_personnel.value);
	formData.append("newTeacher.double_teacher_type",
			input_double_teacher_type.value);
	formData.append("newTeacher.engineering_background",
			input_engineering_background.value);
	formData.append("newTeacher.industry_background",
			input_industry_background.value);
	formData.append("newTeacher.graduate_tutor_type",
			input_graduate_tutor_type.value);
	formData
			.append("newTeacher.number_of_doctor", input_number_of_doctor.value);
	formData
			.append("newTeacher.number_of_master", input_number_of_master.value);
	xhr.send(formData);
}