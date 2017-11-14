var notice_json = null;

function List_Notice_By_PageAndSearch(pageIndex) {

	document.getElementById("i_pulse").style.display = "block";

	/*
	 * 
	 */
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				console.debug("通知:" + xhr.responseText);
				notice_json = JSON.parse(xhr.responseText);
				/*
				 * 
				 * 清空原表数据
				 * 
				 */
				var new_tr_list = document.getElementsByClassName("new_tr");
				var long = new_tr_list.length;
				for (var num = 0; num < long; num++) {
					new_tr_list[0].parentNode.removeChild(new_tr_list[0]);
				}
				/*
				 * 
				 * 
				 */
				var table_notice = document.getElementById("table_notice");
				var new_tr = null;
				var new_td = null;
				for (var num = 0; num < notice_json.listNoticeDTO.length; num++) {

					new_tr = document.createElement("tr");
					table_notice.firstElementChild.appendChild(new_tr);
					new_tr.className = "new_tr";
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = notice_json.listNoticeDTO[num].bysjglxt_notice.notice_content;
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = notice_json.listNoticeDTO[num].launchName;
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					if (notice_json.listNoticeDTO[num].bysjglxt_notice.notice_state == 2) {
						new_td.innerHTML = '<span class="label label-danger">未读</span>';
					} else {
						new_td.innerHTML = '<span class="label label-default">已读</span>';
					}
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = notice_json.listNoticeDTO[num].bysjglxt_notice.notice_gmt_create;
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<i class="fa fa-bell-slash" id="'
							+ notice_json.listNoticeDTO[num].bysjglxt_notice.notice_id
							+ '" onclick="readNotice(this)" style="cursor: pointer;" ></i>';
				}
				/*
				 * 设置页数
				 */
				document.getElementById("span_pageIndex").innerHTML = notice_json.pageIndex;
				document.getElementById("span_totalPages").innerHTML = notice_json.totalPages;
				document.getElementById("span_totalRecords").innerHTML = notice_json.totalRecords;
				// 让加载图标消失
				document.getElementById("i_pulse").style.display = "none";
				// 让全选框取消选择
				// document.getElementById("checkbox_all_select").checked =
				// false;
				/*
				 * 角色控制
				 */
				roleControl();
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST", "/bysjglxt/notice/NoticeManagement_ListNoticeByPage");

	var formData = new FormData();
	/*
	 * 
	 */
	if (pageIndex == null || pageIndex.preventDefault) {
		pageIndex = 1;
	}

	/*
	 * 
	 */
	formData.append("noticeVO.pageIndex", pageIndex);

	xhr.send(formData);

}
function flip(flipPage) {
	switch (flipPage) {
	case 1: {
		List_Notice_By_PageAndSearch(1)
		break;
	}
	case 2: {
		if (notice_json.pageIndex - 1 == 0) {
			toastr.warning("已经是第一页了");
		} else {
			List_Notice_By_PageAndSearch(notice_json.pageIndex - 1);
		}
		break;
	}
	case 3: {
		if (notice_json.pageIndex == notice_json.totalPages) {
			toastr.warning("已经是最后一页了");
		} else {
			List_Notice_By_PageAndSearch(notice_json.pageIndex + 1);
		}
		break;
	}
	case 4: {
		List_Notice_By_PageAndSearch(notice_json.totalPages);

		break;
	}

	}
}