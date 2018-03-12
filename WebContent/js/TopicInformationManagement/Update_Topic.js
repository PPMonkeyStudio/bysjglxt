function Update_Topic() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				alert(xhr.responseText)
				var topicCurrentProcessDTO = JSON.parse(xhr.responseText);
				if (topicCurrentProcessDTO == null) {
					toastr.error("管理员未开启选题的流程");
					return;
				} else {
					for (var num = 0; topicCurrentProcessDTO.listTaskBelongProcess.length; num++) {
						if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskInstance.task_instance_state == 1) {
							console
									.debug("正在进行的选题任务："
											+ topicCurrentProcessDTO.listTaskBelongProcess[num].taskDefinition.task_definition_name);
							if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskDefinition.task_definition_name != "创建选题") {
								toastr.error("已经过了修改选题的时间");
								return;
							}
							break;
						}
					}
					/*
					 * 
					 */

					var input_topic_id = document
							.getElementById("input_topic_id");
					var input_topic_name_chinese = document
							.getElementById("input_topic_name_chinese");
					var input_topic_name_english = document
							.getElementById("input_topic_name_english");
					var select_topic_source = document
							.getElementById("select_topic_source");
					var select_topic_type = document
							.getElementById("select_topic_type");
					var input_topic_requirement = document
							.getElementById("input_topic_requirement");

					var input_topic_remark = document
							.getElementById("input_topic_remark");

					var input_topic_student_max = document
							.getElementById("input_topic_student_max");

					if (input_topic_name_chinese.value.length == 0) {
						toastr.error("课题中文名称不能为空");
						return;
					}

					var xhr2 = false;
					xhr2 = new XMLHttpRequest();
					xhr2.onreadystatechange = function() {
						var message;
						if (xhr2.readyState == 4) {
							if (xhr2.status == 200) {
								$("#modal_Topic_Information").modal("hide");
								List_MyTopic_By_PageAndSearch(topic_json.pageIndex);
							} else {
								toastr.error(xhr2.status);
							}
						}
					}

					var formData = new FormData();

					formData
							.append(
									"topicInformationManagementDTO.bysjglxtTopic.topic_id",
									input_topic_id.value);

					formData
							.append(
									"topicInformationManagementDTO.bysjglxtTopic.topic_name_chinese",
									input_topic_name_chinese.value);

					formData
							.append(
									"topicInformationManagementDTO.bysjglxtTopic.topic_name_english",
									input_topic_name_english.value);

					formData
							.append(
									"topicInformationManagementDTO.bysjglxtTopic.topic_source",
									select_topic_source.value);

					formData
							.append(
									"topicInformationManagementDTO.bysjglxtTopic.topic_type",
									select_topic_type.value);

					formData
							.append(
									"topicInformationManagementDTO.bysjglxtTopic.topic_requirement",
									input_topic_requirement.value);

					formData
							.append(
									"topicInformationManagementDTO.bysjglxtTopic.topic_remark",
									input_topic_remark.value);

					formData
							.append(
									"topicInformationManagementDTO.bysjglxtTopic.topic_student_max",
									input_topic_student_max.value);
					xhr2
							.open("POST",
									"/bysjglxt/topic/TopicInformationManagement_UpdateTopic");
					xhr2.send(formData);

					/*
					 * 
					 */
				}
			} else {
				toastr.error(xhr2.status);
			}
		}
	}
	xhr.open("POST",
			"/bysjglxt/process/ProcessManagement_getTopicCurrentProcess");
	xhr.send(null);

}