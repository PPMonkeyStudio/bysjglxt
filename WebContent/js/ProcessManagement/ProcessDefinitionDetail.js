var json_ProcessDefinition = null;

/*
 * 流程定义详细信息
 */
function ProcessDefinitionDetail(ProcessDefinitionID) {
	/*
	 * 显示/隐藏
	 */
	var maxDiv_List_ProcessDefinition = document
			.getElementById("maxDiv_List_ProcessDefinition");
	maxDiv_List_ProcessDefinition.style.display = "none";
	var maxDiv_ProcessDefinitionDetail = document
			.getElementById("maxDiv_ProcessDefinitionDetail");
	maxDiv_ProcessDefinitionDetail.style.display = "block";
	/*
	 * 清空标题 清空内容
	 */
	document.querySelector('#maxDiv_ProcessDefinitionDetail div h3').innerHTML = '';
	document.querySelector('#maxDiv_ProcessDefinitionDetail .panel-body').innerHTML = '<table id="" class="table table-hover "'
			+ 'style="text-align: center; margin: 20px 0;">'
			+ '<tbody>'
			+ '<tr>'
			+ '<th>序号</th>'
			+ '<th>任务定义名称</th>'
			+ '<th>任务类型</th>'
			+ '<th>任务执行角色</th>'
			+ '<th>返回的任务节点</th>'
			+ '</tr>'
			+ '</tbody>'
			+ '</table>';
	/*
	 * 
	 */

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				json_ProcessDefinition = JSON.parse(xhr.responseText);
				document
						.querySelector("#maxDiv_ProcessDefinitionDetail div h3").innerHTML = json_ProcessDefinition.bysjglxtProcessDefinition.process_definition_name;
				var new_tr;
				var new_td;
				for (var num = 0; num < json_ProcessDefinition.List_bysjglxtTaskDefinition.length; num++) {

					new_tr = document.createElement("tr");

					new_tr.appendChild(document.createTextNode(''));
					document
							.querySelector(
									'#maxDiv_ProcessDefinitionDetail .panel-body table tbody')
							.appendChild(new_tr);

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_td.innerHTML = num + 1;
					new_tr.appendChild(new_td);

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_td.innerHTML = json_ProcessDefinition.List_bysjglxtTaskDefinition[num].task_definition_name;
					new_tr.appendChild(new_td);

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					if (json_ProcessDefinition.List_bysjglxtTaskDefinition[num].task_definition_type == 1) {
						new_td.innerHTML = '顺序结构';
					} else {
						new_td.innerHTML = '选择结构';
					}
					new_tr.appendChild(new_td);

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					if (json_ProcessDefinition.List_bysjglxtTaskDefinition[num].task_definition_role == 1) {
						new_td.innerHTML = '指导老师';
					} else if (json_ProcessDefinition.List_bysjglxtTaskDefinition[num].task_definition_role == 2) {
						new_td.innerHTML = '评阅老师';
					} else if (json_ProcessDefinition.List_bysjglxtTaskDefinition[num].task_definition_role == 3) {
						new_td.innerHTML = '领导小组组长';
					} else if (json_ProcessDefinition.List_bysjglxtTaskDefinition[num].task_definition_role == 4) {
						new_td.innerHTML = '教研室主任';
					} else {
						new_td.innerHTML = '学生';
					}
					new_tr.appendChild(new_td);

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_td.innerHTML = json_ProcessDefinition.List_bysjglxtTaskDefinition[num].task_definition_return;
					new_tr.appendChild(new_td);

				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	var formData = new FormData();
	formData.append("processDefinitionId", ProcessDefinitionID);
	xhr.open("POST",
			"/bysjglxt/process/ProcessManagement_getProcessDefinitionDTO");

	xhr.send(formData);

}