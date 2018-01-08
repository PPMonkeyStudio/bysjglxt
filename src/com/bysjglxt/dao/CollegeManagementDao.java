package com.bysjglxt.dao;

import java.util.List;

import com.bysjglxt.domain.DO.bysjglxt_college;
import com.bysjglxt.domain.DO.bysjglxt_section;
import com.bysjglxt.domain.DO.bysjglxt_teacher_basic;
import com.bysjglxt.domain.DO.bysjglxt_teacher_user;

public interface CollegeManagementDao {

	public List<bysjglxt_college> getListCollege();

	public List<bysjglxt_teacher_user> getListCollegeAdmin(String college_id);

	public bysjglxt_teacher_basic getTeacherBasicById(String trim);

	public bysjglxt_section getSectionById(String trim);

	public bysjglxt_teacher_user getTeacherUserById(String teacherUserId);

	public void saveObj(Object obj);

}
