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
				
				List_MyManagementGraduationProject_By_PageAndSearch(1);
			}
		}
	}
	xhr.open("POST","/bysjglxt/graduationProject/GraduationProjectManagement_piliangAssign");
	xhr.send(formData);
}
function jianyan(){
	//TODO 判断数量是否为数字
}