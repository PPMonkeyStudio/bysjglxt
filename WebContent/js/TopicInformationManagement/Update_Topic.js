function Update_Topic() {
	var input_topic_id = document.getElementById("input_topic_id");
	var input_topic_name_chinese = document
			.getElementById("input_topic_name_chinese");
	var input_topic_name_english = document
			.getElementById("input_topic_name_english");
	var select_topic_source = document.getElementById("select_topic_source");
	var select_topic_type = document.getElementById("select_topic_type");
	var input_topic_requirement = document
			.getElementById("input_topic_requirement");

	var input_topic_remark = document.getElementById("input_topic_remark");

	var input_topic_student_max = document
			.getElementById("input_topic_student_max");

	if (input_topic_name_chinese.value.length == 0) {
		toastr.error("课题中文名称不能为空");
		return;
	}

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				window.location = "/bysjglxt/topic/TopicInformationManagement_MyTopicListPage";
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();

	xhr.open("POST", "/bysjglxt/topic/TopicInformationManagement_UpdateTopic");

	formData.append("topicInformationManagementDTO.bysjglxtTopic.topic_id",
			input_topic_id.value);

	formData.append(
			"topicInformationManagementDTO.bysjglxtTopic.topic_name_chinese",
			input_topic_name_chinese.value);

	formData.append(
			"topicInformationManagementDTO.bysjglxtTopic.topic_name_english",
			input_topic_name_english.value);

	formData.append("topicInformationManagementDTO.bysjglxtTopic.topic_source",
			select_topic_source.value);

	formData.append("topicInformationManagementDTO.bysjglxtTopic.topic_type",
			select_topic_type.value);

	formData.append(
			"topicInformationManagementDTO.bysjglxtTopic.topic_requirement",
			input_topic_requirement.value);

	formData.append("topicInformationManagementDTO.bysjglxtTopic.topic_remark",
			input_topic_remark.value);

	formData.append(
			"topicInformationManagementDTO.bysjglxtTopic.topic_student_max",
			input_topic_student_max.value);
	/*
	 * 
	 */

	xhr.send(formData);
}