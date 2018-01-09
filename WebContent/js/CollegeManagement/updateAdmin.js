function updateAdmin() {
	var jc = $
			.confirm({
				title : '重新分配管理员',
				content : '<form id="form_addCollege">'
						+ '<table  class="table table-bordered" style="text-align: center;">'
						+ '<tbody>' + '<tr>' + '<td>选择教师：</td><td>'
						+ '<select  name="" class="form-control" >'
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
								toastr.success("重新分配成功");
								jc.close();
								List_College();
							} else {
								toastr.error(xhr_2.status);
							}
						}
					}
					var formData = new FormData(document
							.getElementById("form_addCollege"));
					xhr.open("POST",
							"/bysjglxt/college/CollegeManagement_addCollege");
					xhr.send(formData);
				},
				buttons : {
					'重新分配' : {
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
										toastr.error(xhr_2.status);
									}
								}
							}
							var formData = new FormData(document
									.getElementById("form_addCollege"));
							xhr
									.open("POST",
											"/bysjglxt/college/CollegeManagement_addCollege");
							xhr.send(formData);
							return false;
						}
					},
					'取消' : function() {
					}

				}

			});
}