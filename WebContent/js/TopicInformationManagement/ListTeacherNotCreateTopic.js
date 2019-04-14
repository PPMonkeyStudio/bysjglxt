function ListTeacherNotCreateTopic() {
	var jc = $.confirm({
				icon : 'fa fa-users',
				columnClass : 'col-md-12',
				theme : 'modern',
				title : '教师未创建选题情况',
				content : '<table id="table_TeacherNotTopic" class="table table-hover table-bordered" style="text-align: center; margin: 20px 0;">'
						+ '<tbody>'
						+ '<tr>'
						+ '<th>教师工号</th>'
						+ '<th>教师姓名</th>'
						+ '</tr>'
						+ '</tbody>'
						+ '</table>',
				type : 'orange',
				buttons : {
					返回 : function() {
					}
				},
				onContentReady : function() {
					list_TeacherTopicNotCreate();
				}
			});
}

function list_TeacherTopicNotCreate() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var teacher_topic_json = JSON.parse(xhr.responseText);
				/*
				 * 
				 */
				var new_tr_list = document.getElementsByClassName("new_tr_TeacherNotTopic");
				var long = new_tr_list.length;
				/*
				 * 
				 */
				var table_student = document.getElementById("table_TeacherNotTopic");
				var new_tr = null;
				var new_td = null;
				for (var num = 0; num < long; num++) {
					new_tr_list[0].parentNode.removeChild(new_tr_list[0]);
				}
				/*
				 * 
				 */
				for (var num = 0; num < teacher_topic_json.length; num++) {
					new_tr = document.createElement("tr");
					table_student.firstElementChild.appendChild(new_tr);
					new_tr.className = "new_tr_TeacherNotTopic";
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = teacher_topic_json[num].teacherUser.user_teacher_num;
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = teacher_topic_json[num].tacherBasic.name;
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	xhr.open("POST","/bysjglxt/topic/TopicInformationManagement_getTeacherNotTopicInfo");
	xhr.send(null);
}