function TopicDistributionStudentDisplay(obj) {
	var jc = $
			.confirm({
				icon : 'fa fa-users',
				columnClass : 'col-md-12',
				theme : 'modern',
				title : '指定学生提前选题',
				content : '<div class="input-group" style="width: 300px; float: right;margin:0 20px 20px 0;">'
						+ '<input id="'
						+ obj.id
						+ '" class="form-control input_search_TopicDistributionStudent" oninput="list_DistributionStudent(this.id)" type="text">'
						+ '<span class="input-group-addon">'
						+ '<i class="fa fa-search"></i>'
						+ '</span>'
						+ '</div>'
						+ '<table id="table_TopicDistributionStudent" class="table table-hover table-bordered" style="text-align: center; margin: 20px 0;">'
						+ '<tbody>'
						+ '<tr>'
						+ '<th>学号</th>'
						+ '<th>姓名</th>'
						+ '<th>已指定</th>'
						+ '<th>操作</th>'
						+ '</tr>'
						+ '</tbody>'
						+ '</table>',
				type : 'orange',
				buttons : {
					确定 : function() {
					}
				},
				onContentReady : function() {
					list_DistributionStudent(obj.id);
				}
			});
}

function list_DistributionStudent(topic) {
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
					new_tr_list[0].parentNode.removeChild(new_tr_list[0]);
				}
				/*
				 * 
				 */
				for (var num = 0; num < student_json.length; num++) {

					new_tr = document.createElement("tr");
					table_student.firstElementChild.appendChild(new_tr);
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
					if (new_td.innerHTML = student_json[num].designation != 1) {
						new_td.innerHTML = '✔';
					} else {
						new_td.innerHTML = '';
					}
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					if (new_td.innerHTML = student_json[num].designation != 1) {
						new_td.innerHTML = '<button class="btn btn-default" id="'
								+ student_json[num].bysjglxtStudentUser.user_student_id
								+ '_'
								+ topic
								+ '" onclick="DistributionStudent(this)" >移出</button>';
					} else {
						new_td.innerHTML = '<button class="btn btn-default" id="'
								+ student_json[num].bysjglxtStudentUser.user_student_id
								+ '_'
								+ topic
								+ '" onclick="DistributionStudent(this)" >指定</button>';
					}

				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	var formData = new FormData();
	var search = document
			.getElementsByClassName("input_search_TopicDistributionStudent")[0].value;
	if (search == undefined || search == null || search == "") {
	} else {
		formData.append("search", search);
	}
	formData.append("studentSelectTopic", topic);

	xhr
			.open("POST",
					"/bysjglxt/topic/TopicInformationManagement_listDesignationStudentInformation");
	xhr.send(formData);
}
/*
 * 指定单个学生选此题
 */
function DistributionStudent(student_topic) {
	if (json_topicCurrentProcessDTO == '{}') {
		toastr.error("管理员未开启选题的流程");
		return;
	} else {
		for (var num = 0; topicCurrentProcessDTO.listTaskBelongProcess.length; num++) {
			if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskInstance.task_instance_state == 1) {
				if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskDefinition.task_definition_name != "公布选题") {
					toastr.error("还未到公布选题的时间");
					return;
				}
				break;
			}
		}
		var str_student_topic = student_topic.id.split("_");
		var xhr = false;
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			var message;
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					if (xhr.responseText == 'success') {
						toastr.success("操作成功");
						list_DistributionStudent(str_student_topic[1])
					}
				}
			}
		}

		var formData = new FormData();

		formData.append("studentID", str_student_topic[0]);
		formData.append("studentSelectTopic", str_student_topic[1]);
		xhr
				.open("POST",
						"/bysjglxt/topic/TopicInformationManagement_distributionTopicStudent");
		xhr.send(formData);
	}
}