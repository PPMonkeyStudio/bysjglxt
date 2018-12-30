function update_defence_leader(button) {
	$
			.confirm({
				columnClass : 'col-md-12',
				title : '',
				content : '<h5>答辩评语：</h5>'
						+ '<textarea class="form-control" style="resize: none;height:200px;" id="textarea_defence_leader_comment"></textarea>'
						+ '<h5>答辩评分：</h5>'
						+ '<table id="table_defence_leader" class="table table-bordered table-hover" style="text-align: center;">'
						+ '<tbody>' + '</tbody>' + '</table>',
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
							xhr.open("POST","/bysjglxt/graduationProject/GraduationProjectManagement_updateDefence");
							var formData = new FormData();
							var textarea_defence_leader_comment = document
									.getElementById("textarea_defence_leader_comment");
							formData.append("updateDefence.defence_student",button.id);
							formData.append("updateDefence.defence_record",textarea_defence_leader_comment.value);
							formData.append("updateDefence.defence_leader_comment",textarea_defence_leader_comment.value);
							formData.append("updateDefence.defence_grade_writing",document.getElementById("defence_grade_writing").value);
							formData.append("updateDefence.defence_grade_normalization",document.getElementById("defence_grade_normalization").value);
							formData.append("updateDefence.defence_grade_complete",document.getElementById("defence_grade_complete").value);
							formData.append("updateDefence.defence_grade_technology",document.getElementById("defence_grade_technology").value);
							formData.append("updateDefence.defence_grade_practicability",document.getElementById("defence_grade_practicability").value);
							formData.append("updateDefence.defence_grade_appearance",document.getElementById("defence_grade_appearance").value);
							formData.append("updateDefence.defence_grade_statement",document.getElementById("defence_grade_statement").value);
							formData.append("updateDefence.defence_grade_answer",document.getElementById("defence_grade_answer").value);
							formData.append("updateDefence.defence_grade_defence",document.getElementById("defence_grade_defence").value);
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

								/*
								 * 
								 */
								var table_defence_leader = document
										.getElementById("table_defence_leader");

								var new_tr = document.createElement("tr");
								table_defence_leader.firstChild
										.appendChild(new_tr);
								new_tr.innerHTML = '<td>考核项目</td>'
										+ '<td>满分</td>' + '<td>评分</td>';
								/*
								 * 
								 * 
								 * 
								 */
								var new_tr = document.createElement("tr");
								table_defence_leader.firstChild
										.appendChild(new_tr);
								var new_td = document.createElement("td");
								new_td.innerHTML = '写作水平';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								new_td.innerHTML = '10';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								switch (defence.defence_grade_writing) {
								case 0: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()" class="form-control" id="defence_grade_writing" >'
											+ '<option value="0" selected="selected">0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '</select>';
									break;
								}
								case 1: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_writing" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1"  selected="selected">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '</select>';
									break;
								}
								case 2: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_writing" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2" selected="selected">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '</select>';
									break;
								}
								case 3: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_writing" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3" selected="selected">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '</select>';
									break;
								}
								case 4: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_writing" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4" selected="selected">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10" >10</option>'
											+ '</select>';
									break;
								}
								case 5: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_writing" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5" selected="selected">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10" >10</option>'
											+ '</select>';
									break;
								}
								case 6: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_writing" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6" selected="selected">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10" >10</option>'
											+ '</select>';
									break;
								}
								case 7: {
									new_td.innerHTML = '<select onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_writing" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7" selected="selected">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10" >10</option>'
											+ '</select>';
									break;
								}
								case 8: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_writing" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8" selected="selected">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10" >10</option>'
											+ '</select>';
									break;
								}
								case 9: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_writing" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9" selected="selected">9</option>'
											+ '<option value="10" >10</option>'
											+ '</select>';
									break;
								}
								case 10: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_writing" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10" selected="selected">10</option>'
											+ '</select>';
									break;
								}

								}
								new_tr.appendChild(new_td);
								/*
								 * 
								 * 
								 * 
								 */
								var new_tr = document.createElement("tr");
								table_defence_leader.firstChild
										.appendChild(new_tr);
								var new_td = document.createElement("td");
								new_td.innerHTML = '规范程度';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								new_td.innerHTML = '6';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								switch (defence.defence_grade_normalization) {
								case 0: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()" class="form-control" id="defence_grade_normalization" >'
											+ '<option value="0"  selected="selected">0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '</select>';
									break;
								}
								case 1: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_normalization" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1" selected="selected">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '</select>';
									break;
								}
								case 2: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_normalization" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2" selected="selected">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '</select>';
									break;
								}
								case 3: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_normalization" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3" selected="selected">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '</select>';
									break;
								}
								case 4: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_normalization" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4" selected="selected">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '</select>';
									break;
								}
								case 5: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_normalization" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5" selected="selected">5</option>'
											+ '<option value="6">6</option>'
											+ '</select>';
									break;
								}
								case 6: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_normalization" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6" selected="selected">6</option>'
											+ '</select>';
									break;
								}

								}
								new_tr.appendChild(new_td);
								/*
								 * 
								 * 
								 * 
								 */
								var new_tr = document.createElement("tr");
								table_defence_leader.firstChild
										.appendChild(new_tr);
								var new_td = document.createElement("td");
								new_td.innerHTML = '完成情况';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								new_td.innerHTML = '4';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								switch (defence.defence_grade_complete) {
								case 0: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_complete()" class="form-control" id="defence_grade_complete" >'
											+ '<option value="0"  selected="selected">0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '</select>';
									break;
								}
								case 1: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_complete()"  class="form-control" id="defence_grade_complete" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1" selected="selected">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '</select>';
									break;
								}
								case 2: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_complete()"  class="form-control" id="defence_grade_complete" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2" selected="selected">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '</select>';
									break;
								}
								case 3: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_complete()"  class="form-control" id="defence_grade_complete" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3" selected="selected">3</option>'
											+ '<option value="4">4</option>'
											+ '</select>';
									break;
								}
								case 4: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_complete()"  class="form-control" id="defence_grade_complete" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4" selected="selected">4</option>'
											+ '</select>';
									break;
								}

								}
								new_tr.appendChild(new_td);
								/*
								 * 
								 * 
								 * 
								 */
								var new_tr = document.createElement("tr");
								table_defence_leader.firstChild
										.appendChild(new_tr);
								var new_td = document.createElement("td");
								new_td.innerHTML = '成果技术水平(理论分析、计算、实验和实物性能)';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								new_td.innerHTML = '14';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								switch (defence.defence_grade_technology) {
								case 0: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()" class="form-control" id="defence_grade_technology" >'
											+ '<option value="0"  selected="selected">0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '</select>';
									break;
								}
								case 1: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_technology" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1" selected="selected">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '</select>';
									break;
								}
								case 2: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_technology" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2" selected="selected">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '</select>';
									break;
								}
								case 3: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_technology" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3" selected="selected">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '</select>';
									break;
								}
								case 4: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_technology" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4" selected="selected">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '</select>';
									break;
								}
								case 5: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_technology" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5" selected="selected">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '</select>';
									break;
								}
								case 6: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_technology" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6" selected="selected">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10" >10</option>'
											+ '</select>';
									break;
								}
								case 7: {
									new_td.innerHTML = '<select onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_technology" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7" selected="selected">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10" >10</option>'
											+ '</select>';
									break;
								}
								case 8: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_technology" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8" selected="selected">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '</select>';
									break;
								}
								case 9: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_technology" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9" selected="selected">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '</select>';
									break;
								}
								case 10: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_technology" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10" selected="selected">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '</select>';
									break;
								}
								case 11: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_technology" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11" selected="selected">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '</select>';
									break;
								}
								case 12: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_technology" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12" selected="selected">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '</select>';
									break;
								}
								case 13: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_technology" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13" selected="selected">13</option>'
											+ '<option value="14">14</option>'
											+ '</select>';
									break;
								}
								case 14: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_technology" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14" selected="selected">14</option>'
											+ '</select>';
									break;
								}

								}
								new_tr.appendChild(new_td);
								/*
								 * 
								 * 
								 * 
								 */
								var new_tr = document.createElement("tr");
								table_defence_leader.firstChild
										.appendChild(new_tr);
								var new_td = document.createElement("td");
								new_td.innerHTML = '设计的正确性、创造性和实用性等情况';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								new_td.innerHTML = '16';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								switch (defence.defence_grade_practicability) {
								case 0: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()" class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0"  selected="selected">0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 1: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1" selected="selected">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 2: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2" selected="selected">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 3: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3" selected="selected">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 4: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4" selected="selected">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 5: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5" selected="selected">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 6: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6" selected="selected">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 7: {
									new_td.innerHTML = '<select onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7" selected="selected">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 8: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8" selected="selected">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 9: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9" selected="selected">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 10: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10" selected="selected">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 11: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11" selected="selected">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 12: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12" selected="selected">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 13: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13" selected="selected">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 14: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14" selected="selected">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 15: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15" selected="selected">15</option>'
											+ '<option value="16">16</option>'
											+ '</select>';
									break;
								}
								case 16: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_practicability" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16" selected="selected">16</option>'
											+ '</select>';
									break;
								}

								}
								new_tr.appendChild(new_td);
								/*
								 * 
								 * 
								 * 
								 * 
								 */
								var new_tr = document.createElement("tr");
								table_defence_leader.firstChild
										.appendChild(new_tr);
								var new_td = document.createElement("td");
								new_td.innerHTML = '仪表';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								new_td.innerHTML = '5';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								switch (defence.defence_grade_appearance) {
								case 0: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()" class="form-control" id="defence_grade_appearance" >'
											+ '<option value="0"  selected="selected">0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '</select>';
									break;
								}
								case 1: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_appearance" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1" selected="selected">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '</select>';
									break;
								}
								case 2: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_appearance" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2" selected="selected">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '</select>';
									break;
								}
								case 3: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_appearance" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3" selected="selected">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '</select>';
									break;
								}
								case 4: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_appearance" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4" selected="selected">4</option>'
											+ '<option value="5">5</option>'
											+ '</select>';
									break;
								}
								case 5: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_appearance" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5" selected="selected">5</option>'
											+ '</select>';
									break;
								}

								}
								new_tr.appendChild(new_td);
								/*
								 * 
								 * 
								 * 
								 * 
								 * 
								 * 
								 */
								var new_tr = document.createElement("tr");
								table_defence_leader.firstChild
										.appendChild(new_tr);
								var new_td = document.createElement("td");
								new_td.innerHTML = '内容陈述';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								new_td.innerHTML = '20';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								switch (defence.defence_grade_statement) {
								case 0: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()" class="form-control" id="defence_grade_statement" >'
											+ '<option value="0"  selected="selected">0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 1: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1" selected="selected">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 2: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2" selected="selected">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 3: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3" selected="selected">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 4: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4" selected="selected">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 5: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5" selected="selected">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 6: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6" selected="selected">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 7: {
									new_td.innerHTML = '<select onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7" selected="selected">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 8: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8" selected="selected">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 9: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9" selected="selected">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 10: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10" selected="selected">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 11: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11" selected="selected">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 12: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12" selected="selected">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 13: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13" selected="selected">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 14: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14" selected="selected">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 15: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15" selected="selected">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 16: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16" selected="selected">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 17: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17" selected="selected">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 18: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18" selected="selected">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 19: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19" selected="selected">19</option>'
											+ '<option value="20">20</option>'
											+ '</select>';
									break;
								}
								case 20: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_statement" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20" selected="selected">20</option>'
											+ '</select>';
									break;
								}
								}
								new_tr.appendChild(new_td);
								/*
								 * 
								 * 
								 * 
								 */
								var new_tr = document.createElement("tr");
								table_defence_leader.firstChild
										.appendChild(new_tr);
								var new_td = document.createElement("td");
								new_td.innerHTML = '回答问题正确性';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								new_td.innerHTML = '25';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								switch (defence.defence_grade_answer) {
								case 0: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()" class="form-control" id="defence_grade_answer" >'
											+ '<option value="0"  selected="selected">0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 1: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1" selected="selected">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 2: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2" selected="selected">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 3: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3" selected="selected">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 4: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4" selected="selected">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 5: {
									new_td.innerHTML = '<select   onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5" selected="selected">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 6: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5" >5</option>'
											+ '<option value="6" selected="selected">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 7: {
									new_td.innerHTML = '<select onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6" >6</option>'
											+ '<option value="7" selected="selected">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 8: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8" selected="selected">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 9: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9" selected="selected">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 10: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10" selected="selected">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 11: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11" selected="selected">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 12: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12" selected="selected">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 13: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13" selected="selected">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 14: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14" selected="selected">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 15: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15" selected="selected">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 16: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16" selected="selected">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 17: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17" selected="selected">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 18: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18" selected="selected">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 19: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19" selected="selected">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 20: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20" selected="selected">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 21: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21" selected="selected">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 22: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22" selected="selected">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 23: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23" selected="selected">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 24: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24" selected="selected">24</option>'
											+ '<option value="25">25</option>'
											+ '</select>';
									break;
								}
								case 25: {
									new_td.innerHTML = '<select  onchange="change_defence_grade_defence()"  class="form-control" id="defence_grade_answer" >'
											+ '<option value="0" >0</option>'
											+ '<option value="1">1</option>'
											+ '<option value="2">2</option>'
											+ '<option value="3">3</option>'
											+ '<option value="4">4</option>'
											+ '<option value="5">5</option>'
											+ '<option value="6">6</option>'
											+ '<option value="7">7</option>'
											+ '<option value="8">8</option>'
											+ '<option value="9">9</option>'
											+ '<option value="10">10</option>'
											+ '<option value="11">11</option>'
											+ '<option value="12">12</option>'
											+ '<option value="13">13</option>'
											+ '<option value="14">14</option>'
											+ '<option value="15">15</option>'
											+ '<option value="16">16</option>'
											+ '<option value="17">17</option>'
											+ '<option value="18">18</option>'
											+ '<option value="19">19</option>'
											+ '<option value="20">20</option>'
											+ '<option value="21">21</option>'
											+ '<option value="22">22</option>'
											+ '<option value="23">23</option>'
											+ '<option value="24">24</option>'
											+ '<option value="25" selected="selected">25</option>'
											+ '</select>';
									break;
								}

								}
								new_tr.appendChild(new_td);
								/*
								 * 
								 * 
								 * 
								 */
								var new_tr = document.createElement("tr");
								table_defence_leader.firstChild
										.appendChild(new_tr);
								var new_td = document.createElement("td");
								new_td.innerHTML = '总分';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								new_td.innerHTML = '100';
								new_tr.appendChild(new_td);
								var new_td = document.createElement("td");
								new_td.style = "text-align: center;";
								new_td.innerHTML = '<input id="defence_grade_defence" disabled="disabled" class="form-control" style="text-align: center;"/>';
								new_tr.appendChild(new_td);
								/*
								 * 
								 * 
								 * 
								 * 
								 * 
								 * 
								 * 
								 * 
								 * 
								 */
								change_defence_grade_defence();
								/*
								 * 
								 */
								var textarea_defence_leader_comment = document.getElementById("textarea_defence_leader_comment");
								if (defence.defence_leader_comment != undefined) {
									textarea_defence_leader_comment.innerHTML = defence.defence_leader_comment;
								} else {
									textarea_defence_leader_comment.innerHTML = '';
								}
								/*
								 * 
								 */
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

function change_defence_grade_defence() {
	var defence_grade_defence = document
			.getElementById("defence_grade_defence");

	var total = parseInt(document.getElementById("defence_grade_writing").value)
			+ parseInt(document.getElementById("defence_grade_normalization").value)
			+ parseInt(document.getElementById("defence_grade_complete").value)
			+ parseInt(document.getElementById("defence_grade_technology").value)
			+ parseInt(document.getElementById("defence_grade_practicability").value)
			+ parseInt(document.getElementById("defence_grade_appearance").value)
			+ parseInt(document.getElementById("defence_grade_statement").value)
			+ parseInt(document.getElementById("defence_grade_answer").value);
	defence_grade_defence.value = total;
}