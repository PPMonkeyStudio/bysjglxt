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
					if (EXCEL_Comment_List[num].comment_basic_num == undefined) {
						EXCEL_Comment_List[num].comment_basic_num = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_name == undefined) {
						EXCEL_Comment_List[num].comment_basic_name = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_year == undefined) {
						EXCEL_Comment_List[num].comment_basic_year = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_grade == undefined) {
						EXCEL_Comment_List[num].comment_basic_grade = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_idtype == undefined) {
						EXCEL_Comment_List[num].comment_basic_idtype = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_idcaard == undefined) {
						EXCEL_Comment_List[num].comment_basic_idcaard = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_age == undefined) {
						EXCEL_Comment_List[num].comment_basic_age = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_sex == undefined) {
						EXCEL_Comment_List[num].comment_basic_sex = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_nation == undefined) {
						EXCEL_Comment_List[num].comment_basic_nation = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_politicalvisage == undefined) {
						EXCEL_Comment_List[num].comment_basic_politicalvisage = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_native_place == undefined) {
						EXCEL_Comment_List[num].comment_basic_native_place = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_commenttype == undefined) {
						EXCEL_Comment_List[num].comment_basic_commenttype = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_enrollmenttype == undefined) {
						EXCEL_Comment_List[num].comment_basic_enrollmenttype = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_teachingmethods == undefined) {
						EXCEL_Comment_List[num].comment_basic_teachingmethods = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_professionalcode == undefined) {
						EXCEL_Comment_List[num].comment_basic_professionalcode = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_major == undefined) {
						EXCEL_Comment_List[num].comment_basic_major = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_independentmajorname == undefined) {
						EXCEL_Comment_List[num].comment_basic_independentmajorname = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_is_normalmajor == undefined) {
						EXCEL_Comment_List[num].comment_basic_is_normalmajor = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_is_disability == undefined) {
						EXCEL_Comment_List[num].comment_basic_is_disability = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_householdregistrationtype == undefined) {
						EXCEL_Comment_List[num].comment_basic_householdregistrationtype = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_transactiontypes == undefined) {
						EXCEL_Comment_List[num].comment_basic_transactiontypes = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_entranceeducation == undefined) {
						EXCEL_Comment_List[num].comment_basic_entranceeducation = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_enrollmentmode == undefined) {
						EXCEL_Comment_List[num].comment_basic_enrollmentmode = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_reasonsfordroppingoutofschool == undefined) {
						EXCEL_Comment_List[num].comment_basic_reasonsfordroppingoutofschool = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_phone == undefined) {
						EXCEL_Comment_List[num].comment_basic_phone = "";
					}
					if (EXCEL_Comment_List[num].comment_basic_college == undefined) {
						EXCEL_Comment_List[num].comment_basic_college = "";
					}

				}

				var table_excel_comment = document
						.getElementById("table_excel_comment");

				var new_th = document.createElement("tr");

				new_th.appendChild(document.createTextNode(''));

				table_excel_comment.firstElementChild.appendChild(new_th);

				new_th.innerHTML = '<th>学号</th>' + '<th>姓名</th>'
						+ '<th>年制</th>' + '<th>年级</th>' + '<th>证件类型</th>'
						+ '<th>身份证/护照号码</th>' + '<th>年龄</th>' + '<th>性别</th>'
						+ '<th>民族</th>' + '<th>政治面貌</th>' + '<th>生源地</th>'
						+ '<th>学生类型</th>' + '<th>招生类型</th>' + '<th>授课方式</th>'
						+ '<th>专业代码</th>' + '<th>专业名称</th>' + '<th>自主专业名称</th>'
						+ '<th>是否师范类</th>' + '<th>是否残疾</th>' + '<th>户口类型</th>'
						+ '<th>异动类型</th>' + '<th>入学学历</th>' + '<th>招生方式</th>'
						+ '<th>休退学原因</th>' + '<th>联系方式</th>' + '<th>学院</th>';

				for (var num = 0; num < EXCEL_Comment_List.length; num++) {

					var new_tr = document.createElement("tr");

					new_tr.appendChild(document.createTextNode(''));

					table_excel_comment.firstElementChild.appendChild(new_tr);

					new_tr.innerHTML = '<td>'
							+ EXCEL_Comment_List[num].comment_basic_num
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_name
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_year
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_grade
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_idtype
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_idcaard
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_age
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_sex
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_nation
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_politicalvisage
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_native_place
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_commenttype
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_enrollmenttype
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_teachingmethods
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_professionalcode
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_major
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_independentmajorname
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_is_normalmajor
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_is_disability
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_householdregistrationtype
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_transactiontypes
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_entranceeducation
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_enrollmentmode
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_reasonsfordroppingoutofschool
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_phone
							+ '</td><td>'
							+ EXCEL_Comment_List[num].comment_basic_college
							+ '</td>';
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
					"/bysjglxt/graduationProject/GraduationProjectManagement_PreviewCommentEXCEL");

	var formData = new FormData();
	formData.append("EXCEL_Comment", file.files[0]);
	xhr.send(formData);
}