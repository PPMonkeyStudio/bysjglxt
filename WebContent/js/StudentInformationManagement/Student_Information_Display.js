;
var year = ['三年制','四年制','五年制'];
var grade = ['本科一年级','本科二年级','本科三年级','本科四年级'];
var idcard = ['身份证','护照'];
var sex = ['男','女'];
var is = ['是','否'];
var hukou = ['农村','城镇'];
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
							new_tr_1.innerHTML = '<th>学号</th><td><input disabled name="updateStudent.student_basic_num"   style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_num
									+ '"></input></td><th>姓名</th><td><input name="updateStudent.student_basic_name"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_name
									+ '"></input></td>';
							/*
							 * 
							 */
							/*new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild.appendChild(new_tr_1);
							
							let zhi = '<th>年制</th><td><select name="updateTeacherBasic.student_basic_year" class="form-control" style="width: auto;" id="studentBasicYear">';
							for(let i of year){
							    zhi += '<option value="'+i+'"';
							    if(i == student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_year){
							    	zhi += ' selected '
							    }
							    zhi += '>'+i+'</option>';
							}
							zhi += '</select></td><th>年级</th><td><select name="updateStudent.student_basic_grade" class="form-control" style="width: auto;" id="teacherBasicInfoXueWei">';
							for(let i of grade){
							    zhi += '<option value="'+i+'"';
							    if(i == student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_grade){
							    	zhi += ' selected '
							    }
							    zhi += '>'+i+'</option>';
							}
							new_tr_1.innerHTML = zhi;*/
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1);
							zhi = '<th>证件类型</th><td><select name="updateStudent.student_basic_idtype" class="form-control" style="width: auto;" id="studentBasicIdType">';
							for(let i of idcard){
							    zhi += '<option value="'+i+'"';
							    if(i == student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_idtype){
							    	zhi += ' selected '
							    }
							    zhi += '>'+i+'</option>';
							}
							zhi += '</select></td>'
								+ '<th>身份证/护照号码</th><td><input name="updateStudent.student_basic_idcaard"  style="text-align: center;" class="input_TeacherInformation form-control"  value="'
								+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_idcaard
								+ '"></input></td>';
							new_tr_1.innerHTML = zhi;
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1);
							zhi = '<th>级别</th><td colspan="3"><input disabled name="updateStudent.student_basic_level"  style="text-align: center;" class=" form-control"  value="'
								+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_level
								+ '"></input></td>';
							/*zhi += '<th>性别</th><select name="updateStudent.student_basic_sex" class="form-control" style="width: auto;" id="studentBasicSex">';
							for(let i of sex){
							    zhi += '<option value="'+i+'"';
							    if(i == student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_sex){
							    	zhi += ' selected '
							    }
							    zhi += '>'+i+'</option>';
							}*/
							zhi += '</select></td>';
							new_tr_1.innerHTML = zhi;
							/*
							 * 
							 */
							/*new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>民族</th><td><input name="updateStudent.student_basic_nation"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_nation
									+ '"></input></td><th>政治面貌</th><td><input name="updateStudent.student_basic_politicalvisage"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_politicalvisage
									+ '"></input></td>';*/
							/*
							 * 
							 */
							/*new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>籍贯</th><td><input name="updateStudent.student_basic_native_place" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_native_place
									+ '"></input></td><th>学生类型</th><td><input name="updateStudent.student_basic_studenttype"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_studenttype
									+ '"></input></td>';*/
							/*
							 * 
							 */
							/*new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>招生类型</th><td><input name="updateStudent.student_basic_enrollmenttype" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_enrollmenttype
									+ '"></input></td><th>授课方式</th><td><input name="updateStudent.student_basic_teachingmethods" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_teachingmethods
									+ '"></input></td>';*/
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>专业名称</th><td colspan="3"><select name="updateStudent.student_basic_major" class="form-control" style="width: auto;" id="studentBasicMajor"></select></td>';
							majorJ(student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_major);
							/*
							 * 
							 */
							/*new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1);
							zhi = '<th>自主专业名称</th><td><input name="updateStudent.student_basic_independentmajorname" style="text-align: center;" class=" form-control"  value="'
								+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_independentmajorname
								+ '"></input></td>';
							zhi += '<th>是否是师范类专业</th><td><select name="updateStudent.student_basic_is_normalmajor" class="form-control" style="width: auto;" id="studentBasicShi">';
							for(let i of is){
							    zhi += '<option value="'+i+'"';
							    if(i == student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_is_normalmajor){
							    	zhi += ' selected '
							    }
							    zhi += '>'+i+'</option>';
							}
							zhi += '</select></td>';
							new_tr_1.innerHTML = zhi;*/
							/*
							 * 
							 */
							/*new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1);
							zhi = '<th>是否残疾</th><td><select name="updateStudent.student_basic_is_disability" class="form-control" style="width: auto;" id="studentBasicDis">';
							for(let i of is){
							    zhi += '<option value="'+i+'"';
							    if(i == student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_is_disability){
							    	zhi += ' selected '
							    }
							    zhi += '>'+i+'</option>';
							}
							zhi += '</select></td>';
							zhi += '<th>户口类型</th><td><select name="updateStudent.student_basic_householdregistrationtype" class="form-control" style="width: auto;" id="studentBasicHu">';
							for(let i of hukou){
							    zhi += '<option value="'+i+'"';
							    if(i == student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_householdregistrationtype){
							    	zhi += ' selected '
							    }
							    zhi += '>'+i+'</option>';
							}
							zhi += '</select></td>';	
							new_tr_1.innerHTML = zhi;*/
							/*
							 * 
							 */
							/*new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>异动类型</th><td><input name="updateStudent.student_basic_transactiontypes" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_transactiontypes
									+ '"></input></td><th>入学学历</th><td><input name="updateStudent.student_basic_entranceeducation" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_entranceeducation
									+ '"></input></td>';*/
							/*
							 * 
							 */
							/*new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>招生方式</th><td><input name="updateStudent.student_basic_enrollmentmode" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_enrollmentmode
									+ '"></input></td><th>休退学原因</th><td><input name="updateStudent.student_basic_reasonsfordroppingoutofschool" style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_reasonsfordroppingoutofschool
									+ '"></input></td>';*/
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>电话号码</th><td><input name="updateStudent.student_basic_phone"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_phone
									+ '"></input></td><th>qq</th><td><input name="updateStudent.student_basic_qq"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_qq
									+ '"></input></td>';
							/*
							 * 
							 */
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_student_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>班级</th><td><input name="updateStudent.student_basic_class"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_class
									+ '"></input></td><th>学院</th><td><input disabled name="updateStudent.student_basic_college"  style="text-align: center;" class=" form-control"  value="'
									+ student_json.list_StudentInformationDTO[num].bysjglxtStudentBasic.student_basic_college
									+ '"></input></td>';
						}

					}
				}
			});
	

}
function majorJ(user_student_major){
	var xhr2 = false;
	xhr2 = new XMLHttpRequest();
	xhr2.onreadystatechange = function() {
		var message;
		if (xhr2.readyState == 4) {
			if (xhr2.status == 200) {
				SectionList = JSON.parse(xhr2.responseText);
				var sele = document.getElementById("studentBasicMajor");
				var k = '';
				for (var num = 0; num < SectionList.length; num++) {
					var option = '<option value="'+SectionList[num].major_professionalcode+'"'
					if(user_student_major == SectionList[num].major_name){
						option += 'selected'
					}
					option += '>'+SectionList[num].major_name+'</option>';
					k += option;
				}
				sele.innerHTML = k;
				$('#studentBasicMajor').selectpicker('refresh');
				$('#studentBasicMajor').selectpicker('render');
			} else {
				toastr.error(xhr2.status);
			}
		}
	}
	xhr2.open("POST","/bysjglxt/student/StudentInformationManagement_GetStudentMajor");
	xhr2.send(null);
}