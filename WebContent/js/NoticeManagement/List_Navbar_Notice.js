function List_Navbar_Notice() {
	var xhr_num = false;
	xhr_num = new XMLHttpRequest();
	xhr_num.onreadystatechange = function() {
		if (xhr_num.readyState == 4) {
			if (xhr_num.status == 200) {
				var num_Navbar_Notice = document
						.getElementById("num_Navbar_Notice");
				num_Navbar_Notice.innerHTML = xhr_num.responseText;

				var xhr = false;
				xhr = new XMLHttpRequest();
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4) {
						if (xhr.status == 200) {
							console.debug("导航的通知:" + xhr.responseText);
							var navbarNotice_json = JSON
									.parse(xhr.responseText);
							/*
							 * 清空原表数据
							 */
							var il_Navbar_Notice = document
									.getElementsByClassName("il_Navbar_Notice");
							var long = il_Navbar_Notice.length;
							for (var num = 0; num < long; num++) {
								il_Navbar_Notice[0].parentNode
										.removeChild(il_Navbar_Notice[0]);
							}
							/*
							 * 
							 */

							/*
							 * 
							 */
							var num_Navbar_Notice = document
									.getElementById("num_Navbar_Notice")
							var ul_Navbar_Notice = document
									.getElementById("ul_Navbar_Notice");
							var new_li = null;
							for (var num = 0; num < navbarNotice_json.length; num++) {
								new_li = document.createElement("li");
								new_li.className = "il_Navbar_Notice";
								new_li.innerHTML = '<a href="#" class="notification-item">'
										+ '<span class="dot bg-success"></span>'
										+ navbarNotice_json[num].bysjglxt_notice.notice_content
										+ '</a>';
								ul_Navbar_Notice.insertBefore(new_li,
										ul_Navbar_Notice.firstChild);
							}

						} else {
							toastr.error(xhr.status);
						}
					}
				}
				var formData = new FormData();
				xhr.open("POST",
						"/bysjglxt/notice/NoticeManagement_ListNavbarNotice");
				xhr.send(formData);

			} else {
				toastr.error(xhr_num.status);
			}
		}
	}
	var formData = new FormData();
	xhr_num.open("POST", "/bysjglxt/notice/NoticeManagement_numNavbarNotice");
	xhr_num.send(formData);

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}