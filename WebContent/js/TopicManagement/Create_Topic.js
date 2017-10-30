function Create_Topic() {
	var topic_name_chinese = document.getElementById("topic_name_chinese");
	var topic_name_english = document.getElementById("topic_name_english");
	var topic_requirement = document.getElementById("topic_requirement");
	var topic_source = document.getElementById("topic_source");
	var topic_type = document.getElementById("topic_type");
	var topic_remark = document.getElementById("topic_remark");

	var topic_invite_teacher_name = document
			.getElementById("topic_invite_teacher_name");
	var topic_invite_teacher_sex = document
			.getElementById("topic_invite_teacher_sex");
	var topic_invite_teacher_unitname = document
			.getElementById("topic_invite_teacher_unitname");
	var topic_invite_teacher_technicalitle = document
			.getElementById("topic_invite_teacher_technicalitle");
	var topic_invite_teacher_duties = document
			.getElementById("topic_invite_teacher_duties");

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

				window.location = "/bysjglxt/topic/TopicManagement_TopicListPage";
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();

	xhr.open("POST", "/bysjglxt/topic/TopicManagement_CreateTopic");

	if (!topic_name_chinese.value == "") {
		formData
				.append("newTopic.topic_name_chinese", topic_name_chinese.value);
	}
	if (!topic_name_english.value == "") {
		formData
				.append("newTopic.topic_name_english", topic_name_english.value);
	}
	if (!topic_requirement.value == "") {
		formData.append("newTopic.topic_requirement", topic_requirement.value);
	}
	formData.append("newTopic.topic_source", topic_source.value);
	formData.append("newTopic.topic_type", topic_type.value);

	if (!topic_remark.value == "") {
		formData.append("newTopic.topic_remark", topic_remark.value);
	}

	xhr.send(formData);
}