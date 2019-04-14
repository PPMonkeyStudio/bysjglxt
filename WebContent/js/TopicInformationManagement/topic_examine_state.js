function closeTopicList() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				if (xhr.responseText == "success") {
					toastr.success("所选课题已关闭");
					List_Topic_By_PageAndSearch(1);
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	var checkbox_select = document.getElementsByClassName("checkbox_select");
	var formData = new FormData();
	for (var num = 0; num < checkbox_select.length; num++) {
		if (checkbox_select[num].checked) {
			formData.append("listCloseTopicID", checkbox_select[num].id);
		}
	}
	xhr.open("POST",
			"/bysjglxt/topic/TopicInformationManagement_closeTopicList");
	xhr.send(formData);
}

function agreeTopicList() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				if (xhr.responseText == "success") {
					toastr.success("所选课题已通过");
					List_Topic_By_PageAndSearch(1);
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	var checkbox_select = document.getElementsByClassName("checkbox_select");
	var formData = new FormData();
	for (var num = 0; num < checkbox_select.length; num++) {
		if (checkbox_select[num].checked) {
			formData.append("listAgreeTopicID", checkbox_select[num].id);
		}
	}
	xhr.open("POST",
			"/bysjglxt/topic/TopicInformationManagement_agreeTopicList");
	xhr.send(formData);
}

function refuseTopicList() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				if (xhr.responseText == "success") {
					toastr.success("所选课题已拒绝");
					List_Topic_By_PageAndSearch(1);
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	var checkbox_select = document.getElementsByClassName("checkbox_select");
	var formData = new FormData();
	for (var num = 0; num < checkbox_select.length; num++) {
		if (checkbox_select[num].checked) {
			formData.append("listRefuseTopicID", checkbox_select[num].id);
		}
	}
	xhr.open("POST",
			"/bysjglxt/topic/TopicInformationManagement_refuseTopicList");
	xhr.send(formData);
}

function deleteTopicList() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				if (xhr.responseText == "success") {
					toastr.success("所选课题已删除");
					window.location.href="/bysjglxt/topic/TopicInformationManagement_MyTopicListPage";
				}
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	var checkbox_select = document.getElementsByClassName("checkbox_select");
	var formData = new FormData();
	for (var num = 0; num < checkbox_select.length; num++) {
		if (checkbox_select[num].checked) {
			formData.append("listDeleteTopicID", checkbox_select[num].id);
		}
	}
	xhr.open("POST",
			"/bysjglxt/topic/TopicInformationManagement_deleteTopicList");
	xhr.send(formData);
}