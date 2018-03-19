function Delete_Teacher() {
	$
			.confirm({
				title : '警告！删除教师',
				content : '此操作将删除所有所选的教师账号，并且将教师对应的数据一并删除',
				type : 'red',
				autoClose : '取消|5000',// 自动关闭
				buttons : {
					删除 : {
						btnClass : 'btn-red',
						action : function() {
							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										if (xhr.responseText == "success") {
											toastr.success("删除成功");
											List_Teacher_By_PageAndSearch(teacher_json.pageIndex);
										}
									} else {
										toastr.error(xhr.status);
									}
								}
							}

							var checkbox_select = document
									.getElementsByClassName("checkbox_select");

							var ListDeleteTeacherNum = null;

							var formData = new FormData();

							for (var num = 0; num < checkbox_select.length; num++) {
								if (checkbox_select[num].checked) {
									formData.append("ListTeacherID",
											checkbox_select[num].id);
								}

							}

							xhr
									.open("POST",
											"/bysjglxt/teacher/TeacherInformationManagement_DeleteTeacher");

							xhr.send(formData);
						}
					},
					取消 : function() {
					}
				}
			});

}