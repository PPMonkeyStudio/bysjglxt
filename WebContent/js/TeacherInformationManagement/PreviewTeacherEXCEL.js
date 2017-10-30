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
							+ EXCEL_Teacher_List[num].job_number
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].name
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].sex
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].birthday
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].induction_date
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].job_statue
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].unit_number
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].unit_name
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].highest_education
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].highest_degree
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].learn_edge_structure
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].professional_title
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].subject_category
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].teaching_type
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].teaching_profession_name
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].teaching_profession_no
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].profession_teaching_date
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].experimental_technical_personnel
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].double_teacher_type
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].engineering_background
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].industry_background
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].graduate_tutor_type
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].number_of_doctor
							+ '</td><td>'
							+ EXCEL_Teacher_List[num].number_of_master
							+ '</td><td>';
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
					"/bysjglxt/teacher/TeacherInformationManagement_PreviewTeacherEXCEL");

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
			"/bysjglxt/teacher/TeacherInformationManagement_SaveTeacherEXCEL");

	var formData = new FormData();
	formData.append("EXCEL_Teacher", EXCEL_Teacher_File.files[0]);
	xhr.send(formData);

	document.getElementById("i_pulse_2").style.display = "block";
}