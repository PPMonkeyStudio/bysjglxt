function roleControl() {
	var long = 0;
	var student_control = document.getElementsByClassName("student_control");
	var teacher_control = document.getElementsByClassName("teacher_control");
	var college_control = document.getElementsByClassName("college_control");
	var admin_control = document.getElementsByClassName("admin_control");
	var noAdmin_control = document.getElementsByClassName("noAdmin_control");
	/*
	 * 学生
	 */
	if (userStudentDTO == null) {
		long = student_control.length;
		for (var num = 0; num < long; num++) {
			student_control[0].parentNode.removeChild(student_control[0]);
		}
	}
	/*
	 * 教师
	 */
	if (userTeacherDTO == null) {
		long = teacher_control.length;
		for (var num = 0; num < long; num++) {
			teacher_control[0].parentNode.removeChild(teacher_control[0]);
		}
		//
		long = college_control.length;
		for (var num = 0; num < long; num++) {
			college_control[0].parentNode.removeChild(college_control[0]);
		}
	} else {
		/*
		 * 院系管理员
		 */
		if (userTeacherDTO.bysjglxtTeacherUser.user_teacher_is_college_admin == 2) {
			long = college_control.length;
			for (var num = 0; num < long; num++) {
				college_control[0].parentNode.removeChild(college_control[0]);
			}
		}
	}

	/*
	 * 系统管理员
	 */
	if (admin == null) {
		long = admin_control.length;
		for (var num = 0; num < long; num++) {
			admin_control[0].parentNode.removeChild(admin_control[0]);
		}
	} else {
		long = noAdmin_control.length;
		for (var num = 0; num < long; num++) {
			noAdmin_control[0].parentNode.removeChild(noAdmin_control[0]);
		}
	}
	/*
	 * 
	 */
	stop_load();

}
