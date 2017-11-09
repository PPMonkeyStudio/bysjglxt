function repulseTask(button) {
	$
			.confirm({
				title : '驳回任务',
				content : '操作不可撤回，是否驳回?',
				type : 'red',
				buttons : {
					'通过' : {
						btnClass : 'btn-red',
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
											"/bysjglxt/process/ProcessManagement_repulseTask");

							formData.append("repulseTaskID", button.id);

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