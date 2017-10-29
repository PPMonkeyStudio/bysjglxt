package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.VO.TeacherInformationManagementVO;

public interface TeacherInformationManagementDao {

	List<bysjglxt_teacher_user> list_TeacherUserInformation_All();

	bysjglxt_teacher_basic get_TeacherBasicInformation_ByUserBasic(String user_teacher_basic);

	boolean saveTeacherBasic(bysjglxt_teacher_basic bysjglxt_teacher_basic);

	boolean saveTeacher(bysjglxt_teacher_user bysjglxt_teacher_user);

	bysjglxt_teacher_user getStudentById(String teacher_id);

	boolean deleteTeacherBasicInfoById(String user_teacher_basic);

	boolean deleteTeacherInfoById(String user_teacher_id);

	boolean create_Section(bysjglxt_section newSection);

	List<bysjglxt_teacher_basic> listTeacherAllBasicInformationByAndSearch(
			TeacherInformationManagementVO teacherInformationManagementVO);

	bysjglxt_teacher_user getTeacherInfoByBasicId(String teacher_basic_id, String section);

	List<bysjglxt_teacher_basic> listTeacherBasicInformationByPageAndSearch(
			TeacherInformationManagementVO teacherInformationManagementVO);

	boolean deleteSection(String string);

	List<String> listBysjglxtSection();

}
