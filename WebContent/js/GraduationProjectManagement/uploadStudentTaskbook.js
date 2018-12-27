var num_student_taskbook = 0;
//TODO 将这边的数据进行初始化
function addStudentTaskBook() {

	if (num_student_taskbook >= 1) {
		toastr.error("已完善任务书，请先点击删除再上传");
		return;
	}
	// 总父容器
	var div_wan_taskbook_box = document.getElementById("div_wan_taskbook_box");

	// 创建input
	var input_wan_taskbook = document.createElement("input");
	input_wan_taskbook.type = "file";
	input_wan_taskbook.id = "input_wan_taskbook";
	input_wan_taskbook.className = "";
	div_wan_taskbook_box.appendChild(input_wan_taskbook);
	input_wan_taskbook.style.display = "none";
	input_wan_taskbook.onchange = taskbookWanFileChange;
	input_wan_taskbook.click();
}

function taskbookWanFileChange(wan_taskbook) {
	num_student_taskbook++;
	var div_wan_taskbook_box = document.getElementById("div_wan_taskbook_box");
	var div_wan_taskbook_father = document.createElement("div");
	var div_wan_taskbook_img = document.createElement("img");
	var div_wan_taskbook_name = document.createElement("a");
	var div_wan_taskbook_del = document.createElement("div");

	div_wan_taskbook_box.appendChild(div_wan_taskbook_father);
	div_wan_taskbook_father.appendChild(div_wan_taskbook_img);
	div_wan_taskbook_father.appendChild(div_wan_taskbook_name);
	div_wan_taskbook_father.appendChild(div_wan_taskbook_del);
	
	div_wan_taskbook_father.style.float = "left";
	div_wan_taskbook_father.style.width = "100%";
	div_wan_taskbook_father.style.margin = "0 0 20px 0";
	div_wan_taskbook_father.id = "div_wan_taskbook_father";
	
	
	
	div_wan_taskbook_name.href = '/bysjglxt/graduationProject/GraduationProjectManagement_downloadWanTaskBook?DissertationUserID='
		+ currentProcessDto.processInstance.process_instance_man;
	var xiazaiWanOk = document.getElementById("wan_taskbook_is_xiazai");
	if("1" == xiazaiWanOk.value){
		div_wan_taskbook_name.style.color="green";
	}
	// 求出后缀
	if (wan_taskbook == "[object Event]") {
		var input_annex_type = wan_taskbook.target.files[0].name
				.substring(wan_taskbook.target.files[0].name.lastIndexOf(".") + 1);
	} else {
		var input_annex_type = wan_taskbook.substring(wan_taskbook
				.lastIndexOf(".") + 1);
	}

	switch (input_annex_type) {
	case "doc":
	case "DOC":
	case "docx":
	case "DOCX": {
		div_wan_taskbook_img.src = "../img/word.png";
		break;
	}
	default: {
		div_wan_taskbook_img.src = "../img/unknown.png";
		break;
	}
	}

	div_wan_taskbook_img.style.float = "left";
	div_wan_taskbook_img.style.margin = "10px 10px 0 10px";

	if (wan_taskbook == "[object Event]") {
		div_wan_taskbook_name.innerHTML = wan_taskbook.target.files[0].name;
	} else {
		div_wan_taskbook_name.innerHTML = wan_taskbook;
	}
	div_wan_taskbook_name.style.width = "auto";
	div_wan_taskbook_name.style.float = "left";
	div_wan_taskbook_name.style.lineHeight = "32px";
	div_wan_taskbook_name.style.margin = "10px 0 0 0";
	if (wan_taskbook == "[object Event]") {
	} else {
		div_wan_taskbook_name.className = "div_old_wan_taskbook";
	}
	div_wan_taskbook_del.id = "div_wan_taskbook_del";
	div_wan_taskbook_del.innerHTML = "X";
	div_wan_taskbook_del.style.width = "auto";
	div_wan_taskbook_del.style.float = "left";
	div_wan_taskbook_del.style.backgroundColor = "#d9534f";
	div_wan_taskbook_del.style.color = "white";
	div_wan_taskbook_del.style.margin = "10px 0 0 10px";
	div_wan_taskbook_del.style.padding = "5px 10px";
	div_wan_taskbook_del.style.borderRadius = "50%";
	div_wan_taskbook_del.style.cursor = "pointer";
	div_wan_taskbook_del.onclick = delWanTaskBook;
}
function delWanTaskBook() {
	num_student_taskbook--;
	var div_wan_taskbook_box = document.getElementById("div_wan_taskbook_box");
	var div_wan_taskbook_father = document
			.getElementById("div_wan_taskbook_father");
	var input_wan_taskbook = document.getElementById("input_wan_taskbook");

	div_wan_taskbook_box.removeChild(div_wan_taskbook_father);
	if (input_wan_taskbook != null) {
		div_wan_taskbook_box.removeChild(input_wan_taskbook);
	}
}
function uploadReportOpening() {

}