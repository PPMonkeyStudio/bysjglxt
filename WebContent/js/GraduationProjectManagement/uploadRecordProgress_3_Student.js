var num_student_taskbook = 0;
//TODO 将这边的数据进行初始化
function addzhongRecordProgressZiliao() {
	if (num_student_taskbook >= 1) {
		toastr.error("已存在资料，请先点击删除再上传");
		return;
	}
	// 总父容器
	var div_zhong_recordprogress_box = document.getElementById("div_zhong_recordprogress_box");

	// 创建input
	var input_zhong_recordprogress = document.createElement("input");
	input_zhong_recordprogress.type = "file";
	input_zhong_recordprogress.id = "input_zhong_recordprogress";
	input_zhong_recordprogress.className = "";
	div_zhong_recordprogress_box.appendChild(input_zhong_recordprogress);
	input_zhong_recordprogress.style.display = "none";
	input_zhong_recordprogress.onchange = zhongRecordProgressFileChange;
	input_zhong_recordprogress.click();
}

function zhongRecordProgressFileChange(qianRecordProgress) {
	num_student_taskbook++;
	var div_zhong_recordprogress_box = document.getElementById("div_zhong_recordprogress_box");
	var div_zhong_recordprogress_father = document.createElement("div");
	var div_zhong_recordprogress_img = document.createElement("img");
	var div_zhong_recordprogress_name = document.createElement("a");
	var div_zhong_recordprogress_del = document.createElement("div");

	div_zhong_recordprogress_box.appendChild(div_zhong_recordprogress_father);
	div_zhong_recordprogress_father.appendChild(div_zhong_recordprogress_img);
	div_zhong_recordprogress_father.appendChild(div_zhong_recordprogress_name);
	div_zhong_recordprogress_father.appendChild(div_zhong_recordprogress_del);
	
	div_zhong_recordprogress_father.style.float = "left";
	div_zhong_recordprogress_father.style.width = "100%";
	div_zhong_recordprogress_father.style.margin = "0 0 20px 0";
	div_zhong_recordprogress_father.id = "div_zhong_recordprogress_father";
	
	
	
	div_zhong_recordprogress_name.href = '/bysjglxt/graduationProject/GraduationProjectManagement_downloadZhongRecordProgress?DissertationUserID='
		+ currentProcessDto.processInstance.process_instance_man;
	var xiazaiWanOk = document.getElementById("student_id_zhong_record_progress_is_xiazai");
	if("1" == xiazaiWanOk.value){
		div_zhong_recordprogress_name.style.color="green";
	}
	// 求出后缀
	if (qianRecordProgress == "[object Event]") {
		var input_annex_type = qianRecordProgress.target.files[0].name
				.substring(qianRecordProgress.target.files[0].name.lastIndexOf(".") + 1);
	} else {
		var input_annex_type = qianRecordProgress.substring(qianRecordProgress
				.lastIndexOf(".") + 1);
	}

	switch (input_annex_type) {
	case "doc":
	case "DOC":
	case "docx":
	case "DOCX": {
		div_zhong_recordprogress_img.src = "../img/word.png";
		break;
	}
	default: {
		div_zhong_recordprogress_img.src = "../img/unknown.png";
		break;
	}
	}

	div_zhong_recordprogress_img.style.float = "left";
	div_zhong_recordprogress_img.style.margin = "10px 10px 0 10px";

	if (qianRecordProgress == "[object Event]") {
		div_zhong_recordprogress_name.innerHTML = qianRecordProgress.target.files[0].name;
	} else {
		div_zhong_recordprogress_name.innerHTML = qianRecordProgress;
	}
	div_zhong_recordprogress_name.style.width = "auto";
	div_zhong_recordprogress_name.style.float = "left";
	div_zhong_recordprogress_name.style.lineHeight = "32px";
	div_zhong_recordprogress_name.style.margin = "10px 0 0 0";
	if (qianRecordProgress == "[object Event]") {
	} else {
		div_zhong_recordprogress_name.className = "div_old_zhong_recordprogress";
	}
	div_zhong_recordprogress_del.id = "div_zhong_recordprogress_del";
	div_zhong_recordprogress_del.innerHTML = "X";
	div_zhong_recordprogress_del.style.width = "auto";
	div_zhong_recordprogress_del.style.float = "left";
	div_zhong_recordprogress_del.style.backgroundColor = "#d9534f";
	div_zhong_recordprogress_del.style.color = "white";
	div_zhong_recordprogress_del.style.margin = "10px 0 0 10px";
	div_zhong_recordprogress_del.style.padding = "5px 10px";
	div_zhong_recordprogress_del.style.borderRadius = "50%";
	div_zhong_recordprogress_del.style.cursor = "pointer";
	div_zhong_recordprogress_del.onclick = delZhongRecordProgress;
}
function delZhongRecordProgress() {
	num_student_taskbook--;
	var div_zhong_recordprogress_box = document.getElementById("div_zhong_recordprogress_box");
	var div_zhong_recordprogress_father = document
			.getElementById("div_zhong_recordprogress_father");
	var input_zhong_recordprogress = document.getElementById("input_zhong_recordprogress");

	div_zhong_recordprogress_box.removeChild(div_zhong_recordprogress_father);
	if (input_zhong_recordprogress != null) {
		div_zhong_recordprogress_box.removeChild(input_zhong_recordprogress);
	}
}
function uploadReportOpening() {

}