var myManagementGraduationProject_json = null;

function List_MyManagementGraduationProject_By_PageAndSearch(pageIndex) {

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
				console.debug("我管理的毕业设计：" + xhr.responseText);
				myManagementGraduationProject_json = JSON
						.parse(xhr.responseText);
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
				 */
				var table_myManagementGraduationProject = document
						.getElementById("table_myManagementGraduationProject");
				var new_tr = null;
				var new_td = null;
				for (var num = 0; num < myManagementGraduationProject_json.list_TeacherManagementStudentDTO.length; num++) {

					new_tr = document.createElement("tr");
					new_tr.appendChild(document.createTextNode(''));
					table_myManagementGraduationProject.firstElementChild
							.appendChild(new_tr);
					new_tr.className = "new_tr";
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = myManagementGraduationProject_json.list_TeacherManagementStudentDTO[num].studentInformationDTO.bysjglxtStudentBasic.student_basic_num;
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = myManagementGraduationProject_json.list_TeacherManagementStudentDTO[num].studentInformationDTO.bysjglxtStudentBasic.student_basic_name;
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = myManagementGraduationProject_json.list_TeacherManagementStudentDTO[num].bysjglxtTopic.topic_name_chinese;

					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);

					if (myManagementGraduationProject_json.list_TeacherManagementStudentDTO[num].taskDTO == null
							|| myManagementGraduationProject_json.list_TeacherManagementStudentDTO[num].taskDTO.taskInstance == null) {
						new_td.innerHTML = '<span class="label label-primary">未开始</span>';
					} else if (myManagementGraduationProject_json.list_TeacherManagementStudentDTO[num].taskDTO.taskInstance.task_instance_state == "1") {
						new_td.innerHTML = '<span class="label label-danger">活动</span>';
					} else if (myManagementGraduationProject_json.list_TeacherManagementStudentDTO[num].taskDTO.taskInstance.task_instance_state == "2") {
						new_td.innerHTML = '<span class="label label-dafaule">结束</span>';
					}
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);

					if (myManagementGraduationProject_json.list_TeacherManagementStudentDTO[num].taskDTO == null
							|| myManagementGraduationProject_json.list_TeacherManagementStudentDTO[num].taskDTO.taskDefinition == null) {
						new_td.innerHTML = '<span class="label label-primary">未开始</span>';
					} else {
						new_td.innerHTML = '<span class="label label-success">'
								+ myManagementGraduationProject_json.list_TeacherManagementStudentDTO[num].taskDTO.taskDefinition.task_definition_name
								+ '</span>';
					}
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					if (myManagementGraduationProject_json.list_TeacherManagementStudentDTO[num].taskDTO == null
							|| myManagementGraduationProject_json.list_TeacherManagementStudentDTO[num].taskDTO.taskInstance == null) {
						new_td.innerHTML = '';
					} else {
						new_td.innerHTML = '<button  onclick="window.location = \'\/bysjglxt\/graduationProject\/GraduationProjectManagement_MyGraduationProjectPage?MyManagementGraduationProjectStudentID='
								+ myManagementGraduationProject_json.list_TeacherManagementStudentDTO[num].studentInformationDTO.bysjglxtStudentUser.user_student_id
								+ '\'" class="btn btn-default">毕业设计</button>';
					}
				}
				/*
				 * 设置页数
				 */
				document.getElementById("span_pageIndex").innerHTML = myManagementGraduationProject_json.pageIndex;
				document.getElementById("span_totalPages").innerHTML = myManagementGraduationProject_json.totalPages;
				document.getElementById("span_totalRecords").innerHTML = myManagementGraduationProject_json.totalRecords;
				// 让加载图标消失
				document.getElementById("i_pulse").style.display = "none";
				// 让全选框取消选择
				// document.getElementById("checkbox_all_select").checked =
				// false;
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
			.open(
					"POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_ListMyManagementGraduationProjectByPageAndSearch");

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
		formData.append("teacherManagementStudentVO.search", search);
	}
	/*
	 * 
	 */
	formData.append("teacherManagementStudentVO.state", document
			.getElementById("select_state").value);
	/*
	 * 
	 */
	formData.append("teacherManagementStudentVO.pageIndex", pageIndex);

	xhr.send(formData);

}
function flip(flipPage) {
	switch (flipPage) {
	case 1: {
		List_MyManagementGraduationProject_By_PageAndSearch(1)
		break;
	}
	case 2: {
		if (myManagementGraduationProject_json.pageIndex - 1 == 0) {
			toastr.warning("已经是第一页了");
		} else {
			List_MyManagementGraduationProject_By_PageAndSearch(myManagementGraduationProject_json.pageIndex - 1);
		}
		break;
	}
	case 3: {
		if (myManagementGraduationProject_json.pageIndex == myManagementGraduationProject_json.totalPages) {
			toastr.warning("已经是最后一页了");
		} else {
			List_MyManagementGraduationProject_By_PageAndSearch(myManagementGraduationProject_json.pageIndex + 1);
		}
		break;
	}
	case 4: {
		List_MyManagementGraduationProject_By_PageAndSearch(myManagementGraduationProject_json.totalPages);

		break;
	}

	}
}