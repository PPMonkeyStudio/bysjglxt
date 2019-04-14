function Student_Give_Operate_Premission() {

	$
			.confirm({
				title : '警告！打开所选学生',
				content : '此操作将打开所选学生账号，学生的数据将被重置，且此操作无法撤回，确定要继续吗？',
				type : 'red',
				autoClose : '取消|5000',// 自动关闭
				buttons : {
					'确认打开' : {
						btnClass : 'btn-red',
						action : function() {
							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										if (xhr.responseText == "success") {
											toastr.success("已打开所选学生");
											List_Student_By_PageAndSearch(student_json.pageIndex);
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
													"ListGiveOperatePremissionStudentID",
													checkbox_select[num].id);
								}
							}
							xhr
									.open("POST",
											"/bysjglxt/student/StudentInformationManagement_GiveStudentOperatePremission");
							xhr.send(formData);
						}
					},
					'取消' : function() {
					}
				}
			});
}