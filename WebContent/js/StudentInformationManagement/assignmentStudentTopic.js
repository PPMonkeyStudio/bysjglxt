var button1 = null;
var ll = null;
function assignmentStudentTopic(obj) {
	button1 = obj;
	ll = $.confirm({
				icon : 'fa fa-users',
				columnClass : 'col-md-12',
				theme : 'modern',
				title : '分配课题给学生',
				content :
					`
					<div class="input-group" style="width: 1050px; float: right;margin:0 20px 20px 0;">
						<form id="overTheYearsTopic">
							<table id="table_overTheYearsTopic" class="table table-hover table-bordered" style="text-align: center; margin: 20px 0;">
								<tbody>
									<tr>
										<th>中文名称</th>
										<th>课题来源</th>
										<th>课题性质</th>
										<th>指导老师</th>
										<th>操作</th>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					`,
				onContentReady : function() {
					$.ajax({
						type:'POST',
						url:'/bysjglxt/topic/TopicInformationManagement_listSelectBysjglxtTopic',
						dataType:"json",
						data:null,
						success:function(data){
							var topic_json = data;
							/**
							 * 
							 */
							var new_tr_list = document.getElementsByClassName("new_tr_topic");
							var long = new_tr_list.length;
							/*
							 * 
							 */
							var table_topic = document.getElementById("table_overTheYearsTopic");
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
								new_tr.className = "new_tr_topic";
								/*
								 * 
								 */
								new_td = document.createElement("td");
								new_tr.appendChild(new_td);
								if (topic_json != undefined
										&& topic_json[num].bysjglxtTopic.topic_name_chinese != "") {
									new_td.innerHTML = topic_json[num].bysjglxtTopic.topic_name_chinese;
								} else {
									new_td.innerHTML = '无';
								}

								new_td = document.createElement("td");
								new_td.appendChild(document.createTextNode(''));
								new_tr.appendChild(new_td);
								if (topic_json != undefined
										&& topic_json[num].bysjglxtTopic.topic_source != "") {
									new_td.innerHTML = topic_json[num].bysjglxtTopic.topic_source;
								} else {
									new_td.innerHTML = '无';
								}

								new_td = document.createElement("td");
								new_td.appendChild(document.createTextNode(''));
								new_tr.appendChild(new_td);
								if (topic_json != undefined
										&& topic_json[num].bysjglxtTopic.topic_type != "") {
									new_td.innerHTML = topic_json[num].bysjglxtTopic.topic_type;
								} else {
									new_td.innerHTML = '无';
								}
								new_td = document.createElement("td");
								new_td.appendChild(document.createTextNode(''));
								new_tr.appendChild(new_td);
								if (topic_json != undefined
										&& topic_json[num].teacherInformationDTO.bysjglxtTeacherBasic.name != "") {
									new_td.innerHTML = topic_json[num].teacherInformationDTO.bysjglxtTeacherBasic.name;
								}
								/**
								 * 
								 * 
								 */
								new_td = document.createElement("td");
								new_td.appendChild(document.createTextNode(''));
								new_tr.appendChild(new_td);
								new_td.innerHTML = '<button type="button" class="btn btn-blue" id="' + topic_json[num].bysjglxtTopic.topic_id +'" onclick="assign(this)" >选择</button>';
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
function assign(obj){
	$.ajax({
		type:'POST',
		url:'/bysjglxt/process/ProcessManagement_getTopicCurrentProcess',
		dataType:"json",
		success:function(data){
			var topicCurrentProcessDTO = data;
			if (topicCurrentProcessDTO == null || topicCurrentProcessDTO.listTaskBelongProcess == null) {
				toastr.error("管理员未开启选题的流程");
				return;
			}else {
				for (var num = 0; topicCurrentProcessDTO.listTaskBelongProcess.length; num++) {
					if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskInstance.task_instance_state == 1) {
						if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskDefinition.task_definition_name != "分配选题") {
							toastr
									.error("还未到分配选题的时间");
							return;
						}
						break;
					}
				}
				var formData = new FormData();
				formData.append("assignmentTopicId",obj.id);
				formData.append("assignmentStudentUserId",button1.id);
				$.ajax({
					type:'POST',
					url:'/bysjglxt/topic/TopicInformationManagement_assignmentStudentTopic',
				    processData: false,
				    contentType: false,
					dataType:"text",
					data:formData,
					success:function(data){
						ll.close();
						List_Student_By_PageAndSearch(student_json.pageIndex);
					},
					error:function(){
						toastr.error("出现错误");
					}
				});
			}
		},
		error:function(){
			toastr.error("后台系统出错");
		}
	});
}


