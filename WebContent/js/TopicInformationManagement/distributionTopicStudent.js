function distributionTopicStudent(button) {
	$
			.confirm({
				title : '指定学生选题',
				content : '<select  id="select_distributionTopicStudent" class="form-control" style="margin:20px 0; ">'
						+ '</select>',
				type : 'blue',
				onContentReady : function() {
					var xhr = false;
					xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						var message;
						if (xhr.readyState == 4) {
							if (xhr.status == 200) {

								var student_json = JSON.parse(xhr.responseText);

								var select_distributionTopicStudent = document
										.getElementById("select_distributionTopicStudent");
								for (var num = 0; num < student_json.length; num++) {
									var option = document
											.createElement("option");
									option
											.appendChild(document
													.createTextNode(student_json[num].bysjglxtStudentBasic.student_basic_name));
									option.value = student_json[num].topic_id;
									select_distributionTopicStudent
											.appendChild(option);
								}

							} else {

								toastr.error(xhr.status);
							}
						}
					}
					xhr
							.open("POST",
									"/bysjglxt/student/StudentInformationManagement_listStudentNoClose");
					xhr.send(formData);

				},
				buttons : {
					'分配' : {
						btnClass : 'btn-blue',
						action : function() {

						}
					},
					'取消' : function() {
					}

				}

			});
}