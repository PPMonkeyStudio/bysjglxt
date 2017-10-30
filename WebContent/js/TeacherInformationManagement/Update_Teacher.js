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

	$("#modal_Teacher_Information").modal("hide");

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

	xhr.send(formData);
}
