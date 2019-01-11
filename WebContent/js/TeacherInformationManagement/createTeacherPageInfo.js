function getChangeMajorCode(obj,ll){
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var major = JSON.parse(xhr.responseText);
				var k = '#'+ll
				if(major == undefined){
					$(k).val("");
				}else{
					$(k).val(major.major_professionalcode);
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	var formData = new FormData();
	formData.append("bysjglxtMajor.major_name",obj.value);
	xhr.open("POST","/bysjglxt/major/MajorManagement_getMajorByMajorName");
	xhr.send(formData);
}
