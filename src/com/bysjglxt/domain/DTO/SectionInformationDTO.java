package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_section;

public class SectionInformationDTO {

	private bysjglxt_section bysjglxtSection;

	// 教研室主任DTO
	private TeacherInformationDTO TeacherInformationDTO;

	@Override
	public String toString() {
		return "SectionInformationDTO [bysjglxtSection=" + bysjglxtSection + ", TeacherInformationDTO="
				+ TeacherInformationDTO + "]";
	}

	public bysjglxt_section getBysjglxtSection() {
		return bysjglxtSection;
	}

	public void setBysjglxtSection(bysjglxt_section bysjglxtSection) {
		this.bysjglxtSection = bysjglxtSection;
	}

	public TeacherInformationDTO getTeacherInformationDTO() {
		return TeacherInformationDTO;
	}

	public void setTeacherInformationDTO(TeacherInformationDTO teacherInformationDTO) {
		TeacherInformationDTO = teacherInformationDTO;
	}

}
