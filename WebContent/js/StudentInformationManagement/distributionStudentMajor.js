function distributionStudentMajor(this_button) {
	var jc = $
			.confirm({
				columnClass : 'col-md-6 col-md-offset-3',
				title : '分配专业',
				content : '<form id="form_distributionStudentMajor">'
						+ '<table id="table_distributionStudentMajor" class="table table-hover table-bordered" style="text-align: center;">'
						+ '<tbody></tbody>' + '</table>' + '</form>',
				buttons : {
					'提交修改 ' : {
						btnClass : 'btn-blue',
						action : function() {
							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										if (xhr.responseText == "分配成功") {
											toastr.success("分配成功");
											List_Student_By_PageAndSearch(student_json.pageIndex);
											jc.close();
										} else {
											toastr.success("分配失败");
										}
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							var formData = new FormData(
									document
											.getElementById("form_distributionStudentMajor"));

							xhr
									.open("POST",
											"/bysjglxt/student/StudentInformationManagement_distributionStudentMajor");

							xhr.send(formData);
							/*
							 * 
							 */
							return false;
						}
					},
					'返回 ' : function() {
					}
				},
				onContentReady : function() {

					var xhr = false;
					xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						var message;
						if (xhr.readyState == 4) {
							if (xhr.status == 200) {
								var distributionMajor_json = JSON
										.parse(xhr.responseText);
								/*
								 * 
								 */
								var new_tr_1 = null;
								var table_distributionStudentMajor = document
										.getElementById("table_distributionStudentMajor");
								table_distributionStudentMajor.firstElementChild.innerHTML = "";
								/*
								 * 
								 */
								new_tr_1 = document.createElement("tr");
								table_distributionStudentMajor.firstElementChild
										.appendChild(new_tr_1);
								new_tr_1.innerHTML = '<th></th><td><input name="studentUser.user_student_id"  style="text-align: center;display:none;" class="form-control"  value="'
										+ this_button.id + '"></input></td>';
								/*
								 * 
								 */
								new_tr_1 = document.createElement("tr");
								table_distributionStudentMajor.firstElementChild
										.appendChild(new_tr_1)
								new_tr_1.innerHTML = '<th>选择专业</th><td><select name="major.major_id" id="select_distributionStudentMajor" style="text-align: center;" class="form-control" ></select></td>';
								for (var num2 = 0; num2 < distributionMajor_json.length; num2++) {
									var option = document
											.createElement("option");
									option
											.appendChild(document
													.createTextNode(distributionMajor_json[num2].major_name));
									document.getElementById(
											"select_distributionStudentMajor")
											.appendChild(option);
									option.value = distributionMajor_json[num2].major_id;
								}
								/*
								 * ajax结束
								 */
							} else {
								toastr.error(xhr.status);
							}
						}
					}
					xhr
							.open("POST",
									"/bysjglxt/student/StudentInformationManagement_GetStudentMajor");

					xhr.send(null);
				}
			});
}