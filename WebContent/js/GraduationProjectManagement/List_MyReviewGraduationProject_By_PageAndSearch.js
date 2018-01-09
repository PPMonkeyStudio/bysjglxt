var myReviewGraduationProject_json = null;

function List_MyReviewGraduationProject_By_PageAndSearch(pageIndex) {

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
				console.debug("我评阅的毕业设计：" + xhr.responseText);
				myReviewGraduationProject_json = JSON.parse(xhr.responseText);
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
				var table_myReviewGraduationProject = document
						.getElementById("table_myReviewGraduationProject");
				var new_tr = null;
				var new_td = null;
				for (var num = 0; num < myReviewGraduationProject_json.list_TeacherTutorStudentDTO.length; num++) {

					new_tr = document.createElement("tr");
					new_tr.appendChild(document.createTextNode(''));
					table_myReviewGraduationProject.firstElementChild
							.appendChild(new_tr);
					new_tr.className = "new_tr";
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = myReviewGraduationProject_json.list_TeacherTutorStudentDTO[num].studentInformationDTO.bysjglxtStudentUser.user_student_num;
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = myReviewGraduationProject_json.list_TeacherTutorStudentDTO[num].studentInformationDTO.bysjglxtStudentBasic.student_basic_name;
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = myReviewGraduationProject_json.list_TeacherTutorStudentDTO[num].bysjglxtTopic.topic_name_chinese;

					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);

					if (myReviewGraduationProject_json.list_TeacherTutorStudentDTO[num].taskDTO == null
							|| myReviewGraduationProject_json.list_TeacherTutorStudentDTO[num].taskDTO.taskInstance == null) {
						new_td.innerHTML = '<span class="label label-primary">未开始</span>';
					} else if (myReviewGraduationProject_json.list_TeacherTutorStudentDTO[num].taskDTO.taskInstance.task_instance_state == "1") {
						new_td.innerHTML = '<span class="label label-danger">活动</span>';
					} else if (myReviewGraduationProject_json.list_TeacherTutorStudentDTO[num].taskDTO.taskInstance.task_instance_state == "2") {
						new_td.innerHTML = '<span class="label label-dafaule">结束</span>';
					}
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);

					if (myReviewGraduationProject_json.list_TeacherTutorStudentDTO[num].taskDTO == null
							|| myReviewGraduationProject_json.list_TeacherTutorStudentDTO[num].taskDTO.taskDefinition == null) {
						new_td.innerHTML = '<span class="label label-primary">未开始</span>';
					} else {
						new_td.innerHTML = '<span class="label label-success">'
								+ myReviewGraduationProject_json.list_TeacherTutorStudentDTO[num].taskDTO.taskDefinition.task_definition_name
								+ '</span>';
					}
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					if (myReviewGraduationProject_json.list_TeacherTutorStudentDTO[num].taskDTO == null
							|| myReviewGraduationProject_json.list_TeacherTutorStudentDTO[num].taskDTO.taskInstance == null) {
						new_td.innerHTML = '';
					} else {
						new_td.innerHTML = '<button  onclick="window.location = \'\/bysjglxt\/graduationProject\/GraduationProjectManagement_MyGraduationProjectPage?MyTutorGraduationProjectStudentID='
								+ myReviewGraduationProject_json.list_TeacherTutorStudentDTO[num].studentInformationDTO.bysjglxtStudentUser.user_student_id
								+ '\'" class="btn btn-default">毕业设计</button>';

					}

				}
				/*
				 * 设置页数
				 */
				document.getElementById("span_pageIndex").innerHTML = myReviewGraduationProject_json.pageIndex;
				document.getElementById("span_totalPages").innerHTML = myReviewGraduationProject_json.totalPages;
				document.getElementById("span_totalRecords").innerHTML = myReviewGraduationProject_json.totalRecords;
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
					"/bysjglxt/graduationProject/GraduationProjectManagement_listMyReviewGraduationProjectByPageAndSearch");

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
		formData.append("teacherTutorStudentVO.search", search);
	}
	/*
	 * 
	 */
	formData.append("teacherTutorStudentVO.state", document
			.getElementById("select_state").value);
	/*
	 * 
	 */
	formData.append("teacherTutorStudentVO.pageIndex", pageIndex);

	xhr.send(formData);

}
function flip(flipPage) {
	switch (flipPage) {
	case 1: {
		List_MyReviewGraduationProject_By_PageAndSearch(1)
		break;
	}
	case 2: {
		if (myReviewGraduationProject_json.pageIndex - 1 == 0) {
			toastr.warning("已经是第一页了");
		} else {
			List_MyReviewGraduationProject_By_PageAndSearch(myReviewGraduationProject_json.pageIndex - 1);
		}
		break;
	}
	case 3: {
		if (myReviewGraduationProject_json.pageIndex == myReviewGraduationProject_json.totalPages) {
			toastr.warning("已经是最后一页了");
		} else {
			List_MyReviewGraduationProject_By_PageAndSearch(myReviewGraduationProject_json.pageIndex + 1);
		}
		break;
	}
	case 4: {
		List_MyReviewGraduationProject_By_PageAndSearch(myReviewGraduationProject_json.totalPages);

		break;
	}

	}
}