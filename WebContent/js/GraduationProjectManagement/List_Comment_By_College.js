var CollegeComment_json = null;

function List_Comment_By_College(pageIndex) {
	document.getElementById("i_pulse").style.display = "block";
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				CollegeComment_json = JSON.parse(xhr.responseText);
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
				var table_Comment = document.getElementById("table_Comment");
				var new_tr = null;
				var new_td = null;
				for (var num = 0; num < CollegeComment_json.listComment.length; num++) {
					/*
					 * 
					 */
					new_tr = document.createElement("tr");
					table_Comment.firstElementChild.appendChild(new_tr);
					new_tr.className = "new_tr";
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = CollegeComment_json.listComment[num].comment_content;
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = CollegeComment_json.listComment[num].comment_grade;
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = CollegeComment_json.listComment[num].comment_category;
					/*
					 * 选择
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<label class="fancy-checkbox">'
							+ '<input id="'
							+ CollegeComment_json.listComment[num].comment_id
							+ '" type="checkbox" class="checkbox_select">'
							+ '<span></span></label>';
					/*
					 * 
					 */
				}
				/*
				 * 设置页数
				 */
				document.getElementById("span_pageIndex").innerHTML = CollegeComment_json.pageIndex;
				document.getElementById("span_totalPages").innerHTML = CollegeComment_json.totalPages;
				document.getElementById("span_totalRecords").innerHTML = CollegeComment_json.totalRecords;
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
					"/bysjglxt/graduationProject/GraduationProjectManagement_listAllCommentCollege");

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
	formData.append("commentInformationVO.category", document
			.getElementById("select_category").value);
	/*
	 * 
	 */
	formData.append("commentInformationVO.grade", document
			.getElementById("select_grade").value);
	/*
	 * 
	 */
	formData.append("teacherManagementStudentVO.pageIndex", pageIndex);

	xhr.send(formData);
}