window.onload = List_ProcessDefinition_By_PageAndSearch;

var processDefinition_json = null;

function List_ProcessDefinition_By_PageAndSearch(pageIndex) {

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
				processDefinition_json = JSON.parse(xhr.responseText);
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
				var table_processDefinition = document
						.getElementById("table_processDefinition");
				var new_tr = null;
				var new_td = null;
				for (var num = 0; num < processDefinition_json.list_ProcessDefinitionInformationDTO.length; num++) {

					new_tr = document.createElement("tr");

					new_tr.appendChild(document.createTextNode(''));
					table_processDefinition.firstElementChild
							.appendChild(new_tr);
					new_tr.className = "new_tr";

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<i style="cursor: pointer;" id="'
							+ processDefinition_json.list_ProcessDefinitionInformationDTO[num].bysjglxtProcessDefinitionUser.user_processDefinition_id
							+ '" onclick="ProcessDefinition_Information_Display(this)" class="fa fa-edit "></i>';

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<label class="fancy-checkbox">'
							+ '<input id="'
							+ processDefinition_json.list_ProcessDefinitionInformationDTO[num].bysjglxtProcessDefinitionUser.user_processDefinition_id
							+ '" type="checkbox" class="checkbox_select">'
							+ '<span></span></label>';

				}

				/*
				 * 设置页数
				 */
				document.getElementById("span_pageIndex").innerHTML = processDefinition_json.pageIndex;
				document.getElementById("span_totalPages").innerHTML = processDefinition_json.totalPages;
				document.getElementById("span_totalRecords").innerHTML = processDefinition_json.totalRecords;
				// 让加载图标消失
				document.getElementById("i_pulse").style.display = "none";
				// 让全选框取消选择
				document.getElementById("checkbox_all_select").checked = false;
				/*
				 * 角色控制
				 */
				roleControl();
			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST",
			"/bysjglxt/process/ProcessManagement_ListProcessDefinition");

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
	/*
	 * 
	 */
	formData.append("processDefinitionInformationManagementVO.pageIndex",
			pageIndex);

	xhr.send(formData);

}
function flip(flipPage) {
	switch (flipPage) {
	case 1: {
		List_ProcessDefinition_By_PageAndSearch(1)
		break;
	}
	case 2: {
		if (processDefinition_json.pageIndex - 1 == 0) {
			toastr.warning("已经是第一页了");
		} else {
			List_ProcessDefinition_By_PageAndSearch(processDefinition_json.pageIndex - 1);
		}
		break;
	}
	case 3: {
		if (processDefinition_json.pageIndex == processDefinition_json.totalPages) {
			toastr.warning("已经是最后一页了");
		} else {
			List_ProcessDefinition_By_PageAndSearch(processDefinition_json.pageIndex + 1);
		}
		break;
	}
	case 4: {
		List_ProcessDefinition_By_PageAndSearch(processDefinition_json.totalPages);

		break;
	}

	}
}