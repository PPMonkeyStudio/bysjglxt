function saveNewComment() {
	var jc = $
			.confirm({
				title : '创建评语',
				content : '<hr>'
						+ '<form id="form_saveNewComment">'
						+ '<table  class="table table-bordered" style="text-align: center;">'
						+ '<tbody>'
						+ '<tr>'
						+ '<td>等级：</td><td>'
						+ '<select  name="comment.comment_grade" class="form-control" >'
						+ '<option value="很好">很好</option>'
						+ '<option value="好">好</option>'
						+ '<option value="一般">一般</option>'
						+ '<option value="差">差</option>'
						+ '</select>'
						+ '</td>'
						+ '</tr>'
						+ '<td>类别：</td><td>'
						+ '<select  name="comment.comment_category" class="form-control" >'
						+ '<option value="引言">引言</option>'
						+ '<option value="工作态度">工作态度</option>'
						+ '<option value="-选题质量">选题质量</option>'
						+ '<option value="题目难易度">题目难易度</option>'
						+ '<option value="题目工作量">题目工作量</option>'
						+ '<option value="题目与生产、科研、实验室建设等实际的结合程度">题目与生产、科研、实验室建设等实际的结合程度</option>'
						+ '<option value="综合运用知识">综合运用知识</option>'
						+ '<option value="查阅文献资料及资料应用">查阅文献资料及资料应用</option>'
						+ '<option value="实验设计">实验设计</option>'
						+ '<option value="计算能力">计算能力</option>'
						+ '<option value="外语应用">外语应用</option>'
						+ '<option value="计算机应用">计算机应用</option>'
						+ '<option value="创新">创新</option>'
						+ '<option value="对实验结果的分析能力">对实验结果的分析能力</option>'
						+ '<option value="插图（或图纸）质量">插图（或图纸）质量</option>'
						+ '<option value="设计的实用性与科学性">设计的实用性与科学性</option>'
						+ '<option value="设计规范化程度">设计规范化程度</option>'
						+ '<option value="设计说明书撰写水平">设计说明书撰写水平</option>'
						+ '<option value="总结">总结</option>'
						+ '</select>'
						+ '</td>'
						+ '</tr>'
						+ '<tr><td colspan="2">'
						+ '<textarea class="form-control" style="resize: none;height:200px;" name="comment.comment_content" >评语内容</textarea>'
						+ '</td></tr>' + '</tbody>' + '</table>' + '</form>',
				onContentReady : function() {
				},
				buttons : {
					'确认创建' : {
						btnClass : 'btn-blue',
						action : function() {

							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										toastr.success("创建成功");
										jc.close();
										List_Comment_By_College(1);
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							/*
							 * 
							 */
							var formData = new FormData(document
									.getElementById("form_saveNewComment"));

							xhr
									.open("POST",
											"/bysjglxt/graduationProject/GraduationProjectManagement_saveNewComment");
							xhr.send(formData);
							return false;
						}
					},
					'取消' : function() {
					}

				}

			});
}