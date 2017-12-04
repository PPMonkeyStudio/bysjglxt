var topic_json = null;

var distributionTopicStudent_ID = null;

function show_distributionTopicStudent(button) {
	distributionTopicStudent_ID = button.id;
	$("#modal_distributionTopicStudent").modal("show");
}
function List_MyTopic_By_PageAndSearch(pageIndex) {
	document.getElementById("i_pulse").style.display = "block";
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				topic_json = JSON.parse(xhr.responseText);
				/*
				 * 清空原表数据
				 */
				var new_tr_list = document.getElementsByClassName("new_tr");
				var long = new_tr_list.length;
				for (var num = 0; num < long; num++) {
					new_tr_list[0].parentNode.removeChild(new_tr_list[0]);
				}

				var table_topic = document.getElementById("table_my_topic");
				var new_tr = null;
				var new_td = null;
				for (var num = 0; num < topic_json.list_TopicInformationDTO.length; num++) {

					new_tr = document.createElement("tr");
					new_tr.appendChild(document.createTextNode(''));
					table_topic.firstElementChild.appendChild(new_tr);
					new_tr.className = "new_tr";

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != undefined
							&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_chinese != "") {
						new_td.innerHTML = topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_name_chinese;
					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != undefined
							&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_source != "") {
						new_td.innerHTML = topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_source;
					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != undefined
							&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_type != "") {
						new_td.innerHTML = topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_type;
					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_max != "-1") {
						new_td.innerHTML = topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_num
								+ '/'
								+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_max;
					} else {
						new_td.innerHTML = topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_student_num;
					}

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (topic_json.list_TopicInformationDTO[num].teacherInformationDTO != undefined
							&& topic_json.list_TopicInformationDTO[num].teacherInformationDTO.bysjglxtTeacherBasic != "") {
						new_td.innerHTML = topic_json.list_TopicInformationDTO[num].teacherInformationDTO.bysjglxtTeacherBasic.name;
					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_td.className = "teacher_control";
					new_tr.appendChild(new_td);
					if (topic_json.list_TopicInformationDTO[num].bysjglxtTopic != undefined
							&& topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_examine_state != "") {
						switch (topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_examine_state) {
						case "未审核": {
							new_td.innerHTML = '<span class="label label-primary">'
									+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_examine_state
									+ '</span>';
							break;
						}
						case "审核已通过": {
							new_td.innerHTML = '<span class="label label-success">'
									+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_examine_state
									+ '</span>';
							break;
						}
						case "审核未通过": {
							new_td.innerHTML = '<span class="label label-danger">'
									+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_examine_state
									+ '</span>';
							break;
						}
						case "已关闭": {
							new_td.innerHTML = '<span class="label label-default">'
									+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_examine_state
									+ '</span>';
							break;
						}
						}

					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<button style="cursor: pointer;" id="'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_id
							+ '" onclick="MyTopic_Information_Display(this)" class="btn btn-default">详情</button>'
							+ '<button  style="cursor: pointer;" id="'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_id
							+ '" onclick="TopicDistributionStudentDisplay(this)" class="teacher_control btn btn-default">指定学生</button>';

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<label class="fancy-checkbox" >'
							+ '<input  id="'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_id
							+ '" type="checkbox" class="checkbox_select">'
							+ '<span></span></label></div>';

				}

				/*
				 * * 设置页数 /
				 */document.getElementById("span_pageIndex").innerHTML = topic_json.pageIndex;
				document.getElementById("span_totalPages").innerHTML = topic_json.totalPages;
				document.getElementById("span_totalRecords").innerHTML = topic_json.totalRecords;
				// 让加载图标消失
				document.getElementById("i_pulse").style.display = "none";

				// 让全选框取消选择
				document.getElementById("checkbox_all_select").checked = false;

				/*
				 * 角色控制
				 */
				roleControl();

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr
			.open("POST",
					"/bysjglxt/topic/TopicInformationManagement_ListMyTopicByPageAndSearch");

	var formData = new FormData();
	/*
	 * * /
	 */if (pageIndex == null || pageIndex.preventDefault) {
		pageIndex = 1;
	}
	/*
	 * * /
	 */var search = document.getElementById("input_search").value;
	if (search == undefined || search == null || search == "") {
	} else {
		formData.append("topicInformationManagementVO.search", search);
	}
	/*
	 * * 课题来源 /
	 */if (document.getElementById("select_source").value != "-1") {
		formData.append("topicInformationManagementVO.source", document
				.getElementById("select_source").value);
	}
	/*
	 * * 课题类型 /
	 */if (document.getElementById("select_type").value != "-1") {
		formData.append("topicInformationManagementVO.type", document
				.getElementById("select_type").value);
	}
	/*
	 * * 课题状态 /
	 */if (document.getElementById("select_state") != null
			&& document.getElementById("select_state").value != "-1") {
		formData.append("topicInformationManagementVO.state", document
				.getElementById("select_state").value);
	}

	/*
	 * * /
	 */
	/*
	 * * /
	 */formData.append("topicInformationManagementVO.pageIndex", pageIndex);

	xhr.send(formData);
}
function flip(flipPage) {
	switch (flipPage) {
	case 1: {
		List_Topic_By_PageAndSearch(1)
		break;
	}
	case 2: {
		if (topic_json.pageIndex - 1 == 0) {
			toastr.warning("已经是第一页了");
		} else {
			List_Topic_By_PageAndSearch(topic_json.pageIndex - 1);
		}
		break;
	}
	case 3: {
		if (topic_json.pageIndex == topic_json.totalPages) {
			toastr.warning("已经是最后一页了");
		} else {
			List_Topic_By_PageAndSearch(topic_json.pageIndex + 1);
		}
		break;
	}
	case 4: {
		List_Topic_By_PageAndSearch(topic_json.totalPages);

		break;
	}

	}
}