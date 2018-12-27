function taskbookShenFileChange(shen_taskbook) {
	var div_shen_taskbook_box = document.getElementById("div_shen_taskbook_box");
	var div_shen_taskbook_father = document.createElement("div");
	var div_shen_taskbook_img = document.createElement("img");
	var div_shen_taskbook_name = document.createElement("a");

	div_shen_taskbook_box.appendChild(div_shen_taskbook_father);
	div_shen_taskbook_father.appendChild(div_shen_taskbook_img);
	div_shen_taskbook_father.appendChild(div_shen_taskbook_name);
	
	div_shen_taskbook_father.style.float = "left";
	div_shen_taskbook_father.style.width = "100%";
	div_shen_taskbook_father.style.margin = "0 0 20px 0";
	div_shen_taskbook_father.id = "div_shen_taskbook_father";
	
	
	
	div_shen_taskbook_name.href = '/bysjglxt/graduationProject/GraduationProjectManagement_downloadWanTaskBook?DissertationUserID='
		+ currentProcessDto.processInstance.process_instance_man;
	var xiazaiShenOk = document.getElementById("shen_taskbook_is_xiazai");
	if("1" == xiazaiShenOk.value){
		div_shen_taskbook_name.style.color="green";
	}
	// 求出后缀
	if (shen_taskbook == "[object Event]") {
		var input_annex_type = shen_taskbook.target.files[0].name
				.substring(shen_taskbook.target.files[0].name.lastIndexOf(".") + 1);
	} else {
		var input_annex_type = shen_taskbook.substring(shen_taskbook
				.lastIndexOf(".") + 1);
	}

	switch (input_annex_type) {
	case "doc":
	case "DOC":
	case "docx":
	case "DOCX": {
		div_shen_taskbook_img.src = "../img/word.png";
		break;
	}
	default: {
		div_shen_taskbook_img.src = "../img/unknown.png";
		break;
	}
	}

	div_shen_taskbook_img.style.float = "left";
	div_shen_taskbook_img.style.margin = "10px 10px 0 10px";

	if (shen_taskbook == "[object Event]") {
		div_shen_taskbook_name.innerHTML = shen_taskbook.target.files[0].name;
	} else {
		div_shen_taskbook_name.innerHTML = shen_taskbook;
	}
	div_shen_taskbook_name.style.width = "auto";
	div_shen_taskbook_name.style.float = "left";
	div_shen_taskbook_name.style.lineHeight = "32px";
	div_shen_taskbook_name.style.margin = "10px 0 0 0";
	if (shen_taskbook == "[object Event]") {
	} else {
		div_shen_taskbook_name.className = "div_old_shen_taskbook";
	}
}
