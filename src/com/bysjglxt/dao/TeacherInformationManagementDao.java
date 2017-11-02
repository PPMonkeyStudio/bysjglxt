package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.VO.TeacherInformationManagementVO;

public interface TeacherInformationManagementDao {

	public List<bysjglxt_teacher_user> list_TeacherUserInformation_All();

	public bysjglxt_teacher_basic get_TeacherBasicInformation_ByUserBasic(String user_teacher_basic);

	boolean saveTeacherBasic(bysjglxt_teacher_basic bysjglxt_teacher_basic);

	public boolean saveTeacher(bysjglxt_teacher_user bysjglxt_teacher_user);

	public bysjglxt_teacher_user getStudentById(String teacher_id);

	public boolean deleteTeacherBasicInfoById(String user_teacher_basic);

	public boolean deleteTeacherInfoById(String user_teacher_id);

	public List<bysjglxt_teacher_basic> listTeacherAllBasicInformationByAndSearch(
			TeacherInformationManagementVO teacherInformationManagementVO);

	public bysjglxt_teacher_user getTeacherInfoByBasicId(String teacher_basic_id);

	public List<bysjglxt_teacher_basic> listTeacherBasicInformationByPageAndSearch(
			TeacherInformationManagementVO teacherInformationManagementVO);

	public List<bysjglxt_section> listBysjglxtSection();

	public bysjglxt_section get_TeacherSectionInformation_ByUserSectionId(String user_teacher_section);

	public boolean updateBasic(bysjglxt_teacher_basic bysjglxtTeacherBasic);

	public boolean updateUser(bysjglxt_teacher_user bysjglxtTeacherUser);

	public List<String> list_Teacher_Title();

	public List<bysjglxt_teacher_basic> getResultBySearch(TeacherInformationManagementVO teacherInformationManagementVO);

	public boolean updatePassword(String user_teacher_id, String password);

	public boolean teacherBasicIsExist(String job_number);

}
