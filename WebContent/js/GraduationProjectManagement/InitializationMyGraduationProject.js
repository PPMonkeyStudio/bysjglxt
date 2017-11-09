var currentProcessDto = null;
var current_processDefinitionName = null;
function InitializationMyGraduationProject() {
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				currentProcessDto = JSON.parse(xhr.responseText);
				for (var num = 0; currentProcessDto.listTaskBelongProcess.length; num++) {
					if (currentProcessDto.listTaskBelongProcess[num].taskInstance.task_instance_state == 1) {
						current_processDefinitionName = currentProcessDto.listTaskBelongProcess[num].taskDefinition.task_definition_name;
						break;
					}
				}

				switch (current_processDefinitionName) {
				case "指导老师完成任务书": {
					var banner_Taskbook_Teacher = document
							.getElementById("banner_Taskbook_Teacher");
					banner_Taskbook_Teacher.style.color = 'white';
					banner_Taskbook_Teacher.parentNode.style.backgroundColor = '#428bca';
					Taskbook_Teacher();
					break;
				}
				case "教研室主任填写任务书审核意见": {
					var banner_Taskbook_Section = document
							.getElementById("banner_Taskbook_Section");
					banner_Taskbook_Section.style.color = 'white';
					banner_Taskbook_Section.parentNode.style.backgroundColor = '#428bca';
					Taskbook_Section();
					break;
				}
				case "学生完成开题报告": {
					break;
				}
				case "学生完成前期进展情况记录": {
					break;
				}
				case "指导老师填写前期进展情况意见": {
					break;
				}
				case "学生完成中期进展情况记录": {
					break;
				}
				case "指导老师填写中期进展情况意见": {
					break;
				}
				case "学生完成后期进展情况记录": {
					break;
				}
				case "指导老师填写后期进展情况意见": {
					break;
				}
				case "学生完成完善期进展情况记录": {
					break;
				}
				case "指导老师填写完善期进展情况意见": {
					break;
				}
				case "学生完成个人学习总结": {
					break;
				}
				case "指导老师填写个人学习总结意见": {
					break;
				}
				case "学生提交答辩论文": {
					break;
				}
				case "指导老师填写形式审查表": {
					break;
				}
				case "领导小组长填写形式审查表(核查)": {
					break;
				}
				case "指导老师填写评价审阅表": {
					break;
				}
				case "评阅老师填写评阅审查表": {
					break;
				}

				}

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST", "/bysjglxt/process/ProcessManagement_getCurrentProcess");

	xhr.send(formData);
}

function Taskbook_Teacher() {
	document.getElementById("GraduationProjectTitle").innerHTML = '指导老师完成任务书';
	var banner_Taskbook_Teacher = document
			.getElementById("banner_Taskbook_Teacher");
	banner_Taskbook_Teacher.click();
	/*
	 * 
	 */
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var taskbook = JSON.parse(xhr.responseText);

				var tab1 = document.getElementById("tab1");
				tab1.innerHTML = '';

				var h4 = document.createElement("h4");
				h4.innerHTML = '研究主要内容及基本要求：';
				tab1.appendChild(h4);

				var textarea_1 = document.createElement("textarea");
				textarea_1.className = 'form-control';
				textarea_1.style = "margin:10px 0 50px 0;resize: vertical;"
				if (taskbook.taskbook_acontent_required != null) {
					textarea_1.innerHTML = taskbook.taskbook_acontent_required;
				} else {
					textarea_1.innerHTML = '';
				}

				tab1.appendChild(textarea_1);

				var h4 = document.createElement("h4");
				h4.innerHTML = '主要参考资料：';
				tab1.appendChild(h4);

				var textarea_2 = document.createElement("textarea");
				textarea_2.className = 'form-control';
				textarea_2.style = "margin:10px 0 50px 0;resize: vertical;"
				if (taskbook.taskbook_reference != null) {
					textarea_2.innerHTML = taskbook.taskbook_reference;
				} else {
					textarea_2.innerHTML = '';
				}
				tab1.appendChild(textarea_2);

				var h4 = document.createElement("h4");
				h4.innerHTML = '进程计划：';
				tab1.appendChild(h4);

				var textarea_3 = document.createElement("textarea");
				textarea_3.className = 'form-control';
				textarea_3.style = "margin:10px 0 50px 0;resize: vertical;"
				if (taskbook.taskbook_plan != null) {
					textarea_3.innerHTML = taskbook.taskbook_plan;
				} else {
					textarea_3.innerHTML = '';
				}
				tab1.appendChild(textarea_3);

				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				if ("指导老师完成任务书" != current_processDefinitionName) {
					textarea_1.disabled = "disabled";
					textarea_2.disabled = "disabled";
					textarea_3.disabled = "disabled";
					var button_SaveGraduationProject = document
							.getElementById("button_SaveGraduationProject");
					button_SaveGraduationProject.style.display = "none";
				} else {
					button_SaveGraduationProject.style.display = "block";
				}

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_get_Taskbook");

	xhr.send(formData);
}

