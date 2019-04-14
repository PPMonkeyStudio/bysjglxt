var num_taskbook = 0;

function addTeacherTaskBook() {
	console.log('dddddddddddddddddddddd:'+num_taskbook)
	if (num_taskbook >= 1) {
		toastr.error("已下发任务书，请先点击删除再上传");
		return;
	}

	// 总父容器
	var div_xia_taskbook_box = document.getElementById("div_xia_taskbook_box");

	// 创建input
	var input_xia_taskbook = document.createElement("input");
	input_xia_taskbook.type = "file";
	input_xia_taskbook.id = "input_xia_taskbook";
	input_xia_taskbook.className = "";
	div_xia_taskbook_box.appendChild(input_xia_taskbook);
	input_xia_taskbook.style.display = "none";
	input_xia_taskbook.onchange = taskbookFileChange;
	input_xia_taskbook.click();
}

function taskbookFileChange(xia_taskbook) {
	console.log('dddddddddddddddddddddddd:',num_taskbook)
	num_taskbook++;
	console.log("zuihou:",num_taskbook);
	var div_xia_taskbook_box = document.getElementById("div_xia_taskbook_box");
	var div_xia_taskbook_father = document.createElement("div");
	var div_xia_taskbook_img = document.createElement("img");
	var div_xia_taskbook_name = document.createElement("a");
	var div_xia_taskbook_del = document.createElement("div");

	div_xia_taskbook_box.appendChild(div_xia_taskbook_father);
	div_xia_taskbook_father.appendChild(div_xia_taskbook_img);
	div_xia_taskbook_father.appendChild(div_xia_taskbook_name);
	div_xia_taskbook_father.appendChild(div_xia_taskbook_del);
	
	div_xia_taskbook_father.style.float = "left";
	div_xia_taskbook_father.style.width = "100%";
	div_xia_taskbook_father.style.margin = "0 0 20px 0";
	div_xia_taskbook_father.id = "div_xia_taskbook_father";
	
	
	
	div_xia_taskbook_name.href = '/bysjglxt/graduationProject/GraduationProjectManagement_downloadXiaTaskBook?DissertationUserID='
		+ currentProcessDto.processInstance.process_instance_man;
	var xiazaiOk = document.getElementById("xia_taskbook_is_xiazai");
	console.log(xiazaiOk.value)
	if("1" == xiazaiOk.value){
		div_xia_taskbook_name.style.color="green";
	}
	// 求出后缀
	if (xia_taskbook == "[object Event]") {
		var input_annex_type = xia_taskbook.target.files[0].name
				.substring(xia_taskbook.target.files[0].name.lastIndexOf(".") + 1);
	} else {
		var input_annex_type = xia_taskbook.substring(xia_taskbook
				.lastIndexOf(".") + 1);
	}

	switch (input_annex_type) {
	case "doc":
	case "DOC":
	case "docx":
	case "DOCX": {
		div_xia_taskbook_img.src = "../img/word.png";
		break;
	}
	default: {
		div_xia_taskbook_img.src = "../img/unknown.png";
		break;
	}
	}

	div_xia_taskbook_img.style.float = "left";
	div_xia_taskbook_img.style.margin = "10px 10px 0 10px";
	
	if (xia_taskbook == "[object Event]") {
		div_xia_taskbook_name.innerHTML = xia_taskbook.target.files[0].name;
	} else {
		div_xia_taskbook_name.innerHTML = xia_taskbook;
	}
	div_xia_taskbook_name.style.width = "auto";
	div_xia_taskbook_name.style.float = "left";
	div_xia_taskbook_name.style.lineHeight = "32px";
	div_xia_taskbook_name.style.margin = "10px 0 0 0";
	if (xia_taskbook == "[object Event]") {
	} else {
		div_xia_taskbook_name.className = "div_old_xia_taskbook";
	}
	div_xia_taskbook_del.id = "div_xia_taskbook_del";
	div_xia_taskbook_del.innerHTML = "X";
	div_xia_taskbook_del.style.width = "auto";
	div_xia_taskbook_del.style.float = "left";
	div_xia_taskbook_del.style.backgroundColor = "#d9534f";
	div_xia_taskbook_del.style.color = "white";
	div_xia_taskbook_del.style.margin = "10px 0 0 10px";
	div_xia_taskbook_del.style.padding = "5px 10px";
	div_xia_taskbook_del.style.borderRadius = "50%";
	div_xia_taskbook_del.style.cursor = "pointer";
	div_xia_taskbook_del.onclick = delXiaTaskBook;

}
function delXiaTaskBook() {
	num_taskbook--;
	console.log("zuihou:",num_taskbook);
	var div_xia_taskbook_box = document.getElementById("div_xia_taskbook_box");
	var div_xia_taskbook_father = document
			.getElementById("div_xia_taskbook_father");
	var input_xia_taskbook = document.getElementById("input_xia_taskbook");

	div_xia_taskbook_box.removeChild(div_xia_taskbook_father);
	if (input_xia_taskbook != null) {
		div_xia_taskbook_box.removeChild(input_xia_taskbook);
	}
}
function uploadReportOpening() {

}