package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;

/**
 * 
 * @author JXX
 *
 */
public class TeacherDTO {
	private bysjglxt_teacher_user teacherUser;
	private bysjglxt_teacher_basic tacherBasic;

	public bysjglxt_teacher_user getTeacherUser() {
		return teacherUser;
	}

	public void setTeacherUser(bysjglxt_teacher_user teacherUser) {
		this.teacherUser = teacherUser;
	}

	public bysjglxt_teacher_basic getTacherBasic() {
		return tacherBasic;
	}

	public void setTacherBasic(bysjglxt_teacher_basic tacherBasic) {
		this.tacherBasic = tacherBasic;
	}

	@Override
	public String toString() {
		return "TeacherDTO [teacherUser=" + teacherUser + ", tacherBasic=" + tacherBasic + "]";
	}

}
