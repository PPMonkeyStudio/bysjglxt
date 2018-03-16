var major_json = null;

function List_Major_By_Page(pageIndex) {
	/*
	 * 
	 */
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				major_json = JSON.parse(xhr.responseText);
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
				var table_major = document.getElementById("table_major");

				var new_tr;
				var new_td;
				for (var num = 0; num < major_json.listMajorDTO.length; num++) {
					new_tr = document.createElement("tr");
					new_tr.appendChild(document.createTextNode(''));
					table_major.firstElementChild.appendChild(new_tr);
					new_tr.className = "new_tr";
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (major_json.listMajorDTO[num].bysjglxtMajor != undefined
							&& major_json.listMajorDTO[num].bysjglxtMajor.major_professionalcode != undefined) {
						new_td.innerHTML = major_json.listMajorDTO[num].bysjglxtMajor.major_professionalcode;
					} else {
						new_td.innerHTML = '无';
					}
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (major_json.listMajorDTO[num].bysjglxtMajor != undefined
							&& major_json.listMajorDTO[num].bysjglxtMajor.major_name != undefined) {
						new_td.innerHTML = major_json.listMajorDTO[num].bysjglxtMajor.major_name;
					} else {
						new_td.innerHTML = '无';
					}
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (major_json.listMajorDTO[num].bysjglxtSection != undefined
							&& major_json.listMajorDTO[num].bysjglxtSection.section_name != undefined) {
						new_td.innerHTML = major_json.listMajorDTO[num].bysjglxtSection.section_name;
					} else {
						new_td.innerHTML = '无';
					}
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					new_td.innerHTML = '';
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<label class="fancy-checkbox">'
							+ '<input id="'
							+ major_json.listMajorDTO[num].bysjglxtMajor.major_id
							+ '" type="checkbox" class="checkbox_select">'
							+ '<span></span></label></td>';

				}

				/*
				 * 设置页数
				 */
				document.getElementById("span_pageIndex").innerHTML = major_json.pageIndex;
				document.getElementById("span_totalPages").innerHTML = major_json.totalPages;
				document.getElementById("span_totalRecords").innerHTML = major_json.totalRecords;

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST", "/bysjglxt/major/MajorManagement_listMajorByPage");

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
	formData.append("majorVO.pageIndex", pageIndex);

	xhr.send(formData);

}
function flip(flipPage) {
	switch (flipPage) {
	case 1: {
		List_Major_By_Page(1)
		break;
	}
	case 2: {
		if (major_json.pageIndex - 1 == 0) {
			toastr.warning("已经是第一页了");
		} else {
			List_Major_By_Page(major_json.pageIndex - 1);
		}
		break;
	}
	case 3: {
		if (major_json.pageIndex == major_json.totalPages) {
			toastr.warning("已经是最后一页了");
		} else {
			List_Major_By_Page(major_json.pageIndex + 1);
		}
		break;
	}
	case 4: {
		List_Major_By_Page(major_json.totalPages);

		break;
	}

	}
}