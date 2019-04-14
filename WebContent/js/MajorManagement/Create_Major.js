function Create_Major() {

	var jc = $
			.confirm({
				title : '新增专业',
				type : 'blue',
				content : '<hr>'
						+ '<table  class="table table-bordered" style="text-align: center;">'
						+ '<tbody>'
						+ '<tr>'
						+ '<td>专业代码：</td>'
						+ '<td><input id="input_major_professionalcode" class="form-control" /></td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td>专业名称：</td>'
						+ '<td><input id="input_major_name" class="form-control" /></td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td>所属教研室：</td>'
						+ '<td><select id="select_section" class="form-control" >'
						+ '</select></td>' + '</tr>' + '</tbody>' + '</table>',
				onContentReady : function() {
					Get_Teacher_Section(document
							.getElementById("select_section"));
				},
				buttons : {
					'创建' : {
						btnClass : 'btn-blue',
						action : function() {
							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										if (xhr.responseText == "1") {
											toastr.success("创建专业成功", "操作成功");
											jc.close();
											List_Major_By_Page(major_json.pageIndex)
										}
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							var formData = new FormData();

							var input_major_professionalcode = document
									.getElementById("input_major_professionalcode");
							var input_major_name = document
									.getElementById("input_major_name");
							var select_section = document
									.getElementById("select_section");

							if (input_major_professionalcode.value == "") {
								toastr.error("专业代码不可为空", "操作失败");
								return false;
							}
							if (input_major_name.value == "") {
								toastr.error("专业名称不可为空", "操作失败");
								return false;
							}

							xhr.open("POST",
									"/bysjglxt/major/MajorManagement_addMajor");

							formData.append(
									"bysjglxtMajor.major_professionalcode",
									input_major_professionalcode.value);

							formData.append("bysjglxtMajor.major_name",
									input_major_name.value);

							formData.append(
									"bysjglxtMajor.major_belong_section",
									select_section.value);

							xhr.send(formData);

							return false;
						}
					},
					'放弃' : function() {
					}
				}
			});
}