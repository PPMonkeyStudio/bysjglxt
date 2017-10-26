package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_student_basic;
import com.bysjglxt.domain.DO.bysjglxt_student_user;
import com.bysjglxt.domain.VO.StudentInformationManagementVO;

public interface StudentInformationManagementDao {

	List<bysjglxt_student_user> list_StudentUserInformation_All();

	bysjglxt_student_basic get_StudentBasicInformation_ByUserBasic(String id);

	boolean saveStudentBasic(bysjglxt_student_basic bysjglxt_student_basic);

	boolean saveStudent(bysjglxt_student_user bysjglxt_student_user);

	bysjglxt_student_user getStudentByNum(String student_num);

	boolean deleteStudentBasicInfoById(String user_student_basic);

	boolean deleteStudentInfoById(String user_student_id);

	List<bysjglxt_student_basic> listStudentBasicInformationByPageAndSearch(
			StudentInformationManagementVO studentInformationManagementVO);

	

}
