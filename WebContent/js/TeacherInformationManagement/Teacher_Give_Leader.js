function Teacher_Give_Leader() {

	$
			.confirm({
				title : '打开答辩小组组长权限',
				content : '答辩小组组长将有权限修改所有答辩学生的答辩评分',
				type : 'blue',
				buttons : {
					'打开' : {
						btnClass : 'btn-blue',
						action : function() {
							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										toastr.success("已打开所选教师的答辩小组组长权限");
										List_Teacher_By_PageAndSearch(teacher_json.pageIndex);
									} else {
										toastr.error(xhr.status);
									}
								}
							}

							var checkbox_select = document
									.getElementsByClassName("checkbox_select");

							var formData = new FormData();

							for (var num = 0; num < checkbox_select.length; num++) {
								if (checkbox_select[num].checked) {
									formData.append("ListTeacherID",
											checkbox_select[num].id);
								}
							}
							xhr
									.open("POST",
											"/bysjglxt/teacher/TeacherInformationManagement_GiveTeacherLeader");
							xhr.send(formData);
						}
					},
					'取消' : function() {
					}
				}
			});
}