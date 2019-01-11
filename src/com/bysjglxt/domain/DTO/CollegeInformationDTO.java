package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_college;

public class CollegeInformationDTO {
	private TeacherInformationDTO teacherInformationDTO;
	private bysjglxt_college college;

	private String state = "success";


	@Override
	public String toString() {
		return "CollegeInformationDTO [teacherInformationDTO=" + teacherInformationDTO + ", college=" + college
				+ ", state=" + state + "]";
	}

	public bysjglxt_college getCollege() {
		return college;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public CollegeInformationDTO(TeacherInformationDTO teacherInformationDTO, bysjglxt_college college) {
		super();
		this.teacherInformationDTO = teacherInformationDTO;
		this.college = college;
	}

	public CollegeInformationDTO() {
		super();
	}

}
