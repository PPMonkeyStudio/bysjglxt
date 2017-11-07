var myTask_json = null;
function List_MyTask(pageIndex) {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				alert(xhr.responseText);
				myTask_json = JSON.parse(xhr.responseText);
				/*
				 * 清空原表数据
				 */
				var new_tr_list = document.getElementsByClassName("new_tr");
				var long = new_tr_list.length;
				for (var num = 0; num < long; num++) {
					new_tr_list[0].parentNode.removeChild(new_tr_list[0]);
				}

				var table_myTask = document.getElementById("table_myTask");
				var new_tr = null;
				var new_td = null;
				for (var num = 0; num < myTask_json.list_ProcessDetailDTO.length; num++) {
					new_tr = document.createElement("tr");
					new_tr.appendChild(document.createTextNode(''));
					table_myTask.firstElementChild.appendChild(new_tr);
					new_tr.className = "new_tr";

					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					new_td.innerHTML = myTask_json.list_ProcessDetailDTO[num].bysjglxt_task_definition.task_definition_name;
				}
				/*
				 * 设置页数
				 */
				document.getElementById("span_pageIndex").innerHTML = myTask_json.pageIndex;
				document.getElementById("span_totalPages").innerHTML = myTask_json.totalPages;
				document.getElementById("span_totalRecords").innerHTML = myTask_json.totalRecords;
				// 让加载图标消失
				document.getElementById("i_pulse").style.display = "none";

				/*
				 * 角色控制
				 */
				roleControl();

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST", "/bysjglxt/process/ProcessManagement_ListMyTask");

	var formData = new FormData();

	formData.append("processManagementVO.pageIndex", pageIndex);

	xhr.send(formData);
}