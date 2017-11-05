var json_ProcessDefinition = null;
function ProcessDefinitionDetail() {
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
	document.querySelector('#maxDiv_ProcessDefinitionDetail div h3 span').innerHTML = "";
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
				json_ProcessDefinition = JSON.parse(xhr.responseText);
				document.getElementById("ProcessDefinitionTitle").innerHTM = json_ProcessDefinition.bysjglxtProcessDefinition.process_definition_name;

				for (var num = 0; num < json_ProcessDefinition.List_bysjglxtTaskDefinition.length; num++) {

				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST",
			"/bysjglxt/process/ProcessManagement_getProcessDefinitionDTO");

	xhr.send(null);

}