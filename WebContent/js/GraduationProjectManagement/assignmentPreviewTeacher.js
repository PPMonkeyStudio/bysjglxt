function assignmentPreviewTeacher() {
	$.confirm({
				icon : 'fa fa-users',
				columnClass : 'col-md-12',
				theme : 'modern',
				title : '批量分配评阅老师',
				content :
					`
					<div style="float:left;margin:auto;width:75%;min-height:300px;">
						<div style="float:left;margin-left:50px;">
							<b>所属教研室</b><select name="updateTeacherUser.user_teacher_section" class="form-control" style="width: 200px;" id="teacherBasicInfoSection">
								
							</select>
						</div>
						<div style="float:left;margin-left:50px;">
							<b>学生数</b><input id="student_num" type="number" style="width:200px;" class="form-control"></input>
						</div>
						<div style="float:left;margin-left:50px;">
							<b>评阅老师数</b><input id="preview_num" type="number" style="width:200px;" class="form-control"></input>
						</div>
						<div style="float:right;margin-right:0px;">
							<button class="btn btn-success" id="piliang_assign_btn" onclick="piliangAssign()">
								批量分配
							</button>
						</div>
						<table id="table_teacher_assign" class="table table-hover table-bordered" style="text-align: center;">
							<tbody>
								
							</tbody>
						</table>
					</div>
					`,
				onContentReady : function() {
					var select = document.getElementById("teacherBasicInfoSection");
					Get_Teacher_Section(select);
				},
				buttons:{
					返回 : function() {
					}
				}
			});
}
function piliangAssign(){
	jianyan();
	var formData = new FormData();
	var teacherBasicInfoSection = document.getElementById("teacherBasicInfoSection");
	var student_num = document.getElementById("student_num");
	var preview_num = document.getElementById("preview_num");
	if(teacherBasicInfoSection.value != '' && teacherBasicInfoSection.value != null){
		formData.append("sectionId",teacherBasicInfoSection.value);
	}
	if(student_num.value != '' && student_num.value != null){
		formData.append("studentNum",student_num.value);
	}
	if(preview_num.value != '' && preview_num.value != null){
		formData.append("teacherNum",preview_num.value);
	}
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				//生成下面的表格
				var reviewInfo = JSON.parse(xhr.responseText);
				var new_tr_list = document.getElementsByClassName("new_select_student_topic");
				var long = new_tr_list.length;
				var table_topic = document.getElementById("table_teacher_assign");
				var new_tr = null;
				var new_td = null;
				for (var num = 0; num < long; num++) {
					new_tr_list[0].parentNode.removeChild(new_tr_list[0]);
				}
				new_tr = document.createElement("tr");
				new_tr.appendChild(document.createTextNode(''));
				table_topic.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_select_student_topic";
				new_tr.innerHTML='<th>序号</th><th>学号</th><th>姓名(学生)</th><th>指导老师姓名</th><th>评阅老师姓名</th><th>操作</th>';
				for (var num = 0; num < reviewInfo.length; num++) {
					new_tr = document.createElement("tr");
					new_tr.appendChild(document.createTextNode(''));
					table_topic.firstElementChild.appendChild(new_tr);
					new_tr.className = "new_select_student_topic";
					/*
					 * 
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = num+1;
					//
					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (reviewInfo != undefined
							&& reviewInfo[num].studentInformationDTO.bysjglxtStudentBasic.student_basic_num != "") {
						new_td.innerHTML = reviewInfo[num].studentInformationDTO.bysjglxtStudentBasic.student_basic_num;
					} else {
						new_td.innerHTML = '无';
					}
					//
					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (reviewInfo != undefined
							&& reviewInfo[num].studentInformationDTO.bysjglxtStudentBasic.student_basic_name != "") {
						new_td.innerHTML = reviewInfo[num].studentInformationDTO.bysjglxtStudentBasic.student_basic_name;
					} else {
						new_td.innerHTML = '无';
					}
					//
					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (reviewInfo != undefined
							&& reviewInfo[num].tutorTeacherInformationDTO.bysjglxtTeacherBasic.name != "") {
						new_td.innerHTML = reviewInfo[num].tutorTeacherInformationDTO.bysjglxtTeacherBasic.name;
					} else {
						new_td.innerHTML = '无';
					}
					//
					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					if (reviewInfo != undefined
							&& reviewInfo[num].reviewTeacherInformationDTO.bysjglxtTeacherBasic.name != "") {
						new_td.innerHTML = reviewInfo[num].reviewTeacherInformationDTO.bysjglxtTeacherBasic.name;
					} else {
						new_td.innerHTML = '无';
					}
					//
					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					new_td.innerHTML='<a onclick="updateTopicReview(this)" id="'
						+ reviewInfo[num].studentInformationDTO.bysjglxtStudentUser.user_student_id
						+ '">更改评阅教师</a>';
				}
				List_MyManagementGraduationProject_By_PageAndSearch(1);
			}
		}
	}
	xhr.open("POST","/bysjglxt/graduationProject/GraduationProjectManagement_piliangAssign");
	xhr.send(formData);
}
function updateTopicReview(obj){
	topicReview(obj,2);
}
function jianyan(){
	//TODO 判断数量是否为数字
}