var num_reportopening = 0;

function addTeacherReportOpening() {

	if (num_reportopening >= 1) {
		toastr.error("已上传开题报告");
		return;
	}

	// 总父容器
	var div_teacher_report_opening_box = document.getElementById("div_teacher_report_opening_box");

	// 创建input
	var input_report_opening = document.createElement("input");
	input_report_opening.type = "file";
	input_report_opening.id = "input_report_opening";
	input_report_opening.className = "";
	div_teacher_report_opening_box.appendChild(input_report_opening);
	input_report_opening.style.display = "none";
	input_report_opening.onchange = teacherReportOpeningChange;
	input_report_opening.click();
}

function teacherReportOpeningChange(report_opening) {
	num_reportopening++;
	var div_teacher_report_opening_box = document.getElementById("div_teacher_report_opening_box");
	var div_report_opening_teacher_father = document.createElement("div");
	var div_teacher_report_opening_img = document.createElement("img");
	var div_teacher_report_opening_name = document.createElement("a");
	var div_teacher_report_opening_del = document.createElement("div");

	div_teacher_report_opening_box.appendChild(div_report_opening_teacher_father);
	div_report_opening_teacher_father.appendChild(div_teacher_report_opening_img);
	div_report_opening_teacher_father.appendChild(div_teacher_report_opening_name);
	div_report_opening_teacher_father.appendChild(div_teacher_report_opening_del);

	div_report_opening_teacher_father.style.float = "left";
	div_report_opening_teacher_father.style.width = "100%";
	div_report_opening_teacher_father.style.margin = "0 0 20px 0";
	div_report_opening_teacher_father.id = "div_report_opening_teacher_father";
	div_teacher_report_opening_name.href = '/bysjglxt/graduationProject/GraduationProjectManagement_downloadReportOpening?DissertationUserID='
		+ currentProcessDto.processInstance.process_instance_man;
	var xiazaiWanOk = document.getElementById("report_file_is_xiazai");
	if("1" == xiazaiWanOk.value){
		div_teacher_report_opening_name.style.color="green";
	}
	// 求出后缀
	if (report_opening == "[object Event]") {
		var input_annex_type = report_opening.target.files[0].name
				.substring(report_opening.target.files[0].name.lastIndexOf(".") + 1);
	} else {
		var input_annex_type = report_opening.substring(report_opening
				.lastIndexOf(".") + 1);
	}

	switch (input_annex_type) {
	case "doc":
	case "DOC":
	case "docx":
	case "DOCX": {
		div_teacher_report_opening_img.src = "../img/word.png";
		break;
	}
	default: {
		div_teacher_report_opening_img.src = "../img/unknown.png";
		break;
	}
	}

	div_teacher_report_opening_img.style.float = "left";
	div_teacher_report_opening_img.style.margin = "10px 10px 0 10px";

	if (report_opening == "[object Event]") {
		div_teacher_report_opening_name.innerHTML = report_opening.target.files[0].name;
	} else {
		div_teacher_report_opening_name.innerHTML = report_opening;
	}
	div_teacher_report_opening_name.style.width = "auto";
	div_teacher_report_opening_name.style.float = "left";
	div_teacher_report_opening_name.style.lineHeight = "32px";
	div_teacher_report_opening_name.style.margin = "10px 0 0 0";
	if (report_opening == "[object Event]") {
	} else {
		div_teacher_report_opening_name.className = "div_old_report_opening";
	}
	div_teacher_report_opening_del.id = "div_teacher_report_opening_del";
	div_teacher_report_opening_del.innerHTML = "X";
	div_teacher_report_opening_del.style.width = "auto";
	div_teacher_report_opening_del.style.float = "left";
	div_teacher_report_opening_del.style.backgroundColor = "#d9534f";
	div_teacher_report_opening_del.style.color = "white";
	div_teacher_report_opening_del.style.margin = "10px 0 0 10px";
	div_teacher_report_opening_del.style.padding = "5px 10px";
	div_teacher_report_opening_del.style.borderRadius = "50%";
	div_teacher_report_opening_del.style.cursor = "pointer";
	div_teacher_report_opening_del.onclick = delTeacherReportOpening;

}
function delTeacherReportOpening() {

	num_reportopening--;
	var div_teacher_report_opening_box = document.getElementById("div_teacher_report_opening_box");
	var div_report_opening_teacher_father = document
			.getElementById("div_report_opening_teacher_father");
	var input_report_opening = document.getElementById("input_report_opening");

	div_teacher_report_opening_box.removeChild(div_report_opening_teacher_father);
	if (input_report_opening != null) {
		div_teacher_report_opening_box.removeChild(input_report_opening);
	}
}


function studentReportOpeningChange(report_opening){
	var div_student_report_opening_box = document.getElementById("div_student_report_opening_box");
	var div_student_report_opening_father = document.createElement("div");
	var div_student_report_opening_img = document.createElement("img");
	var div_student_report_opening_name = document.createElement("a");

	div_student_report_opening_box.appendChild(div_student_report_opening_father);
	div_student_report_opening_father.appendChild(div_student_report_opening_img);
	div_student_report_opening_father.appendChild(div_student_report_opening_name);
	
	div_student_report_opening_father.style.float = "left";
	div_student_report_opening_father.style.width = "100%";
	div_student_report_opening_father.style.margin = "0 0 20px 0";
	div_student_report_opening_father.id = "div_student_report_opening_father";
	
	
	
	div_student_report_opening_name.href = '/bysjglxt/graduationProject/GraduationProjectManagement_downloadReportOpening?DissertationUserID='
		+ currentProcessDto.processInstance.process_instance_man;
	var xiazaiShenOk = document.getElementById("report_file_is_xiazai");
	if("1" == xiazaiShenOk.value){
		div_student_report_opening_name.style.color="green";
	}
	// 求出后缀
	if (report_opening == "[object Event]") {
		var input_annex_type = report_opening.target.files[0].name
				.substring(report_opening.target.files[0].name.lastIndexOf(".") + 1);
	} else {
		var input_annex_type = report_opening.substring(report_opening
				.lastIndexOf(".") + 1);
	}

	switch (input_annex_type) {
	case "doc":
	case "DOC":
	case "docx":
	case "DOCX": {
		div_student_report_opening_img.src = "../img/word.png";
		break;
	}
	default: {
		div_student_report_opening_img.src = "../img/unknown.png";
		break;
	}
	}

	div_student_report_opening_img.style.float = "left";
	div_student_report_opening_img.style.margin = "10px 10px 0 10px";

	if (report_opening == "[object Event]") {
		div_student_report_opening_name.innerHTML = report_opening.target.files[0].name;
	} else {
		div_student_report_opening_name.innerHTML = report_opening;
	}
	div_student_report_opening_name.style.width = "auto";
	div_student_report_opening_name.style.float = "left";
	div_student_report_opening_name.style.lineHeight = "32px";
	div_student_report_opening_name.style.margin = "10px 0 0 0";
	if (report_opening == "[object Event]") {
	} else {
		div_student_report_opening_name.className = "div_old_shen_taskbook";
	}
}
function uploadReportOpening() {

}