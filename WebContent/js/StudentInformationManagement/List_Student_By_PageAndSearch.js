window.onload = List_Student_By_PageAndSearch;

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

				for (var num = 0; num < student_json.list_StudentInformationDTO.length; num++) {

					var new_tr = document.createElement("tr");

					new_tr.appendChild(document.createTextNode(''));
					table_student.firstElementChild.appendChild(new_tr);
					new_tr.className = "new_tr";
					new_tr.innerHTML = '<td>'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_num
							+ '</td><td>'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_name
							+ '</td><td>'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_sex
							+ '</td><td>'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_major
							+ '</td><td>'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_grade
							+ '</td><td>'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_is_operate_premission
							+ '</td><td style="padding: 0;"><button id="'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_id
							+ '" onclick="Student_Information_Display(this)" style="margin:3px 0 0 0;"class="btn btn-default">详细</button></td>'
							+ '<td>'
							+ '<label class="fancy-checkbox">'
							+ '<input id="'
							+ student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_id
							+ '" type="checkbox" class="checkbox_select">'
							+ '<span></span></label></td>';
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
	// 专业
	if (document.getElementById("select_major").value == "-1") {
	} else {
		formData.append("studentInformationManagementVO.student_basic_major",
				document.getElementById("select_major").value);
	}
	// 年级
	if (document.getElementById("select_grade").value == "-1") {
	} else {
		formData.append("studentInformationManagementVO.student_basic_grade",
				document.getElementById("select_grade").value);
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