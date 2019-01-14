function sendMessage() {

	var jc = $
			.confirm({
				title : '发送消息',
				columnClass:'col-md-8 col-md-offset-2',
				content : `
							<span>收件人</span><select data-live-search="true" class="form-control" style="width: 80%;" id="get_message_info">
								<option value="">--请选择--</option>
							</select>
							<br>
							<br>
							<span>正文</span><textarea class="form-control" rows="8" id="message_content"></textarea>
						`,
				buttons : {
					'发送 ' : {
						btnClass : 'btn-red',
						action : function() {
							var send_info = document.getElementById("get_message_info");
							var send_content = document.getElementById("message_content");
							var formData = new FormData();
							
							if(send_info != null){
								formData.append("sendString",send_info.value);
							}
							if(send_content != null){
								formData.append("message_content",send_content.value);
							}
							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										var role = xhr.responseText;
									}
								}
							}
							xhr.open("POST","/bysjglxt/notice/NoticeManagement_sendMessage");
							xhr.send(formData);
						}
					},
					'返回 ' : function() {
					}
				},
				onContentReady : function() {
					//college
					var college_id;
					if(userStudentDTO != null){
						college_id = userStudentDTO.bysjglxtStudentUser.user_student_belong_college;
					}else if(userTeacherDTO != null){
						college_id = userTeacherDTO.bysjglxtTeacherUser.user_teacher_belong_college;
					}
					console.log(college_id);
					//TODO 移除内容
					//
					var xhr = false;
					xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						var message;
						if (xhr.readyState == 4) {
							if (xhr.status == 200) {
								var role = xhr.responseText;
								var select = document.getElementById("get_message_info");
								console.log(role)
								switch(role){
								case "admin":
									addEWaiAdmin(select);
									addJiaoYanShiTeacher(select);
									addJiaoYanShiStudent(select);
									addEWaiTeacher(select);
									getAllTeacher(select,college_id);
									getAllStudent(select,college_id);
									break;
								case "student":
									addEWaiStudent(select);
									getAllTeacher(select,college_id);
									getAllStudent(select,college_id);
									break;
								case "teacher":
									addEWaiTeacher(select);
									getAllTeacher(select,college_id);
									getAllStudent(select,college_id);
									break;
								case "jiaoyanshi":
									addEWaiTeacher(select);
									addJiaoYanShiTeacher(select);
									addJiaoYanShiStudent(select);
									getAllTeacher(select,college_id);
									getAllStudent(select,college_id);
									break;
								}
							} else {
								toastr.error(xhr.status);
							}
						} 
					}
					xhr.open("POST","/bysjglxt/loginLogout/LoginLogoutManagement_getUserRole");
					xhr.send(null);
				}
			});

}
function addEWaiAdmin(select){
	var option = document.createElement("option");
	option.appendChild(document.createTextNode("学院所有师生"));
	select.appendChild(option);
	option.value = "AllStudentTeacher_";
	option = document.createElement("option");
	option.appendChild(document.createTextNode("学院所有老师"));
	select.appendChild(option);
	option.value = "AllTeacher_";
	option = document.createElement("option");
	option.appendChild(document.createTextNode("学院所有学生"));
	select.appendChild(option);
	option.value = "AllStudent_";
	$('select').selectpicker('refresh');
	$('select').selectpicker('render');
}
function addEWaiStudent(select){
	var option = document.createElement("option");
	option.appendChild(document.createTextNode("我的指导老师"));
	select.appendChild(option);
	option.value = "MyTutorTeacher_";
	$('select').selectpicker('refresh');
	$('select').selectpicker('render');
}
function addEWaiTeacher(select){
	var option = document.createElement("option");
	option.appendChild(document.createTextNode("我指导的学生"));
	select.appendChild(option);
	option.value = "MyTutorStudent_";
	$('select').selectpicker('refresh');
	$('select').selectpicker('render');
}
function addJiaoYanShiTeacher(select){
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var section_list = JSON.parse(xhr.responseText);
				if(section_list.length<=0){
					return;
				}
				for(var num=0;num<section_list.length;num++){
					var option = document.createElement("option");
					option.appendChild(document.createTextNode(section_list[num].section_name+"全体老师"));
					select.appendChild(option);
					option.value = "jiaoyanteacher_"+section_list[num].section_id;
				}
				$('select').selectpicker('refresh');
				$('select').selectpicker('render');
			}
		}
	}
	xhr.open("POST","/bysjglxt/section/SectionInformationManagement_getSectionByUser");
	xhr.send(null);
}
function addJiaoYanShiStudent(select){
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var section_list = JSON.parse(xhr.responseText);
				if(section_list.length<=0){
					return;
				}
				for(var num=0;num<section_list.length;num++){
					var option = document.createElement("option");
					option.appendChild(document.createTextNode(section_list[num].section_name+"全体学生"));
					select.appendChild(option);
					option.value = "jiaoyanstudent_"+section_list[num].section_id;
				}
				$('select').selectpicker('refresh');
				$('select').selectpicker('render');
			}
		}
	}
	xhr.open("POST","/bysjglxt/section/SectionInformationManagement_getSectionByUser");
	xhr.send(null);
}
function getAllTeacher(select,college_id){
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var teacher_list = JSON.parse(xhr.responseText);
				if(teacher_list.length<=0){
					return;
				}
				for(var num=0;num<teacher_list.length;num++){
					var option = document.createElement("option");
					option.appendChild(document.createTextNode(teacher_list[num].bysjglxtTeacherBasic.job_number+"_"+teacher_list[num].bysjglxtTeacherBasic.name));
					select.appendChild(option);
					option.value = "teacher_"+teacher_list[num].bysjglxtTeacherUser.user_teacher_id;
				}
				$('select').selectpicker('refresh');
				$('select').selectpicker('render');
			}
		}
	}
	var formData = new FormData();
	formData.append("college.college_id",college_id);
	xhr.open("POST","/bysjglxt/teacher/TeacherInformationManagement_listTeacherAllByCollege");
	xhr.send(formData);
}
function getAllStudent(select,college_id){
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var student_list = JSON.parse(xhr.responseText);
				if(student_list.length<=0){
					return;
				}
				for(var num=0;num<student_list.length;num++){
					var option = document.createElement("option");
					option.appendChild(document.createTextNode(student_list[num].bysjglxtStudentBasic.student_basic_num+"_"+student_list[num].bysjglxtStudentBasic.student_basic_name));
					select.appendChild(option);
					option.value = "student_"+student_list[num].bysjglxtStudentUser.user_student_id;
				}
				$('select').selectpicker('refresh');
				$('select').selectpicker('render');
			}
		}
	}
	var formData = new FormData();
	formData.append("studentUser.user_student_belong_college",college_id);
	xhr.open("POST","/bysjglxt/student/StudentInformationManagement_listStudentNoCloseByCollege");
	xhr.send(formData);
}