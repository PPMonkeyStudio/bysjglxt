function addCollege() {
	var jc = $
			.confirm({
				title : '添加院系',
				content : '<form id="form_addCollege">'
						+ '<table  class="table table-bordered" style="text-align: center;">'
						+ '<tbody>'
						+ '<tr>'
						+ '<td>院系代码：</td><td><input  name="college.college_code" class="form-control"  /></td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td>院系名称：</td><td><input  name="college.college_name" class="form-control"  /></td>'
						+ '</tr>' + '</tbody>' + '</table>' + '</form>',
				type : 'blue',
				onContentReady : function() {
				},
				buttons : {
					'添加' : {
						btnClass : 'btn-blue',
						action : function() {

							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										toastr.success("创建成功");
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