function Student_Take_Operate_Premission() {
	$
			.confirm({
				title : '警告！关闭所选学生',
				content : '此操作将关闭所选学生账号，学生数据将被固化且将无法操作系统，若重新打开则学生数据将被清空，且此操作无法撤回，确定要继续吗？',
				type : 'red',
				autoClose : '取消|5000',// 自动关闭
				buttons : {
					'确认关闭' : {
						btnClass : 'btn-red',
						action : function() {
							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										if (xhr.responseText == "success") {
											toastr.success("已关闭所选学生");
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