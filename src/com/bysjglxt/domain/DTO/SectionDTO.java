package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;

public class SectionDTO {
	private bysjglxt_section section;
	private bysjglxt_teacher_user teacherUser;
	private bysjglxt_teacher_basic teacherBasic;

	public bysjglxt_section getSection() {
		return section;
	}

	public void setSection(bysjglxt_section section) {
		this.section = section;
	}

	public bysjglxt_teacher_user getTeacherUser() {
		return teacherUser;
	}

	public void setTeacherUser(bysjglxt_teacher_user teacherUser) {
		this.teacherUser = teacherUser;
	}

	public bysjglxt_teacher_basic getTeacherBasic() {
		return teacherBasic;
	}

	public void setTeacherBasic(bysjglxt_teacher_basic teacherBasic) {
		this.teacherBasic = teacherBasic;
	}

	@Override
	public String toString() {
		return "SectionDTO [section=" + section + ", teacherUser=" + teacherUser + ", teacherBasic=" + teacherBasic
				+ "]";
	}

}
