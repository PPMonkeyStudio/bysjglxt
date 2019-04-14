package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;

public class DesignationStudentInformationDTO {

	private bysjglxt_student_basic bysjglxtStudentBasic;
	private bysjglxt_student_user bysjglxtStudentUser;

	/**
	 * 1:没有被指定到该课题 2.被指定到
	 */

	private int designation = 1;

	@Override
	public String toString() {
		return "DesignationStudentInformationDTO [bysjglxtStudentBasic=" + bysjglxtStudentBasic
				+ ", bysjglxtStudentUser=" + bysjglxtStudentUser + ", designation=" + designation + "]";
	}

	public bysjglxt_student_basic getBysjglxtStudentBasic() {
		return bysjglxtStudentBasic;
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

	public int getDesignation() {
		return designation;
	}

	public void setDesignation(int designation) {
		this.designation = designation;
	}

}
