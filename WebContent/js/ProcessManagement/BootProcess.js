function BootProcess() {
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
	xhr.open("POST", "/bysjglxt/process/ProcessManagement_BootProcess");
	xhr.send(null);
}