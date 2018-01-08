var college_json = null;

function List_College() {

	/*
	 * 
	 */
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				console.debug(xhr.responseText);
				college_json = JSON.parse(xhr.responseText);
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
				var table_college = document.getElementById("table_college");
				var new_tr = null;
				var new_td = null;
				for (var num = 0; num < college_json.length; num++) {

					new_tr = document.createElement("tr");
					table_college.firstElementChild.appendChild(new_tr);
					new_tr.className = "new_tr";
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = college_json[num].college.college_name;
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					if (college_json[num].teacherInformationDTO == undefined
							|| college_json[num].teacherInformationDTO.bysjglxtTeacherBasic == undefined) {
						new_td.innerHTML = '无';
					} else {
						new_td.innerHTML = college_json[num].teacherInformationDTO.bysjglxtTeacherBasic.name;
					}

					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<div class="dropdown" >'
							+ '<i  class="fa fa-ellipsis-v fa-2x" style="cursor: pointer;" id="dLabel" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></i>'
							+ '<ul class="dropdown-menu" aria-labelledby="dLabel">'
							+ '<li><a id="'
							+ college_json[num].college.college_id
							+ '" onclick="Student_Information_Display(this)" >分配管理员</a></li>'
							+ '</div>';
				}

				// 让加载图标消失
				document.getElementById("i_pulse").style.display = "none";
				/*
				 * 角色控制
				 */
				roleControl();
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST", "/bysjglxt/college/CollegeManagement_listAllCollege");

	xhr.send(null);

}
