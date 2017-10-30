var EXCEL_Teacher_List = null;

var EXCEL_Teacher_File = null

function Preview_Teacher_EXCEL(file) {

	remove_Preview_Teacher_EXCEL();

	EXCEL_Teacher_File = file;
	document.getElementById("i_pulse_2").style.display = "block";

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				EXCEL_Teacher_List = JSON.parse(xhr.responseText);
				/*
				 * 将undefinded转化为空字符串
				 */
				for (var num = 0; num < EXCEL_Teacher_List.length; num++) {

				}

				var table_excel_Teacher = document
						.getElementById("table_excel_Teacher");

				var new_th = document.createElement("tr");

				new_th.appendChild(document.createTextNode(''));

				table_excel_Teacher.firstElementChild.appendChild(new_th);

				new_th.innerHTML = '<th>工号</th>' + '<th>姓名</th>'
						+ '<th>性别</th>' + '<th>出生年月</th>' + '<th>入校时间</th>'
						+ '<th>任职状态</th>' + '<th>单位号</th>' + '<th>单位名称</th>'
						+ '<th>学历</th>' + '<th>高学位</th>' + '<th>学缘</th>'
						+ '<th>专业技术职称</th>' + '<th>学科类别</th>' + '<th>任教类型</th>'
						+ '<th>任教专业名称</th>' + '<th>任教专业代码</th>'
						+ '<th>专业任教时间</th>' + '<th>是否实验技术人员</th>'
						+ '<th>是否双师型</th>' + '<th>是否工程背景</th>'
						+ '<th>是否行业背景</th>' + '<th>导师类别</th>'
						+ '<th>校内指导博士生数</th>' + '<th>校内指导硕士生数</th>';

				for (var num = 0; num < EXCEL_Teacher_List.length; num++) {

					var new_tr = document.createElement("tr");

					new_tr.appendChild(document.createTextNode(''));

					table_excel_Teacher.firstElementChild.appendChild(new_tr);

					new_tr.innerHTML = '<td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_num
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_name
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_year
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_grade
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_idtype
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_idcaard
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_age
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_sex
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_nation
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_politicalvisage
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_native_place
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_Teachertype
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_enrollmenttype
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_teachingmethods
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_professionalcode
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_major
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_independentmajorname
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_is_normalmajor
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_is_disability
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_householdregistrationtype
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_transactiontypes
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_entranceeducation
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_enrollmentmode
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_reasonsfordroppingoutofschool
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_phone
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].Teacher_basic_college
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
					"/bysjglxt/Teacher/TeacherInformationManagement_PreviewTeacherEXCEL");

	var formData = new FormData();
	formData.append("EXCEL_Teacher", file.files[0]);
	xhr.send(formData);
}

function remove_Preview_Teacher_EXCEL() {

	/*
	 * 清空input中的值
	 */
	document.getElementById('input_upload_Teacher_excel').outerHTML = document
			.getElementById('input_upload_Teacher_excel').outerHTML;

	/*
	 * 清空table中的html数据
	 */
	var table_excel_Teacher = document.getElementById("table_excel_Teacher");
	table_excel_Teacher.firstElementChild.innerHTML = '';

	/*
	 * 清除存在js中的值
	 */
	EXCEL_Teacher_List = null;

	toastr.success("数据已重置");

}

function Save_Teacher_EXCEL() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				if (xhr.responseText == 'success') {
					document.getElementById("i_pulse_2").style.display = "none";
					$("#modal_excel").modal("hide");
					toastr.success("导入成功");
					List_Teacher_By_PageAndSearch(1);
				} else {
					document.getElementById("i_pulse_2").style.display = "none";
				}
			} else {
				document.getElementById("i_pulse_2").style.display = "none";
				toastr.error(xhr.status);
			}
		}
	}

	xhr.open("POST",
			"/bysjglxt/Teacher/TeacherInformationManagement_SaveTeacherEXCEL");

	var formData = new FormData();
	formData.append("EXCEL_Teacher", EXCEL_Teacher_File.files[0]);
	xhr.send(formData);

	document.getElementById("i_pulse_2").style.display = "block";
}