function Taskbook_Section() {
	document.getElementById("GraduationProjectTitle").innerHTML = '教研室主任填写任务书审核意见';
	var banner_Taskbook_Section = document
			.getElementById("banner_Taskbook_Section");
	banner_Taskbook_Section.click();
	/*
	 * 
	 */
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var taskbook = JSON.parse(xhr.responseText);

				var tab1 = document.getElementById("tab1");
				tab1.innerHTML = '';

				var h4 = document.createElement("h4");
				h4.innerHTML = '研究主要内容及基本要求：';
				tab1.appendChild(h4);

				var textarea_1 = document.createElement("textarea");
				textarea_1.className = 'form-control';
				textarea_1.style = "margin:10px 0 50px 0;resize: vertical;"
				if (taskbook.taskbook_acontent_required != null) {
					textarea_1.innerHTML = taskbook.taskbook_acontent_required;
				} else {
					textarea_1.innerHTML = '';
				}

				tab1.appendChild(textarea_1);

				var h4 = document.createElement("h4");
				h4.innerHTML = '主要参考资料：';
				tab1.appendChild(h4);

				var textarea_2 = document.createElement("textarea");
				textarea_2.className = 'form-control';
				textarea_2.style = "margin:10px 0 50px 0;resize: vertical;"
				if (taskbook.taskbook_reference != null) {
					textarea_2.innerHTML = taskbook.taskbook_reference;
				} else {
					textarea_2.innerHTML = '';
				}
				tab1.appendChild(textarea_2);

				var h4 = document.createElement("h4");
				h4.innerHTML = '进程计划：';
				tab1.appendChild(h4);

				var textarea_3 = document.createElement("textarea");
				textarea_3.className = 'form-control';
				textarea_3.style = "margin:10px 0 50px 0;resize: vertical;"
				if (taskbook.taskbook_plan != null) {
					textarea_3.innerHTML = taskbook.taskbook_plan;
				} else {
					textarea_3.innerHTML = '';
				}
				tab1.appendChild(textarea_3);

				/*
				 * 让不是现在进行的流程的不可编辑
				 */
				if ("教研室主任填写任务书审核意见" != current_processDefinitionName) {
					textarea_1.disabled = "disabled";
					textarea_2.disabled = "disabled";
					textarea_3.disabled = "disabled";
					var button_SaveGraduationProject = document
							.getElementById("button_SaveGraduationProject");
					button_SaveGraduationProject.style.display = "none";
				} else {
					button_SaveGraduationProject.style.display = "block";
				}

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_get_Taskbook");

	xhr.send(formData);

}
function report_opening() {

}
function RecordProgress_1_Student() {

}
function RecordProgress_1_Teacher() {

}
function RecordProgress_2_Student() {

}
function RecordProgress_2_Teacher() {

}
function RecordProgress_3_Student() {

}
function RecordProgress_3_Teacher() {

}
function RecordProgress_4_Student() {

}
function RecordProgress_4_Teacher() {

}
function summary_student() {

}
function summary_teacher() {

}
function dissertation() {

}
function examination_formal_teacher() {

}
function examination_formal_leader() {

}
function evaluate_tutor() {

}
function evaluate_review() {

}