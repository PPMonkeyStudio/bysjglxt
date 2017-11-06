function CreatProcessDefinition() {
	$
			.confirm({
				title : '创建新的流程定义',
				content : '<table  class="table table-hover" style="text-align: center;">'
						+ '<tbody>'
						+ '<tr>'
						+ '<input id="input_ProcessDefinition_name" style="margin:10px 0 20px;" placeholder="流程定义名称" class="form-control" required />'
						+ '</tr>'
						+ '<tr>'
						+ '<select id="select_ProcessDefinition_role" class="form-control" style="width: auto;">'
						+ '<option value="1">领导小组组长</option>'
						+ '<option value="2">学生</option>'
						+ '</select>'
						+ '</tr>' + '</tbody>' + '</table>',
				buttons : {
					'确认创建' : function() {
						var xhr = false;
						xhr = new XMLHttpRequest();
						xhr.onreadystatechange = function() {
							var message;
							if (xhr.readyState == 4) {
								if (xhr.status == 200) {
									ProcessDefinitionDetail(xhr.responseText);
								} else {
									toastr.error(xhr.status);
								}
							}
						}
						var formData = new FormData();
						formData
								.append(
										"newProcessDefinition.process_definition_name",
										document
												.getElementById("input_ProcessDefinition_name").value);
						formData
								.append(
										"newProcessDefinition.process_definition_instance_role",
										document
												.getElementById("select_ProcessDefinition_role").value);
						xhr
								.open("POST",
										"/bysjglxt/process/ProcessManagement_CreatProcessDefinition");
						xhr.send(formData);

					},
					'取消' : function() {
					}

				}
			});
}
