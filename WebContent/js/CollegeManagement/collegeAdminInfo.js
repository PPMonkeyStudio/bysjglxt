function collegeAdminInfo(collegeCode) {
	var jc = $
			.confirm({
				title : '账号信息',
				content:`
							<hr>
							<p style="text-align: center;">管理员账号信息</p>
							<table  class="table table-bordered" style="text-align: center;">
								<tbody>
									<tr>
										<td colspan="4">院系信息</td>
									</tr>
									<tr>
										<td>院系代码：</td><td><input id="college_code" input_explain="sads" name="college.college_code" class="input_not_empty form-control" disabled  /></td>
									</tr>
									<tr>
										<td>院系名称：</td><td><input id="college_name"  name="college.college_name" class="input_not_empty form-control"  disabled /></td>
									</tr>
									<tr>
										<td colspan="4">院系管理员</td>
									</tr>
									<tr>
										<td>工号：</td><td><input id="job_number" name="bysjglxt_teacher_basic.job_number" class="input_not_empty form-control" disabled /></td>
									</tr>
									<tr>
										<td>姓名：</td><td><input id="name" name="bysjglxt_teacher_basic.name" class="input_not_empty form-control" disabled /></td>
									</tr>
								</tbody>
							</table>
						`,
				type : 'red',
				onContentReady :function() {
					var xhr = false;
					xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						var message;
						if (xhr.readyState == 4) {
							if (xhr.status == 200) {
							var collegeInformationDTO = JSON.parse(xhr.responseText);
							$("#college_code").val(collegeInformationDTO.college.college_code);
							$("#college_name").val(collegeInformationDTO.college.college_name);
							$("#job_number").val(collegeInformationDTO.teacherInformationDTO.bysjglxtTeacherBasic.job_number);
							$("#name").val(collegeInformationDTO.teacherInformationDTO.bysjglxtTeacherBasic.name);
							// jc.close();
							// List_College();
							} else {
								toastr.error(xhr.status);
							}
						}
					}
					/*
					 * 
					 */
					/*if (!checkInputNotEmpty()) {
						return false;
					}*/
					/*
					 * 
					 */
					var formData = new FormData();
					formData.append("college.college_code",collegeCode);
					xhr.open("POST","/bysjglxt/college/CollegeManagement_getCollegeAdminInfoByCollegeCode");
					xhr.send(formData);
					return false;
				},
				buttons : {
					'确认' : function() {
					 },
					'取消' : function() {
					}

				}

			});
}