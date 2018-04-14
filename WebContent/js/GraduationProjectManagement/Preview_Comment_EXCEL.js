function Preview_Comment_EXCEL(file) {

	remove_Preview_Comment_EXCEL();

	EXCEL_Comment_File = file;
	document.getElementById("i_pulse_2").style.display = "block";

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				EXCEL_Comment_List = JSON.parse(xhr.responseText);
				/*
				 * 将undefinded转化为空字符串
				 */
				for (var num = 0; num < EXCEL_Comment_List.length; num++) {
					if (EXCEL_Comment_List[num].comment_grade == undefined) {
						EXCEL_Comment_List[num].comment_grade = "";
					}
					if (EXCEL_Comment_List[num].comment_category == undefined) {
						EXCEL_Comment_List[num].comment_category = "";
					}
					if (EXCEL_Comment_List[num].comment_content == undefined) {
						EXCEL_Comment_List[num].comment_content = "";
					}

				}

				var table_excel_comment = document
						.getElementById("table_excel_comment");

				var new_th = document.createElement("tr");

				new_th.appendChild(document.createTextNode(''));

				table_excel_comment.firstElementChild.appendChild(new_th);

				new_th.innerHTML = '<th>类别</th>' + '<th>等级</th>'
						+ '<th>内容</th>';

				for (var num = 0; num < EXCEL_Comment_List.length; num++) {

					var new_tr = document.createElement("tr");

					new_tr.appendChild(document.createTextNode(''));

					table_excel_comment.firstElementChild.appendChild(new_tr);

					new_tr.innerHTML = '<td>'
							+ EXCEL_Comment_List[num].comment_grade
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_category
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_content + '</td>';
				}
				// 让加载图标消失
				document.getElementById("i_pulse_2").style.display = "none";

			} else {
				document.getElementById("i_pulse_2").style.display = "none";
				toastr.error(xhr.status);
			}
		}
	}

	xhr
			.open("POST",
					"/bysjglxt/graduationProject/GraduationProjectManagement_getPreviewListComment");

	var formData = new FormData();
	formData.append("EXCEL_Comment", file.files[0]);
	xhr.send(formData);
}