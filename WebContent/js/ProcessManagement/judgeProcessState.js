function judgeProcessState(button_Process) {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				if(xhr.responseText == 'wu'){
					BootProcess(button_Process);
				}else{
					waringBootProcess();
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	var formData = new FormData();
	formData.append("processDefinitionId",button_Process.id);
	xhr.open("POST","/bysjglxt/process/ProcessManagement_judgeProcessState");
	xhr.send(formData);
}
function waringBootProcess(){
	$.confirm({
				title : '警告！',
				content : '该流程已经开启。若需重复开启，请先完成当前流程任务后再次开启流程',
				type : 'red',
				autoClose : '取消|5000',// 自动关闭
				buttons : {
					取消 : function() {
					}
				}
			});
}