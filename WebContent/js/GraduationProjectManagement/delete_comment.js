function delete_comment() {
	$
			.confirm({
				title : '删除评语',
				content : '此操作将删除所选择的评语',
				type : 'red',
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
											List_Comment_By_College(CollegeComment_json.pageIndex);
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
									formData.append("listCommentId",
											checkbox_select[num].id);
								}

							}

							xhr
									.open("POST",
											"/bysjglxt/graduationProject/GraduationProjectManagement_deleteListComment");

							xhr.send(formData);
						}
					},
					取消 : function() {
					}
				}
			});
}