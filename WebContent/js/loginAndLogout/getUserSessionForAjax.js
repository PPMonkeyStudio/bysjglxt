var userTeacherDTO = null;
var userStudentDTO = null;

function getUserSessionForAjax() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				/*
				 * 
				 */
				try {
					var userJsonDTO = JSON.parse(xhr.responseText);
				} catch (e) {
					toastr.error("登录状态失效");
					toastr.error(e);
					return;
				}
				/*
				 * 
				 */
				if (userJsonDTO.bysjglxtStudentUser != null) {
					userStudentDTO = userJsonDTO;
					//
					var USER_NAME = document.getElementById("USER_NAME");
					USER_NAME.innerHTML = userJsonDTO.bysjglxtStudentBasic.student_basic_name;
					//
				} else if (userJsonDTO.bysjglxtTeacherUser != null) {
					userTeacherDTO = userJsonDTO;
					//
					var USER_NAME = document.getElementById("USER_NAME");
					USER_NAME.innerHTML = userJsonDTO.bysjglxtTeacherBasic.name;
					//
				} else {
					toastr.error("登录状态失效");
				}
				/*
				 * 
				 */

				/*
				 * 
				 */
				try {
					List_Student_By_PageAndSearch(1);
				} catch (e) {
					try {
						List_Teacher_By_PageAndSearch(1);
					} catch (e) {
						try {
							List_Section_By_Page(1);
						} catch (e) {
							try {
								List_Topic_By_PageAndSearch(1);
							} catch (e) {
							}
						}
					}
				}
				/*
				 * 
				 */
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