function Update_Major(thisMajorID) {

	var jc = $
			.confirm({
				title : '修改专业',
				type : 'blue',
				content : ''
						+ '<table  class="table table-bordered" style="text-align: center;">'
						+ '<tbody>'
						+ '<tr><input id="input_major_id" class="form-control" style="display:none;" />'
						+ '<td>专业代码：</td>'
						+ '<td><input id="input_major_professionalcode" class="form-control" /></td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td>专业名称：</td>'
						+ '<td><input id="input_major_name" class="form-control" /></td>'
						+ '</tr>'
						+ '<tr>'
						+ '<td>所属教研室：</td>'
						+ '<td><select id="select_section" class="form-control" >'
						+ '</select></td>' + '</tr>' + '</tbody>' + '</table>',
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
										if (xhr.responseText == "1") {
											toastr.success("已修改", "操作成功");
											List_Major_By_Page(major_json.pageIndex);
											jc.close();
										}
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							var formData = new FormData();
							/*
							 * 不可以更改
							 */
							formData.append("bysjglxtMajor.major_id", document
									.getElementById("input_major_id").value);

							/*
							 * 可以更改
							 */
							formData
									.append(
											"bysjglxtMajor.major_professionalcode",
											document
													.getElementById("input_major_professionalcode").value);
							formData
									.append(
											"bysjglxtMajor.major_name",
											document
													.getElementById("input_major_name").value);
							formData
									.append(
											"bysjglxtMajor.major_belong_section",
											document
													.getElementById("select_section").value);
							/*
							 * 
							 */
							xhr
									.open("POST",
											"/bysjglxt/major/MajorManagement_updateMajor");

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
					for (var num_1 = 0; num_1 < major_json.listMajorDTO.length; num_1++) {
						if (major_json.listMajorDTO[num_1].bysjglxtMajor.major_id == thisMajorID.id) {
							break;
						}

					}
					/*
					 * 
					 */
					var input_major_id = document
							.getElementById("input_major_id");
					var input_major_professionalcode = document
							.getElementById("input_major_professionalcode");
					var input_major_name = document
							.getElementById("input_major_name");
					var select_section = document
							.getElementById("select_section");
					input_major_id.value = major_json.listMajorDTO[num_1].bysjglxtMajor.major_id;
					input_major_professionalcode.value = major_json.listMajorDTO[num_1].bysjglxtMajor.major_professionalcode;
					input_major_name.value = major_json.listMajorDTO[num_1].bysjglxtMajor.major_name;
					/*
					 * 获取教研室列表
					 */
					var xhr = false;
					xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						var message;
						if (xhr.readyState == 4) {
							if (xhr.status == 200) {
								var SectionList = JSON.parse(xhr.responseText);
								for (var num = 0; num < SectionList.length; num++) {
									var option = document
											.createElement("option");
									option
											.appendChild(document
													.createTextNode(SectionList[num].section_name));
									select_section.appendChild(option);
									option.value = SectionList[num].section_id;
									if (major_json.listMajorDTO[num_1].bysjglxtMajor.major_belong_section == SectionList[num].section_id) {
										option.selected = "selected";
									}
								}
							} else {
								toastr.error(xhr.status);
							}
						}
					}

					xhr
							.open("POST",
									"/bysjglxt/teacher/TeacherInformationManagement_GetTeacherSection");

					xhr.send(null);
					/*
					 * 
					 */
				}
			});
}