var userTeacherDTO = null;
var userStudentDTO = null;

function getUserSessionForAjax() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var userJsonDTO = JSON.parse(xhr.responseText);
				if (userJsonDTO.bysjglxtStudentUser != null) {
					toastr.success("学生");
					userStudentDTO = userJsonDTO;
				} else if (userJsonDTO.bysjglxtTeacherUser != null) {
					toastr.success("教师");
					userTeacherDTO = userJsonDTO;
				} else {
					toastr.error("登录状态失效");
				}
				List_Topic_By_PageAndSearch();
				roleControl();
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();

	xhr
			.open("POST",
					"/bysjglxt/loginLogout/LoginLogoutManagement_getUserSessionForAjax");

	xhr.send(formData);
}