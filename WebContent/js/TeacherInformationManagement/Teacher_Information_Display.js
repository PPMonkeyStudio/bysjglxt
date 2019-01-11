;
var zhicheng = ['讲师','未评级','助教','其他初级','其他中级','副教授','教授'];
var xueli = ['硕士研究生','专科及以下','大学本科','博士研究生'];
var xuewei = ['硕士','无学位','学士','博士'];
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
							var formData = new FormData(document.getElementById("form_UpdateTeacher"));
							xhr.open("POST","/bysjglxt/teacher/TeacherInformationManagement_UpdateTeacherBasic");
							xhr.send(formData);
							/*
							 * 
							 */
							return false;
						}
					},
					'确定 ' : function() {
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
							new_tr_1.innerHTML = '<th>工号</th><td><input disabled name="updateTeacherBasic.job_number"  style="text-align: center;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.job_number
									+ '"></input></td><th>姓名</th><td><input name="updateTeacherBasic.name"  style="text-align: center;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.name
									+ '"></input></td>';

							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>性别</th><td><select name="updateTeacherBasic.sex" class="form-control" style="width: auto;" id="teacherBasicInfoSex"></select></td>'
									+ '<th>出生年月</th><td><input name="updateTeacherBasic.birthday"  style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.birthday
									+ '"></input></td>';
							if(teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.sex != null && teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.sex !=undefined){
								if(teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.sex == '男'){
									new_tr_1.innerHTML = '<th>性别</th><td><select name="updateTeacherBasic.sex" class="form-control" style="width: auto;" id="teacherBasicInfoSex"><option value="男" selected>男</option><option value="女">女</option></select></td>'
										+ '<th>出生年月</th><td><input name="updateTeacherBasic.birthday"  style="text-align: center;" class="input_TeacherInformation form-control"  value="'
										+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.birthday
										+ '"></input></td>';
								}else if(teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.sex == '女'){
									new_tr_1.innerHTML = '<th>性别</th><td><select name="updateTeacherBasic.sex" class="form-control" style="width: auto;" id="teacherBasicInfoSex"><option value="男">男</option><option value="女" selected>女</option></select></td>'
										+ '<th>出生年月</th><td><input name="updateTeacherBasic.birthday"  style="text-align: center;" class="input_TeacherInformation form-control"  value="'
										+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.birthday
										+ '"></input></td>';
								}else{
									new_tr_1.innerHTML = '<th>性别</th><td><select name="updateTeacherBasic.sex" class="form-control" style="width: auto;" id="teacherBasicInfoSex"><option value="男">男</option><option value="女">女</option></select></td>'
										+ '<th>出生年月</th><td><input name="updateTeacherBasic.birthday"  style="text-align: center;" class="input_TeacherInformation form-control"  value="'
										+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.birthday
										+ '"></input></td>';
								}
							}
							
							
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
							table_teacher_detail.firstElementChild.appendChild(new_tr_1);
							var zhi = '<th>专业技术职称</th><td><select name="updateTeacherBasic.professional_title" class="form-control" style="width: auto;" id="teacherBasicInfoSex">';
							for(let i of zhicheng){
							    zhi += '<option value="'+i+'"';
							    if(i == teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.professional_title){
							    	zhi += ' selected '
							    }
							    zhi += '>'+i+'</option>';
							}
							zhi += '</select></td>'
								+ '<th>单位名称</th><td><input disabled name="updateTeacherBasic.unit_name"  style="text-align: center;" class="input_TeacherInformation form-control"  value="'
								+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.unit_name
								+ '"></input></td>';
							new_tr_1.innerHTML = zhi;
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1);
							zhi = '';
							zhi += '<th>学历</th><td><select name="updateTeacherBasic.highest_education" class="form-control" style="width: auto;" id="teacherBasicInfoXueLi">';
							for(let i of xueli){
							    zhi += '<option value="'+i+'"';
							    if(i == teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.highest_education){
							    	zhi += ' selected '
							    }
							    zhi += '>'+i+'</option>';
							}
							zhi += '</select></td><th>最高学位</th><td><select name="updateTeacherBasic.highest_degree" class="form-control" style="width: auto;" id="teacherBasicInfoXueWei">';
							for(let i of xuewei){
							    zhi += '<option value="'+i+'"';
							    if(i == teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.highest_degree){
							    	zhi += ' selected '
							    }
							    zhi += '>'+i+'</option>';
							}
							zhi += '</select></td>';
							console.log(zhi)
							new_tr_1.innerHTML = zhi;
							zhi = '';
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild
									.appendChild(new_tr_1);
							var qq = teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.qq;
							if(qq == null || qq == undefined){
								qq = "无";
							}
							var phone = teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherBasic.phone;
							if(phone == null || phone == undefined){
								phone = "无";
							}
							new_tr_1.innerHTML = '<th>QQ</th><td><input name="updateTeacherBasic.qq" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ qq
									+ '"></input></td><th>电话号码</th><td><input name="updateTeacherBasic.phone" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ phone
									+ '"></input></td>';
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild.appendChild(new_tr_1);
							new_tr_1.innerHTML = '<th>所属教研室</th><td colspan="3"><select name="updateTeacherUser.user_teacher_section" class="form-control" style="width: auto;" id="teacherBasicInfoSection"></select></td>';
							sectionJ(teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_section);
							new_tr_1 = document.createElement("tr");
							new_tr_1.appendChild(document.createTextNode(''));
							table_teacher_detail.firstElementChild.appendChild(new_tr_1);
							new_tr_1.innerHTML = '<th>最多可指导学生数</th><td colspan="3"><input name="updateTeacherUser.user_teacher_max_guidance" style="text-align: center;" class="input_TeacherInformation form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_max_guidance
									+ '"></input></td>';
						}
					}
				}
			});
}
function sectionJ(user_teacher_section){
	var xhr2 = false;
	xhr2 = new XMLHttpRequest();
	xhr2.onreadystatechange = function() {
		var message;
		if (xhr2.readyState == 4) {
			if (xhr2.status == 200) {
				SectionList = JSON.parse(xhr2.responseText);
				var sele = document.getElementById("teacherBasicInfoSection");
				var k = '';
				for (var num = 0; num < SectionList.length; num++) {
					var option = '<option value="'+SectionList[num].section_id+'"'
					if(user_teacher_section == SectionList[num].section_id){
						option += 'selected'
					}
					option += '>'+SectionList[num].section_name+'</option>';
					k += option;
				}
				console.log(k)
				sele.innerHTML = k;
				$('#teacherBasicInfoSection').selectpicker('refresh');
				$('#teacherBasicInfoSection').selectpicker('render');
			} else {
				toastr.error(xhr2.status);
			}
		}
	}
	xhr2.open("POST","/bysjglxt/teacher/TeacherInformationManagement_GetTeacherSection");
	xhr2.send(null);
}
