function Create_Topic() {
	var topic_name_chinese = document.getElementById("topic_name_chinese");
	var topic_name_english = document.getElementById("topic_name_english");
	var topic_requirement = document.getElementById("topic_requirement");
	var topic_source = document.getElementById("topic_source");
	var topic_type = document.getElementById("topic_type");
	var topic_remark = document.getElementById("topic_remark");

	if (topic_name_chinese.value.length == 0) {
		toastr.error("课题中文名称不能为空");
		return;
	}

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				window.location = "/bysjglxt/topic/TopicInformationManagement_TopicListPage";
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();

	xhr.open("POST", "/bysjglxt/topic/TopicInformationManagement_CreateTopic");

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
	formData.append("topicInformationManagementDTO.bysjglxtTopic.topic_source",
			topic_source.value);
	formData.append("topicInformationManagementDTO.bysjglxtTopic.topic_type",
			topic_type.value);
	if (!topic_remark.value == "") {
		formData.append(
				"topicInformationManagementDTO.bysjglxtTopic.topic_remark",
				topic_remark.value);
	}
	/*
	 * 
	 */

	xhr.send(formData);
}