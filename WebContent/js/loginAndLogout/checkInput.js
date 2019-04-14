/*
 * 输入校验
 */
function checkInputNotEmpty() {
	var input_not_empty = document.getElementsByClassName("input_not_empty");
	var num_not_empty = 0;
	for (var num = 0; num < input_not_empty.length; num++) {
		if (input_not_empty[num].value == "") {
			num_not_empty++;
			toastr.error("必填项不可为空");
			return false;
		}
	}
	if (num_not_empty == 0) {
		return true;
	}

}