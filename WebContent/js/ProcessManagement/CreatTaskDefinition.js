function CreatTaskDefinition() {

	if (userStudentDTO != null) {
		toastr.error("无权限");
		return;
	}

	$
			.confirm({
				title : '创建任务节点',
				content : '<table  class="table table-hover" style="text-align: center;">'
						+ '<tbody><br>'
						+ '<tr>任务定义名称：'
						+ '<input id="input_task_definition_name" style="margin:10px 0 10px;" class="form-control" required />'
						+ '</tr><br>'
						+ '<tr>选择任务的结构：'
						+ '<select  id="select_task_definition_type" class="form-control" style="width: auto;margin:10px 0 10px;">'
						+ '<option value="1">顺序结构</option>'
						+ '<option value="2">选择结构</option>'
						+ '</select>'
						+ '</tr><br>'
						+ '<tr>选择任务执行的角色：'
						+ '<select  id="select_task_definition_role" class="form-control" style="width: auto;margin:10px 0 10px;">'
						+ '<option value="1">指导老师</option>'
						+ '<option value="2">评阅老师</option>'
						+ '<option value="3">领导小组组长</option>'
						+ '<option value="4">教研室主任</option>'
						+ '<option value="5">学生</option>'
						+ '</select>'
						+ '</tr><br>'
						+ '<tr>选择任务返回的节点：'
						+ '<select  id="select_task_definition_return" class="form-control" style="width: auto;margin:10px 0 10px;">'
						+ '</select>' + '</tr>' + '</tbody>' + '</table>',
				buttons : {
					'确认创建' : function() {
						var xhr = false;
						xhr = new XMLHttpRequest();
						xhr.onreadystatechange = function() {
							var message;
							if (xhr.readyState == 4) {
								if (xhr.status == 200) {
									ProcessDefinitionDetail(json_ProcessDefinition.bysjglxtProcessDefinition.process_definition_id);
								} else {
									toastr.error(xhr.status);
								}
							}
						}
						var formData = new FormData();
						formData
								.append(
										"newTaskDefinition.task_definition_name",
										document
												.getElementById("input_task_definition_name").value);
						formData
								.append(
										"newTaskDefinition.task_definition_type",
										document
												.getElementById("select_task_definition_type").value);
						formData
								.append(
										"newTaskDefinition.task_definition_process_definition",
										json_ProcessDefinition.bysjglxtProcessDefinition.process_definition_id);
						formData
								.append(
										"newTaskDefinition.task_definition_role",
										document
												.getElementById("select_task_definition_role").value);
						/*
						 * 如果是顺序结构则不传return的节点
						 */
						if (document
								.getElementById("select_task_definition_type").value == "1") {
							// 不给，如果给null会给字符串
						} else {
							formData
									.append(
											"newTaskDefinition.task_definition_return",
											document
													.getElementById("select_task_definition_return").value);
						}

						xhr
								.open("POST",
										"/bysjglxt/process/ProcessManagement_CreatTaskDefinition");
						xhr.send(formData);

					},
					'取消' : function() {
					}

				},
				// 模态框加载完成之后触发的方法
				onContentReady : function() {
					var select_task_definition_return = document
							.getElementById("select_task_definition_return");
					for (var num = 0; num < json_ProcessDefinition.List_bysjglxtTaskDefinition.length; num++) {
						var option = document.createElement("option");
						option
								.appendChild(document
										.createTextNode(json_ProcessDefinition.List_bysjglxtTaskDefinition[num].task_definition_name));
						option.value = json_ProcessDefinition.List_bysjglxtTaskDefinition[num].task_definition_id;
						select_task_definition_return.appendChild(option);
					}
				}
			});
}
