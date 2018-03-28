function Start_GraduationProject_All() {

	var button_Start_GraduationProject_All = document
			.getElementById("button_Start_GraduationProject_All");
	button_Start_GraduationProject_All.disabled = "disabled";
	button_Start_GraduationProject_All.innerHTML = '<i class="fa fa-refresh fa-spin"></i> 正在开启所有符合条件的毕业设计流程...';

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				toastr.success("已开启所有符合条件的毕业设计流程", "操作成功");
				//
				button_Start_GraduationProject_All.disabled = "";
				button_Start_GraduationProject_All.innerHTML = '<i class="fa fa-bug"></i>开启所有符合条件的毕业设计流程';
				// 
				List_MyManagementGraduationProject_By_PageAndSearch(1);
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	xhr.open("POST", "/bysjglxt/process/ProcessManagement_openGraduProcess");
	xhr.send(null);
}