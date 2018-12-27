var jc = null;
function overTheYearsTopic(obj) {
	jc = $.confirm({
				icon : 'fa fa-users',
				columnClass : 'col-md-12',
				theme : 'modern',
				title : '历年课题',
				content :
						`
							<div class="input-group" style="width: 1050px; float: right;margin:0 20px 20px 0;">
								<form id="overTheYearsTopic">
									<table id="table_overTheYearsTopic" class="table table-hover table-bordered" style="text-align: center; margin: 20px 0;">
										<tbody>
											<tr>
												<th>中文名称</th>
												<th>
													<select class="form-control"
														style="width: auto; margin: 0 auto;" id="select_source"
														onchange="listAllTopic()">
														<option value="-1">课题来源</option>
														<option value="各类课题项目">各类课题项目</option>
														<option value="导师指定">导师指定</option>
														<option value="题目指南">题目指南</option>
														<option value="自选">自选</option>
														<option value="其它">其它</option>
													</select>
												</th>
												<th>
													<select class="form-control"
														style="width: auto; margin: 0 auto;" id="select_type"
														onchange="listAllTopic()">
														<option value="-1">课题性质</option>
														<option value="理论研究">理论研究</option>
														<option value="应用基础研究">应用基础研究</option>
														<option value="应用与理论结合研究">应用与理论结合研究</option>
														<option value="实际应用">实际应用</option>
													</select>
												</th>
												<th>操作</th>
											</tr>
										</tbody>
									</table>
								</form>
							</div>
						`,
				type : 'orange',
				buttons : {
					返回 : function() {
					}
				},
				onContentReady : function() {
					listAllTopic();
				}
			});
}
function listAllTopic() {
	var formData = new FormData();
	/*
	 * * 课题来源 /
	 */
	if (document.getElementById("select_source").value != "-1") {
		formData.append("topic.topic_source", document.getElementById("select_source").value);
	}
	/*
	 * * 课题类型 /
	 */
	if (document.getElementById("select_type").value != "-1") {
		formData.append("topic.topic_type", document.getElementById("select_type").value);
	};
	$.ajax({
		type:'POST',
		url:'/bysjglxt/topic/TopicInformationManagement_listAllTopic',
	    processData: false,
	    contentType: false,
		dataType:"text",
		data:formData,
		success:function(data){
			var topic_json = JSON.parse(data);
			console.log('ddd',topic_json)
			/**
			 * 
			 */
			var new_tr_list = document.getElementsByClassName("new_tr_topic");
			var long = new_tr_list.length;
			/*
			 * 
			 */
			var table_topic = document.getElementById("table_overTheYearsTopic");
			var new_tr = null;
			var new_td = null;
			for (var num = 0; num < long; num++) {
				new_tr_list[0].parentNode.removeChild(new_tr_list[0]);
			}
			/**
			 * 
			 */
			for (var num = 0; num < topic_json.length; num++) {
				new_tr = document.createElement("tr");
				new_tr.appendChild(document.createTextNode(''));
				table_topic.firstElementChild.appendChild(new_tr);
				new_tr.className = "new_tr_topic";
				/*
				 * 
				 */
				new_td = document.createElement("td");
				new_tr.appendChild(new_td);
				if (topic_json != undefined
						&& topic_json[num][0].topic_name_chinese != "") {
					new_td.innerHTML = topic_json[num][0].topic_name_chinese;
				} else {
					new_td.innerHTML = '无';
				}
				
				new_td = document.createElement("td");
				new_td.appendChild(document.createTextNode(''));
				new_tr.appendChild(new_td);
				if (topic_json != undefined
						&& topic_json[num][0].topic_source != "") {
					new_td.innerHTML = topic_json[num][0].topic_source;
				} else {
					new_td.innerHTML = '无';
				}
				
				new_td = document.createElement("td");
				new_td.appendChild(document.createTextNode(''));
				new_tr.appendChild(new_td);
				if (topic_json != undefined
						&& topic_json[num][0].topic_type != "") {
					new_td.innerHTML = topic_json[num][0].topic_type;
				} else {
					new_td.innerHTML = '无';
				}
				/**
				 * 
				 * 
				 */
				new_td = document.createElement("td");
				new_td.appendChild(document.createTextNode(''));
				new_tr.appendChild(new_td);
				new_td.innerHTML = '<button type="button" class="btn btn-default" id="' + topic_json[num][0].topic_id +'" onclick="selectTopicPre(this)" >选择</button>';
			}
		},
		error:function(){
			toastr.error('sss');
		}
	});
}

function selectTopicPre(obj){
	$.post({
		type:'POST',
		url:'/bysjglxt/topic/TopicInformationManagement_getTopicById',
		dataType:'json',
		data:{"topicId":obj.id},
		success:function(data){
			$('#topic_name_chinese').val(data.topic_name_chinese);
			$('#topic_name_english').val(data.topic_name_english);
			$("#topic_source").find("option[value='"+data.topic_source+"']").attr("selected",true);
			$("#topic_type").find("option[value='"+data.topic_type+"']").attr("selected",true);
			/*
			 * $('#topic_source').val(data.topic_source);
			 * $('#topic_type').val(data.topic_type);
			 */
			
			$('#topic_requirement').val(data.topic_requirement);
			$('#topic_remark').val(data.topic_remark);
			$('#topic_remark').val(data.topic_remark);
			
		}
	});
	
	/*
	 * var tr_parent = $obj.parent().parent();
	 */
	jc.close();
}

