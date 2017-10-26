package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;

public interface TeacherInformationManagementDao {

	List<bysjglxt_teacher_user> list_TeacherUserInformation_All();

	bysjglxt_teacher_basic get_TeacherBasicInformation_ByUserBasic(String user_teacher_basic);

	boolean saveTeacherBasic(bysjglxt_teacher_basic bysjglxt_teacher_basic);

	boolean saveTeacher(bysjglxt_teacher_user bysjglxt_teacher_user);

	bysjglxt_teacher_user getStudentByNum(String teacher_num);

	boolean deleteTeacherBasicInfoById(String user_teacher_basic);

	boolean deleteTeacherInfoById(String user_teacher_id);

}
