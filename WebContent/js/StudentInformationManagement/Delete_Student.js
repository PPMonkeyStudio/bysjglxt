function Delete_Student() {

	$
			.confirm({
				title : '警告！删除学生',
				content : '此操作将删除所有所选的学生账号，并且将学生对应的数据一并删除',
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
											List_Student_By_PageAndSearch(student_json.pageIndex);
										}
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							var checkbox_select = document
									.getElementsByClassName("checkbox_select");
							var ListDeleteStudentNum = null;
							var formData = new FormData();
							for (var num = 0; num < checkbox_select.length; num++) {
								if (checkbox_select[num].checked) {
									formData.append("ListDeleteStudentID",
											checkbox_select[num].id);
								}
							}
							xhr
									.open("POST",
											"/bysjglxt/student/StudentInformationManagement_DeleteStudent");
							xhr.send(formData);
						}
					},
					取消 : function() {
					}
				}
			});

}