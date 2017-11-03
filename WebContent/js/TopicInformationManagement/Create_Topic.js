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
				if (xhr.responseText != "登录状态已失效") {
					window.location = "/bysjglxt/topic/TopicInformationManagement_TopicListPage";
				} else {
					toastr.error("登录状态已失效");
					window.location = "/bysjglxt/login.jsp";
				}
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
	if (!topic_invite_teacher_name.value == "") {
		formData
				.append(
						"topicInformationManagementDTO.bysjglxtTopicInviteTeacher.topic_invite_teacher_name",
						topic_invite_teacher_name.value);
	}
	if (!topic_invite_teacher_sex.value == "") {
		formData
				.append(
						"topicInformationManagementDTO.bysjglxtTopicInviteTeacher.topic_invite_teacher_sex",
						topic_invite_teacher_sex.value);
	}
	if (!topic_invite_teacher_unitname.value == "") {
		formData
				.append(
						"topicInformationManagementDTO.bysjglxtTopicInviteTeacher.topic_invite_teacher_unitname",
						topic_invite_teacher_unitname.value);
	}
	if (!topic_invite_teacher_technicalitle.value == "") {
		formData
				.append(
						"topicInformationManagementDTO.bysjglxtTopicInviteTeacher.topic_invite_teacher_technicalitle",
						topic_invite_teacher_technicalitle.value);
	}
	if (!topic_invite_teacher_duties.value == "") {
		formData
				.append(
						"topicInformationManagementDTO.bysjglxtTopicInviteTeacher.topic_invite_teacher_duties",
						topic_invite_teacher_duties.value);
	}

	xhr.send(formData);
}