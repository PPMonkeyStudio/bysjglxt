function distributionTopicStudent(button) {

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

	var formData = new FormData();
	formData.append("studentSelectTopic", distributionTopicStudent_ID);
	formData.append("studentIDList", $('#select_distributionTopicStudent')
			.val());
	alert($('#select_distributionTopicStudent').val());
	xhr
			.open("POST",
					"/bysjglxt/topic/TopicInformationManagement_distributionTopicStudent");
	xhr.send(formData);

}