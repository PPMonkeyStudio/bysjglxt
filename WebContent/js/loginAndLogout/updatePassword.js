function updatePassword() {

	var jc = $
			.confirm({
				title : '修改密码',
				content : '<table  class="table table-hover" style="text-align: center;">'
						+ '<tbody>'
						// + '<tr>旧密码：'
						// + '<input type="password" id="oldPassword"
						// class="form-control" required />'
						// + '</tr>'
						+ '<tr>旧密码：'
						+ '<input type="password" id="oldPassword" class="form-control" required />'
						+ '</tr>'
						+ '<tr>新密码：'
						+ '<input type="password" id="newPassword" class="form-control" required />'
						+ '</tr>'
						+ '<tr>重复密码：'
						+ '<input type="password" id="newPassword2" class="form-control" required />'
						+ '</tr>' + '</tbody>' + '</table>',
				buttons : {
					'提交修改 ' : {
						btnClass : 'btn-red',
						action : function() {
							// var oldPassword = document
							// .getElementById("oldPassword");
							var oldPassword = document
									.getElementById("oldPassword");
							var newPassword = document
									.getElementById("newPassword");
							var newPassword2 = document
									.getElementById("newPassword2");

							/*
							 * 2、判断密码是否复合要求（长度20之内，不能带空格）
							 */
							if (newPassword.value.length <= 20) {

							} else {
								toastr.error("新密码长度不得大于二十");
								return false;
							}
							/*
							 * 3、判断两次输入密码是否一致
							 */
							if (newPassword.value == newPassword2.value) {

							} else {
								toastr.error("新密码输入不一致");
								return false;
							}
							/*
							 * 通过ajax传给后台旧密码和新密码，得到旧密码是否正确，若正确则表示修改成功
							 */
							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										if (xhr.responseText == "1") {
											toastr.success("修改成功");
											jc.close();
										} else if (xhr.responseText == "-1") {
											toastr.error("旧密码输入错误");
										} else {
											toastr.error("修改失败");
										}
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							var formData = new FormData();
							formData.append("newPassword", newPassword.value);
							formData.append("oldPassword", oldPassword.value);
							xhr.open("POST",
											"/bysjglxt/loginLogout/LoginLogoutManagement_updatePassword");
							xhr.send(formData);
							return false;
						}
					},
					'返回 ' : function() {
					}
				},
				onContentReady : function() {

				}
			});

}