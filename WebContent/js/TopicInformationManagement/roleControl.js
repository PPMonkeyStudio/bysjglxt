function roleControl() {
	/*
	 * 学生
	 */
	if (userStudentDTO != null) {
		document.getElementById("button_selectTopic").style.display = "block";
	}
	/*
	 * 教师
	 */
	if (userTeacherDTO != null) {
		/*
		 * 领导小组组长
		 */
		if (userTeacherDTO.bysjglxtLeader != null) {
			document.getElementById("button_updateTopic").style.display = "block";
		}
	}

}