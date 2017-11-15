function studentSelectTopic_early() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
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
							if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskDefinition.task_definition_name != "提前选题") {
								toastr.error("还未到提前选题的时间");
								return;
							}
							break;
						}
					}
					var xhr = false;
					xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						var message;
						if (xhr.readyState == 4) {
							if (xhr.status == 200) {
								switch (xhr.responseText) {
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
								toastr.error(xhr.status);
							}
						}
					}

					var formData = new FormData();
					formData.append("studentSelectTopic", document
							.getElementById("info_topic_id").value);
					xhr
							.open("POST",
									"/bysjglxt/topic/TopicInformationManagement_studentSelectTopic");
					xhr.send(formData);

				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	xhr
			.open("POST",
					"/bysjglxt/topic/TopicInformationManagement_getTopicCurrentProcess");
	xhr.send(null);
}