var topic_json = null;

function List_Topic_By_PageAndSearch(pageIndex) {
	if (userStudentDTO != null) {
		if (json_topicCurrentProcessDTO == '{}') {
		} else {
			for (var num6667 = 0; num6667 < topicCurrentProcessDTO.listTaskBelongProcess.length; num6667++) {
				if (topicCurrentProcessDTO.listTaskBelongProcess[num6667].taskInstance.task_instance_state == 1) {
					if (topicCurrentProcessDTO.listTaskBelongProcess[num6667].taskDefinition.task_definition_name == "创建选题") {
						document.getElementById("div_TopicPage").parentNode
								.removeChild(document
										.getElementById("div_TopicPage"));
						return;
					}
				}
			}
		}
	}

	document.getElementById("i_pulse").style.display = "block";
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				topic_json = JSON.parse(xhr.responseText);
				/*
				 * 
				 * 
				 * 清空原表数据
				 */
				var new_tr_list = document.getElementsByClassName("new_tr");
				var long = new_tr_list.length;
				for (var num = 0; num < long; num++) {
					new_tr_list[0].parentNode.removeChild(new_tr_list[0]);
				}
				/*
				 * * /
				 */var table_topic = document.getElementById("table_topic");
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
					if (topic_json.list_TopicInformationDTO[num].teacherInformationDTO != undefined
							&& topic_json.list_TopicInformationDTO[num].teacherInformationDTO.bysjglxtTeacherBasic != "") {
						new_td.innerHTML = topic_json.list_TopicInformationDTO[num].teacherInformationDTO.bysjglxtTeacherBasic.name;
					} else {
						new_td.innerHTML = '无';
					}
					/**
					 * 实现要求
					 */
					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					new_td.innerHTML = topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_requirement;

					/**
					 * 备注
					 */
					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					new_td.innerHTML = topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_remark;
					
					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (userTeacherDTO != null
							&& userTeacherDTO.bysjglxtLeader != null) {
						new_td.innerHTML = '<button style="" id="'
								+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_id
								+ '" onclick="studentSelectTopic(this)" class="btn btn-default">详细</button>';
					} else {
						new_td.innerHTML = '<button style="" id="'
								+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_id
								+ '" onclick="studentSelectTopic(this)" class="btn btn-default">详细</button>';
					}
					if (userTeacherDTO != null && userTeacherDTO.bysjglxtTeacherUser.user_teacher_is_college_admin == 1){
						new_td = document.createElement("td");
						new_td.appendChild(document.createTextNode(''));
						new_tr.appendChild(new_td);
						new_td.innerHTML = '<label class="fancy-checkbox" >'
								+ '<input  id="'
								+ topic_json.list_TopicInformationDTO[num].bysjglxtTopic.topic_id
								+ '" type="checkbox" class="checkbox_select">'
								+ '<span></span></label></div>';
					}
				}

				/*
				 * * 设置页数 /
				 */
				document.getElementById("span_pageIndex").innerHTML = topic_json.pageIndex;
				document.getElementById("span_totalPages").innerHTML = topic_json.totalPages;
				document.getElementById("span_totalRecords").innerHTML = topic_json.totalRecords;
				// 让加载图标消失
				document.getElementById("i_pulse").style.display = "none";

				// 让全选框取消选择
				document.getElementById("checkbox_all_select").checked = false;

				/*
				 * * 角色控制 /
				 */roleControl();

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST","/bysjglxt/topic/TopicInformationManagement_ListTopicByPageAndSearch");

	var formData = new FormData();
	/*
	 * * /
	 */if (pageIndex == null || pageIndex.preventDefault) {
		pageIndex = 1;
	}
	/*
	 * 
	 */
	var search = document.getElementById("input_search").value;
	if (search == undefined || search == null || search == "") {
	} else {
		formData.append("topicInformationManagementVO.search", search);
	}
	/*
	 * 课题来源
	 */if (document.getElementById("select_source").value != "-1") {
		formData.append("topicInformationManagementVO.source", document
				.getElementById("select_source").value);
	}
	/*
	 * 课题类型
	 */if (document.getElementById("select_type").value != "-1") {
		formData.append("topicInformationManagementVO.type", document
				.getElementById("select_type").value);
	}
	/*
	 * 指导老师
	 */
	if (document.getElementById("select_teacher").value != "-1") {
		formData.append("topicInformationManagementVO.teacher", document
				.getElementById("select_teacher").value);
	}
	/*
	 * 
	 */
	formData.append("topicInformationManagementVO.pageIndex", pageIndex);
	
	//历年
	formData.append("topicInformationManagementVO.year","2");
	
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
