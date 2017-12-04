function Topic_Information_Display(this_button) {

	$("#modal_Topic_Information").modal("show");
	var new_tr_1 = null;
	var table_topic_detail = null;
	table_topic_detail = document.getElementById("table_topic_detail");
	table_topic_detail.firstElementChild.innerHTML = "";

	for (var num = 0; num < topic_json.list_TopicInformationDTO.length; num++) {

		if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_id == this_button.id) {

			/*
			 * 如果指定的学生中有，则显示提前选题按钮
			 */
			var button_selectTopic_early = document
					.getElementById("button_selectTopic_early");
			if (button_selectTopic_early != null) {
				button_selectTopic_early.style.display = 'none';
				if (userStudentDTO != null
						&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student != null) {
					var studentList = topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student
							.split("#&#");
					for (var num = 0; num < studentList.length; num++) {
						if (studentList[num] == userStudentDTO.bysjglxtStudentUser.user_student_id) {
							var button_selectTopic_early = document
									.getElementById("button_selectTopic_early");
							button_selectTopic_early.style.display = 'block';
							break;
						}
					}
				}
			}
			/*
			 * 
			 */
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_topic_detail.firstElementChild.appendChild(new_tr_1);
			new_tr_1.innerHTML = '<input id="info_topic_id" style="display:none;" class="form-control" value="'
					+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_id
					+ '"></input>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_topic_detail.firstElementChild.appendChild(new_tr_1);
			if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
					&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_chinese != null) {
				new_tr_1.innerHTML = '<th>课题中文名称</th><td>'
						+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_chinese
						+ '</td>';
			} else {
				new_tr_1.innerHTML = '<th>课题中文名称</th><td>无</td>';
			}

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_topic_detail.firstElementChild.appendChild(new_tr_1);
			if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
					&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_english != null) {
				new_tr_1.innerHTML = '<th>课题英文名称</th><td>'
						+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_english
						+ '</td>';
			} else {
				new_tr_1.innerHTML = '<th>课题英文名称</th><td>无</td>';
			}

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_topic_detail.firstElementChild.appendChild(new_tr_1);
			if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
					&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_source != null) {
				new_tr_1.innerHTML = '<th>课题来源</th><td>'
						+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_source
						+ '</td>';
			} else {
				new_tr_1.innerHTML = '<th>课题来源</th><td>无</td>';
			}

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_topic_detail.firstElementChild.appendChild(new_tr_1);
			if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
					&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_type != null) {
				new_tr_1.innerHTML = '<th>课题性质</th><td>'
						+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_type
						+ '</td>';
			} else {
				new_tr_1.innerHTML = '<th>课题性质</th><td>无</td>';
			}

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_topic_detail.firstElementChild.appendChild(new_tr_1);
			if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
					&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_requirement != null) {
				new_tr_1.innerHTML = '<th>实现要求</th><td>'
						+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_requirement
						+ '</td>';
			} else {
				new_tr_1.innerHTML = '<th>实现要求</th><td>无</td>';
			}

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_topic_detail.firstElementChild.appendChild(new_tr_1);
			if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
					&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_remark != null) {
				new_tr_1.innerHTML = '<th>备注</th><td>'
						+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_remark
						+ '</td>';
			} else {
				new_tr_1.innerHTML = '<th>备注</th><td>无</td>';
			}

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_topic_detail.firstElementChild.appendChild(new_tr_1);
			if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
					&& topic_json.list_TopicInformationDTO[num].teacherInformationDTO.bysjglxtTeacherBasic.name != null) {
				new_tr_1.innerHTML = '<th>指导老师</th><td>'
						+ topic_json.list_TopicInformationDTO[num].teacherInformationDTO.bysjglxtTeacherBasic.name
						+ '</td>';
			} else {
				new_tr_1.innerHTML = '<th>指导老师</th><td>无</td>';
			}

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_topic_detail.firstElementChild.appendChild(new_tr_1);
			if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
					&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_num != null) {
				new_tr_1.innerHTML = '<th>已选学生数</th><td>'
						+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_num
						+ '</td>';
			} else {
				new_tr_1.innerHTML = '<th>已选学生数</th><td>无</td>';
			}
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_topic_detail.firstElementChild.appendChild(new_tr_1);
			if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_max != "-1") {
				new_tr_1.innerHTML = '<th>课题学生上限</th><td>'
						+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_max
						+ '</td>';
			} else {
				new_tr_1.innerHTML = '<th>课题学生上限</th><td>不限</td>';
			}
		}

	}

	/*
	 * 
	 */

}