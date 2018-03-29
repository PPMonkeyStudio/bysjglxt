function Save_Comment_EXCEL() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				if (xhr.responseText == '导入成功') {
					document.getElementById("i_pulse_2").style.display = "none";
					$("#modal_excel").modal("hide");
					toastr.success("导入成功");
					List_Comment_By_College(1);
				} else {
					toastr.error(xhr.responseText);
					document.getElementById("i_pulse_2").style.display = "none";
				}
			} else {
				document.getElementById("i_pulse_2").style.display = "none";
				toastr.error(xhr.status);
			}
		}
	}

	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_saveComment");

	var formData = new FormData();

	var input_upload_comment_excel = document
			.getElementById("input_upload_comment_excel");
	formData.append("EXCEL_Comment", input_upload_comment_excel.files[0]);
	xhr.send(formData);

	document.getElementById("i_pulse_2").style.display = "block";
}