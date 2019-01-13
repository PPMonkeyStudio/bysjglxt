function topicReview(button,state) {
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
										toastr.success("已分配评阅教师");
										if(state == 2){
											//根据学生id获取该学生的评阅老师信息 updateTeacherUser.
											var xhr2 = false;
											xhr2 = new XMLHttpRequest();
											xhr2.onreadystatechange = function() {
												var message;
												if (xhr2.readyState == 4) {
													if (xhr2.status == 200) {
														console.log(data);
														var data = JSON.parse(xhr2.responseText);
														console.log(button)
														var name = button.parentNode.previousElementSibling;
														console.log(name)
														name.innerHTML = data.bysjglxtTeacherBasic.name;
													}
												}
											}
											var formData = new FormData();
											formData.append("studentUser.user_student_id",button.id);
											xhr2.open("POST","/bysjglxt/teacher/TeacherInformationManagement_getTeacherInfoByUserId");
											xhr2.send(formData);
										}
										List_MyManagementGraduationProject_By_PageAndSearch(myManagementGraduationProject_json.pageIndex);
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							xhr.open("POST","/bysjglxt/topic/TopicInformationManagement_AssignmentReviewTeacher");
							var select_topicReview = document.getElementById("select_topicReview");
							var formData = new FormData();
							formData.append("assignmentTopicId", button.id);
							formData.append("assignmentReviewTeacherId",select_topicReview.value);
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