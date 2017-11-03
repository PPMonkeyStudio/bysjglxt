function studentSelectTopic() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				if (xhr.responseText == "success") {
					toastr.success("选题成功");
					List_Topic_By_PageAndSearch(1);
				}
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