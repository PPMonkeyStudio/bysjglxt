function CreatTaskDefinition() {
	$
			.confirm({
				title : '创建新的流程定义',
				content : '<table  class="table table-hover" style="text-align: center;">'
						+ '<tbody><br>'
						+ '<tr>任务定义名称：'
						+ '<input id="input_task_definition_name" style="margin:10px 0 10px;" class="form-control" required />'
						+ '</tr><br>'
						+ '<tr>选择任务的结构：'
						+ '<select  id="select_task_definition_type" class="form-control" style="width: auto;margin:10px 0 10px;">'
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
						+ '<select  id="" class="form-control" style="width: auto;margin:10px 0 10px;">'
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

						/*
						 * 
						 */
						formData
								.append(
										"newTaskDefinition.task_definition_name",
										document
												.getElementById("input_task_definition_name").value);
						/*
						 * 
						 */
						formData
								.append(
										"newTaskDefinition.task_definition_type",
										document
												.getElementById("select_task_definition_type").value);
						/*
						 * 
						 */
						formData
								.append(
										"newTaskDefinition.task_definition_process_definition",
										json_ProcessDefinition.bysjglxtProcessDefinition.process_definition_id);
						/*
						 * 
						 */
						formData
								.append(
										"newTaskDefinition.task_definition_role",
										document
												.getElementById("select_task_definition_role").value);
						/*
						 * 
						 */

						/*
						 * 
						 */

						/*
						 * 
						 */

						/*
						 * 
						 */
						xhr
								.open("POST",
										"/bysjglxt/process/ProcessManagement_CreatTaskDefinition");
						xhr.send(formData);

					},
					'取消' : function() {
					}

				}
			});

}
