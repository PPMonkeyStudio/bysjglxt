function BootProcess(button_Process) {

	$
			.confirm({
				title : '开启流程',
				content : '<input id="input_process_instance_name" class="form-control"   value="'
						+ button_Process.parentNode.id
						+ '——'
						+ (userStudentDTO == null ? userTeacherDTO.bysjglxtTeacherBasic.name
								: userStudentDTO.bysjglxtStudentBasic.student_basic_name)
						+ '" '
						+ (userTeacherDTO == null
								|| userTeacherDTO.bysjglxtLeader == null ? 'disabled="disabled"'
								: '') + ' ></input>',
				type : 'green',
				buttons : {
					'开启' : {
						btnClass : 'btn-green',
						action : function() {
							var xhr = false;
							var formData = new FormData();
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										window.location = '/bysjglxt/process/ProcessManagement_MyTask';
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							xhr
									.open("POST",
											"/bysjglxt/process/ProcessManagement_BootProcess");

							formData
									.append(
											"bootProcess.process_instance_process_definition",
											button_Process.id);
							formData.append(
									"bootProcess.process_instance_name",
									input_process_instance_name.value);
							xhr.send(formData);
						}
					},
					'取消' : function() {

					}

				},
				// 模态框加载完成之后触发的方法
				onContentReady : function() {

				}
			});

}