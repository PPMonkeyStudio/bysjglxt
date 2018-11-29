var button1 = null;
var ll = null;
function processController(obj) {
	button1 = obj;
	ll = $.confirm({
				icon : 'fa fa-users',
				columnClass : 'col-md-12',
				theme : 'modern',
				title : '流程控制',
				content :
					`
					<div class="input-group" style="width: 1050px; float: right;margin:0 20px 20px 0;">
						<table id="table_processController" class="table table-hover table-bordered" style="text-align: center; margin: 20px 0;">
							<tbody>
								<tr>
									<th>任务名称</th>
									<th>任务执行角色</th>
									<th>是否可更改</th>
									<th>更改状态</th>
								</tr>
							</tbody>
						</table>
					</div>
					`,
				onContentReady : listprocess,
				buttons:{
					返回 : function() {
					}
				}
			});
}
function listprocess(){
	$.ajax({
		type:'POST',
		url:'/bysjglxt/graduationProject/GraduationProjectManagement_getStudentGraduationProcess',
		dataType:"json",
		data:{"studentUserId":button1.id},
		success:function(data){
			var jsonData = data;
			/*
			 * 
			 */
			var new_tr_list = document.getElementsByClassName("new_tr_processController");
			var long = new_tr_list.length;
			/*
			 * 
			 */
			var table_student = document.getElementById("table_processController");
			var new_tr = null;
			var new_td = null;
			for (var num = 0; num < long; num++) {
				new_tr_list[0].parentNode.removeChild(new_tr_list[0]);
			}
			/*
			 * 
			 */
			for (var num = 0; num < jsonData.length; num++) {
				new_tr = document.createElement("tr");
				table_student.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr_processController";
				/*
				 * 
				 */
				new_td = document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = jsonData[num].taskDefinition.task_definition_name;
				/*
				 * 
				 */
				new_td = document.createElement("td");
				new_tr.appendChild(new_td);
				if (jsonData[num].taskDefinition.task_definition_role == 1) {
					new_td.innerHTML = '<span class="label label-primary">指导老师</span>';
				} else if (jsonData[num].taskDefinition.task_definition_role == 2) {
					new_td.innerHTML = '<span class="label label-info">评阅老师</span>';
				} else if (jsonData[num].taskDefinition.task_definition_role == 3) {
					new_td.innerHTML = '<span class="label label-danger">领导小组组长</span>';
				} else if (jsonData[num].taskDefinition.task_definition_role == 4) {
					new_td.innerHTML = '<span class="label label-warning">教研室主任</span>';
				} else {
					new_td.innerHTML = '<span class="label label-success">学生</span>';
				}
				/*
				 * 
				 */
				new_td = document.createElement("td");
				new_tr.appendChild(new_td);
				if (jsonData[num].taskInstance.task_instance_is_update == 1) {
					new_td.innerHTML = '✔';
				} else {
					new_td.innerHTML = '×';
				}
				/*
				 * 
				 */
				new_td = document.createElement("td");
				new_tr.appendChild(new_td);
				if (new_td.innerHTML = jsonData[num].taskInstance.task_instance_is_update == 1) {
					new_td.innerHTML = '<button class="btn btn-default" id="'+jsonData[num].taskInstance.task_instance_id+'" onclick="updateProcessController(this,-1)" >移出</button>';
				} else {
					new_td.innerHTML = '<button class="btn btn-default" id="'+jsonData[num].taskInstance.task_instance_id+'" onclick="updateProcessController(this,1)" >更改</button>';
				}
			}
		}
	});
}
function updateProcessController(obj,state){
	$.ajax({
		type:'POST',
		url:'/bysjglxt/graduationProject/GraduationProjectManagement_updateTaskInstaceState',
		dataType:'text',
		data:{"taskInstanceId":obj.id,"state":state},
		success:function(data){
			if(1==data){
				listprocess();
			}
		}
	});
}



