/*
 * 开始修改
 */
function start_Update_Teacher() {

	document.getElementById("button_sure_update").style.display = "block";
	document.getElementById("button_stop_update").style.display = "block";
	document.getElementById("button_start_update").style.display = "none";

	/*
	 * 模态框收回触发
	 */
	$('#modal_Teacher_Information').on('hide.bs.modal', function(e) {
		document.getElementById("button_sure_update").style.display = "none";
		document.getElementById("button_stop_update").style.display = "none";
		document.getElementById("button_start_update").style.display = "block";
	})

	var input_TeacherInformation = document
			.getElementsByClassName("input_TeacherInformation");
	for (var num = 0; num < input_TeacherInformation.length; num++) {
		input_TeacherInformation[num].disabled = "";
	}

}
/*
 * 放弃修改
 */
function stop_Update_Teacher() {

	document.getElementById("button_sure_update").style.display = "none";
	document.getElementById("button_stop_update").style.display = "none";
	document.getElementById("button_start_update").style.display = "block";

	$("#modal_Teacher_Information").modal("hide");
}
/*
 * 提交修改
 */
function Update_Teacher() {

	document.getElementById("button_sure_update").style.display = "none";
	document.getElementById("button_stop_update").style.display = "none";
	document.getElementById("button_start_update").style.display = "block";

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
					$("#modal_Teacher_Information").modal("hide");
					toastr.success("更新成功");
					List_Teacher_By_PageAndSearch(1);
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();

	xhr.open("POST",
			"/bysjglxt/teacher/TeacherInformationManagement_UpdateTeacher");

	formData.append("updateTeacher.teacher_basic_id", document
			.getElementById("info_teacher_basic_id").value);

	formData.append("updateTeacher.job_number", document
			.getElementById("info_job_number").value);

	formData.append("updateTeacher.sex",
			document.getElementById("info_sex").value);
	formData.append("updateTeacher.birthday", document
			.getElementById("info_birthday").value);
	formData.append("updateTeacher.induction_date", document
			.getElementById("info_induction_date").value);
	formData.append("updateTeacher.job_statue", document
			.getElementById("info_job_statue").value);
	formData.append("updateTeacher.unit_number", document
			.getElementById("info_unit_number").value);
	formData.append("updateTeacher.unit_name", document
			.getElementById("info_unit_name").value);
	formData.append("updateTeacher.highest_education", document
			.getElementById("info_highest_education").value);
	formData.append("updateTeacher.highest_degree", document
			.getElementById("info_highest_degree").value);
	formData.append("updateTeacher.learn_edge_structure", document
			.getElementById("info_learn_edge_structure").value);
	formData.append("updateTeacher.professional_title", document
			.getElementById("info_professional_title").value);
	formData.append("updateTeacher.subject_category", document
			.getElementById("info_subject_category").value);
	formData.append("updateTeacher.teaching_type", document
			.getElementById("info_teaching_type").value);
	formData.append("updateTeacher.teaching_profession_name", document
			.getElementById("info_teaching_profession_name").value);
	formData.append("updateTeacher.teaching_profession_no", document
			.getElementById("info_teaching_profession_no").value);
	formData.append("updateTeacher.profession_teaching_date", document
			.getElementById("info_profession_teaching_date").value);
	formData.append("updateTeacher.experimental_technical_personnel", document
			.getElementById("info_experimental_technical_personnel").value);
	formData.append("updateTeacher.double_teacher_type", document
			.getElementById("info_double_teacher_type").value);
	formData.append("updateTeacher.engineering_background", document
			.getElementById("info_engineering_background").value);
	formData.append("updateTeacher.industry_background", document
			.getElementById("info_industry_background").value);
	formData.append("updateTeacher.graduate_tutor_type", document
			.getElementById("info_graduate_tutor_type").value);
	formData.append("updateTeacher.number_of_doctor", document
			.getElementById("info_number_of_doctor").value);
	formData.append("updateTeacher.number_of_master", document
			.getElementById("info_number_of_master").value);

	xhr.send(formData);
}
