package com.bysjglxt.domain.DTO;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic;

/**
 * 
 * @author JXX
 *
 */
public class TeacherTopicInformationDTO {
	bysjglxt_teacher_user teacherUser;
	bysjglxt_teacher_basic teacherBasic;
	List<bysjglxt_topic> listTopic;

	public bysjglxt_teacher_user getTeacherUser() {
		return teacherUser;
	}

	public void setTeacherUser(bysjglxt_teacher_user teacherUser) {
		this.teacherUser = teacherUser;
	}

	public bysjglxt_teacher_basic getTeacherBasic() {
		return teacherBasic;
	}

	public void setTeacherBasic(bysjglxt_teacher_basic teacherBasic) {
		this.teacherBasic = teacherBasic;
	}

	public List<bysjglxt_topic> getListTopic() {
		return listTopic;
	}

	public void setListTopic(List<bysjglxt_topic> listTopic) {
		this.listTopic = listTopic;
	}

	@Override
	public String toString() {
		return "TeacherTopicInformationDTO [teacherUser=" + teacherUser + ", teacherBasic=" + teacherBasic
				+ ", listTopic=" + listTopic + "]";
	}

}
