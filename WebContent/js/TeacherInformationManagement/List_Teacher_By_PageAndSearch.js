window.onload = List_Teacher_By_PageAndSearch;

var teacher_json = null;

function List_Teacher_By_PageAndSearch(pageIndex) {

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
				teacher_json = JSON.parse(xhr.responseText);
				/*
				 * 清空原表数据
				 */
				var new_tr_list = document.getElementsByClassName("new_tr");
				var long = new_tr_list.length;

				for (var num = 0; num < long; num++) {
					new_tr_list[0].parentNode.removeChild(new_tr_list[0]);
				}
				/*
				 * 
				 */
				var table_teacher = document.getElementById("table_teacher");

				for (var num = 0; num < teacher_json.list_TeacherInformationDTO.length; num++) {

					var new_tr = document.createElement("tr");

					new_tr.appendChild(document.createTextNode(''));

					table_teacher.firstElementChild.appendChild(new_tr);

					new_tr.className = "new_tr";

					new_tr.innerHTML = '<td>'
							+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.job_number
							+ '</td><td>'
							+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.name
							+ '</td><td>'
							+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.sex
							+ '</td><td>'
							+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_section
							+ '</td><td>'
							+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.professional_title
							+ '</td><td style="padding: 0;"><button id="'
							+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_id
							+ '" onclick="Teacher_Information_Display(this)" style="margin:3px 0 0 0;"class="btn btn-default btn-ms">详细</button></td>'
							+ '<td>'
							+ '<label class="fancy-checkbox">'
							+ '<input id="'
							+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_id
							+ '" type="checkbox" class="checkbox_select">'
							+ '<span></span></label></td>';
				}

				/*
				 * 设置页数
				 */
				document.getElementById("span_pageIndex").innerHTML = teacher_json.pageIndex;
				document.getElementById("span_totalPages").innerHTML = teacher_json.totalPages;
				document.getElementById("span_totalRecords").innerHTML = teacher_json.totalRecords;

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
					"/bysjglxt/teacher/TeacherInformationManagement_ListTeacherByPageAndSearch");

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
		formData.append("teacherInformationManagementVO.search", search);
	}
	/*
	 * 
	 */
	// 性别
	var select_sex = document.getElementById("select_sex");
	if (select_sex.value == "男") {
		formData.append("teacherInformationManagementVO.sex", select_sex.value);
	} else if (select_sex.value == "女") {
		formData.append("teacherInformationManagementVO.sex", select_sex.value);
	}
	/*
	 * 
	 */
	formData.append("teacherInformationManagementVO.pageIndex", pageIndex);

	xhr.send(formData);

}
function flip(flipPage) {
	switch (flipPage) {
	case 1: {
		List_Teacher_By_PageAndSearch(1)
		break;
	}
	case 2: {
		if (teacher_json.pageIndex - 1 == 0) {
			toastr.warning("已经是第一页了");
		} else {
			List_Teacher_By_PageAndSearch(teacher_json.pageIndex - 1);
		}
		break;
	}
	case 3: {
		if (teacher_json.pageIndex == teacher_json.totalPages) {
			toastr.warning("已经是最后一页了");
		} else {
			List_Teacher_By_PageAndSearch(teacher_json.pageIndex + 1);
		}
		break;
	}
	case 4: {
		List_Teacher_By_PageAndSearch(teacher_json.totalPages);

		break;
	}

	}
}