function remove_Preview_Comment_EXCEL() {

	/*
	 * 清空input中的值
	 */
	document.getElementById('input_upload_comment_excel').outerHTML = document
			.getElementById('input_upload_comment_excel').outerHTML;

	/*
	 * 清空table中的html数据
	 */
	var table_excel_comment = document.getElementById("table_excel_comment");
	table_excel_comment.firstElementChild.innerHTML = '';

	/*
	 * 清除存在js中的值
	 */
	EXCEL_Comment_List = null;

	toastr.success("数据已重置");

}