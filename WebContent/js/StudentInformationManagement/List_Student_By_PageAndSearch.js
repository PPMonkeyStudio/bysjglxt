var student_json = null;

function List_Student_By_PageAndSearch(pageIndex) {

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
				student_json = JSON.parse(xhr.responseText);
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
				var table_student = document.getElementById("table_student");
				var new_tr = null;
				var new_td = null;
				for (var num = 0; num < student_json.list_StudentInformationDTO.length; num++) {

					new_tr = document.createElement("tr");
					table_student.firstElementChild.appendChild(new_tr);
					new_tr.className = "new_tr";

					new_td = document.createElement("td");
					new_tr.appendChild(new_td);

					if (student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic != undefined
							&& student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_num != "") {
						new_td.innerHTML = student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_num;
					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					if (student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic != undefined
							&& student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_name != "") {
						new_td.innerHTML = student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_name;
					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					if (student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic != undefined
							&& student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_sex != "") {
						new_td.innerHTML = student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_sex;
					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					if (student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic != undefined
							&& student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_major != "") {
						new_td.innerHTML = student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_major;
					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					if (student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic != undefined
							&& student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_level != "") {
						new_td.innerHTML = student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_level;
					} else {
						new_td.innerHTML = '无';
					}

					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					if (student_json.list_StudentInformationDTO[num].bysjglxtStudentUser != undefined) {
						if (student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_is_operate_premission == 1) {
							new_td.innerHTML = '<span class="label label-danger">活动</span>';
						} else {
							new_td.innerHTML = '<span class="label label-default">已关闭</span>';
						}

					} else {
						new_td.innerHTML = '';
					}

					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					if (student_json.list_StudentInformationDTO[num].bysjglxtStudentUser != undefined) {
						if (student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_is_select_topic == 1) {
							new_td.innerHTML = '<span class="label label-primary">已选题</span>';
						} else {
							new_td.innerHTML = '<span class="label label-default">未选题</span>';
						}

					} else {
						new_td.innerHTML = '';
					}

					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					if(student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_is_operate_premission == 1){
						new_td.innerHTML = '<div class="dropdown" >'
							+ '<i  class="fa fa-ellipsis-v fa-2x" style="cursor: pointer;" id="dLabel" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></i>'
							+ '<ul class="dropdown-menu" aria-labelledby="dLabel">'
							+ '<li><a id="'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_id
							+ '" onclick="Student_Information_Display(this)" >详细基础信息</a></li>'
							+ '<li><a id="'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_id
							+ '" onclick="assignmentStudentTopic(this)">分配选题</a></li>'
							+ '<li><a id="'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_id
							+ '" onclick="distributionStudentMajor(this)">分配专业</a></li>'
							+ '</div>';
					}
					/*if (student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_is_select_topic == 2
							&& student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_is_operate_premission == 1) {
						
					} else {
						new_td.innerHTML = '<div class="dropdown" >'
								+ '<i  class="fa fa-ellipsis-v fa-2x" style="cursor: pointer;" id="dLabel" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></i>'
								+ '<ul class="dropdown-menu" aria-labelledby="dLabel">'
								+ '<li><a id="'
								+ student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_id
								+ '" onclick="Student_Information_Display(this)" >详细基础信息</a></li>'
								+ '<li><a id="'
								+ student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_id
								+ '" onclick="distributionStudentMajor(this)">分配专业</a></li>'
								+ '</div>';
					}*/

					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<label class="fancy-checkbox">'
							+ '<input id="'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_id
							+ '" type="checkbox" class="checkbox_select">'
							+ '<span></span></label>';

				}

				/*
				 * 设置页数
				 */
				document.getElementById("span_pageIndex").innerHTML = student_json.pageIndex;
				document.getElementById("span_totalPages").innerHTML = student_json.totalPages;
				document.getElementById("span_totalRecords").innerHTML = student_json.totalRecords;
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
					"/bysjglxt/student/StudentInformationManagement_ListStudentByPageAndSearch");

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
		formData.append("studentInformationManagementVO.search", search);
	}
	/*
	 * 
	 */
	// 性别
	var select_sex = document.getElementById("select_sex");
	if (select_sex.value == "男") {
		formData.append("studentInformationManagementVO.sex", select_sex.value);
	} else if (select_sex.value == "女") {
		formData.append("studentInformationManagementVO.sex", select_sex.value);
	}
	// 权限
	if (document.getElementById("select_premission").value == "1") {
		formData
				.append(
						"studentInformationManagementVO.user_student_is_operate_premission",
						document.getElementById("select_premission").value);
	} else if (document.getElementById("select_premission").value == "0") {
		formData
				.append(
						"studentInformationManagementVO.user_student_is_operate_premission",
						document.getElementById("select_premission").value);
	}
	// 选题
	if (document.getElementById("select_is_select_topic").value == "1") {
		formData.append(
				"studentInformationManagementVO.user_student_is_select_topic",
				document.getElementById("select_is_select_topic").value);
	} else if (document.getElementById("select_is_select_topic").value == "2") {
		formData.append(
				"studentInformationManagementVO.user_student_is_select_topic",
				document.getElementById("select_is_select_topic").value);
	}
	// 专业
	if (document.getElementById("select_major").value == "-1") {
	} else {
		formData.append("studentInformationManagementVO.student_basic_major",
				document.getElementById("select_major").value);
	}
	// 级别
	if (document.getElementById("select_level").value == "-1") {
	} else {
		formData.append("studentInformationManagementVO.student_basic_level",
				document.getElementById("select_level").value);
	}
	/*
	 * 
	 */
	formData.append("studentInformationManagementVO.pageIndex", pageIndex);

	xhr.send(formData);

}
function flip(flipPage) {
	switch (flipPage) {
	case 1: {
		List_Student_By_PageAndSearch(1)
		break;
	}
	case 2: {
		if (student_json.pageIndex - 1 == 0) {
			toastr.warning("已经是第一页了");
		} else {
			List_Student_By_PageAndSearch(student_json.pageIndex - 1);
		}
		break;
	}
	case 3: {
		if (student_json.pageIndex == student_json.totalPages) {
			toastr.warning("已经是最后一页了");
		} else {
			List_Student_By_PageAndSearch(student_json.pageIndex + 1);
		}
		break;
	}
	case 4: {
		List_Student_By_PageAndSearch(student_json.totalPages);

		break;
	}

	}
}