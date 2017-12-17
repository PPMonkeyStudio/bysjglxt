
﻿function TopicDistributionStudentDisplay() {
	var jc = $
			.confirm({
				icon : 'fa fa-users',
				columnClass : 'col-md-12',
				theme : 'modern',
				title : '指定学生提前选题',
				content : '<table id="table_TopicDistributionStudent" class="table table-hover table-bordered" style="text-align: center; margin: 20px 0;">'
						+ '<tbody>'
						+ '<tr>'
						+ '<th>学号</th>'
						+ '<th>姓名</th>'
						+ '<th><select class="form-control" id="select_state" style="width: auto;margin:auto;"  onchange="(1)">'
						+ '<option value="-1">专业（全部）</option>'
						+ '</select></th>'
						+ '<th><select class="form-control" id="select_state" style="width: auto;margin:auto;"  onchange="(1)">'
						+ '<option value="-1">级别（全部）</option>'
						+ '</select></th>'
						+ '<th>已指定</th>'
						+ '<th>操作</th>'
						+ '</tr>' + '</tbody>' + '</table>',
				type : 'orange',
				buttons : {
					返回 : function() {
					}
				},
				onContentReady : function() {

					var xhr = false;
					xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						var message;
						if (xhr.readyState == 4) {
							if (xhr.status == 200) {
								var student_json = JSON.parse(xhr.responseText);
								/*
								 * 
								 */
								var new_tr_list = document
										.getElementsByClassName("new_tr_TopicDistributionStudent");
								var long = new_tr_list.length;
								/*
								 * 
								 */
								var table_student = document
										.getElementById("table_TopicDistributionStudent");
								var new_tr = null;
								var new_td = null;
								for (var num = 0; num < long; num++) {
									new_tr_list[0].parentNode
											.removeChild(new_tr_list[0]);
								}
								/*
								 * 
								 */
								for (var num = 0; num < student_json.length; num++) {

									new_tr = document.createElement("tr");
									table_student.firstElementChild
											.appendChild(new_tr);
									new_tr.className = "new_tr_TopicDistributionStudent";
									/*
									 * 
									 */
									new_td = document.createElement("td");
									new_tr.appendChild(new_td);
									new_td.innerHTML = student_json[num].bysjglxtStudentBasic.student_basic_num;
									/*
									 * 
									 */
									new_td = document.createElement("td");
									new_tr.appendChild(new_td);
									new_td.innerHTML = student_json[num].bysjglxtStudentBasic.student_basic_name;
									/*
									 * 
									 */
									new_td = document.createElement("td");
									new_tr.appendChild(new_td);
									new_td.innerHTML = student_json[num].bysjglxtStudentBasic.student_basic_major;
									/*
									 * 
									 */
									new_td = document.createElement("td");
									new_tr.appendChild(new_td);
									new_td.innerHTML = student_json[num].bysjglxtStudentBasic.student_basic_level;
									/*
									 * 
									 */
									new_td = document.createElement("td");
									new_tr.appendChild(new_td);
									if (new_td.innerHTML = student_json[num].bysjglxtStudentUser.user_student_is_select_topic == 1) {
										new_td.innerHTML = '✔';
									} else {
										new_td.innerHTML = '';
									}
									/*
									 * 
									 */
									new_td = document.createElement("td");
									new_tr.appendChild(new_td);
									new_td.innerHTML = '<button class="btn btn-default" id="'
											+ student_json[num].bysjglxtStudentUser.user_student_id
											+ '" onclick="DistributionStudent(this)" >指定</button>';
								}

							} else {
								toastr.error(xhr.status);
							}
						}
					}

					xhr
							.open("POST",
									"/bysjglxt/student/StudentInformationManagement_listStudentNoClose");
					xhr.send(null);

				}
			});
}
/*
 * 指定单个学生选此题
 */
function DistributionStudent() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
			}
		}
	}

	xhr
			.open("POST",
					"/bysjglxt/student/StudentInformationManagement_listStudentNoClose");
	xhr.send(null);

}