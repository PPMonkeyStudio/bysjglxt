function login() {

	var button_login = document.getElementById("button_login");
	button_login.disabled = "disabled";
	button_login.innerHTML = '<i class="fa fa-refresh fa-spin"></i> 正在登录...';

	var login_username = document.getElementById("login_username");
	var login_password = document.getElementById("login_password");

	var formData = new FormData();

	formData.append("username", login_username.value);
	formData.append("password", login_password.value);

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				switch (xhr.responseText) {
				case "账号不存在": {
					toastr.error("账号不存在");
					button_login.disabled = "";
					button_login.innerHTML = '登录';
					break;
				}
				case "密码不正确": {
					toastr.error("密码不正确");
					button_login.disabled = "";
					button_login.innerHTML = '登录';
					break;
				}
				case "无权限": {
					toastr.error("无权限");
					button_login.disabled = "";
					button_login.innerHTML = '登录';
					break;
				}
				case "教师登录成功": {
					window.location = "/bysjglxt/loginLogout/LoginLogoutManagement_index";
					break;
				}
				case "学生登录成功": {
					window.location = "/bysjglxt/loginLogout/LoginLogoutManagement_index";
					break;
				}
				case "管理员登录成功": {
					window.location = "/bysjglxt/college/CollegeManagement_CollegeManagementPage";
					break;
				}
				}
			} else {
				button_login.disabled = "";
				button_login.innerHTML = '登录';
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST", "/bysjglxt/loginLogout/LoginLogoutManagement_login");
	xhr.send(formData);

}