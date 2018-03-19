function readNotice(button) {
	var xhr = false;
	var formData = new FormData();
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				List_Notice_By_PageAndSearch(notice_json.pageIndex);
				List_Navbar_Notice();
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	xhr.open("POST", "/bysjglxt/notice/NoticeManagement_readNotice");

	formData.append("readNoticeID", button.id);

	xhr.send(formData);
}