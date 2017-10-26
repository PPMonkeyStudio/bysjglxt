function Student_Information_Display(this_button) {

	$("#modal_Student_Information").modal("show");

	for (var num = 0; num < student_json.list_StudentInformationDTO.length; num++) {

		if (student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_num == this_button.id) {
			/*
			 * 
			 */
			var new_tr_1 = null;
			var table_student_detail = null;
			table_student_detail = document
					.getElementById("table_student_detail");

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_student_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>学号</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_num
					+ '</td><th>姓名</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_name
					+ '</td>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_student_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>年制</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basicd_year
					+ '</td><th>年级</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_grade
					+ '</td>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_student_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>证件类型</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_idtype
					+ '</td><th>身份证/护照号码</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_idcaard
					+ '</td>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_student_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>年龄</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_age
					+ '</td><th>性别</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_sex
					+ '</td>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_student_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>民族</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_nation
					+ '</td><th>政治面貌</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_politicalvisage
					+ '</td>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_student_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>籍贯</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_native_place
					+ '</td><th>学生类型</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_studenttype
					+ '</td>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_student_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>招生类型</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_enrollmenttype
					+ '</td><th>授课方式</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_teachingmethods
					+ '</td>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_student_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>专业代码</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_professionalcode
					+ '</td><th>专业名称</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_major
					+ '</td>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_student_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>自主专业名称</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_independentmajorname
					+ '</td><th>是否是师范类专业</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_is_normalmajor
					+ '</td>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_student_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>是否残疾</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_is_disability
					+ '</td><th>户口类型</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_householdregistrationtype
					+ '</td>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_student_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>异动类型</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_transactiontypes
					+ '</td><th>入学学历</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_entranceeducation
					+ '</td>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_student_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>招生方式</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_enrollmentmode
					+ '</td><th>休退学原因</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_reasonsfordroppingoutofschool
					+ '</td>';
			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_student_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>联系方式</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_phone
					+ '</td><th>学院</th><td>'
					+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_college
					+ '</td>';
		}

	}

	/*
	 * 
	 */

}