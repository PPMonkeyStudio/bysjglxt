function login() {

	var login_num = document.getElementById("login_num");
	var login_password = document.getElementById("login_password");

	var formData = new FormData();

	formData.append("", login_num.value);

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

	xhr.open("POST", "/bysjglxt/loginLogout/LoginLogoutManagement_login");
	xhr.send(formData);

}