function specialStudentSelectTopic(button) {
	$
			.confirm({
				title : '分配课题给学生',
				content : '<select  id="select_distributionTopicStudent" class="form-control" style="width: auto;">'
						+ '</select>',
				onContentReady : function() {
					var xhr = false;
					xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						var message;
						if (xhr.readyState == 4) {
							if (xhr.status == 200) {

								var topic_json = JSON.parse(xhr.responseText);
								var select_distributionTopicStudent = document
										.getElementById("select_distributionTopicStudent");
								for (var num = 0; num < topic_json.length; num++) {
									var option = document
											.createElement("option");
									option
											.appendChild(document
													.createTextNode(topic_json[num].topic_name_chinese));
									option.value = topic_json[num].topic_id;
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
									"/bysjglxt/topic/TopicInformationManagement_listSelectBysjglxtTopic");
					xhr.send(null);
				},
				buttons : {
					'分配' : function() {
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
										"/bysjglxt/topic/TopicInformationManagement_specialStudentSelectTopic");
						var formData = new FormData();
						formData
								.append(
										"topicId",
										document
												.getElementById("select_distributionTopicStudent").value);
						formData.append("studentUserId", button.id);

						xhr.send(formData);
					},
					'取消' : function() {
					}

				}

			});

}