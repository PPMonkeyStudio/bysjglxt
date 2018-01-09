function Student_Information_Display(this_button) {

	var jc = $
			.confirm({
				columnClass : 'col-md-6 col-md-offset-3',
				title : '学生详细信息',
				content : '<form id="form_UpdateStudent">'
						+ '<table id="table_student_detail" class="table table-hover table-bordered" style="text-align: center;">'
						+ '<tbody>' + '</tbody>' + '</table>' + '</form>',
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
											List_Student_By_PageAndSearch(student_json.pageIndex);
											Student_Information_Display(this_button);
										}
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							var formData = new FormData(document
									.getElementById("form_UpdateStudent"));
							xhr
									.open("POST",
											"/bysjglxt/student/StudentInformationManagement_UpdateStudent");
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
					var table_student_detail = null;
					table_student_detail = document
							.getElementById("table_student_detail");

					table_student_detail.firstElementChild.innerHTML = "";

					for (var num = 0; num < student_json.list_StudentInformationDTO.length; num++) {

						if (student_json.list_StudentInformationDTO[num].bysjglxtStudentUser.user_student_id == this_button.id) {
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<input name="updateStudent.student_basic_id"   style="text-align: center;display:none;" class="form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_id
									+ '"></input>';
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>学号</th><td><input name="updateStudent.student_basic_num"   style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_num
									+ '"></input></td><th>姓名</th><td><input name="updateStudent.student_basic_name"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_name
									+ '"></input></td>';
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>年制</th><td><input name="updateStudent.student_basic_year"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_year
									+ '"></input></td><th>年级</th><td><input name="updateStudent.student_basic_grade"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_grade
									+ '"></input></td>';
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>证件类型</th><td><input name="updateStudent.student_basic_idtype"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_idtype
									+ '"></input></td><th>身份证/护照号码</th><td><input name="updateStudent.student_basic_idcaard"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_idcaard
									+ '"></input></td>';
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>级别</th><td><input name="updateStudent.student_basic_level"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_level
									+ '"></input></td><th>性别</th><td><input name="updateStudent.student_basic_sex"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_sex
									+ '"></input></td>';
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>民族</th><td><input name="updateStudent.student_basic_nation"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_nation
									+ '"></input></td><th>政治面貌</th><td><input name="updateStudent.student_basic_politicalvisage"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_politicalvisage
									+ '"></input></td>';
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>籍贯</th><td><input name="updateStudent.student_basic_native_place" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_native_place
									+ '"></input></td><th>学生类型</th><td><input name="updateStudent.student_basic_studenttype"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_studenttype
									+ '"></input></td>';
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>招生类型</th><td><input name="updateStudent.student_basic_enrollmenttype" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_enrollmenttype
									+ '"></input></td><th>授课方式</th><td><input name="updateStudent.student_basic_teachingmethods" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_teachingmethods
									+ '"></input></td>';
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>专业代码</th><td><input name="updateStudent.student_basic_professionalcode" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_professionalcode
									+ '"></input></td><th>专业名称</th><td><input name="updateStudent.student_basic_major" style="text-align: center;" disabled="disabled" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_major
									+ '"></input></td>';
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>自主专业名称</th><td><input name="updateStudent.student_basic_independentmajorname" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_independentmajorname
									+ '"></input></td><th>是否是师范类专业</th><td><input name="updateStudent.student_basic_is_normalmajor" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_is_normalmajor
									+ '"></input></td>';
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>是否残疾</th><td><input name="updateStudent.student_basic_is_disability" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_is_disability
									+ '"></input></td><th>户口类型</th><td><input name="updateStudent.student_basic_householdregistrationtype" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_householdregistrationtype
									+ '"></input></td>';
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>异动类型</th><td><input name="updateStudent.student_basic_transactiontypes" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_transactiontypes
									+ '"></input></td><th>入学学历</th><td><input name="updateStudent.student_basic_entranceeducation" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_entranceeducation
									+ '"></input></td>';
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>招生方式</th><td><input name="updateStudent.student_basic_enrollmentmode" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_enrollmentmode
									+ '"></input></td><th>休退学原因</th><td><input name="updateStudent.student_basic_reasonsfordroppingoutofschool" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_reasonsfordroppingoutofschool
									+ '"></input></td>';
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>联系方式</th><td><input name="updateStudent.student_basic_phone"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_phone
									+ '"></input></td><th>学院</th><td><input name="updateStudent.student_basic_college"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_college
									+ '"></input></td>';
							/*
							 * 
							 */
						}

					}
				}
			});

}