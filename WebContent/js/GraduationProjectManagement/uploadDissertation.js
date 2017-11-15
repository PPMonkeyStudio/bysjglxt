var num_dissertation = 0;

function addDissertation() {

	if (num_dissertation >= 1) {
		toastr.error("已上传毕业论文");
		return;
	}

	// 总父容器
	var div_dissertation_box = document.getElementById("div_dissertation_box");

	// 创建input
	var input_dissertation = document.createElement("input");
	input_dissertation.type = "file";
	input_dissertation.id = "input_dissertation";
	input_dissertation.className = "";
	div_dissertation_box.appendChild(input_dissertation);
	input_dissertation.style.display = "none";
	input_dissertation.onchange = dissertationChange;
	input_dissertation.click();
}

function dissertationChange(dissertation) {

	num_dissertation++;
	console.debug("num_dissertation：" + num_dissertation);
	var div_dissertation_box = document.getElementById("div_dissertation_box");
	var div_dissertation_father = document.createElement("div");
	var div_dissertation_img = document.createElement("img");
	var div_dissertation_name = document.createElement("a");
	var div_dissertation_del = document.createElement("div");

	div_dissertation_box.appendChild(div_dissertation_father);
	div_dissertation_father.appendChild(div_dissertation_img);
	div_dissertation_father.appendChild(div_dissertation_name);
	div_dissertation_father.appendChild(div_dissertation_del);

	div_dissertation_father.style.float = "left";
	div_dissertation_father.style.width = "100%";
	div_dissertation_father.style.margin = "0 0 20px 0";
	div_dissertation_father.id = "div_dissertation_father";

	// 求出后缀
	if (dissertation == "[object Event]") {
		var input_annex_type = dissertation.target.files[0].name
				.substring(dissertation.target.files[0].name.lastIndexOf(".") + 1);
	} else {
		div_dissertation_name.href = '/bysjglxt/graduationProject/GraduationProjectManagement_downloadDissertation?DissertationUserID='
				+ currentProcessDto.processInstance.process_instance_man;
		var input_annex_type = dissertation.substring(dissertation
				.lastIndexOf(".") + 1);
	}

	switch (input_annex_type) {
	case "doc":
	case "DOC":
	case "docx":
	case "DOCX": {
		div_dissertation_img.src = "../img/word.png";
		break;
	}
	default: {
		div_dissertation_img.src = "../img/unknown.png";
		break;
	}
	}

	div_dissertation_img.style.float = "left";
	div_dissertation_img.style.margin = "10px 10px 0 10px";

	if (dissertation == "[object Event]") {
		div_dissertation_name.innerHTML = dissertation.target.files[0].name;
	} else {
		div_dissertation_name.innerHTML = dissertation;
	}
	div_dissertation_name.style.width = "auto";
	div_dissertation_name.style.float = "left";
	div_dissertation_name.style.lineHeight = "32px";
	div_dissertation_name.style.margin = "10px 0 0 0";
	if (dissertation == "[object Event]") {
	} else {
		div_dissertation_name.className = "div_old_dissertation";
	}
	div_dissertation_del.innerHTML = "X";
	div_dissertation_del.style.width = "auto";
	div_dissertation_del.style.float = "left";
	div_dissertation_del.style.backgroundColor = "#d9534f";
	div_dissertation_del.style.color = "white";
	div_dissertation_del.style.margin = "10px 0 0 10px";
	div_dissertation_del.style.padding = "5px 10px";
	div_dissertation_del.style.borderRadius = "50%";
	div_dissertation_del.style.cursor = "pointer";
	div_dissertation_del.onclick = del_dissertation;

}
function del_dissertation() {

	num_dissertation--;
	console.debug("num_dissertation：" + num_dissertation);
	var div_dissertation_box = document.getElementById("div_dissertation_box");
	var div_dissertation_father = document
			.getElementById("div_dissertation_father");
	var input_dissertation = document.getElementById("input_dissertation");

	div_dissertation_box.removeChild(div_dissertation_father);
	if (input_dissertation != null) {
		div_dissertation_box.removeChild(input_dissertation);
	}
}
function uploadDissertation() {

}