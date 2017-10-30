window.onload = List_Section_By_Page;

var section_json = null;

function List_Section_By_Page(pageIndex) {

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
				alert(xhr.responseText);
				section_json = JSON.parse(xhr.responseText);

				/*
				 * 清空原表数据
				 */
				var new_tr_list = document.getElementsByClassName("new_tr");
				var long = new_tr_list.length;

				for (var num = 0; num < long; num++) {
					new_tr_list[0].parentNode.removeChild(new_tr_list[0]);
				}
				/*
				 * 
				 */
				var table_section = document.getElementById("table_section");

				for (var num = 0; num < section_json.list_SectionInformationDTO.length; num++) {

					var new_tr = document.createElement("tr");

					new_tr.appendChild(document.createTextNode(''));

					table_section.firstElementChild.appendChild(new_tr);

					new_tr.className = "new_tr";

					new_tr.innerHTML = '<td>'
							+ section_json.list_SectionInformationDTO[num].bysjglxtSection.section_name
							+ '</td><td>'
							+ '<label class="fancy-checkbox">'
							+ '<input id="'
							+ section_json.list_SectionInformationDTO[num].bysjglxtSection.section_id
							+ '" type="checkbox" class="checkbox_select">'
							+ '<span></span></label></td>';
				}

				/*
				 * 设置页数
				 */
				document.getElementById("span_pageIndex").innerHTML = section_json.pageIndex;
				document.getElementById("span_totalPages").innerHTML = section_json.totalPages;
				document.getElementById("span_totalRecords").innerHTML = section_json.totalRecords;

				// 让加载图标消失
				document.getElementById("i_pulse").style.display = "none";

				// 让全选框取消选择
				document.getElementById("checkbox_all_select").checked = false;

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST",
			"/bysjglxt/section/SectionInformationManagement_ListSectionByPage");

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

	/*
	 * 
	 */
	formData.append("sectionInformationManagementVO.pageIndex", pageIndex);

	xhr.send(formData);

}
function flip(flipPage) {
	switch (flipPage) {
	case 1: {
		List_Section_By_Page(1)
		break;
	}
	case 2: {
		if (section_json.pageIndex - 1 == 0) {
			toastr.warning("已经是第一页了");
		} else {
			List_Section_By_Page(section_json.pageIndex - 1);
		}
		break;
	}
	case 3: {
		if (section_json.pageIndex == section_json.totalPages) {
			toastr.warning("已经是最后一页了");
		} else {
			List_Section_By_Page(section_json.pageIndex + 1);
		}
		break;
	}
	case 4: {
		List_Section_By_Page(section_json.totalPages);

		break;
	}

	}
}