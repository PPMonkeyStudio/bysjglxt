var topicCurrentProcessDTO = null;
var json_topicCurrentProcessDTO = null;
function getTopicCurrentProcess() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				console.log("xhr.responseText:" + xhr.responseText);
				json_topicCurrentProcessDTO = xhr.responseText;
				topicCurrentProcessDTO = JSON.parse(xhr.responseText);
				var text_TopicCurrentProcess = document
						.getElementById("text_TopicCurrentProcess");
				var bar_TopicCurrentProcess = document
						.getElementById("bar_TopicCurrentProcess");
				if (json_topicCurrentProcessDTO == '{}') {
					text_TopicCurrentProcess.innerHTML = '<span class="label label-default">管理员未开启选题流程</span>';
					bar_TopicCurrentProcess.parentNode.style.display = 'none';
					return;
				} else {
					for (var num = 0; topicCurrentProcessDTO.listTaskBelongProcess.length; num++) {
						if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskInstance.task_instance_state == 1) {
							if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskDefinition.task_definition_name == "创建选题") {
								text_TopicCurrentProcess.innerHTML = '正在进行：<span class="label label-info">创建选题</span>';
								bar_TopicCurrentProcess.style.width = '20%';
								bar_TopicCurrentProcess.className = 'progress-bar progress-bar-info';
							} else if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskDefinition.task_definition_name == "公布选题") {
								text_TopicCurrentProcess.innerHTML = '正在进行：<span class="label label-primary">公布选题</span>';
								bar_TopicCurrentProcess.style.width = '40%';
								bar_TopicCurrentProcess.className = 'progress-bar progress-bar-primary';
							} else if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskDefinition.task_definition_name == "正式选题") {
								text_TopicCurrentProcess.innerHTML = '正在进行：<span class="label label-success">正式选题</span>';
								bar_TopicCurrentProcess.style.width = '60%';
								bar_TopicCurrentProcess.className = 'progress-bar progress-bar-success';
							} else if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskDefinition.task_definition_name == "分配选题") {
								text_TopicCurrentProcess.innerHTML = '正在进行：<span class="label label-warning">分配选题</span>';
								bar_TopicCurrentProcess.style.width = '80%';
								bar_TopicCurrentProcess.className = 'progress-bar progress-bar-warning';
							} else if (topicCurrentProcessDTO.listTaskBelongProcess[num].taskDefinition.task_definition_name == "完成选题") {
								text_TopicCurrentProcess.innerHTML = '正在进行：<span class="label label-danger">选题流程已结束，请开始毕业设计</span>';
								bar_TopicCurrentProcess.style.width = '100%';
								bar_TopicCurrentProcess.className = 'progress-bar progress-bar-danger';
							}
							break;
						}
					}
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST",
			"/bysjglxt/process/ProcessManagement_getTopicCurrentProcess");

	xhr.send();
}