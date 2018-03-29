function passTask(button) {
	$
			.confirm({
				title : '通过任务',
				content : '操作不可撤回，是否通过?',
				type : 'green',
				buttons : {
					'通过' : {
						btnClass : 'btn-green',
						action : function() {
							var xhr = false;
							var formData = new FormData();
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										if (xhr.responseText != "success") {
											toastr.error(xhr.responseText);
										} else {
											/*
											 * 我忘记我为何要刷新页面，好像是因为有东西要刷新才能保证
											 */
											window.location = '/bysjglxt/process/ProcessManagement_MyTask';
										}
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							xhr
									.open("POST",
											"/bysjglxt/process/ProcessManagement_passTask");

							formData.append("passTaskID", button.id);

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