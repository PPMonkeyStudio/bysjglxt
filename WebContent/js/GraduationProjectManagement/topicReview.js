function topicReview(button) {
	$
			.confirm({
				title : '分配评阅教师',
				content : '<select id="select_topicReview" class="form-control" ></select>',
				type : 'blue',
				buttons : {
					分配 : {
						btnClass : 'btn-blue',
						action : function() {
							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
									} else {
										toastr.error(xhr.status);
									}
								}
							}

							xhr
									.open("POST",
											"/bysjglxt/topic/TopicInformationManagement_AssignmentReviewTeacher");

							var select_topicReview = document
									.getElementById("select_topicReview");

							var formData = new FormData();
							formData.append("assignmentTopicId", button.id);

							formData.append("assignmentReviewTeacherId",
									select_topicReview.value);
							xhr.send(formData);
							/*
							 * 
							 */

						}
					},
					取消 : function() {
					}
				},
				onContentReady : function() {
					var xhr = false;
					xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						var message;
						if (xhr.readyState == 4) {
							if (xhr.status == 200) {
								var teacherList = JSON.parse(xhr.responseText);
								var select_topicReview = document
										.getElementById("select_topicReview");
								for (var num = 0; num < teacherList.length; num++) {
									var option = document
											.createElement("option");
									option
											.appendChild(document
													.createTextNode(teacherList[num].bysjglxtTeacherBasic.name));

									select_topicReview.appendChild(option);
									option.value = teacherList[num].bysjglxtTeacherUser.user_teacher_id;
								}
							} else {
								toastr.error(xhr.status);
							}
						}
					}

					xhr
							.open("POST",
									"/bysjglxt/teacher/TeacherInformationManagement_ListTeacherAll");
					xhr.send(null);
				}

			});
}