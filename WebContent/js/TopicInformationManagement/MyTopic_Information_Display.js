function MyTopic_Information_Display(this_button) {

	$("#modal_Topic_Information").modal("show");
	var new_tr_1 = null;
	var table_topic_detail = null;
	table_topic_detail = document.getElementById("table_topic_detail");
	table_topic_detail.firstElementChild.innerHTML = "";

	for (var num = 0; num < topic_json.list_TopicInformationDTO.length; num++) {

		if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_id == this_button.id) {
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_topic_detail.firstElementChild.appendChild(new_tr_1);
			new_tr_1.innerHTML = '<input id="input_topic_id" style="display:none;" class="form-control" value="'
					+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_id
					+ '"></input>';

			if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_examine_state == "未审核") {

				/*
				 * 可修改
				 */

				var button_updateTopic = document
						.getElementById("button_updateTopic");
				button_updateTopic.style.display = "block";
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				new_tr_1.innerHTML = '<th>课题中文名称</th><td><input style="text-align: center;" id="input_topic_name_chinese" class="form-control" value="'
						+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_chinese
						+ '"></input></td>';
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
						&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_english != null) {
					new_tr_1.innerHTML = '<th>课题英文名称</th><td><input style="text-align: center;" id="input_topic_name_english" class="form-control" value="'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_english
							+ '"></input></td>';
				} else {
					new_tr_1.innerHTML = '<th>课题英文名称</th><td><input style="text-align: center;" id="input_topic_name_english" class="form-control"></input></td>';
				}
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				new_tr_1.innerHTML = '<th>课题来源</th><td>'
						+ '<select class="form-control" style="width: auto;" id="select_topic_source" >'
						+ '<option id="option_source_1" value="各类课题项目">各类课题项目</option>'
						+ '<option id="option_source_2" value="导师指定">导师指定</option>'
						+ '<option id="option_source_3" value="题目指南">题目指南</option>'
						+ '<option id="option_source_4" value="自选">自选</option>'
						+ '<option id="option_source_5" value="其它">其它</option>'
						+ '</select>' + '</td>';
				switch (topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_source) {
				case "各类课题项目": {
					document.getElementById("option_source_1").selected = "selected";
					break;
				}
				case "导师指定": {
					document.getElementById("option_source_2").selected = "selected";
					break;
				}
				case "题目指南": {
					document.getElementById("option_source_3").selected = "selected";
					break;
				}
				case "自选": {
					document.getElementById("option_source_4").selected = "selected";
					break;
				}
				case "其它": {
					document.getElementById("option_source_5").selected = "selected";
					break;
				}
				}
				/*
				 * 课题性质
				 * topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_type
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				new_tr_1.innerHTML = '<th>课题性质</th><td>'
						+ '<select class="form-control" style="width: auto;" id="select_topic_type" >'
						+ '<option id="option_type_1" value="理论研究">理论研究</option>'
						+ '<option id="option_type_2" value="应用基础研究">应用基础研究</option>'
						+ '<option id="option_type_3" value="应用与理论结合研究">应用与理论结合研究</option>'
						+ '<option id="option_type_4" value="实际应用">实际应用</option>'
						+ '</select>' + '</td>';
				switch (topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_type) {
				case "理论研究": {
					document.getElementById("option_type_1").selected = "selected";
					break;
				}
				case "应用基础研究": {
					document.getElementById("option_type_2").selected = "selected";
					break;
				}
				case "应用与理论结合研究": {
					document.getElementById("option_type_3").selected = "selected";
					break;
				}
				case "实际应用": {
					document.getElementById("option_type_4").selected = "selected";
					break;
				}
				}
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
						&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_requirement != null) {
					new_tr_1.innerHTML = '<th>实现要求</th><td><input style="text-align: center;" id="input_topic_requirement" class="form-control" value="'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_requirement
							+ '"></input></td>';
				} else {
					new_tr_1.innerHTML = '<th>实现要求</th><td><input style="text-align: center;" id="input_topic_requirement" class="form-control"></input></td>';
				}
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
						&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_remark != null) {
					new_tr_1.innerHTML = '<th>备注</th><td><input style="text-align: center;" id="input_topic_remark" class="form-control" value="'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_remark
							+ '"></input></td>';
				} else {
					new_tr_1.innerHTML = '<th>备注</th><td><input style="text-align: center;" id="input_topic_remark" class="form-control"></input></td>';
				}
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				new_tr_1.innerHTML = '<th>指导老师</th><td>'
						+ topic_json.list_TopicInformationDTO[num].teacherInformationDTO.bysjglxtTeacherBasic.name
						+ '</td>';
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
						&& topic_json.list_TopicInformationDTO[num].bysjglxtTopicInviteTeacher.topic_invite_teacher_name != null) {
					new_tr_1.innerHTML = '<th>协作老师</th><td>'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopicInviteTeacher.topic_invite_teacher_name
							+ '</td>';
				} else {
					new_tr_1.innerHTML = '<th>协作老师</th><td></td>';
				}
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				new_tr_1.innerHTML = '<th>已选学生数</th><td>'
						+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_num
						+ '</td>';
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				new_tr_1.innerHTML = '<th>课题学生上限</th><td><input style="text-align: center;" id="input_topic_student_max" class="form-control" value="'
						+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_max
						+ '"></input></td>';
				/*
				 * 
				 */
				/*
				 * 
				 */
			} else {
				/*
				 * 不可修改
				 */
				var button_updateTopic = document
						.getElementById("button_updateTopic");
				button_updateTopic.style.display = "none";
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				new_tr_1.innerHTML = '<th>课题中文名称</th><td>'
						+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_chinese
						+ '</td>';
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
						&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_english != null) {

					new_tr_1.innerHTML = '<th>课题英文名称</th><td>'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_english
							+ '</td>';

				} else {
					new_tr_1.innerHTML = '<th>课题英文名称</th><td></td>';
				}
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
						&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_source != null) {
					new_tr_1.innerHTML = '<th>课题来源</th><td>'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_source
							+ '</td>';
				} else {
					new_tr_1.innerHTML = '<th>课题来源</th><td></td>';
				}
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
						&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_type != null) {
					new_tr_1.innerHTML = '<th>课题性质</th><td>'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_type
							+ '</td>';
				} else {
					new_tr_1.innerHTML = '<th>课题性质</th><td></td>';
				}
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
						&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_requirement != null) {
					new_tr_1.innerHTML = '<th>实现要求</th><td>'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_requirement
							+ '</td>';
				} else {
					new_tr_1.innerHTML = '<th>实现要求</th><td></td>';
				}
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
						&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_remark != null) {
					new_tr_1.innerHTML = '<th>备注</th><td>'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_remark
							+ '</td>';
				} else {
					new_tr_1.innerHTML = '<th>备注</th><td></td>';
				}
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				new_tr_1.innerHTML = '<th>指导老师</th><td>'
						+ topic_json.list_TopicInformationDTO[num].teacherInformationDTO.bysjglxtTeacherBasic.name
						+ '</td>';
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != null
						&& topic_json.list_TopicInformationDTO[num].bysjglxtTopicInviteTeacher.topic_invite_teacher_name != null) {
					new_tr_1.innerHTML = '<th>协作老师</th><td>'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopicInviteTeacher.topic_invite_teacher_name
							+ '</td>';
				} else {
					new_tr_1.innerHTML = '<th>协作老师</th><td></td>';
				}
				/*
				 * 
				 */
				new_tr_1 = document.createElement("tr");
				new_tr_1.appendChild(document.createTextNode(''));
				table_topic_detail.firstElementChild.appendChild(new_tr_1);
				new_tr_1.innerHTML = '<th>已选学生数</th><td>'
						+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_num
						+ '</td>';

				/*
				 * 
				 */
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
				/*
				 * 
				 */
			}

		}

	}

	/*
	 * 
	 */

}