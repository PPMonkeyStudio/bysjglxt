function drop_Topic() {
	$
			.confirm({
				title : '退选',
				content : '此操作将退选您的课题，您需要重新选题，确认吗',
				type : 'red',
				autoClose : '放弃|5000',// 自动关闭
				buttons : {
					退选 : {
						btnClass : 'btn-red',
						action : function() {
							var xhr = false;
							xhr = new XMLHttpRequest();
							xhr.onreadystatechange = function() {
								var message;
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										$("#modal_Topic_Information").modal(
												"hide");
										List_MyTopic_By_PageAndSearch(1);
									} else {
										toastr.error(xhr.status);
									}
								}
							}
							var formData = new FormData();
							xhr
									.open("POST",
											"/bysjglxt/topic/TopicInformationManagement_dropTopic");
							xhr.send(null);
						}
					},
					放弃 : function() {
					}
				}
			});
}