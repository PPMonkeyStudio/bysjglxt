package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;

public class StudentInformationDTO {

	private bysjglxt_student_basic bysjglxtStudentBasic;

	private bysjglxt_student_user bysjglxtStudentUser;

	/*
	 * 
	 */

	@Override
	public String toString() {
		return "StudentInformationDTO [bysjglxtStudentBasic=" + bysjglxtStudentBasic + ", bysjglxtStudentUser="
				+ bysjglxtStudentUser + "]";
	}

	public bysjglxt_student_basic getBysjglxtStudentBasic() {
		return bysjglxtStudentBasic;
	}

	public StudentInformationDTO(bysjglxt_student_basic bysjglxtStudentBasic,
			bysjglxt_student_user bysjglxtStudentUser) {
		super();
		this.bysjglxtStudentBasic = bysjglxtStudentBasic;
		this.bysjglxtStudentUser = bysjglxtStudentUser;
	}

	public StudentInformationDTO() {
		super();
	}

	public void setBysjglxtStudentBasic(bysjglxt_student_basic bysjglxtStudentBasic) {
		this.bysjglxtStudentBasic = bysjglxtStudentBasic;
	}

	public bysjglxt_student_user getBysjglxtStudentUser() {
		return bysjglxtStudentUser;
	}

	public void setBysjglxtStudentUser(bysjglxt_student_user bysjglxtStudentUser) {
		this.bysjglxtStudentUser = bysjglxtStudentUser;
	}

}
