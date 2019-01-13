package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_college;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;
import com.bysjglxt.domain.DO.bysjglxt_topic_select;
import com.bysjglxt.domain.DTO.TeacherInformationDTO;
import com.bysjglxt.domain.VO.TeacherInformationManagementVO;

public interface TeacherInformationManagementDao {

	public List<bysjglxt_teacher_user> list_TeacherUserInformation_All(String college);

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
			TeacherInformationManagementVO teacherInformationManagementVO, String college);

	public List<bysjglxt_section> listBysjglxtSection(String college);

	public bysjglxt_section get_TeacherSectionInformation_ByUserSectionId(String user_teacher_section);

	public boolean updateBasic(bysjglxt_teacher_basic bysjglxtTeacherBasic);

	public boolean updateUser(bysjglxt_teacher_user bysjglxtTeacherUser);

	public List<String> list_Teacher_Title();

	public List<bysjglxt_teacher_basic> getResultBySearch(TeacherInformationManagementVO teacherInformationManagementVO,
			String college);

	public boolean updatePassword(String user_teacher_id, String password, String moTime);

	public boolean teacherBasicIsExist(String job_number);

	public com.bysjglxt.domain.DO.bysjglxt_section getSectionByMajorCode(String trim);

	public com.bysjglxt.domain.DO.bysjglxt_student_user getStudentByUserId(String userId);

	/**
	 * 获取学院
	 * @param college
	 * @return
	 */
	public bysjglxt_college getCollegeById(String college);

	public TeacherInformationDTO getTeacherInfomationDTOByTeacherUserId(String user_teacher_id);

	/**
	 * 
	 * @param user_student_id
	 * @return
	 */
	public bysjglxt_topic_select getStudentSelectTopic(String user_student_id);

}
