window.onload = List_Topic_By_PageAndSearch;

var topic_json = null;

function List_Topic_By_PageAndSearch(pageIndex) {

	document.getElementById("i_pulse").style.display = "block";

	/*
	 * 
	 */
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				alert(xhr.responseText);
				topic_json = JSON.parse(xhr.responseText);
				/*
				 * 
				 * 清空原表数据
				 * 
				 */
				var new_tr_list = document.getElementsByClassName("new_tr");
				var long = new_tr_list.length;
				for (var num = 0; num < long; num++) {
					new_tr_list[0].parentNode.removeChild(new_tr_list[0]);
				}
				/*
				 * 
				 * 
				 */
				var table_topic = document.getElementById("table_topic");
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

					if (topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic != undefined
							&& topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic.topic_basic_num != "") {
						new_td.innerHTML = topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic.topic_basic_num;
					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic != undefined
							&& topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic.topic_basic_name != "") {
						new_td.innerHTML = topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic.topic_basic_name;
					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic != undefined
							&& topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic.topic_basic_sex != "") {
						new_td.innerHTML = topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic.topic_basic_sex;
					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic != undefined
							&& topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic.topic_basic_major != "") {
						new_td.innerHTML = topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic.topic_basic_major;
					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic != undefined
							&& topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic.topic_basic_grade != "") {
						new_td.innerHTML = topic_json.list_TopicInformationDTO[num].bysjglxtTopicBasic.topic_basic_grade;
					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (topic_json.list_TopicInformationDTO[num].bysjglxtTopicUser != undefined) {
						if (topic_json.list_TopicInformationDTO[num].bysjglxtTopicUser.user_topic_is_operate_premission == 1) {
							new_td.innerHTML = '✔';
						} else {
							new_td.innerHTML = '✘';
							new_td.style.color = "#ff5063";
						}

					} else {
						new_td.innerHTML = '';
					}

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<i style="cursor: pointer;" id="'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopicUser.user_topic_id
							+ '" onclick="Topic_Information_Display(this)" class="fa fa-edit "></i>';

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<label class="fancy-checkbox">'
							+ '<input id="'
							+ topic_json.list_TopicInformationDTO[num].bysjglxtTopicUser.user_topic_id
							+ '" type="checkbox" class="checkbox_select">'
							+ '<span></span></label>';

				}

				/*
				 * 设置页数
				 */
				document.getElementById("span_pageIndex").innerHTML = topic_json.pageIndex;
				document.getElementById("span_totalPages").innerHTML = topic_json.totalPages;
				document.getElementById("span_totalRecords").innerHTML = topic_json.totalRecords;
				// 让加载图标消失
				document.getElementById("i_pulse").style.display = "none";
				// 让全选框取消选择
				document.getElementById("checkbox_all_select").checked = false;
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr
			.open("POST",
					"/bysjglxt/topic/TopicInformationManagement_ListTopicByPageAndSearch");

	var formData = new FormData();
	/*
	 * 
	 */
	if (pageIndex == null || pageIndex.preventDefault) {
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
	 * 
	 */
	// 性别
	var select_sex = document.getElementById("select_sex");
	if (select_sex.value == "男") {
		formData.append("topicInformationManagementVO.sex", select_sex.value);
	} else if (select_sex.value == "女") {
		formData.append("topicInformationManagementVO.sex", select_sex.value);
	}
	// 权限
	if (document.getElementById("select_premission").value == "1") {
		formData
				.append(
						"topicInformationManagementVO.user_topic_is_operate_premission",
						document.getElementById("select_premission").value);
	} else if (document.getElementById("select_premission").value == "0") {
		formData
				.append(
						"topicInformationManagementVO.user_topic_is_operate_premission",
						document.getElementById("select_premission").value);
	}
	// 专业
	if (document.getElementById("select_major").value == "-1") {
	} else {
		formData.append("topicInformationManagementVO.topic_basic_major",
				document.getElementById("select_major").value);
	}
	// 年级
	if (document.getElementById("select_grade").value == "-1") {
	} else {
		formData.append("topicInformationManagementVO.topic_basic_grade",
				document.getElementById("select_grade").value);
	}
	/*
	 * 
	 */
	formData.append("topicInformationManagementVO.pageIndex", pageIndex);

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