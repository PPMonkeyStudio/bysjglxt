function updateAdmin(button) {
	var jc = $
			.confirm({
				title : '重新分配管理员',
				content : '<input id="input_college_id" value="'
						+ button.id
						+ '"  class="form-control"   style="display:none;"/>'
						+ '<form id="form_updateAdmin">'
						+ '<table  class="table table-bordered" style="text-align: center;">'
						+ '<tbody>'
						+ '<tr>'
						+ '<td>选择教师：</td><td>'
						+ '<select  name="bysjglxt_teacher_user.user_teacher_id" class="form-control" >'
						+ '</select>' + '</td>' + '</tr>' + '</tbody>'
						+ '</table>' + '</form>',
				type : 'blue',
				onContentReady : function() {
					var xhr = false;
					xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						var message;
						if (xhr.readyState == 4) {
							if (xhr.status == 200) {
								var teacherList = JSON.parse(xhr.responseText);

								for (var num = 0; num < teacherList.length; num++) {
									var option = document
											.createElement("option");
									option
											.appendChild(document
													.createTextNode(teacherList[num].bysjglxtTeacherBasic.name));
									document
											.getElementsByName("bysjglxt_teacher_user.user_teacher_id")[0]
											.appendChild(option);
									option.value = teacherList[num].bysjglxtTeacherUser.user_teacher_id;
								}
							} else {
								toastr.error(xhr.status);
							}
						}
					}
					var formData = new FormData();
					formData.append("college.college_id", document
							.getElementById("input_college_id").value);
					xhr
							.open("POST",
									"/bysjglxt/teacher/TeacherInformationManagement_listTeacherAllByCollege");
					xhr.send(formData);
				},
				buttons : {
					'确认分配' : {
						btnClass : 'btn-blue',
						action : function() {

							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										toastr.success("分配成功");
										jc.close();
										List_College();
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							var formData = new FormData(document
									.getElementById("form_updateAdmin"));
							xhr
									.open("POST",
											"/bysjglxt/college/CollegeManagement_updateAdmin");
							xhr.send(formData);
							return false;
						}
					},
					'取消' : function() {
					}

				}

			});
}