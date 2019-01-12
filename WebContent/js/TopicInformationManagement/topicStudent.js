function topicStudent(obj) {
	$.confirm({
				icon : 'fa fa-users',
				columnClass : 'col-md-12',
				theme : 'modern',
				title : '选择该课题学生信息',
				content :
					`
					<div class="input-group" style="width: 1050px; float: right;margin:0 20px 20px 0;">
						<form id="overTheYearsTopic">
							<table id="table_select_student" class="table table-hover table-bordered" style="text-align: center; margin: 20px 0;">
								<tbody>
									<tr>
										<th>学号</th>
										<th>姓名</th>
										<th>专业</th>
										<th>班级</th>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					`,
				onContentReady : function() {
					$.ajax({
						type:'POST',
						url:'/bysjglxt/topic/TopicInformationManagement_getStudentByTopicId',
						dataType:"json",
						data:{
							"topicId":obj.id
						},
						success:function(data){
							var topic_json = data;
							/**
							 * 
							 */
							var new_tr_list = document.getElementsByClassName("new_tr_select_topic_student");
							var long = new_tr_list.length;
							/*
							 * 
							 */
							var table_topic = document.getElementById("table_select_student");
							var new_tr = null;
							var new_td = null;
							for (var num = 0; num < long; num++) {
								new_tr_list[0].parentNode.removeChild(new_tr_list[0]);
							}
							/**
							 * 
							 */
							for (var num = 0; num < topic_json.length; num++) {
								new_tr = document.createElement("tr");
								new_tr.appendChild(document.createTextNode(''));
								table_topic.firstElementChild.appendChild(new_tr);
								new_tr.className = "new_tr_select_topic_student";
								/*
								 * 
								 */
								new_td = document.createElement("td");
								new_tr.appendChild(new_td);
								if (topic_json != undefined
										&& topic_json[num].bysjglxtStudentBasic.student_basic_num != "") {
									new_td.innerHTML = topic_json[num].bysjglxtStudentBasic.student_basic_num;
								} else {
									new_td.innerHTML = '无';
								}
								new_td = document.createElement("td");
								new_td.appendChild(document.createTextNode(''));
								new_tr.appendChild(new_td);
								if (topic_json != undefined
										&& topic_json[num].bysjglxtStudentBasic.student_basic_name != "") {
									new_td.innerHTML = topic_json[num].bysjglxtStudentBasic.student_basic_name;
								} else {
									new_td.innerHTML = '无';
								}

								new_td = document.createElement("td");
								new_td.appendChild(document.createTextNode(''));
								new_tr.appendChild(new_td);
								if (topic_json != undefined
										&& topic_json[num].bysjglxtStudentBasic.student_basic_major != "") {
									new_td.innerHTML = topic_json[num].bysjglxtStudentBasic.student_basic_major;
								} else {
									new_td.innerHTML = '无';
								}
							
								new_td = document.createElement("td");
								new_td.appendChild(document.createTextNode(''));
								new_tr.appendChild(new_td);
								if (topic_json != undefined
										&& topic_json[num].bysjglxtStudentBasic.student_basic_class != "") {
									new_td.innerHTML = topic_json[num].bysjglxtStudentBasic.student_basic_class;
								} else {
									new_td.innerHTML = '无';
								}
								
							}
							
							
							
						}
					});
				},
				buttons:{
					返回 : function() {
					}
				}
			});
}