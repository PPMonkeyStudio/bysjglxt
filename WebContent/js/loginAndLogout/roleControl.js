function roleControl() {
	var long = 0;
	var student_delete = document.getElementsByClassName("student_control");
	var teacher_delete = document.getElementsByClassName("teacher_control");
	var leader_delete = document.getElementsByClassName("leader_control");
	/*
	 * 学生
	 */
	if (userStudentDTO == null) {
		long = student_delete.length;
		for (var num = 0; num < long; num++) {
			student_delete[0].parentNode.removeChild(student_delete[0]);
		}
	}
	/*
	 * 教师
	 */
	if (userTeacherDTO == null) {
		long = teacher_delete.length;
		for (var num = 0; num < long; num++) {
			teacher_delete[0].parentNode.removeChild(teacher_delete[0]);
		}
		long = leader_delete.length;
		for (var num = 0; num < long; num++) {
			leader_delete[0].parentNode.removeChild(leader_delete[0]);
		}
	} else {
		/*
		 * 领导小组组长
		 */
		if (userTeacherDTO.bysjglxtLeader == null) {
			long = leader_delete.length;
			for (var num = 0; num < long; num++) {
				leader_delete[0].parentNode.removeChild(leader_delete[0]);
			}
		}
	}

}