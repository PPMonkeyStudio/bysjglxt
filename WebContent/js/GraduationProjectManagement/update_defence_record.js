function update_defence_record(button) {
	$
			.confirm({
				columnClass : 'col-md-6 col-md-offset-3',
				title : '答辩记录',
				content:`
							<b>陈述时间:</b><input type="text" id="defence_chenshu_time">
							<b>问答时间:</b><input type="text" id="defence_tiwen_time">
							<textarea class="form-control" style="resize: none;height:300px;" id="textarea_defence_record"></textarea>
						`,
				type : 'blue',
				buttons : {
					修改 : {
						btnClass : 'btn-blue',
						action : function() {
							var self = this;
							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										toastr.success("修改成功");
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							xhr
									.open("POST",
											"/bysjglxt/graduationProject/GraduationProjectManagement_updateDefenceRecorder");
							var formData = new FormData();
							var textarea_defence_record = document
									.getElementById("textarea_defence_record");
							var input_time1 = document.getElementById("defence_chenshu_time");
							formData.append("updateDefence.defence_chenshu_time",input_time1.value);
							var input_time2 = document.getElementById("defence_tiwen_time");
							formData.append("updateDefence.defence_tiwen_time",input_time2.value);
							formData.append("updateDefence.defence_student",
									button.id);
							formData.append("updateDefence.defence_record",
									textarea_defence_record.value);
							xhr.send(formData);
						}
					},
					取消 : function() {
					}
				},
				onContentReady : function() {
					var self = this;
					var xhr = false;
					xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						var message;
						if (xhr.readyState == 4) {
							if (xhr.status == 200) {
								var defence = JSON.parse(xhr.responseText);
								var textarea_defence_record = document
										.getElementById("textarea_defence_record");
								if (defence.defence_record != undefined) {
									textarea_defence_record.innerHTML = defence.defence_record;
								} else {
									textarea_defence_record.innerHTML = '';
								}
							} else {
								toastr.error(xhr.status);
							}
						}
					}
					xhr
							.open("POST",
									"/bysjglxt/graduationProject/GraduationProjectManagement_get_Defence");
					var formData = new FormData();
					formData.append("updateDefence.defence_student", button.id);
					xhr.send(formData);
				}
			});
}