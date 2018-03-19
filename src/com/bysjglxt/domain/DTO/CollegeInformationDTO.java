package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_college;

public class CollegeInformationDTO {
	private TeacherInformationDTO teacherInformationDTO;
	private bysjglxt_college college;

	@Override
	public String toString() {
		return "CollegeInformationDTO [teacherInformationDTO=" + teacherInformationDTO + ", college=" + college + "]";
	}

	public bysjglxt_college getCollege() {
		return college;
	}

	public void setCollege(bysjglxt_college college) {
		this.college = college;
	}

	public TeacherInformationDTO getTeacherInformationDTO() {
		return teacherInformationDTO;
	}

	public void setTeacherInformationDTO(TeacherInformationDTO teacherInformationDTO) {
		this.teacherInformationDTO = teacherInformationDTO;
	}

}
