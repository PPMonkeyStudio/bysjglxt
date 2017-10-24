package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;

public class TeacherInformationDTO {

	private bysjglxt_teacher_basic bysjglxtTeacherBasic;

	private bysjglxt_teacher_user bysjglxtTeacherUser;

	/*
	 * 
	 */

	public bysjglxt_teacher_basic getBysjglxtTeacherBasic() {
		return bysjglxtTeacherBasic;
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

}
