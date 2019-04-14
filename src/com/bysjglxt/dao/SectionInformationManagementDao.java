package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_notice;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.VO.SectionInformationManagementVO;

public interface SectionInformationManagementDao {

	int getCountSectionLeader();

	List<bysjglxt_section> getListSectionByPage(SectionInformationManagementVO sectionInformationManagementVO,
			String college);

	com.bysjglxt.domain.DO.bysjglxt_teacher_user getBysjglxtTeacherUserById(String section_id);

	bysjglxt_teacher_basic getBysjglxtTeacherBasicById(String user_teacher_basic);

	boolean create_Section(bysjglxt_section newSection);

	boolean deleteSection(String string);

	List<bysjglxt_section> listBysjglxtSection(String userId);

	boolean updateSection(bysjglxt_section bysjglxt_section);

	boolean setTeacherUserSectionNull(String string);

	List<bysjglxt_section> getSectionByUser(String user_teacher_section);

	bysjglxt_notice getNoticeByLaunchBelongLeiXing(String string, String userId, int i);

	boolean savaObj(Object obj);

	bysjglxt_section getSectionById(String section_id);

	bysjglxt_teacher_user getTeacherByCollege(String user_teacher_belong_college);

}
