function studentSelectTopic(this_button) {

	var jc = $
			.confirm({
				columnClass : 'col-md-6 col-md-offset-3',
				title : '学生详细信息',
				content : '<table id="table_topic_detail" class="table table-hover table-bordered" style="text-align: center;">'
						+ '<tbody>' + '</tbody>' + '</table>',
				buttons : {
					button_xt : {
						text : '选题',
						btnClass : 'btn-blue student_control',
						isHidden : true,
						action : function() {
							// if (json_topicCurrentProcessDTO == '{}') {
							// toastr.error("管理员未开启选题的流程");
							// return;
							// } else {
							// for (var num = 0;
							// topicCurrentProcessDTO.listTaskBelongProcess.length;
							// num++) {
							// if
							// (topicCurrentProcessDTO.listTaskBelongProcess[num].taskInstance.task_instance_state
							// == 1) {
							// if
							// (topicCurrentProcessDTO.listTaskBelongProcess[num].taskDefinition.task_definition_name
							// != "正式选题") {
							// toastr.error("还未到正式选题的时间");
							// return;
							// }
							// break;
							// }
							// }
							var xhr_2 = false;
							xhr_2 = new XMLHttpRequest();
							xhr_2.onreadystatechange = function() {
								var message;
								if (xhr_2.readyState == 4) {
									if (xhr_2.status == 200) {
										switch (xhr_2.responseText) {
										case "1": {
											toastr.success("选题成功");
											window.location = "/bysjglxt/topic/TopicInformationManagement_MyTopicListPage";

											break;
										}
										case "-1": {
											toastr.error("指导教师已被选满");
											break;
										}
										case "-2": {
											toastr.error("课题已被选满");
											break;
										}
										case "-3": {
											toastr.error("不可选多个课题");
											break;
										}
										case "-4": {
											toastr.error("服务器繁忙，请稍后再试");
											break;
										}

										}

										List_Topic_By_PageAndSearch(1);
									} else {
										toastr.error(xhr_2.status);
									}
								}
							}

							var formData = new FormData();
							formData.append("studentSelectTopic", document
									.getElementById("info_topic_id").value);
							xhr_2
									.open("POST",
											"/bysjglxt/topic/TopicInformationManagement_studentSelectTopic");
							xhr_2.send(formData);

							// }
							/*
							 * 
							 */
							return false;
						}
					},
					button_tqxt : {
						text : '提前选题',
						btnClass : 'btn-blue button_tqxt student_control',
						isHidden : true,
						action : function() {

							return false;
						}
					},
					'返回 ' : function() {
					}
				},
				onContentReady : function() {
					/*
					 * 
					 */
					if (userStudentDTO != null) {
						if (json_topicCurrentProcessDTO == '{}') {
						} else {
							for (var num666 = 0; num666 < topicCurrentProcessDTO.listTaskBelongProcess.length; num666++) {
								if (topicCurrentProcessDTO.listTaskBelongProcess[num666].taskInstance.task_instance_state == 1) {
									if (topicCurrentProcessDTO.listTaskBelongProcess[num666].taskDefinition.task_definition_name == "正式选题") {
										this.buttons.button_xt.show();
										break;
									}

								}
							}
						}
					}

					/*
					 * 
					 */
					var new_tr_1 = null;
					var table_topic_detail = null;
					table_topic_detail = document
							.getElementById("table_topic_detail");
					table_topic_detail.firstElementChild.innerHTML = "";

					for (var num = 0; num < topic_json.list_TopicInformationDTO.length; num++) {

						if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_id == this_button.id) {

							/*
							 * 如果指定的学生中有，则显示提前选题按钮
							 */
							if (json_topicCurrentProcessDTO == '{}') {
							} else {
								for (var num4 = 0; num4 < topicCurrentProcessDTO.listTaskBelongProcess.length; num4++) {
									if (topicCurrentProcessDTO.listTaskBelongProcess[num4].taskInstance.task_instance_state == 1) {
										if (topicCurrentProcessDTO.listTaskBelongProcess[num4].taskDefinition.task_definition_name == "公布选题") {
											var button_selectTopic_early = document
													.getElementsByClassName("button_tqxt")[0];
											if (button_selectTopic_early != null) {
												if (userStudentDTO != null
														&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student != null) {
													var studentList = topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student
															.split("#&#");
													for (var num2 = 0; num2 < studentList.length; num2++) {
														if (studentList[num2] == userStudentDTO.bysjglxtStudentUser.user_student_id) {
															this.buttons.button_tqxt
																	.show();
															break;
														}
													}
												}
											}
											break;
										}
									}
								}
							}

							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							table_topic_detail.firstElementChild
									.appendChild(new_tr_1);
							new_tr_1.innerHTML = '<input id="info_topic_id" style="display:none;" class="form-control" value="'
									+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_id
									+ '"></input>';

							new_tr_1 = document.createElement("tr");
							table_topic_detail.firstElementChild
									.appendChild(new_tr_1);
							if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
									&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_chinese != null) {
								new_tr_1.innerHTML = '<th>课题中文名称</th><td>'
										+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_chinese
										+ '</td>';
							} else {
								new_tr_1.innerHTML = '<th>课题中文名称</th><td>无</td>';
							}

							new_tr_1 = document.createElement("tr");

							table_topic_detail.firstElementChild
									.appendChild(new_tr_1);
							if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
									&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_english != null) {
								new_tr_1.innerHTML = '<th>课题英文名称</th><td>'
										+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_english
										+ '</td>';
							} else {
								new_tr_1.innerHTML = '<th>课题英文名称</th><td>无</td>';
							}

							new_tr_1 = document.createElement("tr");

							table_topic_detail.firstElementChild
									.appendChild(new_tr_1);
							if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
									&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_source != null) {
								new_tr_1.innerHTML = '<th>课题来源</th><td>'
										+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_source
										+ '</td>';
							} else {
								new_tr_1.innerHTML = '<th>课题来源</th><td>无</td>';
							}

							new_tr_1 = document.createElement("tr");

							table_topic_detail.firstElementChild
									.appendChild(new_tr_1);
							if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
									&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_type != null) {
								new_tr_1.innerHTML = '<th>课题性质</th><td>'
										+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_type
										+ '</td>';
							} else {
								new_tr_1.innerHTML = '<th>课题性质</th><td>无</td>';
							}

							new_tr_1 = document.createElement("tr");

							table_topic_detail.firstElementChild
									.appendChild(new_tr_1);
							if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
									&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_requirement != null) {
								new_tr_1.innerHTML = '<th>实现要求</th><td>'
										+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_requirement
										+ '</td>';
							} else {
								new_tr_1.innerHTML = '<th>实现要求</th><td>无</td>';
							}

							new_tr_1 = document.createElement("tr");

							table_topic_detail.firstElementChild
									.appendChild(new_tr_1);
							if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
									&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_remark != null) {
								new_tr_1.innerHTML = '<th>备注</th><td>'
										+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_remark
										+ '</td>';
							} else {
								new_tr_1.innerHTML = '<th>备注</th><td>无</td>';
							}

							new_tr_1 = document.createElement("tr");
							table_topic_detail.firstElementChild
									.appendChild(new_tr_1);
							if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
									&& topic_json.list_TopicInformationDTO[num].teacherInformationDTO.bysjglxtTeacherBasic.name != null) {
								new_tr_1.innerHTML = '<th>指导老师</th><td>'
										+ topic_json.list_TopicInformationDTO[num].teacherInformationDTO.bysjglxtTeacherBasic.name
										+ '</td>';
							} else {
								new_tr_1.innerHTML = '<th>指导老师</th><td>无</td>';
							}

							new_tr_1 = document.createElement("tr");
							table_topic_detail.firstElementChild
									.appendChild(new_tr_1);
							if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
									&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_num != null) {
								new_tr_1.innerHTML = '<th>已选学生数</th><td>'
										+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_num
										+ '</td>';
							} else {
								new_tr_1.innerHTML = '<th>已选学生数</th><td>无</td>';
							}
							new_tr_1 = document.createElement("tr");
							table_topic_detail.firstElementChild
									.appendChild(new_tr_1);
							if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_max != "-1") {
								new_tr_1.innerHTML = '<th>课题学生上限</th><td>'
										+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_max
										+ '</td>';
							} else {
								new_tr_1.innerHTML = '<th>课题学生上限</th><td>不限</td>';
							}
						}

					}
				}
			});

}
