function List_Navbar_Notice() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				console.debug("导航的通知:" + xhr.responseText);
				var navbarNotice_json = JSON.parse(xhr.responseText);
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
				var num_Navbar_Notice = document
						.getElementById("num_Navbar_Notice");

				num_Navbar_Notice.innerHTML = navbarNotice_json.length;
				/*
				 * 
				 */
				var ul_Navbar_Notice = document
						.getElementById("ul_Navbar_Notice");
				var new_li = null;
				for (var num = 0; num < navbarNotice_json.length; num++) {
					new_li = document.createElement("li");
					new_li.className = "il_Navbar_Notice";
					ul_Navbar_Notice.appendChild(new_li);
					new_li.innerHTML = '<a href="#" class="notification-item">'
							+ '<span class="dot bg-success"></span>'
							+ navbarNotice_json[num].notice_content + '</a>';
				}

			} else {
				toastr.error(xhr.status);
			}
		}
	}
	var formData = new FormData();
	xhr.open("POST", "/bysjglxt/notice/NoticeManagement_ListNavbarNotice");
	xhr.send(formData);
}