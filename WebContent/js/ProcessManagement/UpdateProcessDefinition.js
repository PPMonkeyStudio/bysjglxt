function UpdateProcessDefinition(button_process_definition_id) {

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				ProcessDefinitionDetail(xhr.responseText);
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();
	alert(button_process_definition_id.id);
	formData.append("processDefinitionId", button_process_definition_id.id);
	xhr.open("POST",
			"/bysjglxt/process/ProcessManagement_UpdateProcessDefinition");
	xhr.send(formData);

}