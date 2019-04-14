function Teacher_Fix_User(this_button) {

	var jc = $
			.confirm({
				columnClass : 'col-md-6 col-md-offset-3',
				title : '教师其他信息',
				content : '<form id="form_UpdateTeacherUser">'
						+ '<table id="table_teacher_user" class="table table-hover table-bordered" style="text-align: center;">'
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
											Teacher_Fix_User(this_button);
										}
									} else {
										toastr.error(xhr.status);
									}
								}
							}

							var formData = new FormData(document
									.getElementById("form_UpdateTeacherUser"));
							xhr
									.open("POST",
											"/bysjglxt/teacher/TeacherInformationManagement_UpdateTeacherUser");
							xhr.send(formData);
							return false;
						}
					},
					'返回 ' : function() {
					}
				},
				onContentReady : function() {
					var new_tr_1 = null;
					var table_teacher_user = document
							.getElementById("table_teacher_user");

					table_teacher_user.firstElementChild.innerHTML = "";
					for (var num = 0; num < teacher_json.list_TeacherInformationDTO.length; num++) {

						if (teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_id == this_button.id) {
							/*
							 * 不参与更改的的隐藏的
							 */
							new_tr_1 = document.createElement("tr");
							table_teacher_user.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<input name="updateTeacherUser.user_teacher_id" style="text-align: center;display:none;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_id
									+ '"></input>';
							new_tr_1 = document.createElement("tr");
							table_teacher_user.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<input name="updateTeacherUser.user_teacher_num" style="text-align: center;display:none;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_num
									+ '"></input>';
							/*
							 * 密码
							 */
							new_tr_1 = document.createElement("tr");
							table_teacher_user.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<input name="updateTeacherUser.user_teacher_password"  style="text-align: center;display:none;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_password
									+ '"></input>';
							/*
							 * 基础信息
							 */
							new_tr_1 = document.createElement("tr");
							table_teacher_user.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<input name="updateTeacherUser.user_teacher_basic"  style="text-align: center;display:none;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_basic
									+ '"></input>';
							/*
							 * 指导学生
							 */
							new_tr_1 = document.createElement("tr");
							table_teacher_user.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<input name="updateTeacherUser.user_teacher_guidance_num"  style="text-align: center;display:none;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_guidance_num
									+ '"></input>';
							/*
							 * 学院
							 */
							new_tr_1 = document.createElement("tr");
							table_teacher_user.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<input name="updateTeacherUser.user_teacher_belong_college"  style="text-align: center;display:none;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_belong_college
									+ '"></input>';
							/*
							 * 管理员权限
							 */
							new_tr_1 = document.createElement("tr");
							table_teacher_user.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<input name="updateTeacherUser.user_teacher_is_college_admin"  style="text-align: center;display:none;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_is_college_admin
									+ '"></input>';
							/*
							 * 记录员
							 */
							new_tr_1 = document.createElement("tr");
							table_teacher_user.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<input name="updateTeacherUser.user_teacher_is_recorder"  style="text-align: center;display:none;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_is_recorder
									+ '"></input>';
							/*
							 * 答辩小组组长
							 */
							new_tr_1 = document.createElement("tr");
							table_teacher_user.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<input name="updateTeacherUser.user_teacher_is_defence_leader"  style="text-align: center;display:none;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_is_defence_leader
									+ '"></input>';
							/*
							 * 创建时间
							 */
							new_tr_1 = document.createElement("tr");
							table_teacher_user.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<input name="updateTeacherUser.user_teacher_gmt_create"  style="text-align: center;display:none;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_gmt_create
									+ '"></input>';
							/*
							 * 更改的
							 */
							new_tr_1 = document.createElement("tr");
							table_teacher_user.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>最多可指导的学生数</th><td><input name="updateTeacherUser.user_teacher_max_guidance"  style="text-align: center;" class="form-control"  value="'
									+ teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_max_guidance
									+ '"></input></td>';

							new_tr_1 = document.createElement("tr");
							table_teacher_user.firstElementChild
									.appendChild(new_tr_1)
							new_tr_1.innerHTML = '<th>所属教研室</th><td><select  style="text-align: center;" name="updateTeacherUser.user_teacher_section" class="form-control"  >'
									+ '<option class="option_info_section" value="">无</option>'
									+ '</select></td>';

							for (var num2 = 0; num2 < SectionList.length; num2++) {
								var option = document.createElement("option");
								option
										.appendChild(document
												.createTextNode(SectionList[num2].section_name));
								document
										.getElementsByName("updateTeacherUser.user_teacher_section")[0]
										.appendChild(option);
								option.value = SectionList[num2].section_id;
								option.className = "option_info_section";

								if (option.value == teacher_json.list_TeacherInformationDTO[num].bysjglxtTeacherUser.user_teacher_section) {
									option.selected = "selected";
								}
							}
						}

					}
				}
			});

}
