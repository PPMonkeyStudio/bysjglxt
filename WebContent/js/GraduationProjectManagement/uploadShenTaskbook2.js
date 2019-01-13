var num_student_taskbook = 0;
//TODO 将这边的数据进行初始化
function addShenTaskBook() {
	if (num_student_taskbook >= 1) {
		toastr.error("已存在修改任务书，请先点击删除再上传");
		return;
	}
	// 总父容器
	var div_shen_taskbook_box2 = document.getElementById("div_shen_taskbook_box2");

	// 创建input
	var input_shen_taskbook2 = document.createElement("input");
	input_shen_taskbook2.type = "file";
	input_shen_taskbook2.id = "input_shen_taskbook2";
	input_shen_taskbook2.className = "";
	div_shen_taskbook_box2.appendChild(input_shen_taskbook2);
	input_shen_taskbook2.style.display = "none";
	input_shen_taskbook2.onchange = taskbookShenFileChange2;
	input_shen_taskbook2.click();
}

function taskbookShenFileChange2(shen2_taskbook) {
	num_student_taskbook++;
	var div_shen_taskbook_box2 = document.getElementById("div_shen_taskbook_box2");
	var div_shen_taskbook_father2 = document.createElement("div");
	var div_shen_taskbook_img2 = document.createElement("img");
	var div_shen_taskbook_name2 = document.createElement("a");
	var div_shen_taskbook_del2 = document.createElement("div");

	div_shen_taskbook_box2.appendChild(div_shen_taskbook_father2);
	div_shen_taskbook_father2.appendChild(div_shen_taskbook_img2);
	div_shen_taskbook_father2.appendChild(div_shen_taskbook_name2);
	div_shen_taskbook_father2.appendChild(div_shen_taskbook_del2);
	
	div_shen_taskbook_father2.style.float = "left";
	div_shen_taskbook_father2.style.width = "100%";
	div_shen_taskbook_father2.style.margin = "0 0 20px 0";
	div_shen_taskbook_father2.id = "div_shen_taskbook_father2";
	
	
	
	div_shen_taskbook_name2.href = '/bysjglxt/graduationProject/GraduationProjectManagement_downloadShenTaskBookTwo?DissertationUserID='
		+ currentProcessDto.processInstance.process_instance_man;
	var xiazaiWanOk = document.getElementById("shen2_is_xiazai");
	if("1" == xiazaiWanOk.value){
		div_shen_taskbook_name2.style.color="green";
	}
	// 求出后缀
	if (shen2_taskbook == "[object Event]") {
		var input_annex_type = shen2_taskbook.target.files[0].name
				.substring(shen2_taskbook.target.files[0].name.lastIndexOf(".") + 1);
	} else {
		var input_annex_type = shen2_taskbook.substring(shen2_taskbook
				.lastIndexOf(".") + 1);
	}

	switch (input_annex_type) {
	case "doc":
	case "DOC":
	case "docx":
	case "DOCX": {
		div_shen_taskbook_img2.src = "../img/word.png";
		break;
	}
	default: {
		div_shen_taskbook_img2.src = "../img/unknown.png";
		break;
	}
	}

	div_shen_taskbook_img2.style.float = "left";
	div_shen_taskbook_img2.style.margin = "10px 10px 0 10px";

	if (shen2_taskbook == "[object Event]") {
		div_shen_taskbook_name2.innerHTML = shen2_taskbook.target.files[0].name;
	} else {
		div_shen_taskbook_name2.innerHTML = shen2_taskbook;
	}
	div_shen_taskbook_name2.style.width = "auto";
	div_shen_taskbook_name2.style.float = "left";
	div_shen_taskbook_name2.style.lineHeight = "32px";
	div_shen_taskbook_name2.style.margin = "10px 0 0 0";
	if (shen2_taskbook == "[object Event]") {
	} else {
		div_shen_taskbook_name2.className = "div_old_shen2_taskbook";
	}
	div_shen_taskbook_del2.id = "div_shen_taskbook_del2";
	div_shen_taskbook_del2.innerHTML = "X";
	div_shen_taskbook_del2.style.width = "auto";
	div_shen_taskbook_del2.style.float = "left";
	div_shen_taskbook_del2.style.backgroundColor = "#d9534f";
	div_shen_taskbook_del2.style.color = "white";
	div_shen_taskbook_del2.style.margin = "10px 0 0 10px";
	div_shen_taskbook_del2.style.padding = "5px 10px";
	div_shen_taskbook_del2.style.borderRadius = "50%";
	div_shen_taskbook_del2.style.cursor = "pointer";
	div_shen_taskbook_del2.onclick = delShenTaskBook;
}
function delShenTaskBook() {
	num_student_taskbook--;
	var div_shen_taskbook_box2 = document.getElementById("div_shen_taskbook_box2");
	var div_shen_taskbook_father2 = document
			.getElementById("div_shen_taskbook_father2");
	var input_shen_taskbook2 = document.getElementById("input_shen_taskbook2");

	div_shen_taskbook_box2.removeChild(div_shen_taskbook_father2);
	if (input_shen_taskbook2 != null) {
		div_shen_taskbook_box2.removeChild(input_shen_taskbook2);
	}
}
function uploadReportOpening() {

}