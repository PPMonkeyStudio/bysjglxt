function addCollege() {
	var jc = $
			.confirm({
				title : '创建院系',
				content : '<hr>'
						+ '<p style="text-align: center;">创建之后不可删除，请谨慎操作</p>'
						+ '<form id="form_addCollege">'
						+ '<table  class="table table-bordered" style="text-align: center;">'
						+ '<tbody>'
						+ '<tr><td colspan="4">院系信息</td></tr>'
						+ '<tr>'
						+ '<td>院系代码：</td><td><input id="college_code" input_explain="sads" name="college.college_code" class="input_not_empty form-control"  /></td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td>院系名称：</td><td><input  name="college.college_name" class="input_not_empty form-control"  /></td>'
						+ '</tr>'
						+ '<tr><td colspan="4">院系管理员</td></tr>'
						+ '<tr>'
						/*+ '<td>工号：</td><td><input name="bysjglxt_teacher_basic.job_number" class="input_not_empty form-control"  /></td>'
						+ '</tr>'*/
						+ '<tr>'
						+ '<td>姓名：</td><td><input  name="bysjglxt_teacher_basic.name" class="input_not_empty form-control"  /></td>'
						+ '</tr>'
						+ '<tr>'
						/*+ '<td>性别：</td><td>'
						+ '<select  name="bysjglxt_teacher_basic.sex" class="form-control" >'
						+ '<option value="男">男</option>'
						+ '<option value="女">女</option>' + '</select>'
						+ '</td>' + '</tr>' */
						+ '</tbody>' + '</table>'
						+ '</form>',
				type : 'red',
				onContentReady : function() {
				},
				buttons : {
					'确认创建' : {
						btnClass : 'btn-red',
						action : function() {

							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										switch (xhr.responseText) {
										case '1':
											toastr.success("创建成功");
											collegeAdminInfo($("#college_code").val());
											break;
										case '-2':
											toastr.error("数据重复");
											break;
										case '-1':
											toastr.error("数据错误");
											break;
										}
										jc.close();
										List_College();
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							/*
							 * 
							 */
							if (!checkInputNotEmpty()) {
								return false;
							}
							/*
							 * 
							 */
							var formData = new FormData(document.getElementById("form_addCollege"));
							xhr.open("POST","/bysjglxt/college/CollegeManagement_addCollege");
							xhr.send(formData);
							return false;
						}
					},
					'取消' : function() {
					}

				}

			});
}