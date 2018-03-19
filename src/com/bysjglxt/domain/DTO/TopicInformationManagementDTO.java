package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_topic;

public class TopicInformationManagementDTO {
	private bysjglxt_topic bysjglxtTopic;
	private TeacherInformationDTO teacherInformationDTO;

	@Override
	public String toString() {
		return "TopicInformationManagementDTO [bysjglxtTopic=" + bysjglxtTopic + ", teacherInformationDTO="
				+ teacherInformationDTO + "]";
	}

	public bysjglxt_topic getBysjglxtTopic() {
		return bysjglxtTopic;
	}

	public void setBysjglxtTopic(bysjglxt_topic bysjglxtTopic) {
		this.bysjglxtTopic = bysjglxtTopic;
	}

	public TeacherInformationDTO getTeacherInformationDTO() {
		return teacherInformationDTO;
	}

	public void setTeacherInformationDTO(TeacherInformationDTO teacherInformationDTO) {
		this.teacherInformationDTO = teacherInformationDTO;
	}

}
