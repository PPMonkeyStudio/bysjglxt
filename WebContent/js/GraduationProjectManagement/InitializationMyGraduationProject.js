var currentProcessDto = null;
// 正在进行的任务名
var current_processDefinitionName = null;
// 正在执行这个任务的角色的id
var current_processInstanceUserID = null;

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
						current_processInstanceUserID = currentProcessDto.listTaskBelongProcess[num].taskInstance.task_instance_role;
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
					var banner_report_opening = document
							.getElementById("banner_report_opening");
					banner_report_opening.style.color = 'white';
					banner_report_opening.parentNode.style.backgroundColor = '#428bca';
					report_opening();
					break;
				}
				case "学生完成前期进展情况记录": {
					var banner_RecordProgress_1_Student = document
							.getElementById("banner_RecordProgress_1_Student");
					banner_RecordProgress_1_Student.style.color = 'white';
					banner_RecordProgress_1_Student.parentNode.style.backgroundColor = '#428bca';
					RecordProgress_1_Student();
					break;
				}
				case "指导老师填写前期进展情况意见": {
					var banner_RecordProgress_1_Teacher = document
							.getElementById("banner_RecordProgress_1_Teacher");
					banner_RecordProgress_1_Teacher.style.color = 'white';
					banner_RecordProgress_1_Teacher.parentNode.style.backgroundColor = '#428bca';
					RecordProgress_1_Teacher();
					break;
				}
				case "学生完成中期进展情况记录": {
					var banner_RecordProgress_2_Student = document
							.getElementById("banner_RecordProgress_2_Student");
					banner_RecordProgress_2_Student.style.color = 'white';
					banner_RecordProgress_2_Student.parentNode.style.backgroundColor = '#428bca';
					RecordProgress_2_Student();
					break;
				}
				case "指导老师填写中期进展情况意见": {
					var banner_RecordProgress_2_Teacher = document
							.getElementById("banner_RecordProgress_2_Teacher");
					banner_RecordProgress_2_Teacher.style.color = 'white';
					banner_RecordProgress_2_Teacher.parentNode.style.backgroundColor = '#428bca';
					RecordProgress_2_Teacher();
					break;
				}
				case "学生完成后期进展情况记录": {
					var banner_RecordProgress_3_Student = document
							.getElementById("banner_RecordProgress_3_Student");
					banner_RecordProgress_3_Student.style.color = 'white';
					banner_RecordProgress_3_Student.parentNode.style.backgroundColor = '#428bca';
					RecordProgress_3_Student();
					break;
				}
				case "指导老师填写后期进展情况意见": {
					var banner_RecordProgress_3_Teacher = document
							.getElementById("banner_RecordProgress_3_Teacher");
					banner_RecordProgress_3_Teacher.style.color = 'white';
					banner_RecordProgress_3_Teacher.parentNode.style.backgroundColor = '#428bca';
					RecordProgress_3_Teacher();
					break;
				}
				case "学生完成完善期进展情况记录": {
					var banner_RecordProgress_4_Student = document
							.getElementById("banner_RecordProgress_4_Student");
					banner_RecordProgress_4_Student.style.color = 'white';
					banner_RecordProgress_4_Student.parentNode.style.backgroundColor = '#428bca';
					RecordProgress_4_Student();
					break;
				}
				case "指导老师填写完善期进展情况意见": {
					var banner_RecordProgress_4_Teacher = document
							.getElementById("banner_RecordProgress_4_Teacher");
					banner_RecordProgress_4_Teacher.style.color = 'white';
					banner_RecordProgress_4_Teacher.parentNode.style.backgroundColor = '#428bca';
					RecordProgress_4_Teacher();
					break;
				}
				case "学生完成个人学习总结": {
					var banner_summary_student = document
							.getElementById("banner_summary_student");
					banner_summary_student.style.color = 'white';
					banner_summary_student.parentNode.style.backgroundColor = '#428bca';
					summary_student();
					break;
				}
				case "指导老师填写个人学习总结意见": {
					var banner_summary_teacher = document
							.getElementById("banner_summary_teacher");
					banner_summary_teacher.style.color = 'white';
					banner_summary_teacher.parentNode.style.backgroundColor = '#428bca';
					summary_teacher();
					break;
				}
				case "学生提交答辩论文": {
					var banner_dissertation = document
							.getElementById("banner_dissertation");
					banner_dissertation.style.color = 'white';
					banner_dissertation.parentNode.style.backgroundColor = '#428bca';
					dissertation();
					break;
				}
				case "指导老师填写形式审查表": {
					var banner_examination_formal_teacher = document
							.getElementById("banner_examination_formal_teacher");
					banner_examination_formal_teacher.style.color = 'white';
					banner_examination_formal_teacher.parentNode.style.backgroundColor = '#428bca';
					examination_formal_teacher();
					break;
				}
				case "领导小组长填写形式审查表(核查)": {
					var banner_examination_formal_leader = document
							.getElementById("banner_examination_formal_leader");
					banner_examination_formal_leader.style.color = 'white';
					banner_examination_formal_leader.parentNode.style.backgroundColor = '#428bca';
					examination_formal_leader();
					break;
				}
				case "指导老师填写评价审阅表": {
					var banner_evaluate_tutor = document
							.getElementById("banner_evaluate_tutor");
					banner_evaluate_tutor.style.color = 'white';
					banner_evaluate_tutor.parentNode.style.backgroundColor = '#428bca';
					evaluate_tutor();
					break;
				}
				case "评阅老师填写评阅审查表": {
					var banner_evaluate_review = document
							.getElementById("banner_evaluate_review");
					banner_evaluate_review.style.color = 'white';
					banner_evaluate_review.parentNode.style.backgroundColor = '#428bca';
					evaluate_review();
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
