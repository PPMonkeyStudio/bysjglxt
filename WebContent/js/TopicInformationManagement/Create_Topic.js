function Create_Topic() {

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				alert("ioio");
				console.debug("获取当前选题流程:" + xhr.responseText);
				var topicCurrentProcessDTO = JSON.parse(xhr.responseText);
				if (xhr.responseText == '{}') {
					toastr.error("管理员未开启选题的流程");
					return;
				} else {
					for (var num = 0; topicCurrentProcessDTO.listTaskBelongProcess.length; num++) {
						if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskInstance.task_instance_state == 1) {
							console
									.debug("正在进行的选题任务："
											+ topicCurrentProcessDTO.listTaskBelongProcess[num].taskDefinition.task_definition_name);
							if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskDefinition.task_definition_name != "创建选题") {
								toastr.error("还未到创建选题的时间");
								return;
							}
							break;
						}
					}

					var topic_name_chinese = document
							.getElementById("topic_name_chinese");
					var topic_name_english = document
							.getElementById("topic_name_english");
					var topic_requirement = document
							.getElementById("topic_requirement");
					var topic_source = document.getElementById("topic_source");
					var topic_type = document.getElementById("topic_type");
					var topic_remark = document.getElementById("topic_remark");

					if (topic_name_chinese.value.length == 0) {
						toastr.error("课题中文名称不能为空");
						return;
					}
					var xhr_2 = false;
					xhr_2 = new XMLHttpRequest();
					xhr_2.onreadystatechange = function() {
						var message;
						if (xhr_2.readyState == 4) {
							if (xhr_2.status == 200) {
								window.location = "/bysjglxt/topic/TopicInformationManagement_TopicListPage";
							} else {
								toastr.error(xhr_2.status);
							}
						}
					}
					var formData = new FormData();
					xhr_2.open("POST","/bysjglxt/topic/TopicInformationManagement_CreateTopic");
					if (!topic_name_chinese.value == "") {
						formData
								.append(
										"topicInformationManagementDTO.bysjglxtTopic.topic_name_chinese",
										topic_name_chinese.value);
					}
					if (!topic_name_english.value == "") {
						formData
								.append(
										"topicInformationManagementDTO.bysjglxtTopic.topic_name_english",
										topic_name_english.value);
					}
					if (!topic_requirement.value == "") {
						formData
								.append(
										"topicInformationManagementDTO.bysjglxtTopic.topic_requirement",
										topic_requirement.value);
					}
					formData
							.append(
									"topicInformationManagementDTO.bysjglxtTopic.topic_source",
									topic_source.value);
					formData
							.append(
									"topicInformationManagementDTO.bysjglxtTopic.topic_type",
									topic_type.value);
					if (!topic_remark.value == "") {
						formData
								.append(
										"topicInformationManagementDTO.bysjglxtTopic.topic_remark",
										topic_remark.value);
					}
					/*
					 * 
					 */
					alert("d:"+formData.get("topicInformationManagementDTO.bysjglxtTopic.topic_remark"));
					xhr_2.send(formData);

				}
			} else {
				toastr.error(xhr_2.status);
			}
		}
	}
	xhr.open("POST",
			"/bysjglxt/process/ProcessManagement_getTopicCurrentProcess");
	xhr.send(null);
	/*
	 * 
	 */

}