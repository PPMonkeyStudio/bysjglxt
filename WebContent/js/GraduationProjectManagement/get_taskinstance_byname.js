function get_taskinstance_byname(taskName){
	var xhr = false;
	var formData = new FormData();
	formData.append("taskInstanceName",taskName);
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var taskInstance = JSON.parse(xhr.responseText);
				k = taskInstance.task_instance_is_update;
				_userId_Task = taskInstance.task_instance_role;
				callBack(taskName);
			}
		}
	}
	xhr.open("POST","/bysjglxt/graduationProject/GraduationProjectManagement_getTaskInstance");
	xhr.send(formData);
}






















