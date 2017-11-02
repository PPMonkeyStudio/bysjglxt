package com.bysjglxt.domain.DTO;

import com.bysjglxt.domain.DO.bysjglxt_topic;
import com.bysjglxt.domain.DO.bysjglxt_topic_invite_teacher;

public class TopicInformationDTO {
	private bysjglxt_topic bysjglxtTopic;
	private bysjglxt_topic_invite_teacher bysjglxtTopicInviteTeacher;
	private TeacherInformationDTO teacherInformationDTO;
	public TeacherInformationDTO getTeacherInformationDTO() {
		return teacherInformationDTO;
	}

	public void setTeacherInformationDTO(TeacherInformationDTO teacherInformationDTO) {
		this.teacherInformationDTO = teacherInformationDTO;
	}

	public bysjglxt_topic getBysjglxtTopic() {
		return bysjglxtTopic;
	}

	public void setBysjglxtTopic(bysjglxt_topic bysjglxtTopic) {
		this.bysjglxtTopic = bysjglxtTopic;
	}

	public bysjglxt_topic_invite_teacher getBysjglxtTopicInviteTeacher() {
		return bysjglxtTopicInviteTeacher;
	}

	public void setBysjglxtTopicInviteTeacher(bysjglxt_topic_invite_teacher bysjglxtTopicInviteTeacher) {
		this.bysjglxtTopicInviteTeacher = bysjglxtTopicInviteTeacher;
	}

	@Override
	public String toString() {
		return "TopicInformationDTO [bysjglxtTopic=" + bysjglxtTopic + ", bysjglxtTopicInviteTeacher="
				+ bysjglxtTopicInviteTeacher + "]";
	}

}
