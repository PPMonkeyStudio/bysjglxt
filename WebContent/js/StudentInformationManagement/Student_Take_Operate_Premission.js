function Student_Take_Operate_Premission() {
	$
			.confirm({
				title : '关闭操作权限',
				content : '关闭所选学生的操作权限',
				type : 'red',
				buttons : {
					'分配' : {
						btnClass : 'btn-red',
						action : function() {
							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										if (xhr.responseText == "success") {
											toastr.success("已收回所选学生权限");
											List_Student_By_PageAndSearch(1);
										}
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
									formData
											.append(
													"ListTakeOperatePremissionStudentID",
													checkbox_select[num].id);
								}

							}

							xhr
									.open("POST",
											"/bysjglxt/student/StudentInformationManagement_TakeStudentOperatePremission");

							xhr.send(formData);

						}
					},
					'取消' : function() {
					}
				}
			});
}