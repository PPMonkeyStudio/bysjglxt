package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;

public class TeacherInformationDTO {

	private bysjglxt_teacher_basic bysjglxtTeacherBasic;

	private bysjglxt_teacher_user bysjglxtTeacherUser;

	private bysjglxt_section bysjglxtSection;
	
	/*
	 * 
	 */

	public bysjglxt_teacher_basic getBysjglxtTeacherBasic() {
		return bysjglxtTeacherBasic;
	}

	public TeacherInformationDTO() {
		super();
	}

	public TeacherInformationDTO(bysjglxt_teacher_basic bysjglxtTeacherBasic,
			bysjglxt_teacher_user bysjglxtTeacherUser) {
		super();
		this.bysjglxtTeacherBasic = bysjglxtTeacherBasic;
		this.bysjglxtTeacherUser = bysjglxtTeacherUser;
	}

	@Override
	public String toString() {
		return "TeacherInformationDTO [bysjglxtTeacherBasic=" + bysjglxtTeacherBasic + ", bysjglxtTeacherUser="
				+ bysjglxtTeacherUser + ", bysjglxtSection=" + bysjglxtSection + "]";
	}

	public void setBysjglxtTeacherBasic(bysjglxt_teacher_basic bysjglxtTeacherBasic) {
		this.bysjglxtTeacherBasic = bysjglxtTeacherBasic;
	}

	public bysjglxt_teacher_user getBysjglxtTeacherUser() {
		return bysjglxtTeacherUser;
	}

	public void setBysjglxtTeacherUser(bysjglxt_teacher_user bysjglxtTeacherUser) {
		this.bysjglxtTeacherUser = bysjglxtTeacherUser;
	}

	public bysjglxt_section getBysjglxtSection() {
		return bysjglxtSection;
	}

	public void setBysjglxtSection(bysjglxt_section bysjglxtSection) {
		this.bysjglxtSection = bysjglxtSection;
	}
}
