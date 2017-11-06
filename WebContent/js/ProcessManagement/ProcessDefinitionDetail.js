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
	document.querySelector('#maxDiv_ProcessDefinitionDetail div h3').innerHTML = "";
	document.querySelector('#maxDiv_ProcessDefinitionDetail .panel-body').innerHTML = "";
	/*
	 * 
	 */

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				alert(xhr.responseText);
				json_ProcessDefinition = JSON.parse(xhr.responseText);

				document
						.querySelector("#maxDiv_ProcessDefinitionDetail div h3").innerHTML = json_ProcessDefinition.bysjglxtProcessDefinition.process_definition_name;
				var new_div;
				for (var num = 0; num < json_ProcessDefinition.List_bysjglxtTaskDefinition.length; num++) {
					new_div = document.getElementsByClassName("div");
					new_div
							.appendChild(document
									.createTextNode(json_ProcessDefinition.List_bysjglxtTaskDefinition[num].task_definition_name));

					document.querySelector(
							'#maxDiv_ProcessDefinitionDetail .panel-body')
							.appendChild(new_div);
					new_div.className = "new_div";

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