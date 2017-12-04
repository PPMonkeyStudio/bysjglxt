function studentSelectTopic() {

	if (json_topicCurrentProcessDTO == '{}') {
		toastr.error("管理员未开启选题的流程");
		return;
	} else {
		for (var num = 0; topicCurrentProcessDTO.listTaskBelongProcess.length; num++) {
			if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskInstance.task_instance_state == 1) {
				if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskDefinition.task_definition_name == "正式选题") {
					toastr.error("还未到正式选题的时间");
					return;
				}
				break;
			}
		}

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

	}

}