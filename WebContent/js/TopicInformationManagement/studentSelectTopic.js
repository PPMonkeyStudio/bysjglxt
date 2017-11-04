function studentSelectTopic() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				switch (xhr.responseText) {
				case "1": {
					toastr.success("选题成功");
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
	xhr.open("POST",
			"/bysjglxt/topic/TopicInformationManagement_studentSelectTopic");
	xhr.send(formData);
}