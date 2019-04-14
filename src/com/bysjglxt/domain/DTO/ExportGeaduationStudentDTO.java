package com.bysjglxt.domain.DTO;

import java.util.List;

public class ExportGeaduationStudentDTO {
	private List<StudentInformationDTO> listStudentInformationDTO;
	private String major = "-1";
	private String level = "-1";

	public List<StudentInformationDTO> getListStudentInformationDTO() {
		return listStudentInformationDTO;
	}

	public void setListStudentInformationDTO(List<StudentInformationDTO> listStudentInformationDTO) {
		this.listStudentInformationDTO = listStudentInformationDTO;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "ExportGeaduationStudentDTO [listStudentInformationDTO=" + listStudentInformationDTO + ", major=" + major
				+ ", level=" + level + "]";
	}

}
