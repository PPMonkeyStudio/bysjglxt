function Teacher_Information_Display(this_button) {

	$("#modal_Teacher_Information").modal("show");

	// 将undefined换成空格
	for (var num = 0; num < teacher_json.list_TeacherInformationDTO.length; num++) {
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.name == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.name = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.sex == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.sex = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.birthday == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.birthday = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.induction_date == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.induction_date = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.job_statue == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.job_statue = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.unit_number == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.unit_number = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.unit_name == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.unit_name = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.highest_education == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.highest_education = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.highest_degree == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.highest_degree = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.learn_edge_structure == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.learn_edge_structure = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.professional_title == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.professional_title = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.subject_category == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.subject_category = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teaching_type == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teaching_type = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teaching_profession_name == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teaching_profession_name = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teaching_profession_no == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teaching_profession_no = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.profession_teaching_date == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.profession_teaching_date = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.experimental_technical_personnel == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.experimental_technical_personnel = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.double_teacher_type == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.double_teacher_type = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.engineering_background == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.engineering_background = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.industry_background == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.industry_background = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.graduate_tutor_type == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.graduate_tutor_type = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.number_of_doctor == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.number_of_doctor = "";
		}
		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.number_of_master == undefined) {
			teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.number_of_master = "";
		}
	}

	var new_tr_1 = null;
	var table_teacher_detail = null;
	table_teacher_detail = document.getElementById("table_teacher_detail");

	table_teacher_detail.firstElementChild.innerHTML = "";

	for (var num = 0; num < teacher_json.list_TeacherInformationDTO.length; num++) {

		if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_id == this_button.id) {
			/*
			 * 
			 */

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<input id="info_teacher_basic_id" style="text-align: center;display:none;" class="form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teacher_basic_id
					+ '"></input>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>工号</th><td><input id="info_job_number" style="text-align: center;" class="form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.job_number
					+ '"></input></td><th>姓名</th><td><input id="info_name" style="text-align: center;" class="form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.name
					+ '"></input></td>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>性别</th><td><input id="info_sex" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.sex
					+ '"></input></td><th>出生年月</th><td><input id="info_birthday" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.birthday
					+ '"></input></td>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>入校时间</th><td><input id="info_teacher_basic_idtype" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.induction_date
					+ '"></input></td><th>任职状态</th><td><input id="info_teacher_basic_idcaard" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.job_statue
					+ '"></input></td>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>单位号</th><td><input id="info_teacher_basic_age" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.unit_number
					+ '"></input></td><th>单位名称</th><td><input id="info_teacher_basic_sex" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.unit_name
					+ '"></input></td>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>学历</th><td><input id="info_teacher_basic_nation" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.highest_education
					+ '"></input></td><th>最高学位</th><td><input id="info_teacher_basic_politicalvisage" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.highest_degree
					+ '"></input></td>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>学缘</th><td><input id="info_teacher_basic_native_place" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.learn_edge_structure
					+ '"></input></td><th>专业技术职称</th><td><input id="info_teacher_basic_teachertype" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.professional_title
					+ '"></input></td>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>学科类别</th><td><input id="info_teacher_basic_enrollmenttype" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.subject_category
					+ '"></input></td><th>任教类型</th><td><input id="info_teacher_basic_teachingmethods" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teaching_type
					+ '"></input></td>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>任教专业名称</th><td><input id="info_teacher_basic_professionalcode" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teaching_profession_name
					+ '"></input></td><th>任教专业代码</th><td><input id="info_teacher_basic_major" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teaching_profession_no
					+ '"></input></td>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>专业任教时间</th><td><input id="info_teacher_basic_independentmajorname" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.profession_teaching_date
					+ '"></input></td><th>是否实验技术人员</th><td><input id="info_teacher_basic_is_normalmajor" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.experimental_technical_personnel
					+ '"></input></td>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>是否双师型</th><td><input id="info_teacher_basic_is_disability" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.double_teacher_type
					+ '"></input></td><th>是否工程背景</th><td><input id="info_teacher_basic_householdregistrationtype" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.engineering_background
					+ '"></input></td>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>是否行业背景</th><td><input id="info_teacher_basic_transactiontypes" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.industry_background
					+ '"></input></td><th>导师类别</th><td><input id="info_teacher_basic_entranceeducation" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.graduate_tutor_type
					+ '"></input></td>';

			new_tr_1 = document.createElement("tr");
			new_tr_1.appendChild(document.createTextNode(''));
			table_teacher_detail.firstElementChild.appendChild(new_tr_1)
			new_tr_1.innerHTML = '<th>校内指导博士生数</th><td><input id="info_teacher_basic_enrollmentmode" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.number_of_doctor
					+ '"></input></td><th>校内指导硕士生数</th><td><input id="info_teacher_basic_reasonsfordroppingoutofschool" style="text-align: center;" class="input_TeacherInformation form-control" disabled="disabled" value="'
					+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.number_of_master
					+ '"></input></td>';

		}

	}

	/*
	 * 
	 */

}