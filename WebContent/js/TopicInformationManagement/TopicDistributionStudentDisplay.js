function TopicDistributionStudentDisplay() {
	var jc = $
			.confirm({
				icon : 'fa fa-users',
				columnClass : 'col-md-6 col-md-offset-3 ',
				theme : 'modern',
				title : '指定学生提前选题',
				content : '<div style="width:370px;margin:10px auto;"><select multiple="multiple" id="select_distributionTopicStudent" name="select_distributionTopicStudent[]">'
						+ '</select></div>',
				type : 'orange',
				buttons : {
					保存 : {
						btnClass : 'btn-orange',
						action : function() {

						}
					},
					取消 : function() {
					}
				},
				onContentReady : function() {
					var xhr = false;
					xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						var message;
						if (xhr.readyState == 4) {
							if (xhr.status == 200) {
								var student_json = JSON.parse(xhr.responseText);
								for (var num = 0; num < student_json.length; num++) {
									$('#select_distributionTopicStudent')
											.multiSelect(
													'addOption',
													{
														value : student_json[num].bysjglxtStudentUser.user_student_id,
														text : student_json[num].bysjglxtStudentBasic.student_basic_name
													});
								}
								$('#select_distributionTopicStudent')
										.multiSelect({
											selectableHeader : "input",
											selectionHeader : "input",
										});
								$('#select_distributionTopicStudent')
										.multiSelect('refresh');
							} else {
								toastr.error(xhr.status);
							}
						}
					}
					xhr
							.open("POST",
									"/bysjglxt/student/StudentInformationManagement_listStudentNoClose");
					xhr.send(null);

				}
			});
}