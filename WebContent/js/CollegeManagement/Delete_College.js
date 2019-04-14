function Delete_College() {

	$
			.confirm({
				title : '警告！删除学院',
				content : '此操作将删除所有所选的数据学院',
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
											toastr.success("全部学院操作成功");
											List_College();
										}else if(xhr.responseText == "error"){
											toastr.success("部分学院存在数据不被允许删除");
											List_College();	}
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
									formData.append("ListDeleteCollegeID",
											checkbox_select[num].id);
								}
							}
							xhr.open("POST","/bysjglxt/college/CollegeManagement_DeleteCollege");
							xhr.send(formData);
						}
					},
					取消 : function() {
					}
				}
			});

}