function Teacher_Information_Display(this_button) {

	var jc = $
			.confirm({
				columnClass : 'col-md-6 col-md-offset-3',
				title : '教师详细信息',
				content : '<form id="form_UpdateTeacher">'
						+ '<table id="table_teacher_detail" class="table table-hover table-bordered" style="text-align: center;">'
						+ '<tbody></tbody>' + '</table>' + '</form>',
				buttons : {
					'提交修改 ' : {
						btnClass : 'btn-blue',
						action : function() {
							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										if (xhr.responseText == "success") {
											toastr.success("更新成功");
											jc.close();
											List_Teacher_By_PageAndSearch(teacher_json.pageIndex);
											Teacher_Information_Display(this_button);
										}
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							var formData = new FormData(document
									.getElementById("form_UpdateTeacher"));
							xhr
									.open("POST",
											"/bysjglxt/teacher/TeacherInformationManagement_UpdateTeacherBasic");
							xhr.send(formData);
							/*
							 * 
							 */
							return false;
						}
					},
					'返回 ' : function() {
					}
				},
				onContentReady : function() {
					var new_tr_1 = null;
					var table_teacher_detail = null;
					table_teacher_detail = document
							.getElementById("table_teacher_detail");

					table_teacher_detail.firstElementChild.innerHTML = "";

					for (var num = 0; num < teacher_json.list_TeacherInformationDTO.length; num++) {

						if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_id == this_button.id) {
							/*
							 * 
							 */

							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<input name="updateTeacherBasic.teacher_basic_id"  style="text-align: center;display:none;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teacher_basic_id
									+ '"></input>';

							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>工号</th><td><input name="updateTeacherBasic.job_number"  style="text-align: center;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.job_number
									+ '"></input></td><th>姓名</th><td><input name="updateTeacherBasic.name"  style="text-align: center;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.name
									+ '"></input></td>';

							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>性别</th><td><input name="updateTeacherBasic.sex"  style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.sex
									+ '"></input></td><th>出生年月</th><td><input name="updateTeacherBasic.birthday"  style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.birthday
									+ '"></input></td>';

							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>入校时间</th><td><input name="updateTeacherBasic.induction_date" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.induction_date
									+ '"></input></td><th>任职状态</th><td><input name="updateTeacherBasic.job_statue" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.job_statue
									+ '"></input></td>';

							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>单位号</th><td><input name="updateTeacherBasic.unit_number"  style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.unit_number
									+ '"></input></td><th>单位名称</th><td><input name="updateTeacherBasic.unit_name"  style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.unit_name
									+ '"></input></td>';

							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>学历</th><td><input name="updateTeacherBasic.highest_education" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.highest_education
									+ '"></input></td><th>最高学位</th><td><input name="updateTeacherBasic.highest_degree" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.highest_degree
									+ '"></input></td>';

							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>学缘</th><td><input name="updateTeacherBasic.learn_edge_structure" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.learn_edge_structure
									+ '"></input></td><th>专业技术职称</th><td><input name="updateTeacherBasic.professional_title" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.professional_title
									+ '"></input></td>';

							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>学科类别</th><td><input name="updateTeacherBasic.subject_category" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.subject_category
									+ '"></input></td><th>任教类型</th><td><input name="updateTeacherBasic.teaching_type" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teaching_type
									+ '"></input></td>';

							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>任教专业名称</th><td><input name="updateTeacherBasic.teaching_profession_name" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teaching_profession_name
									+ '"></input></td><th>任教专业代码</th><td><input name="updateTeacherBasic.teaching_profession_no" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.teaching_profession_no
									+ '"></input></td>';

							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>专业任教时间</th><td><input name="updateTeacherBasic.profession_teaching_date" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.profession_teaching_date
									+ '"></input></td><th>是否实验技术人员</th><td><input name="updateTeacherBasic.experimental_technical_personnel" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.experimental_technical_personnel
									+ '"></input></td>';

							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>是否双师型</th><td><input name="updateTeacherBasic.double_teacher_type" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.double_teacher_type
									+ '"></input></td><th>是否工程背景</th><td><input name="updateTeacherBasic.engineering_background" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.engineering_background
									+ '"></input></td>';

							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>是否行业背景</th><td><input name="updateTeacherBasic.industry_background" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.industry_background
									+ '"></input></td><th>导师类别</th><td><input name="updateTeacherBasic.graduate_tutor_type" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.graduate_tutor_type
									+ '"></input></td>';

							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>校内指导博士生数</th><td><input name="updateTeacherBasic.number_of_doctor" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.number_of_doctor
									+ '"></input></td><th>校内指导硕士生数</th><td><input name="updateTeacherBasic.number_of_master" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.number_of_master
									+ '"></input></td>';

						}

					}
				}
			});

	/*
	 * 
	 */

}